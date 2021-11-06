package com.challenge.tenpo.helper;

import com.challenge.tenpo.model.AuditModel;
import com.challenge.tenpo.service.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.Resource;

@Slf4j
@Aspect
@Component
public class AuditAOP {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private AuditService auditService;

    @Around("within(com.challenge.tenpo.controller..*)")
    public Object audit(ProceedingJoinPoint pjp) throws Throwable {
        Object response = null;
        AuditModel auditModel = new AuditModel();
        auditModel.setRequestId(RequestContext.getContext().getId());

        try {
            Object[] args = pjp.getArgs();
            MethodSignature methodSignature = (MethodSignature) pjp.getStaticPart().getSignature();
            Method method = methodSignature.getMethod();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int argIndex = 0; argIndex < args.length; argIndex++) {
                for (Annotation annotation : parameterAnnotations[argIndex]) {
                    if (annotation instanceof RequestBody) {
                        auditModel.setRequest(objectMapper.writeValueAsString(args[argIndex]));
                        break;
                    }

                }
            }
            response = pjp.proceed();
            if(response instanceof ResponseEntity) {
                Object body = ((ResponseEntity) response).getBody();
                auditModel.setResponse(objectMapper.writeValueAsString(body));
            } else {
                auditModel.setResponse(objectMapper.writeValueAsString(response));
            }

        } catch (Exception e) {
            log.error("exception on auditing values " , e.getMessage());
            throw e;
        } finally {
            auditService.auditInfo(auditModel);
        }

        return response;
    }

    @AfterReturning(pointcut = "execution(* com.challenge.tenpo.controller.ControllerExceptionHandler..*())",returning = "errorObj")
    public void auditError(Object errorObj) {
    String requestId = RequestContext.getContext().getId();
    AuditModel auditModel = new AuditModel();
    auditModel.setRequestId(requestId);
        try {
            auditModel.setResponse(objectMapper.writeValueAsString(errorObj));
            auditService.auditInfo(auditModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
