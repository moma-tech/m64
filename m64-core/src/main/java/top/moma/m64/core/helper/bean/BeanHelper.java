package top.moma.m64.core.helper.bean;

import org.springframework.cglib.beans.BeanMap;
import top.moma.m64.core.helper.ClassHelper;
import top.moma.m64.core.helper.CollectionHelper;
import top.moma.m64.core.helper.StringHelper;

import java.util.*;

/**
 * BeanHelper
 *
 * <p>//TODO
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
    return beanToMap(bean, true);
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
    if (null != bean) {
      BeanMap beanMap = BeanMap.create(bean);
      map = new HashMap<>(beanMap.keySet().size());
      for (Object key : beanMap.keySet()) {
        if (Objects.nonNull(beanMap.get(key))) {
          map.put(String.valueOf(key), beanMap.get(key));
        } else {
          if (includeNull) {
            map.put(String.valueOf(key), beanMap.get(key));
          }
        }
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
    Map<String, String> map = Collections.emptyMap();
    if (null != bean) {
      BeanMap beanMap = BeanMap.create(bean);
      map = new HashMap<>(beanMap.keySet().size());
      for (Object key : beanMap.keySet()) {
        if (Objects.nonNull(beanMap.get(key))) {
          map.put(String.valueOf(key), StringHelper.toString(beanMap.get(key)));
        }
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
    BeanMap beanMap = BeanMap.create(bean);
    beanMap.putAll(map);
    return bean;
  }
}
