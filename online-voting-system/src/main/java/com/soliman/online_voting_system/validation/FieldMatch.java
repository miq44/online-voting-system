package com.soliman.online_voting_system.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=FieldMatchValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {
 
	String message() default "Password Don't match";
	
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	String first();
	
	String second();
	
}
