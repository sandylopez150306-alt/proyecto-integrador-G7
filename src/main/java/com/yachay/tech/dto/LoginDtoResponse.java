package com.yachay.tech.dto;

public record LoginDtoResponse(
        String token,
        String tipo,
        String nombres,
        String rol
) {}