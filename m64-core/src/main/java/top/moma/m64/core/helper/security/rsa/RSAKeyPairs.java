package top.moma.m64.core.helper.security.rsa;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import lombok.Data;

/**
 * RSAKeyPairs
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/31/20.
 */
@Data
public class RSAKeyPairs {
  private RSAPublicKey rsaPublicKey;
  private RSAPrivateKey rsaPrivateKey;

  /**
   * publicKeyInBase64
   *
   * <p>Base64 Encode public key as String
   *
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/6/14 11:22
   */
  public String publicKeyInBase64() {
    return Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
  }

  /**
   * privateKeyInBase64
   *
   * <p>Base64 Encode private key as String
   *
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/6/14 11:23
   */
  public String privateKeyInBase64() {
    return Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded());
  }
}
