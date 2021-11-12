package project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import project.dto.ResponseDto;

@ControllerAdvice  // 모든 exception이 이쪽으로 오게끔 설정.
@RestController
public class GlobalExceptionHandler {
  
  @ExceptionHandler(value=Exception.class)  // 모든 Exception이 발생하면 그 에러에 대한 함수를 e에 전달.
  public ResponseDto<String> handleHttpStatusception(Exception e){
    return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
  }
}
