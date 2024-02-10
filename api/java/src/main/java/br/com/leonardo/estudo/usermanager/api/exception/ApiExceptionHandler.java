package br.com.leonardo.estudo.usermanager.api.exception;

import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.BusinessException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  public static final String MSG_ERROR_DEFAULT =
      "An unexpected internal system error has occurred. Try again and if  the problem persists, contact the system administrator.";

  private final MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
      HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
  ) {
    return ResponseEntity.status(status).headers(headers).build();
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
  ) {
    return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
  }

  private ResponseEntity<Object> handleValidationInternal(
      Exception ex, HttpHeaders headers, HttpStatusCode status, WebRequest request, BindingResult bindingResult
  ) {
    String detail = "There are fields filled wrong";

    List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream().map(objectError -> {
      String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

      String name = objectError.getObjectName();

      if (objectError instanceof FieldError) {
        name = ((FieldError) objectError).getField();
      }

      return Problem.Object.builder().name(name).userMessage(message).build();
    }).collect(Collectors.toList());

    Problem problem = createProblemBuilder(status, detail).userMessage(detail).objects(problemObjects).build();

    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String detail = MSG_ERROR_DEFAULT;
    log.error(ex.getMessage(), ex);
    Problem problem = createProblemBuilder(status, detail).userMessage(detail).build();
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
  ) {
    String detail = String.format("Resource %s not found.", ex.getRequestURL());
    Problem problem = createProblemBuilder(status, detail).userMessage(MSG_ERROR_DEFAULT).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
  ) {
    if (ex instanceof MethodArgumentTypeMismatchException) {
      return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
    }
    return super.handleTypeMismatch(ex, headers, status, request);
  }

  private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
  ) {
    String detail = String.format("The param '%s' with value '%s', " + "is invalid. Filled with a type value of %s."
        , ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
    Problem problem = createProblemBuilder(status, detail).userMessage(MSG_ERROR_DEFAULT).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
  ) {
    Throwable rootCause = ExceptionUtils.getRootCause(ex);
    if (rootCause instanceof InvalidFormatException) {
      return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
    } else if (rootCause instanceof PropertyBindingException) {
      return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
    }
    String detail = "The body is wrong";
    Problem problem = createProblemBuilder(status, detail).userMessage(MSG_ERROR_DEFAULT).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private ResponseEntity<Object> handlePropertyBinding(
      PropertyBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
  ) {
    String path = joinPath(ex.getPath());
    String detail = String.format("The field '%s' doesn't exist.", path);
    Problem problem = createProblemBuilder(status, detail).userMessage(MSG_ERROR_DEFAULT).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private ResponseEntity<Object> handleInvalidFormat(
      InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
  ) {
    String path = joinPath(ex.getPath());
    String detail = String.format("The param '%s' with value '%s', " + "is invalid. Filled with a type value of %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());
    Problem problem = createProblemBuilder(status, detail).userMessage(MSG_ERROR_DEFAULT).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontrada(AccessDeniedException ex, WebRequest request) {
    HttpStatus status = HttpStatus.FORBIDDEN;
    String detail = ex.getMessage();
    Problem problem = createProblemBuilder(status, detail).userMessage(detail).userMessage("Forbidden.").build();
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontrada(BusinessException ex, WebRequest request) {
    HttpStatus status = ex.getStatus();
    String detail = ex.getMessage();
    Problem problem = createProblemBuilder(status, detail).userMessage(detail).build();
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request
  ) {
    if (body == null) {
      body = Problem.builder().timestamp(OffsetDateTime.now()).status(status.value()).userMessage(MSG_ERROR_DEFAULT).build();
    } else if (body instanceof String) {
      body = Problem.builder().timestamp(OffsetDateTime.now()).status(status.value()).userMessage(MSG_ERROR_DEFAULT).build();
    }
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, String detail) {
    return Problem.builder().timestamp(OffsetDateTime.now()).status(status.value()).detail(detail);
  }

  private String joinPath(List<JsonMappingException.Reference> references) {
    return references.stream().map(JsonMappingException.Reference::getFieldName).collect(Collectors.joining("."));
  }

}
