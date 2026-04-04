package com.yachay.tech.exceptions;

public record ValidationError(String field, String message) {}