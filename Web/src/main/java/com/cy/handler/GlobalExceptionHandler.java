package com.cy.handler;

import com.cy.CommonResult;
import com.cy.config.security.excepiton.CustomerAuthenticatingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 数据校验异常返回类
 * @date 2021-12-22 23:23:16
 */
@Slf4j
@RestControllerAdvice(basePackages = {
        "com.cy.systemManagement.user.controller",
        "com.cy.systemManagement.role.controller",
        "com.cy.systemManagement.menu.controller",
        "com.cy.homeManagement.house_building.controller",
        "com.cy.homeManagement.house_unit.controller.HouseUnitController",
        "com.cy.homeManagement.house_list.controller.HouseListController",
        "com.cy.parkManagement.park_list.controller",
        "com.cy.liveManagement.live_user.controller",
        "com.cy.feeManagement.FeePower.controller",
        "com.cy.feeManagement.FeeWater.controller",
        "com.cy.feeManagement.FeePark.controller",
        "com.cy.complaintManagement.userComplaint.controller",
        "com.cy.repairManagement.userRepair.controller"
})
public class GlobalExceptionHandler {
    // 处理校验失败异常
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResult<Map<String, String>> handleValidateException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>(8);
        // 将校验错误字段和错误信息提取到map中
        bindingResult.getFieldErrors().forEach(item -> errorMap.put(item.getField(),item.getDefaultMessage()));
        log.error(String.valueOf(errorMap));
        return CommonResult.error(400,"数据出错",errorMap);

    }
    // 处理校验失败异常
    @ExceptionHandler({BindException.class})
    public CommonResult<Map<String, String>> handleValidateException1(BindException e) {

        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>(8);
        // 将校验错误字段和错误信息提取到map中
        bindingResult.getFieldErrors().forEach(item -> errorMap.put(item.getField(),item.getDefaultMessage()));

        return CommonResult.error(400,"数据出错",errorMap);

    }

    // 处理校验失败异常
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult<Map<Path, String>> handleValidException(ConstraintViolationException e){

        // 获取异常信息
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        // 将异常信息收集到Map，key为校验失败的字段，value为失败原因
        Map<Path, String> errorMap = constraintViolations.stream().collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        // 返回校验失败信息
        return CommonResult.error(400,"数据出错",errorMap);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public CommonResult<String> InsufficientAuthenticationException(){
        return CommonResult.error("无权限访问资源");
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public CommonResult<String> CredentialsExpiredException(){
        return CommonResult.error("密码过期，登录失败!");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult<String> AccessDeniedException(){
        return CommonResult.error("无权限处理，请联系管理员");
    }

    @ExceptionHandler(CustomerAuthenticatingException.class)
    public CommonResult<String> CustomerAuthenticatingException(CustomerAuthenticatingException e){
        return CommonResult.error(600,e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public CommonResult<String> BadCredentialsException(){
        return CommonResult.error("用户名或密码错误");
    }

     //处理其他异常
    @ExceptionHandler(Exception.class)
    public CommonResult<Exception> handleOtherException(Exception e) {
        System.out.println("Exception");
        log.error(e.getMessage()+"------"+e.getClass());
        return CommonResult.error("系统未知异常，请联系管理员");
    }
}
