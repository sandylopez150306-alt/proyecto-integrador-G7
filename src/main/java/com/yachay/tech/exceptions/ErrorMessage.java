package com.yachay.tech.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorMessage {

  private final String error;
  private final String message;
  private final Integer code;

  ErrorMessage(Exception exception, Integer code) {
    this.error = exception.toString();
    this.message = exception.getMessage();
    this.code = code;
  }
}
