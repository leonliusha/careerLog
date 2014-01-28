package com.careerlog.util;

import java.lang.reflect.Field;
public class ReflectUtil {
	
	public static Object getFieldValue(Object object, String fieldName){
		Object result = null;
		Field field = getField(object, fieldName);
		if(field != null){
			field.setAccessible(true);
			try {
				result = field.get(object);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
		
	}
	
	public static Field getField(Object object, String fieldName){
		Field field = null;
		for(Class<?> clazz = object.getClass(); clazz != Object.class; clazz= clazz.getSuperclass()){
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return field;	
	}
	
	public static void setFieldValue(Object object, String fieldName, String fieldValue){
		Field field = getField(object, fieldName);
		if(field != null){
			try {
				field.setAccessible(true);
				field.set(object,fieldValue);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
