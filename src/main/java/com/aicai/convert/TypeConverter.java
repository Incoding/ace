package com.aicai.convert;

import java.math.BigDecimal;
import java.math.BigInteger;

import jodd.typeconverter.Convert;

public class TypeConverter {
    /**
     * only do basic convert
     * 
     * @param value
     * @param toType
     * @return
     */
    public static Object convert(Object value, Class<?> toType) {
        Object result = null;
        if ((toType == Integer.class) || (toType == Integer.TYPE))
            result = Convert.toInteger(value);
        if ((toType == Double.class) || (toType == Double.TYPE))
            result = Convert.toDouble(value);
        if ((toType == Boolean.class) || (toType == Boolean.TYPE))
            result = Convert.toBoolean(value);
        if ((toType == Byte.class) || (toType == Byte.TYPE))
            result = Convert.toByte(value);
        if ((toType == Character.class) || (toType == Character.TYPE))
            result = Convert.toCharacter(value);
        if ((toType == Short.class) || (toType == Short.TYPE))
            result = Convert.toShort(value);
        if ((toType == Long.class) || (toType == Long.TYPE))
            result = Convert.toLong(value);
        if ((toType == Float.class) || (toType == Float.TYPE))
            result = Convert.toFloat(value);
        if (toType == BigInteger.class)
            result = Convert.toBigInteger(value);
        if (toType == BigDecimal.class)
            result = Convert.toBigDecimal(value);
        if (toType == String.class)
            result = Convert.toString(value);
        if (Enum.class.isAssignableFrom(toType))
            result = enumValue((Class<Enum>) toType, value);
        return result;
    }

    public static Enum<?> enumValue(Class toClass, Object o) {
        Enum<?> result = null;
        if (o == null) {
            result = null;
        } else if (o instanceof String[]) {
            result = Enum.valueOf(toClass, ((String[]) o)[0]);
        } else if (o instanceof String) {
            result = Enum.valueOf(toClass, (String) o);
        }
        return result;
    }
}
