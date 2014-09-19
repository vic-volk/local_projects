package com.vaadin.tutorial.cleverhome.model;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by vlk on 13.08.2014.
 */
public interface RSAModelInterface {

    RSAPublicKey getRSAPublicKey();

    RSAPrivateKey getRSAPrivateKey();

    byte[] encryptMessage(byte[] message);

    byte[] decryptMessage(byte[] message);

}
