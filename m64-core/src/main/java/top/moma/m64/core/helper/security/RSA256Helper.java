package top.moma.m64.core.helper.security;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.codec.Base64Decoder;
import top.moma.m64.core.helper.codec.Base64Encoder;
import top.moma.m64.core.helper.security.rsa256.RSA256Key;

/**
 * RSA256Helper *
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/31/20.
 */
public class RSA256Helper {

  private RSA256Helper() {}

  public static final String KEY_ALGORITHM = "RSA";
  private static final String PUBLIC_KEY = "RSAPublicKey";
  private static final String PRIVATE_KEY = "RSAPrivateKey";
  private static RSA256Key rsa256Key;

  /**
   * 获得公钥 getPublicKey
   *
   * @param keyMap keyMap
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/29 18:00
   */
  public static String getPublicKey(Map<String, Object> keyMap) {
    // 获得map中的公钥对象 转为key对象
    Key key = (Key) keyMap.get(PUBLIC_KEY);
    // 编码返回字符串
    return encryptBASE64(key.getEncoded());
  }

  /**
   * 获得公钥 inString getPublicKey
   *
   * @param rsa256Key rsa256Key
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/29 18:00
   */
  public static String getPublicKey(RSA256Key rsa256Key) {
    // 获得map中的公钥对象 转为key对象
    Key key = rsa256Key.getRsaPublicKey();
    // 编码返回字符串
    return encryptBASE64(key.getEncoded());
  }

  /**
   * 获得私钥 getPrivateKey
   *
   * @param keyMap keyMap
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/29 18:00
   */
  public static String getPrivateKey(Map<String, Object> keyMap) {
    // 获得map中的私钥对象 转为key对象
    Key key = (Key) keyMap.get(PRIVATE_KEY);
    // 编码返回字符串
    return encryptBASE64(key.getEncoded());
  }

  /**
   * 获得私钥 inString getPrivateKey
   *
   * @param rsa256Key rsa256Key
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/29 18:00
   */
  public static String getPrivateKey(RSA256Key rsa256Key) {
    // 获得map中的私钥对象 转为key对象
    Key key = rsa256Key.getRsaPrivateKey();
    // 编码返回字符串
    return encryptBASE64(key.getEncoded());
  }

  /**
   * 解码返回byte decryptBASE64
   *
   * @param key key
   * @return byte[]
   * @author Created by ivan
   * @since 2023/3/29 18:01
   */
  public static byte[] decryptBASE64(String key) {
    return Base64Decoder.decode(key);
  }

  /**
   * 编码返回字符串 encryptBASE64
   *
   * @param key key
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/29 18:01
   */
  public static String encryptBASE64(byte[] key) {
    return Base64Encoder.encode(key);
  }

  /**
   * 使用KeyPairGenerator 生成公私钥，存放于map对象中
   *
   * @return java.util.Map
   * @author Created by ivan
   * @since 2023/3/29 17:59
   */
  public static Map<String, Object> initKey() {
    Map<String, Object> keyMap = new HashMap<>(2);
    try {
      /* RSA算法要求有一个可信任的随机数源 */
      // 获得对象 KeyPairGenerator 参数 RSA 2048个字节
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
      keyPairGen.initialize(2048);
      // 通过对象 KeyPairGenerator 生成密匙对 KeyPair
      KeyPair keyPair = keyPairGen.generateKeyPair();
      // 公私钥对象存入map中
      keyMap.put(PUBLIC_KEY, keyPair.getPublic());
      keyMap.put(PRIVATE_KEY, keyPair.getPrivate());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return keyMap;
  }

  /**
   * 获取公私钥 getRSA256Key
   *
   * @return top.moma.m64.core.helper.security.rsa256.RSA256Key
   * @author Created by ivan
   * @since 2023/3/29 17:59
   */
  public static synchronized RSA256Key getRSA256Key() {
    if (ObjectHelper.isEmpty(rsa256Key)) {
      synchronized (RSA256Key.class) {
        if (ObjectHelper.isEmpty(rsa256Key)) {
          rsa256Key = new RSA256Key();
          Map<String, Object> map = initKey();
          rsa256Key.setRsaPrivateKey((RSAPrivateKey) map.get(RSA256Helper.PRIVATE_KEY));
          rsa256Key.setRsaPublicKey((RSAPublicKey) map.get(RSA256Helper.PUBLIC_KEY));
        }
      }
    }
    return rsa256Key;
  }
}
