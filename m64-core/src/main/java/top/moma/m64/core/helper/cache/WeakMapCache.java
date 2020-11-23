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
public class WeakMapCache<K, V> implements java.io.Serializable {
  private static final long serialVersionUID = -6003538455202187956L;

  /** 缓存池 */
  private final Map<K, V> weakMapCache;

  /** 读写锁 */
  private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();

  private final ReentrantReadWriteLock.ReadLock readLock = cacheLock.readLock();
  private final ReentrantReadWriteLock.WriteLock writeLock = cacheLock.writeLock();

  public WeakMapCache() {
    weakMapCache = new WeakHashMap<>();
  }

  public WeakMapCache(Map<K, V> weakMapCache) {
    this.weakMapCache = weakMapCache;
  }

  /**
   * get
   *
   * <p>get value
   *
   * @author Created by ivan at 下午3:12 2020/1/20.
   * @return V
   */
  public V get(K key) {
    V value;
    readLock.lock();
    try {
      value = weakMapCache.get(key);
    } finally {
      readLock.unlock();
    }
    return value;
  }

  /**
   * put
   *
   * <p>put value
   *
   * @author Created by ivan at 下午3:16 2020/1/20.
   * @return V
   */
  public V put(K key, V value) {
    writeLock.lock();
    try {
      weakMapCache.put(key, value);
    } finally {
      writeLock.unlock();
    }
    return value;
  }

  /**
   * remove
   *
   * <p>remove value
   *
   * @author Created by ivan at 下午3:16 2020/1/20.
   * @return V
   */
  public V remove(K key) {
    writeLock.lock();
    try {
      return weakMapCache.remove(key);
    } finally {
      writeLock.unlock();
    }
  }

  /**
   * empty
   *
   * <p>empty/clear cache
   *
   * @author Created by ivan at 下午3:17 2020/1/20.
   */
  public void empty() {
    writeLock.lock();
    try {
      weakMapCache.clear();
    } finally {
      writeLock.unlock();
    }
  }
}
