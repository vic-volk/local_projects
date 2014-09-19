package com.vaadin.tutorial.cleverhome.model;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * Created by vlk on 03.08.2014.
 */
public class RSAModel implements RSAModelInterface {

    private Cipher cipher;
    private KeyFactory keyFactory;

    private BigInteger modulus;
    private RSAPrivateKey privKey;
    private RSAPublicKey pubKey;

    public RSAModel(){

        try {
            initRSAParams();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

    }

    public void initRSAParams() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
        keyFactory = KeyFactory.getInstance("RSA", "BC");


    }

    private void generateSecretKey() throws InvalidKeySpecException {
        RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(new BigInteger(
                "12345678", 16), new BigInteger("12345678",
                16));
        this.privKey = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);
    }

    private void generatePublicKey() throws InvalidKeySpecException {
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(
                "12345678", 16), new BigInteger("11", 16));
        this.pubKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
    }

    public byte[] encryptMessage(String message, RSAPublicKey pubKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherText = cipher.doFinal(message.getBytes());
        return cipherText;

    }

    public void decryptMessage(String cipherText, RSAPrivateKey privKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] plainText = cipher.doFinal(cipherText.getBytes());

    }

    @Override
    public RSAPublicKey getRSAPublicKey() {
        if(this.pubKey != null){
            return this.pubKey;
        }
        return null;
    }

    @Override
    public RSAPrivateKey getRSAPrivateKey() {
        if(this.privKey != null){
            return this.privKey;
        }
        return null;
    }

    @Override
    public byte[] encryptMessage(byte[] message) {
        return new byte[0];
    }

    @Override
    public byte[] decryptMessage(byte[] message) {
        return new byte[0];
    }
}
