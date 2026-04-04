package com.yachay.tech.api.exceptions;

public record ValidationError(String field, String message) {}