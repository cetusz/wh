package com.my.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 反射工具类
 */
public class ReflectUtil
{
     
    private static Logger logger = Logger.getLogger(ReflectUtil.class);
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void setFieldValue(Object target, String fname, Class ftype,
            Object fvalue)
    {
        if (target == null
                || fname == null
                || "".equals(fname)
                || (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass())))
        {
            return;
        }
        Class clazz = target.getClass();
        try
        {
            Field field = clazz.getDeclaredField(fname);
            if (!Modifier.isPublic(field.getModifiers()))
            {
                field.setAccessible(true);
            }
            field.set(target, fvalue);
            
        }
        catch (Exception me)
        {
            if (logger.isDebugEnabled())
            {
                logger.debug(me);
            }
        }
    }
    
    /**
     * 通过反射机制克隆一个对象
     * @param obj
     * @return
     * @throws Exception
     */
    public static Object copyObject(Object obj) throws Exception
    {
        Field[] fields = obj.getClass().getDeclaredFields();
        Object newObj = obj.getClass().newInstance();
        for (int i = 0, j = fields.length; i < j; i++)
        {
            String propertyName = fields[i].getName();
            Object propertyValue = getProperty(obj, propertyName);
            setProperty(newObj, propertyName, propertyValue);
        }
        return newObj;
        
    }
    
    // 反射调用setter方法，进行赋值
    @SuppressWarnings("unchecked")
	private static Object setProperty(Object bean, String propertyName,
            Object value) throws Exception
    {
        Class clazz = bean.getClass();
        try
        {
            Field field = clazz.getDeclaredField(propertyName);
            Method method = clazz.getDeclaredMethod(getSetterName(field.getName()),
                    new Class[] { field.getType() });
            return method.invoke(bean, new Object[] { value });
        }
        catch (Exception e)
        {
        	throw e;
        }
    }
    
    // 反射调用getter方法，得到field的值
    @SuppressWarnings("unchecked")
	private static Object getProperty(Object bean, String propertyName) throws Exception
    {
        Class clazz = bean.getClass();
        try
        {
            Field field = clazz.getDeclaredField(propertyName);
            Method method = clazz.getDeclaredMethod(getGetterName(field.getName()),
                    new Class[] {});
            return method.invoke(bean, new Object[] {});
        }
        catch (Exception e)
        {
        	throw e;
        }
    }
    
    // 根据field名，得到getter方法名
    private static String getGetterName(String propertyName)
    {
        String method = "get" + propertyName.substring(0, 1).toUpperCase()
                + propertyName.substring(1);
        return method;
    }
    
    // 根据field名，得到setter方法名
    private static String getSetterName(String propertyName)
    {
        String method = "set" + propertyName.substring(0, 1).toUpperCase()
                + propertyName.substring(1);
        return method;
    }
    
    /**
     * 普通的JavaBean对象转换成Map数据类型。
     * 将普通的JavaBean对象转换成Map数据类型，其中JavaBean声明的变量名作为Map类型的key，
     * 该变量的值，作为其Map数据类型相应key的值。
     * 
     * @param obj
     *            - 普通JavaBean对象
     * @return 返回Map数据类类型
     * 
     * @return Map<String,Object> 返回Map数据类型
     */
    public static Map<String, Object> getObjectAsMap(Object obj)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null)
        {
            return map;
        }
        
        Class clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        String methodname = "";
        for (int i = 0; i < methods.length; i++)
        {
            methodname = methods[i].getName();
            if (methodname.startsWith("get"))
            {
                try
                {
                    Object value = methods[i].invoke(obj);
                    if( value != null && (value instanceof String ) ) {
                    	String str = (String) value;
                    	value = str.trim();
                    }
                    map.put(getFieldName(methodname), value);
                }
                catch (IllegalArgumentException e)
                {
                    logger.debug("Convert JavaBean to Map Error!", e);
                }
                catch (IllegalAccessException e)
                {
                    logger.debug("Convert JavaBean to Map Error!", e);
                }
                catch (InvocationTargetException e)
                {
                    logger.debug("Convert JavaBean to Map Error!", e);
                }
            }
        }
        return map;
    }
    
    private static String getFieldName(String str)
    {
        String firstChar = str.substring(3, 4);
        String out = firstChar.toLowerCase() + str.substring(4);
        return out;
    }
   
}
