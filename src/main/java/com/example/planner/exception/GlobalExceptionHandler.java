package com.example.planner.exception;

import com.example.planner.constant.common.ErrorMessage;
import com.example.planner.dto.common.ErrorResponseDto;
import com.example.planner.util.BindingResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 벨리데이션 관련 예외
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex){

        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = BindingResultUtils.extractErrorMessages(bindingResult);

        ErrorResponseDto responseDto = ErrorResponseDto.of(ErrorMessage.VALIDATION_ERROR.getMessage(),errorMessages);
        return new ResponseEntity<>(responseDto, ErrorMessage.VALIDATION_ERROR.getHttpStatus());
    }

    // Auth 관련 예외
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthenticationException(AuthenticationException ex){

        ErrorResponseDto responseDto = ErrorResponseDto.of(ex.getMessage());
        return new ResponseEntity<>(responseDto, ex.getErrorMessage().getHttpStatus());
    }

    // 로그인 관련 예외
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponseDto> handleLoginException(LoginException ex){

        ErrorResponseDto responseDto = ErrorResponseDto.of(ex.getMessage());
        return new ResponseEntity<>(responseDto, ex.getErrorMessage().getHttpStatus());
    }

    // 코멘트 Not Found 예외
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCommentNotFoundException(CommentNotFoundException ex){

        ErrorResponseDto responseDto = ErrorResponseDto.of(ex.getMessage());
        return new ResponseEntity<>(responseDto, ex.getErrorMessage().getHttpStatus());
    }

    // 일정 Not Found 예외
    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePlanNotFoundException(PlanNotFoundException ex){

        ErrorResponseDto responseDto = ErrorResponseDto.of(ex.getMessage());
        return new ResponseEntity<>(responseDto, ex.getErrorMessage().getHttpStatus());
    }

    // 유저 Not Found 예외
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex){

        ErrorResponseDto responseDto = ErrorResponseDto.of(ex.getMessage());
        return new ResponseEntity<>(responseDto, ex.getErrorMessage().getHttpStatus());
    }
}
