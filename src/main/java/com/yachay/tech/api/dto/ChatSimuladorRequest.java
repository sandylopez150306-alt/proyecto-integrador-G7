package com.yachay.tech.api.dto;

/**
 * Request DTO for the simulator AI chatbot.
 * Contains the user's message and the full report context so the AI can give
 * contextually aware responses about the user's decisions.
 */
public record ChatSimuladorRequest(
        String mensajeUsuario,
        String contextoInforme   // JSON summary of the final report passed from the frontend
) {}
