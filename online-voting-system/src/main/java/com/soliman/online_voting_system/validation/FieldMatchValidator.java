package com.soliman.online_voting_system.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.mchange.v2.beans.BeansUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{

	private String firstFieldName ;
	private String secondFieldName;
	private String errorMessage;
	
	
	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		errorMessage = constraintAnnotation.message();
		
	}


	@Override
	public boolean isValid( Object obj, ConstraintValidatorContext context) {
	   boolean flag = false;
		try {
			BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);
			Object firstObj = wrapper.getPropertyValue(firstFieldName);
			Object secondObj = wrapper.getPropertyValue(secondFieldName);
			if (firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj)) {
				flag = true;
			}else {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(errorMessage).addNode(secondFieldName).addConstraintViolation();
			}

			
		} catch (Exception ignore) {
			System.out.println(ignore.getMessage());
		}
		return flag;
		
		
	}

}
