package ru.kpfu.itis.gabdullina.MyRestApi.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.PASSWORDS_NOT_MATCH;

@Documented
@Constraint(validatedBy = PasswordMatchingValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface PasswordMatching {

    String message() default PASSWORDS_NOT_MATCH;
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};

}
