package com.yachay.tech.api.dto;

public record LoginDtoResponse(
        String token,
        String tipo,
        String nombres,
        String rol
) {}