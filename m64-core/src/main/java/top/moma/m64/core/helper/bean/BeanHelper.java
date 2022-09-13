package top.moma.m64.core.helper.bean;

import lombok.Data;
import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.helper.ClassHelper;
import top.moma.m64.core.helper.CollectionHelper;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.StringHelper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * BeanHelper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/6/21.
 */
public class BeanHelper {

  /**
   * @param bean :
   * @return java.util.Map<java.lang.String , java.lang.Object>
   * @author Created by ivan on 3:26 PM 12/24/18.
   *     <p>//Single Bean to Single Map
   */
  public static <T> Map<String, Object> beanToMap(T bean) {
    return beanToMap(bean, false);
  }

  /**
   * @param bean : Source Bean
   * @param includeNull: if include null value
   * @return java.util.Map<java.lang.String , java.lang.Object>
   * @author Created by ivan on 3:26 PM 12/24/18.
   *     <p>//Single Bean to Single Map
   */
  public static <T> Map<String, Object> beanToMap(T bean, boolean includeNull) {
    Map<String, Object> map = Collections.emptyMap();
    if (ObjectHelper.isNotEmpty(bean)) {
      try {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        map = new HashMap<>(propertyDescriptors.length);
        Method getter;
        for (PropertyDescriptor property : propertyDescriptors) {
          String key = property.getName();
          if (key.compareToIgnoreCase("class") == 0) {
            continue;
          }
          getter = property.getReadMethod();
          Object value = getter != null ? getter.invoke(bean) : null;
          if (Objects.nonNull(value)) {
            map.put(key, value);
          } else {
            if (includeNull) {
              map.put(key, null);
            }
          }
        }
      } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return map;
  }

  /**
   * @param bean :
   * @return java.util.Map<java.lang.String , java.lang.String>
   * @author Created by ivan on 3:04 PM 12/27/18.
   *     <p>bean To <String,String> Map
   *     <p>ignor null value
   */
  public static <T> Map<String, String> beanToStringMap(T bean) {
    return beanToStringMap(bean, false);
  }

  /**
   * description beanToStringMap
   *
   * @param bean bean
   * @param includeNull includeNull
   * @return java.util.Map<java.lang.String,java.lang.String>
   * @author Created by ivan
   * @since 2022/9/13 18:27
   */
  public static <T> Map<String, String> beanToStringMap(T bean, boolean includeNull) {
    Map<String, String> map = Collections.emptyMap();
    if (ObjectHelper.isNotEmpty(bean)) {
      try {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        map = new HashMap<>(propertyDescriptors.length);
        for (PropertyDescriptor property : propertyDescriptors) {
          String key = property.getName();
          if (key.compareToIgnoreCase("class") == 0) {
            continue;
          }
          Method getter = property.getReadMethod();
          Object value = getter != null ? getter.invoke(bean) : null;
          if (Objects.nonNull(value)) {
            map.put(key, StringHelper.toString(value));
          } else {
            if (includeNull) {
              map.put(key, StringConstants.EMPTY);
            }
          }
        }
      } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return map;
  }

  /**
   * *
   *
   * @param beanList :
   * @return java.util.List<java.util.Map < java.lang.String , java.lang.Object>>
   * @author Created by ivan on 3:26 PM 12/24/18.
   *     <p>//Bean List to Map List
   */
  public static <T> List<Map<String, Object>> beansToMaps(List<T> beanList) {
    List<Map<String, Object>> mapList = Collections.emptyList();
    if (CollectionHelper.isNotEmpty(beanList)) {
      mapList = new ArrayList<>(beanList.size());
      Map<String, Object> map;
      T bean;
      for (T anObjList : beanList) {
        bean = anObjList;
        map = beanToMap(bean);
        mapList.add(map);
      }
    }
    return mapList;
  }

  /**
   * @param mapList :
   * @param beanClass :
   * @return java.util.List<T>
   * @author Created by ivan on 3:27 PM 12/24/18.
   *     <p>//Map List to Bean List
   */
  public static <T> List<T> mapsToBeans(List<Map<String, Object>> mapList, Class<T> beanClass) {
    List<T> beanList = Collections.emptyList();
    if (CollectionHelper.isNotEmpty(mapList)) {
      beanList = new ArrayList<>(mapList.size());
      Map<String, Object> map;
      T bean;
      for (Map<String, Object> map1 : mapList) {
        map = map1;
        bean = mapToBean(map, beanClass);
        beanList.add(bean);
      }
    }
    return beanList;
  }

  /**
   * @param map :
   * @param beanClass :
   * @return T
   * @author Created by ivan on 3:28 PM 12/24/18.
   *     <p>//Single Map to Bean
   */
  public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
    T bean = ClassHelper.newInstance(beanClass);
    if (ObjectHelper.isNotEmpty(map)) {
      try {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        String key;
        Method setter;
        for (PropertyDescriptor property : propertyDescriptors) {
          key = property.getName();
          if (map.containsKey(key) && ObjectHelper.isNotEmpty(map.get(key))) {
            setter = property.getWriteMethod();
            setter.invoke(bean, map.get(key));
          }
        }
      } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return bean;
  }

  /**
   * Converter Bean to key=value&key=value
   *
   * @author Created by Ivan at 2021/4/7.
   * @return java.lang.String : return char sequence
   * @param bean : input bean
   */
  public static String beanToStringParis(Object bean) {
    if (ObjectHelper.isNotEmpty(bean)) {
      StringBuilder stringBuilder = new StringBuilder();
      try {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Iterator<PropertyDescriptor> propertyIterator =
            Arrays.stream(propertyDescriptors).iterator();
        PropertyDescriptor property;
        String key;
        Method getter;
        Object value;
        while (propertyIterator.hasNext()) {
          property = propertyIterator.next();
          key = property.getName();
          if (key.compareToIgnoreCase("class") == 0) {
            continue;
          }
          getter = property.getReadMethod();
          value = getter != null ? getter.invoke(bean) : null;
          stringBuilder
              .append(key)
              .append(StringConstants.EQUAL)
              .append(StringHelper.toString(value));
          if (propertyIterator.hasNext()) {
            stringBuilder.append(StringConstants.AMP);
          }
        }
      } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
        e.printStackTrace();
      }
      return stringBuilder.toString();
    }
    return StringConstants.EMPTY;
  }

  public static void main(String[] args) {
    InnerTest innerTest = new InnerTest();
    innerTest.setName("adb");
    innerTest.setType(1);
    Map bMap = BeanHelper.beanToMap(innerTest);
    bMap.entrySet().stream().forEach(System.out::println);
  }

  @Data
  public static class InnerTest {
    private String name;
    private Integer type;
  }
}
