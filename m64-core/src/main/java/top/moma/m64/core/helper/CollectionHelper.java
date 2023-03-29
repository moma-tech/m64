package top.moma.m64.core.helper;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import top.moma.m64.core.constants.M64Constants;

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
   * @param coll coll
   * @return boolean
   * @author Created by ivan
   * @since 2023/3/29 17:27
   */
  public static boolean isEmpty(Collection<?> coll) {
    return (Objects.isNull(coll) || coll.isEmpty());
  }

  /**
   * Check if not empty
   *
   * @param coll coll
   * @return boolean
   * @author Created by ivan on 2:50 PM 11/20/20.
   */
  public static boolean isNotEmpty(Collection<?> coll) {
    return !CollectionHelper.isEmpty(coll);
  }

  /**
   * Create HashMap with init size
   *
   * @param <K> key Type
   * @param <V> value Type
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:28
   */
  public static <K, V> Map<K, V> newHashMap() {
    return new HashMap<>(M64Constants.INIT_MAP_SIZE);
  }

  /**
   * Create HashMap with given size
   *
   * @param <K> key Type
   * @param <V> value Type
   * @param initialCapacity initialCapacity
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:29
   */
  public static <K, V> Map<K, V> newHashMap(int initialCapacity) {
    return new HashMap<>(initialCapacity);
  }

  /**
   * Create HashMap with given map
   *
   * @param <K> key Type
   * @param <V> value Type
   * @param map map
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:29
   */
  public static <K, V> Map<K, V> newHashMap(Map<? extends K, ? extends V> map) {
    return new HashMap<>(map);
  }

  /**
   * Create Empty LinkedHashMap
   *
   * @param <K> key Type
   * @param <V> value Type
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:29
   */
  public static <K, V> Map<K, V> newLinkedHashMap() {
    return new LinkedHashMap<>(M64Constants.INIT_MAP_SIZE);
  }

  /**
   * Create LinkedHashMap with given map
   *
   * @param <K> key Type
   * @param <V> value Type
   * @param map map
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:30
   */
  public static <K, V> Map<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
    return new LinkedHashMap<>(map);
  }

  /**
   * Create Empty Concurrent Map newConcurrentMap
   *
   * @param <K> key Type
   * @param <V> value Type
   * @return java.util.concurrent.ConcurrentMap
   * @author Created by ivan
   * @since 2023/3/29 17:30
   */
  public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
    return new ConcurrentHashMap<>(M64Constants.INIT_MAP_SIZE);
  }

  /**
   * Create Empty Tree Map
   *
   * @param <K> key Type
   * @param <V> value Type
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:30
   */
  @SuppressWarnings("rawtypes")
  public static <K extends Comparable, V> Map<K, V> newTreeMap() {
    return new TreeMap<>();
  }

  /**
   * Create Tree Map with given sorted map
   *
   * @param <K> key Type
   * @param <V> value Type
   * @param map map
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:30
   */
  public static <K, V> Map<K, V> newTreeMap(SortedMap<K, ? extends V> map) {
    return new TreeMap<>(map);
  }

  /**
   * Create Empty Tree Map with given Comparator
   *
   * @param <C> compare Type
   * @param <K> key Type
   * @param <V> value Type
   * @param comparator comparator
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:31
   */
  public static <C, K extends C, V> Map<K, V> newTreeMap(Comparator<C> comparator) {
    return new TreeMap<>(comparator);
  }

  /**
   * Create Empty Enum Map with give Key Type
   *
   * @param <K> key Type
   * @param <V> value Type
   * @param type type
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:31
   */
  public static <K extends Enum<K>, V> Map<K, V> newEnumMap(Class<K> type) {
    return new EnumMap<>((type));
  }

  /**
   * Create Enum Map with given Map
   *
   * @param <K> key Type
   * @param <V> value Type
   * @param map map
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:31
   */
  public static <K extends Enum<K>, V> Map<K, V> newEnumMap(Map<K, ? extends V> map) {
    return new EnumMap<>(map);
  }

  /**
   * Create Empty Identity Hash Map
   *
   * @param <K> key Type
   * @param <V> value Type
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:31
   */
  public static <K, V> Map<K, V> newIdentityHashMap() {
    return new IdentityHashMap<>();
  }

  /**
   * Convert Source to Object Array
   *
   * @param source source
   * @return java.util.List
   * @author Created by ivan
   * @since 2023/3/29 17:32
   */
  public static List<Object> arrayToList(Object source) {
    return Arrays.asList(ObjectHelper.toObjectArray(source));
  }
}
