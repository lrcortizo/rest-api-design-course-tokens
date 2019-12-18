package io.trabe.teaching.rest.controller.rest.external.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.trabe.teaching.rest.model.service.AuthorizationService;

@Component
@Aspect
@Order(1)
public class CheckAuthorizationAspect {

    private final AuthorizationService authorizationService;
    private final AopUtils aopUtils;

    public CheckAuthorizationAspect(AuthorizationService authorizationService,
            AopUtils aopUtils) {
        this.authorizationService = authorizationService;
        this.aopUtils = aopUtils;
    }


    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("within(io.trabe.teaching.rest.controller.rest..*) && @target(org.springframework.web.bind.annotation.RestController)")
    public void withinApplicationRestController() {

    }


    @Pointcut("execution(@io.trabe.teaching.rest.controller.rest.external.annotation.CheckPrivileges * *(..))")
    public void methodAnnotatedWithCheckPrivileges() {
    }


    @Before("publicMethod() && withinApplicationRestController() && methodAnnotatedWithCheckPrivileges()")
    public void checkAuthorization(JoinPoint joinPoint) {
        String userName = (String) getRequest().getAttribute(Constants.USER_ID_ATTRIBUTE);

        // Your implementation: get authorized ids form service and check permissions. You need to retrieve the userId
        // From the joinPoint. Throw NotEnoughPrivilegesException if the user is not authorized. Remember to map the
        // exceptions in the advice

    }


    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
