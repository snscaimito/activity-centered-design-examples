package net.caimito.util;

import java.lang.reflect.Field;

public class TestHelper<T> {

	public static <T> T set(T obj, Object... fieldValues) {
		if (obj == null)
			throw new IllegalArgumentException("The object where the values should be set cannot be null");

		Class<?> objectClass = obj.getClass();

		for (int i = 0; i < fieldValues.length; i += 2) {
			String fieldName = (String) fieldValues[i];
			Object fieldValue = fieldValues[i + 1];

			try {
				Field field = findField(objectClass, fieldName);
				field.setAccessible(true);
				field.set(obj, fieldValue);

			} catch (Exception e) {
				throw new RuntimeException(String.format("Unable to set field value for %s to %s", fieldName, fieldValue), e);
			}
		}

		return obj;
	}

	private static Field findField(Class<?> objectClass, String fieldName) {

		Class<?> cursor = objectClass;

		while (cursor != null) {
			try {
				return cursor.getDeclaredField(fieldName);
			} catch (NoSuchFieldException ex) {
				// Ignore.
			}

			cursor = cursor.getSuperclass();
		}

		throw new RuntimeException(String.format("Class %s does not contain a field named '%s'.", objectClass.getName(), fieldName));
	}

	public static <T> Object get(T obj, String fieldName) {
		try {
			Field field = findField(obj.getClass(), fieldName);
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Unable to get field value from %s", fieldName), e);
		}

	}

}