package com.yachay.tech.api.exceptions;

import java.util.List;

public record ValidationErrorMessage(String message, List<ValidationError> errors) {
}
