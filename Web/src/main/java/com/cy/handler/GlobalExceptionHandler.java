//package com.cy.handler;
//
//import com.cy.ResultMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import javax.validation.Path;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * @author cy
// * @program: newWuye
// * @description: 数据校验异常返回类
// * @date 2021-12-22 23:23:16
// */
//@Slf4j
//@RestControllerAdvice(basePackages = "com.cy.*.controller")
//public class GlobalExceptionHandler {
//    // 处理校验失败异常
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResultMessage handleValidateException(MethodArgumentNotValidException e) {
//        // 获取BindingResult
//        BindingResult bindingResult = e.getBindingResult();
//        Map<String, String> errorMap = new HashMap<>(8);
//        // 将校验错误字段和错误信息提取到map中
//        bindingResult.getFieldErrors().forEach(item -> errorMap.put(item.getField(),item.getDefaultMessage()));
//        return ResultMessage.fail(400,"数据校验出错").add("error", errorMap);
//    }
//
//    // 处理校验失败异常
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResultMessage handleValidException(ConstraintViolationException e){
//        // 获取异常信息
//        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//        // 将异常信息收集到Map，key为校验失败的字段，value为失败原因
//        Map<Path, String> errorMap = constraintViolations.stream().collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
//        // 返回校验失败信息
//        return ResultMessage.fail(400, "数据校验出错").add("errorMap", errorMap);
//    }
//
//    // 处理其他异常
//    @ExceptionHandler(Exception.class)
//    public ResultMessage handleOtherException(Exception e) {
//        return ResultMessage.fail(e.getMessage());
//    }
//}
