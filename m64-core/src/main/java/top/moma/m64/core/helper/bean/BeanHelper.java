package top.moma.m64.core.helper.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.helper.ClassHelper;
import top.moma.m64.core.helper.CollectionHelper;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.StringHelper;

/**
 * BeanHelper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/6/21.
 */
public class BeanHelper {

  private BeanHelper() {}

  /**
   * Single Bean to Single Map
   *
   * @param bean bean
   * @param <T> Object Type
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:37
   */
  public static <T> Map<String, Object> beanToMap(T bean) {
    return beanToMap(bean, false);
  }

  /**
   * Single Bean to Single Map
   *
   * @param bean bean
   * @param <T> Object Type
   * @param includeNull includeNull
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:37
   */
  public static <T> Map<String, Object> beanToMap(T bean, boolean includeNull) {
    Map<String, Object> map = Collections.emptyMap();
    if (ObjectHelper.isEmpty(bean)) {
      return map;
    }
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      map = new HashMap<>(propertyDescriptors.length);
      Method getter;
      for (PropertyDescriptor property : propertyDescriptors) {
        String key = property.getName();
        if (key.compareToIgnoreCase(StringConstants.CLASS) == 0) {
          continue;
        }
        getter = property.getReadMethod();
        Object value = getter != null ? getter.invoke(bean) : null;
        if (includeNull || Objects.nonNull(value)) {
          map.put(key, value);
        }
      }
    } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
      e.printStackTrace();
    }

    return map;
  }

  /**
   * bean To Map ignor null value
   *
   * @param bean bean
   * @param <T> Object Type
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:37
   */
  public static <T> Map<String, String> beanToStringMap(T bean) {
    return beanToStringMap(bean, false);
  }

  /**
   * description beanToStringMap
   *
   * @param bean bean
   * @param <T> Object Type
   * @param includeNull includeNull
   * @return java.util.Map
   * @author Created by ivan
   * @since 2022/9/13 18:27
   */
  public static <T> Map<String, String> beanToStringMap(T bean, boolean includeNull) {
    Map<String, String> map = Collections.emptyMap();
    if (ObjectHelper.isEmpty(bean)) {
      return map;
    }
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      map = new HashMap<>(propertyDescriptors.length);
      for (PropertyDescriptor property : propertyDescriptors) {
        String key = property.getName();
        if (key.compareToIgnoreCase(StringConstants.CLASS) == 0) {
          continue;
        }
        Method getter = property.getReadMethod();
        Object value = getter != null ? getter.invoke(bean) : StringConstants.EMPTY;
        if (includeNull || Objects.nonNull(value)) {
          map.put(key, StringHelper.toString(value));
        }
      }
    } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return map;
  }

  /**
   * Bean List to Map List
   *
   * @param beanList beanList
   * @param <T> Object Type
   * @return java.util.List
   * @author Created by ivan
   * @since 2023/3/29 17:38
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
   * Map List to Bean List
   *
   * @param mapList mapList
   * @param beanClass beanClass
   * @param <T> Object Type
   * @return java.util.List
   * @author Created by ivan
   * @since 2023/3/29 17:38
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
   * Single Map to Bean
   *
   * @param map map
   * @param beanClass beanClass
   * @param <T> Object Type
   * @return T
   * @author Created by ivan
   * @since 2023/3/29 17:38
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
   * Converter Bean to key=value AMP key=value
   *
   * @param bean bean
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/29 17:38
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
}
