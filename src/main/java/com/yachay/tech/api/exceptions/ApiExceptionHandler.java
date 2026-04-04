package com.yachay.tech.api.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler({ UnauthorizedException.class })
  @ResponseBody
  public ErrorMessage unauthorizedRequest(Exception exception) {
      return new ErrorMessage(exception, HttpStatus.UNAUTHORIZED.value());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({
      NotFoundException.class,
      NoResourceFoundException.class
  })
  @ResponseBody
  public ErrorMessage notFoundRequest(Exception exception) {
    return new ErrorMessage(exception, HttpStatus.NOT_FOUND.value());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
      BadRequestException.class,
      DuplicateKeyException.class,
      WebExchangeBindException.class,
      HttpMessageNotReadableException.class,
      ServerWebInputException.class
  })
  @ResponseBody
  public ErrorMessage badRequest(Exception exception) {
    return new ErrorMessage(exception, HttpStatus.BAD_REQUEST.value());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ValidationErrorMessage handleValidationExceptions(MethodArgumentNotValidException ex) {
    var errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
        .collect(Collectors.toList());

    return new ValidationErrorMessage("Validación fallida", errors);
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler({
      ConflictException.class
  })
  @ResponseBody
  public ErrorMessage conflict(Exception exception) {
    return new ErrorMessage(exception, HttpStatus.CONFLICT.value());
  }


  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler({
      ForbiddenException.class
  })
  @ResponseBody
  public ErrorMessage forbidden(Exception exception) {
    return new ErrorMessage(exception, HttpStatus.FORBIDDEN.value());
  }

  @ResponseStatus(HttpStatus.BAD_GATEWAY)
  @ExceptionHandler({
      BadGatewayException.class
  })
  @ResponseBody
  public ErrorMessage badGateway(Exception exception) {
    return new ErrorMessage(exception, HttpStatus.BAD_GATEWAY.value());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({
      Exception.class
  })
  @ResponseBody
  public ErrorMessage exception(Exception exception) { // The error must be corrected
    exception.printStackTrace();
    return new ErrorMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
  }
}
