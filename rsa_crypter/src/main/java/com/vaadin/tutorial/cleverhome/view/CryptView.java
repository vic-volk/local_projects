package com.vaadin.tutorial.cleverhome.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.tutorial.cleverhome.util.RandomUtil;
import com.vaadin.ui.*;
import org.bouncycastle.util.encoders.Base64;

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
 * Created by volk on 24.02.14.
 */

public class CryptView extends HorizontalLayout implements View {

    private VerticalLayout topPanel = new VerticalLayout();
    private VerticalLayout bottomPanel = new VerticalLayout();

    private VerticalLayout leftPanel = new VerticalLayout();
    private VerticalLayout rightPanel = new VerticalLayout();

    private HorizontalLayout centralPanel = new HorizontalLayout();

    private Label header1 = new Label("Crypter");
    private TextArea openKeyField = new TextArea("Открытый ключ");
    private TextArea secretKeyField = new TextArea("Закрытый ключ");
    private TextArea messageField = new TextArea("Сообщение");
    private TextArea cryptedMessageField = new TextArea("Зашифрованное сообщение");

    private Button generateSecretKey = new Button("Генерировать секретный ключ");
    private Button generateOpenKey = new Button("Генерировать открытый ключ");

    private Button encryptMessage = new Button("Зашифровать сообщение");
    private Button decryptMessage = new Button("Расшифровать сообщение");

    Navigator navigator;

    private Cipher cipher;
    private KeyFactory keyFactory;
    private RSAPublicKeySpec pubKeySpec;
    private RSAPrivateKeySpec privKeySpec;

    private RSAPublicKey pubKey;
    private RSAPrivateKey privKey;

    private BigInteger modulus;
    private BigInteger privateExp;
    private BigInteger publicExp;

    byte[] cipherText = new byte[4096];
    byte[] plainText = new byte[4096];


    public CryptView(Navigator navigator) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InvalidKeyException {

        initRSAParams();

        this.navigator = navigator;

        setSizeFull();

        initLayouts();
        initSecondPageButtons();
        initStyles();
        initTextFields();

        addComponent(centralPanel);
        setComponentAlignment(centralPanel, Alignment.MIDDLE_CENTER);

//        addComponent(leftPanel);
//        addComponent(rightPanel);

    }

    private void initRSAParams() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

//        byte[] input = new byte[] { (byte) 0xbe, (byte) 0xef };
        cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");

        keyFactory = KeyFactory.getInstance("RSA", "BC");


    }

    private void initTextFields() {

        messageField.setHeight("100%");

    }

    private void initTables() {


    }

    private void initMeasuresList() {


    }

    private void initStyles(){

        header1.addStyleName("large-header");

    }

    private void initLayouts(){

        centralPanel.setWidth("80%");
        centralPanel.setHeight("80%");

        messageField.setSizeFull();
        cryptedMessageField.setSizeFull();
        secretKeyField.setSizeFull();
        openKeyField.setSizeFull();

        topPanel.addComponent(header1);
        topPanel.addComponent(messageField);
        topPanel.addComponent(cryptedMessageField);
        topPanel.setSizeFull();

        bottomPanel.addComponent(generateSecretKey);
        bottomPanel.addComponent(secretKeyField);
        bottomPanel.addComponent(generateOpenKey);
        bottomPanel.addComponent(openKeyField);
        bottomPanel.addComponent(encryptMessage);
        bottomPanel.addComponent(decryptMessage);
        bottomPanel.setSizeFull();

        leftPanel.setSizeFull();
        leftPanel.addComponent(topPanel);
        leftPanel.addComponent(bottomPanel);

//        rightPanel.setSizeFull();

        centralPanel.addComponent(leftPanel);
//        centralPanel.addComponent(rightPanel);

    }

    /*инициализация кнопок второй страницы*/
    private void initSecondPageButtons(){

        generateSecretKey.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                try {

                    modulus = new BigInteger(String.valueOf(RandomUtil.randomInt(1048576, 2097152)));

                    System.out.println(modulus);

                    privKeySpec = new RSAPrivateKeySpec(new BigInteger(
                            "12345678", 16), new BigInteger("12345678",
                            16));
                    privKey = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);
                    secretKeyField.setValue(new String(Base64.encode(privKey.getEncoded())));


                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }

            }
        });

        generateOpenKey.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                try {

                    pubKeySpec = new RSAPublicKeySpec(new BigInteger(
                            "12345678", 16), new BigInteger("11", 16));
                    pubKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
                    openKeyField.setValue(new String(Base64.encode(pubKey.getEncoded())));

                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }

            }
        });

        encryptMessage.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if((pubKey!=null)&&(privKey!=null)&&(messageField.getValue()!=null)){

                    try {

                        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                        cipherText = cipher.doFinal(messageField.getValue().getBytes());
                        cryptedMessageField.setValue(new String(Base64.encode(cipherText)));

                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }


                }

            }
        });

        decryptMessage.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if((pubKey!=null)&&(privKey!=null)&&(cryptedMessageField.getValue()!=null)){

                    try {

                        cipher.init(Cipher.DECRYPT_MODE, privKey);
                        plainText = cipher.doFinal(cipherText);
                        messageField.setValue(new String(plainText));

                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }


                }

            }
        });


    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

//        Notification.show("Welcome to smart home");

    }
}

