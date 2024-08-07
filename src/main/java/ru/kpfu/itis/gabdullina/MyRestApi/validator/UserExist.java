package ru.kpfu.itis.gabdullina.MyRestApi.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.USER_EXIST;

@Documented
@Constraint(validatedBy = UserExistValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface UserExist {

    String message() default USER_EXIST;
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
