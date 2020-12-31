package top.moma.m64.core.helper.security.rsa256;

import lombok.Data;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * RSA256Key
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/31/20.
 */
@Data
public class RSA256Key {
  private RSAPublicKey rsaPublicKey;
  private RSAPrivateKey rsaPrivateKey;
}
