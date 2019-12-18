package io.trabe.teaching.rest.controller.rest.external.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import static io.trabe.teaching.rest.controller.rest.external.aop.Constants.USER_ID_ATTRIBUTE;

@Component
@Aspect
@Order(0)
public class CheckAuthenticationAspect {


    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("within(io.trabe.teaching.rest.controller.rest..*) && @target(org.springframework.web.bind.annotation.RestController)")
    public void withinApplicationRestController() {

    }

    @Before("withinApplicationRestController() && publicMethod()")
    public void requireValidAuthorizationToken() {
        String authHeader = getRequest().getHeader("Authorization");
        String token = authHeader.substring(7);
        DecodedJWT jwt = JWT.decode(token);

        // Implementation, throwing NotAuthenticatedException when the token is invalid or expired. Map the exceptions
        // in the appropiate advice

        // End of your implementation
        getRequest().setAttribute(USER_ID_ATTRIBUTE, jwt.getSubject());

    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
