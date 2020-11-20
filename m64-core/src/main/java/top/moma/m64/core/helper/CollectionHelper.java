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
  /**
   * Check if Collection isEmpty
   *
   * @author Created by ivan on 2:49 PM 11/20/20.
   * @return boolean
   */
  public static boolean isEmpty(Collection coll) {
    return (Objects.isNull(coll) || coll.isEmpty());
  }
  /**
   * Check if not empty
   *
   * @author Created by ivan on 2:50 PM 11/20/20.
   * @return boolean
   */
  public static boolean isNotEmpty(Collection coll) {
    return !CollectionHelper.isEmpty(coll);
  }

  /**
   * Create HashMap with init size
   *
   * @author Created by ivan on 2:55 PM 11/20/20.
   * @return java.util.HashMap<K,V>
   */
  public static <K, V> HashMap<K, V> newHashMap() {
    return new HashMap<K, V>(M64Constants.INIT_MAP_SIZE);
  }

  /**
   * Create HashMap with given size
   *
   * @author Created by ivan on 2:57 PM 11/20/20.
   * @return java.util.HashMap<K,V>
   */
  public static <K, V> HashMap<K, V> newHashMap(int initialCapacity) {
    return new HashMap<K, V>(initialCapacity);
  }

  /**
   * Create HashMap with given map
   *
   * @author Created by ivan on 2:57 PM 11/20/20.
   * @return java.util.HashMap<K,V>
   */
  public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
    return new HashMap<K, V>(map);
  }

  /**
   * Create Empty LinkedHashMap
   *
   * @author Created by ivan on 2:58 PM 11/20/20.
   * @return java.util.LinkedHashMap<K,V>
   */
  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
    return new LinkedHashMap<K, V>(M64Constants.INIT_MAP_SIZE);
  }

  /**
   * Create LinkedHashMap with given map
   *
   * @author Created by ivan on 2:59 PM 11/20/20.
   * @return java.util.LinkedHashMap<K,V>
   */
  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
    return new LinkedHashMap<K, V>(map);
  }

  /**
   * Create Empty Concurrent Map
   *
   * @author Created by ivan on 3:00 PM 11/20/20.
   * @return java.util.concurrent.ConcurrentMap<K,V>
   */
  public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
    return new ConcurrentHashMap<K, V>(M64Constants.INIT_MAP_SIZE);
  }

  /**
   * Create Empty Tree Map
   *
   * @author Created by ivan on 3:00 PM 11/20/20.
   * @return java.util.TreeMap<K,V>
   */
  @SuppressWarnings("rawtypes")
  public static <K extends Comparable, V> TreeMap<K, V> newTreeMap() {
    return new TreeMap<K, V>();
  }

  /**
   * Create Tree Map with given sorted map
   *
   * @author Created by ivan on 3:00 PM 11/20/20.
   * @return java.util.TreeMap<K,V>
   */
  public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> map) {
    return new TreeMap<K, V>(map);
  }

  /**
   * Create Empty Tree Map with given Comparator
   *
   * @author Created by ivan on 3:01 PM 11/20/20.
   * @return java.util.TreeMap<K,V>
   */
  public static <C, K extends C, V> TreeMap<K, V> newTreeMap(Comparator<C> comparator) {
    return new TreeMap<K, V>(comparator);
  }

  /**
   * Create Empty Enum Map with give Key Type
   *
   * @author Created by ivan on 3:01 PM 11/20/20.
   * @return java.util.EnumMap<K,V>
   */
  public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> type) {
    return new EnumMap<K, V>((type));
  }

  /**
   * Create Enum Map with given Map
   *
   * @author Created by ivan on 3:02 PM 11/20/20.
   * @return java.util.EnumMap<K,V>
   */
  public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Map<K, ? extends V> map) {
    return new EnumMap<K, V>(map);
  }

  /**
   * Create Empty Identity Hash Map
   *
   * @author Created by ivan on 3:03 PM 11/20/20.
   * @return java.util.IdentityHashMap<K,V>
   */
  public static <K, V> IdentityHashMap<K, V> newIdentityHashMap() {
    return new IdentityHashMap<K, V>();
  }
}
