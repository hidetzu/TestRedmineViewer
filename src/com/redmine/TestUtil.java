package com.redmine;

import java.lang.reflect.Field;

public class TestUtil {

	public static void setValue(Object target,
			String fildName, Object value)
					throws NoSuchFieldException, IllegalAccessException {
		Field field = target.getClass().getDeclaredField(fildName);
		field.setAccessible(true);
		field.set(target, value);
	}
	
	public static Object pickValue(Object target, String fieldName )
			throws NoSuchFieldException, IllegalAccessException {
		Field field = target.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(target);
	}
}
