package top.moma.m64.core.helper.cache;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * WeakMapCache
 *
 * <p>Simple Cache with WeakHashMap
 *
 * @author ivan
 * @version 1.0 Created by ivan at 11/23/20.
 */
public class WeakMapCache<K, V> {
  private static final long serialVersionUID = -6003538455202187956L;

  /** 缓存池 */
  private final Map<K, V> simpleMapCache;

  /** 读写锁 */
  private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();

  private final ReentrantReadWriteLock.ReadLock readLock = cacheLock.readLock();
  private final ReentrantReadWriteLock.WriteLock writeLock = cacheLock.writeLock();

  public WeakMapCache() {
    simpleMapCache = new WeakHashMap<>();
  }

  public WeakMapCache(Map<K, V> weakMapCache) {
    this.simpleMapCache = weakMapCache;
  }

  /**
   * get value
   *
   * @param key key
   * @return V
   * @author Created by ivan
   * @since 2023/3/29 17:50
   */
  public V get(K key) {
    V value;
    readLock.lock();
    try {
      value = simpleMapCache.get(key);
    } finally {
      readLock.unlock();
    }
    return value;
  }

  /**
   * put value
   *
   * @param key key
   * @param value value
   * @return V
   * @author Created by ivan
   * @since 2023/3/29 17:51
   */
  public V put(K key, V value) {
    writeLock.lock();
    try {
      simpleMapCache.put(key, value);
    } finally {
      writeLock.unlock();
    }
    return value;
  }

  /**
   * remove value
   *
   * @param key key
   * @return V
   * @author Created by ivan
   * @since 2023/3/29 17:52
   */
  public V remove(K key) {
    writeLock.lock();
    try {
      return simpleMapCache.remove(key);
    } finally {
      writeLock.unlock();
    }
  }

  /**
   * empty/clear cache
   *
   * @author Created by ivan
   * @since 2023/3/29 17:52
   */
  public void empty() {
    writeLock.lock();
    try {
      simpleMapCache.clear();
    } finally {
      writeLock.unlock();
    }
  }
}
