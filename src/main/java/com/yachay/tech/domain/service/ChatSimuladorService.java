package com.yachay.tech.domain.service;

import com.yachay.tech.api.dto.ChatSimuladorRequest;
import com.yachay.tech.api.dto.ChatSimuladorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Service that integrates with the Groq API to provide the simulator's
 * AI coaching chatbot. Uses RestTemplate (no WebFlux dependency needed).
 */
@Service
public class ChatSimuladorService {

    private static final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String GROQ_MODEL   = "llama-3.3-70b-versatile";

    private static final String SYSTEM_PROMPT = """
            Eres un coach de marketing especializado en la simulación de negocios de Yachay-Tech.
            El estudiante acaba de terminar el simulador "Tablet Kallpa" y tiene su informe de decisiones.
            Tu rol es:
            - Ayudar al estudiante a reflexionar sobre sus decisiones estratégicas
            - Explicar por qué ciertas elecciones son más efectivas que otras
            - Guiarlo hacia un pensamiento analítico más profundo
            - Ser motivador pero honesto
            - Responder SIEMPRE en español
            - Mantener respuestas concisas (máximo 3 párrafos)
            No inventes datos del informe. Solo usa el contexto que el estudiante te provea.
            """;

    @Value("${GROQ_API_KEY}")
    private String groqApiKey;

    private final RestTemplate restTemplate;

    public ChatSimuladorService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Sends the user's message to Groq with the simulation report as context.
     */
    public ChatSimuladorResponse chat(ChatSimuladorRequest request) {
        String systemWithContext = SYSTEM_PROMPT + "\n\nCONTEXTO DEL INFORME DEL ESTUDIANTE:\n" + request.contextoInforme();

        Map<String, Object> body = Map.of(
                "model", GROQ_MODEL,
                "messages", List.of(
                        Map.of("role", "system", "content", systemWithContext),
                        Map.of("role", "user", "content", request.mensajeUsuario())
                ),
                "temperature", 0.7,
                "max_tokens", 512
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(groqApiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(GROQ_API_URL, entity, Map.class);
            String content = extractContent(response.getBody());
            return new ChatSimuladorResponse(content);
        } catch (Exception e) {
            return new ChatSimuladorResponse("No se pudo conectar con el asistente IA. Verifica la API key de Groq.");
        }
    }

    @SuppressWarnings("unchecked")
    private String extractContent(Map<?, ?> body) {
        if (body == null) return "No se pudo obtener respuesta de la IA.";
        try {
            List<?> choices  = (List<?>) body.get("choices");
            Map<?, ?> choice = (Map<?, ?>) choices.get(0);
            Map<?, ?> msg    = (Map<?, ?>) choice.get("message");
            return (String) msg.get("content");
        } catch (Exception e) {
            return "Error procesando la respuesta de la IA.";
        }
    }
}
