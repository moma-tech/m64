package top.moma.m64.common.helper.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * JasyptTool
 *
 * <p>Jasypt Version 3.0
 *
 * <p>https://github.com/ulisesbocchio/jasypt-spring-boot#update-11242019-version-300-release-includes
 *
 * @version 1.0
 * @author Created by ivan at 10:48.
 */
public class JasyptTool {

  private JasyptTool() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * buildConfigV3
   *
   * @param password password
   * @return org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig
   * @author Created by ivan
   * @since 2023/6/15 10:50
   */
  public static EnvironmentStringPBEConfig buildConfigV3(String password) {
    EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
    config.setPassword(password);
    config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
    config.setStringOutputType("base64");
    return config;
  }

  /**
   * enc
   *
   * @param original original
   * @param password password
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/6/15 10:50
   */
  public static String enc(String original, String password) {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setConfig(buildConfigV3(password));
    return encryptor.encrypt(original);
  }

  /**
   * dec
   *
   * @param text text
   * @param password password
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/6/15 10:50
   */
  public static String dec(String text, String password) {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setConfig(buildConfigV3(password));
    return encryptor.decrypt(text);
  }
}
