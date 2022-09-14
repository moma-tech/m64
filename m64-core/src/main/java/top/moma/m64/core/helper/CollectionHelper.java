package top.moma.m64.core.helper;

import top.moma.m64.core.constants.M64Constants;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * CollectionHelper
 *
 * <p>Collection Helper
 *
 * @author Created by ivan at 1:46 PM.
 * @version 1.0
 */
public class CollectionHelper {

  private CollectionHelper() {}

  /**
   * Check if Collection isEmpty
   *
   * @author Created by ivan on 2:49 PM 11/20/20.
   * @return boolean
   */
  public static boolean isEmpty(Collection<?> coll) {
    return (Objects.isNull(coll) || coll.isEmpty());
  }
  /**
   * Check if not empty
   *
   * @author Created by ivan on 2:50 PM 11/20/20.
   * @return boolean
   */
  public static boolean isNotEmpty(Collection<?> coll) {
    return !CollectionHelper.isEmpty(coll);
  }

  /**
   * Create HashMap with init size
   *
   * @author Created by ivan on 2:55 PM 11/20/20.
   * @return java.util.HashMap<K,V>
   */
  public static <K, V> Map<K, V> newHashMap() {
    return new HashMap<>(M64Constants.INIT_MAP_SIZE);
  }

  /**
   * Create HashMap with given size
   *
   * @author Created by ivan on 2:57 PM 11/20/20.
   * @return java.util.HashMap<K,V>
   */
  public static <K, V> Map<K, V> newHashMap(int initialCapacity) {
    return new HashMap<>(initialCapacity);
  }

  /**
   * Create HashMap with given map
   *
   * @author Created by ivan on 2:57 PM 11/20/20.
   * @return java.util.HashMap<K,V>
   */
  public static <K, V> Map<K, V> newHashMap(Map<? extends K, ? extends V> map) {
    return new HashMap<>(map);
  }

  /**
   * Create Empty LinkedHashMap
   *
   * @author Created by ivan on 2:58 PM 11/20/20.
   * @return java.util.LinkedHashMap<K,V>
   */
  public static <K, V> Map<K, V> newLinkedHashMap() {
    return new LinkedHashMap<>(M64Constants.INIT_MAP_SIZE);
  }

  /**
   * Create LinkedHashMap with given map
   *
   * @author Created by ivan on 2:59 PM 11/20/20.
   * @return java.util.LinkedHashMap<K,V>
   */
  public static <K, V> Map<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
    return new LinkedHashMap<>(map);
  }

  /**
   * Create Empty Concurrent Map
   *
   * @author Created by ivan on 3:00 PM 11/20/20.
   * @return java.util.concurrent.ConcurrentMap<K,V>
   */
  public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
    return new ConcurrentHashMap<>(M64Constants.INIT_MAP_SIZE);
  }

  /**
   * Create Empty Tree Map
   *
   * @author Created by ivan on 3:00 PM 11/20/20.
   * @return java.util.TreeMap<K,V>
   */
  @SuppressWarnings("rawtypes")
  public static <K extends Comparable, V> Map<K, V> newTreeMap() {
    return new TreeMap<>();
  }

  /**
   * Create Tree Map with given sorted map
   *
   * @author Created by ivan on 3:00 PM 11/20/20.
   * @return java.util.TreeMap<K,V>
   */
  public static <K, V> Map<K, V> newTreeMap(SortedMap<K, ? extends V> map) {
    return new TreeMap<>(map);
  }

  /**
   * Create Empty Tree Map with given Comparator
   *
   * @author Created by ivan on 3:01 PM 11/20/20.
   * @return java.util.TreeMap<K,V>
   */
  public static <C, K extends C, V> Map<K, V> newTreeMap(Comparator<C> comparator) {
    return new TreeMap<>(comparator);
  }

  /**
   * Create Empty Enum Map with give Key Type
   *
   * @author Created by ivan on 3:01 PM 11/20/20.
   * @return java.util.EnumMap<K,V>
   */
  public static <K extends Enum<K>, V> Map<K, V> newEnumMap(Class<K> type) {
    return new EnumMap<>((type));
  }

  /**
   * Create Enum Map with given Map
   *
   * @author Created by ivan on 3:02 PM 11/20/20.
   * @return java.util.EnumMap<K,V>
   */
  public static <K extends Enum<K>, V> Map<K, V> newEnumMap(Map<K, ? extends V> map) {
    return new EnumMap<>(map);
  }

  /**
   * Create Empty Identity Hash Map
   *
   * @author Created by ivan on 3:03 PM 11/20/20.
   * @return java.util.IdentityHashMap<K,V>
   */
  public static <K, V> Map<K, V> newIdentityHashMap() {
    return new IdentityHashMap<>();
  }

  /**
   * Convert Source to Object Array
   *
   * @author Created by ivan on 5:33 PM 11/30/20.
   * @param source : source Object
   * @return java.util.List
   */
  public static List<Object> arrayToList(Object source) {
    return Arrays.asList(ObjectHelper.toObjectArray(source));
  }
}
