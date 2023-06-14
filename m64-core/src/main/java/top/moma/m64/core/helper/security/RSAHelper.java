package top.moma.m64.core.helper.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import top.moma.m64.core.exceptions.M64Exception;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.security.rsa.RSAKeyPairs;

/**
 * RSAHelper *
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/31/20.
 */
public class RSAHelper {

  private RSAHelper() {
    throw new IllegalStateException("Utility class");
  }

  private static final String RSA_CIPHER_ALGORITHM_PUB2PRI =
      "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";
  private static final String RSA_SIGN_ALGORITHM = "SHA256withRSA";
  private static final String RSA_KEY_FACTORY = "RSA";
  private static final Integer RSA_KEY_SIZE = 2048;

  public static final String RSA_PUBLIC_KEY_DEFAULT =
      "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzAknkiaQoddb/hG6vzo8bCPAXOduih17wGn86CBVV88I5ZFm2aAqEhfn36UAdHgOf/s15qMisbot2Usz3r6EzOjC6Ia65N+Jvj6y2PKaY8QC1K5mjqfAx5zJTOTY82WmNMwSv7R78iF6/ObxHjkpvk5ExVnHWqiBk2Id5G1eX5CRt+wrmO32Vcq5qPGGZUMaxXw5OdPvZJwQZJ1fhwhfDBLvDUP4pwqxOxOJ0md15smB2B3Jwsw4k8WKLvZY+EBpfaYqEE1flXraj6Cc56CWMPn3S3OiK/UkZH6L4LgdNO7eMow1jvteQHXuwi3vBJiTS14M3yas5TTZuic4Um97cwIDAQAB";
  public static final String RSA_PRIVATE_KEY_DEFAULT =
      "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDMCSeSJpCh11v+Ebq/OjxsI8Bc526KHXvAafzoIFVXzwjlkWbZoCoSF+ffpQB0eA5/+zXmoyKxui3ZSzPevoTM6MLohrrk34m+PrLY8ppjxALUrmaOp8DHnMlM5NjzZaY0zBK/tHvyIXr85vEeOSm+TkTFWcdaqIGTYh3kbV5fkJG37CuY7fZVyrmo8YZlQxrFfDk50+9knBBknV+HCF8MEu8NQ/inCrE7E4nSZ3XmyYHYHcnCzDiTxYou9lj4QGl9pioQTV+VetqPoJznoJYw+fdLc6Ir9SRkfovguB007t4yjDWO+15Ade7CLe8EmJNLXgzfJqzlNNm6JzhSb3tzAgMBAAECggEAHmOKqkDmL8Skpe28E7k3wJ9+ihfKJfYINXtTuLsAGwjx+Uczu1wYiANZfLzVmYM8HaGrwIMxqqjhJUkHG7jijKZqSTmv4mDM4jHyb0+K4SsThNvI2JxxoQlgDfzTt0S/gYOXk0ftYf3MlJhM90RqTDbaFU6u23jXe70UWK5VbQQV7Jt0jV+2IU7B5v2f20Pgqed6y9DVrFDfJsthe2vtCw0l6tgkziUX7a+1VGC2wQBGwre6OZ2Gn9hxxz2R607ccF3/yqvfLg0DKa8CkaNxIAn482zt2KQyjgYW5ARoD0O+MshZ4Yh5WoOtKwu6k51cX76XRBRbM3ligW7Fu2Li8QKBgQD40iwF96eWiXotus+He6qdoX3yD6XQ7t6V0Pmr5xSgy3Fpr+COAAb/wfdobm63jUWvREDNmFcVglXpuIBlaMBUo1hNuE9tBX6a/zAhzfG24NPuBV4QySg3nqYxRtifXVjoL9+BB0ZoIdy/L3pIK+jT/GXlDBJmLYb1iKaUOJ/s6wKBgQDR7DFCU5xA+yiftjrIuxOFTYhlcvFq1BcH+WlrHOLQjzOUNZnEjOUpzrlH98jtuQg/f2tzW5ZBUvmdsOKNb77SmQpSjoAJVFB0/wiJj35il4kRp+nXNodJnCdgqBs7+A9M4tjaj+Fdy2FAmf7am/Jj05lsjCVDjGp2dxMxMmvpmQKBgHl4URmQr3XkI4tTmaCwlLhjcFLNpMt88Zj97gUnyIA/EVzhCaUJCmGtVZTb5J0jEJPhpCk6Z7kOadaxxay8GLi5DZDTm6LDfe05C0xVd90poQyf/i3/peyRPNztky8pqQ+g32HkJVEMxvFmwjGdjgp/O1c4L3tGWo5facMOabSFAoGADq06zG5YEFr+/huZhIs/1CQVkzI0Gsn1SkNv0WNVoEtCyevtckZ/hyrC3Xs/ew9iuj3IX2pZ2PtaJGJHlKfpaYP1qsv3u68/aM6j5Co6Jd5+YNOij79qOgVG44UdUlYHi9KYYr+IfCxKAmBB5zrb+YrDwUkTGePpVZsBpoDl9pECgYBTtiw1E5a15gSis7fTMGTBPSd7o4UXAnnbXYPRoQZs7/yjhQzrXj/JhrvoEqR2dUh4ROyVncK7dlX7a1s78wTeasMBI+C2TrqHb1YmxVolWL+7o+8o52OnEJSQplzrjvtQYD/5iTQ59HEYXzqRtsfyZ/3XbvAx1L64r16g1VR8vw==";

  private static final String PUBLIC_KEY = "RSAPublicKey";
  private static final String PRIVATE_KEY = "RSAPrivateKey";
  private static RSAKeyPairs rsaKeyPairs;

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
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA_KEY_FACTORY);
      keyPairGen.initialize(RSA_KEY_SIZE);
      KeyPair keyPair = keyPairGen.generateKeyPair();
      keyMap.put(PUBLIC_KEY, keyPair.getPublic());
      keyMap.put(PRIVATE_KEY, keyPair.getPrivate());
    } catch (NoSuchAlgorithmException e) {
      throw new M64Exception("RSAHelper,initKey,error", e);
    }
    return keyMap;
  }

  /**
   * 获取公私钥 getRSAKey
   *
   * @return top.moma.m64.core.helper.security.rsa.RSAKeyPairs
   * @author Created by ivan
   * @since 2023/6/14 11:24
   */
  public static synchronized RSAKeyPairs getRSAKey() {
    if (ObjectHelper.isEmpty(rsaKeyPairs)) {
      synchronized (RSAKeyPairs.class) {
        if (ObjectHelper.isEmpty(rsaKeyPairs)) {
          rsaKeyPairs = new RSAKeyPairs();
          Map<String, Object> map = initKey();
          rsaKeyPairs.setRsaPrivateKey((RSAPrivateKey) map.get(RSAHelper.PRIVATE_KEY));
          rsaKeyPairs.setRsaPublicKey((RSAPublicKey) map.get(RSAHelper.PUBLIC_KEY));
        }
      }
    }
    return rsaKeyPairs;
  }

  /**
   * 获取公私钥 getRSAKeyAsString
   *
   * @return java.lang.String[]
   * @author Created by ivan
   * @since 2023/6/14 11:26
   */
  public static String[] getRSAKeyAsString() {
    RSAKeyPairs keyPairs = getRSAKey();
    return new String[] {keyPairs.publicKeyInBase64(), keyPairs.privateKeyInBase64()};
  }

  /**
   * 计算签名 generateSign
   *
   * @param data data
   * @param privateKey private key
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/6/13 14:46
   */
  public static String generateSign(String data, String privateKey) {
    String result;
    try {
      Signature signature = Signature.getInstance(RSA_SIGN_ALGORITHM);
      PrivateKey priKey =
          KeyFactory.getInstance(RSA_KEY_FACTORY)
              .generatePrivate(
                  new PKCS8EncodedKeySpec(
                      Base64.getDecoder().decode(privateKey.getBytes(StandardCharsets.UTF_8))));
      signature.initSign(priKey);
      signature.update(data.getBytes(StandardCharsets.UTF_8));
      byte[] signed = signature.sign();
      result = new String(Base64.getEncoder().encode(signed), StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new M64Exception("RSAHelper,generateSign,error", e);
    }
    return result;
  }

  /**
   * 校验签名 verifySign
   *
   * @param data data content
   * @param sign sign text
   * @param publicKey public key
   * @return boolean
   * @author Created by ivan
   * @since 2023/6/13 15:56
   */
  public static boolean verifySign(String data, String sign, String publicKey) {
    boolean result;
    try {
      Signature signature = Signature.getInstance(RSA_SIGN_ALGORITHM);
      PublicKey pubKey =
          KeyFactory.getInstance(RSA_KEY_FACTORY)
              .generatePublic(
                  new X509EncodedKeySpec(
                      Base64.getDecoder().decode(publicKey.getBytes(StandardCharsets.UTF_8))));
      signature.initVerify(pubKey);
      signature.update(data.getBytes(StandardCharsets.UTF_8));
      result = signature.verify(Base64.getDecoder().decode(sign.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      throw new M64Exception("RSAHelper,verifySign,error", e);
    }
    return result;
  }

  /**
   * RSA ENCODER CIPHER rsaEncCipher
   *
   * @param source source
   * @param rsaKey rsaKey
   * @param cipher cipher
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 15:48
   */
  private static String rsaEncCipher(String source, Key rsaKey, Cipher cipher)
      throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
    cipher.init(Cipher.ENCRYPT_MODE, rsaKey);
    byte[] datas = source.getBytes(StandardCharsets.UTF_8);
    int inputLen = datas.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offset = 0;
    byte[] cache;
    int i = 0;
    while (inputLen - offset > 0) {
      if (inputLen - offset > 245) {
        cache = cipher.doFinal(datas, offset, 245);
      } else {
        cache = cipher.doFinal(datas, offset, inputLen - offset);
      }
      out.write(cache, 0, cache.length);
      i++;
      offset = i * 245;
    }
    String encryptData = Base64.getEncoder().encodeToString(out.toByteArray());
    out.close();
    return encryptData;
  }

  /**
   * RSA_2048公钥加密 encryptByRSA2048PubKey
   *
   * @param data 原文
   * @param rsaPublicKey rsaPublicKey
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 14:40
   */
  private static String encryptByRSA2048PubKey(String data, RSAPublicKey rsaPublicKey) {
    try {
      Cipher cipher = Cipher.getInstance(RSA_CIPHER_ALGORITHM_PUB2PRI);
      return rsaEncCipher(data, rsaPublicKey, cipher);
    } catch (Exception e) {
      throw new M64Exception("RSAHelper,encryptByRSA2048PubKey,error", e);
    }
  }

  /**
   * 基于数组构建公钥 buildPublicKey
   *
   * @param key key
   * @return java.security.interfaces.RSAPublicKey
   * @author Created by ivan
   * @since 2023/6/14 14:45
   */
  public static RSAPublicKey buildPublicKey(byte[] key) {
    try {
      return (RSAPublicKey)
          KeyFactory.getInstance(RSA_KEY_FACTORY).generatePublic(new X509EncodedKeySpec(key));
    } catch (Exception e) {
      throw new M64Exception("RSAHelper,buildPublicKey,error", e);
    }
  }

  /**
   * 基于String构建公钥 buildPublicKey
   *
   * @param key key
   * @return java.security.interfaces.RSAPublicKey
   * @author Created by ivan
   * @since 2023/6/14 14:45
   */
  public static RSAPublicKey buildPublicKey(String key) {
    return buildPublicKey(Base64.getDecoder().decode(key));
  }

  /**
   * 默认公钥加密 encryptByPubKey
   *
   * @param data 原文
   * @return 密文
   * @author Created by ivan
   * @since 2023/2/3 11:19
   */
  public static String encryptByPubKey(String data) {
    return encryptByRSA2048PubKey(data, buildPublicKey(RSA_PUBLIC_KEY_DEFAULT));
  }

  /**
   * 指定公钥加密 encryptByPubKey
   *
   * @param data 原文
   * @param pubKey pubKey
   * @return 密文
   * @author Created by ivan
   * @since 2023/2/3 11:19
   */
  public static String encryptByPubKey(String data, String pubKey) {
    return encryptByRSA2048PubKey(data, buildPublicKey(pubKey));
  }

  /**
   * 密钥加密 encrypt
   *
   * @param data data
   * @param rsaKeyPairs rsaKeyPairs
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/6/14 14:48
   */
  public static String encrypt(String data, RSAKeyPairs rsaKeyPairs) {
    return encryptByRSA2048PubKey(data, rsaKeyPairs.getRsaPublicKey());
  }

  /**
   * RSA DECODER CIPHER rsaDecCipher
   *
   * @param cipherText cipherText
   * @param rsaKey rsaKey
   * @param cipher cipher
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 15:48
   */
  private static String rsaDecCipher(String cipherText, Key rsaKey, Cipher cipher)
      throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
    cipher.init(Cipher.DECRYPT_MODE, rsaKey);
    byte[] inputByte = Base64.getDecoder().decode(cipherText.getBytes(StandardCharsets.UTF_8));
    int inputLen = inputByte.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offset = 0;
    byte[] cache;
    int i = 0;
    while (inputLen - offset > 0) {
      if (inputLen - offset > 256) {
        cache = cipher.doFinal(inputByte, offset, 256);
      } else {
        cache = cipher.doFinal(inputByte, offset, inputLen - offset);
      }
      out.write(cache, 0, cache.length);
      i++;
      offset = i * 256;
    }
    String data = out.toString();
    out.close();
    return data;
  }

  /**
   * RSA_2048私钥解密 decryptByRSA2048PriKey
   *
   * @param cipherText cipherText
   * @param rsaPrivateKey rsaPrivateKey
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 14:40
   */
  private static String decryptByRSA2048PriKey(String cipherText, RSAPrivateKey rsaPrivateKey) {
    try {
      Cipher cipher = Cipher.getInstance(RSA_CIPHER_ALGORITHM_PUB2PRI);
      return rsaDecCipher(cipherText, rsaPrivateKey, cipher);
    } catch (Exception e) {
      throw new M64Exception("RSAHelper,decryptByRSA2048PriKey,error", e);
    }
  }

  /**
   * 基于数组构建私钥 buildPrivateKey
   *
   * @param key key
   * @return java.security.interfaces.RSAPrivateKey
   * @author Created by ivan
   * @since 2023/6/14 14:42
   */
  public static RSAPrivateKey buildPrivateKey(byte[] key) {
    try {
      return (RSAPrivateKey)
          KeyFactory.getInstance(RSA_KEY_FACTORY).generatePrivate(new PKCS8EncodedKeySpec(key));
    } catch (Exception e) {
      throw new M64Exception("RSAHelper,buildPrivateKey,error", e);
    }
  }

  /**
   * 基于String构建私钥 buildPrivateKey
   *
   * @param key key
   * @return java.security.interfaces.RSAPrivateKey
   * @author Created by ivan
   * @since 2023/6/14 14:42
   */
  public static RSAPrivateKey buildPrivateKey(String key) {
    return buildPrivateKey(Base64.getDecoder().decode(key));
  }

  /**
   * 默认私钥解密 decryptByPriKey
   *
   * @param data 密文
   * @return 原文
   * @author Created by ivan
   * @since 2023/2/3 11:20
   */
  public static String decryptByPriKey(String data) {
    return decryptByRSA2048PriKey(data, buildPrivateKey(RSA_PRIVATE_KEY_DEFAULT));
  }

  /**
   * 指定私钥解密 decryptByPriKey
   *
   * @param data 密文
   * @param priKey 私钥
   * @return 原文
   * @author Created by ivan
   * @since 2023/2/3 11:20
   */
  public static String decryptByPriKey(String data, String priKey) {
    return decryptByRSA2048PriKey(data, buildPrivateKey(priKey));
  }

  /**
   * 密钥解密 decrypt
   *
   * @param data data
   * @param rsaKeyPairs rsaKeyPairs
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/6/14 14:49
   */
  public static String decrypt(String data, RSAKeyPairs rsaKeyPairs) {
    return decryptByRSA2048PriKey(data, rsaKeyPairs.getRsaPrivateKey());
  }
}
