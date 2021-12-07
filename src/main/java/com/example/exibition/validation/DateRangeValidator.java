package com.example.exibition.validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.example.exibition.dto.ExibitionDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateRangeValidator implements ConstraintValidator<DateRange, ExibitionDTO> {
	
	private String fromDate;
	private String toDate;
	
	@Override
	public void initialize(DateRange constraintAnnotation) {
		this.fromDate = constraintAnnotation.fromDate();
		this.toDate = constraintAnnotation.toDate();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(ExibitionDTO value, ConstraintValidatorContext context) {
		if(value != null) {
			LocalDateTime from = getValue(value, fromDate);
			LocalDateTime to = getValue(value, toDate);
			if(from != null && to != null) {
				log.debug("From date: {}", from.toString());
				log.debug("To date: {}", to.toString());
				return from.isEqual(to) || from.isBefore(to);
			}
			
			return true;
		}
		return false;
	}
	
	private LocalDateTime getValue(ExibitionDTO obj, String fieldName) {
		try {
			Class<?> clazz = obj.getClass();
			//for record is not se same as for objects
			RecordComponent[] components = clazz.getRecordComponents();
			for(RecordComponent comp : components) {
				if(comp.getName().equals(fieldName)) {
					return (LocalDateTime) comp.getAccessor().invoke(obj);
				}
			}
			//for objects:
//			Field field = clazz.getField(fieldName);
			//TYPE: field.getType();
//			return (LocalDateTime) field.get(obj);
			//SET: field.set(obj, value);
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
