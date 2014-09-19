package rsa;

import com.vaadin.tutorial.cleverhome.util.RandomUtil;
import junit.framework.TestCase;

import java.math.BigInteger;

/**
 * Created by vlk on 03.08.2014.
 */
public class TestRSAModel extends TestCase {

    public void testModulus(){

        BigInteger modulus = new BigInteger(String.valueOf(RandomUtil.randomInt(1048576, 2097152)));
        System.out.println(modulus);

    }

    public void testInitParams(){

    }

    public void testEncryption(){

    }

    public void testDecryption(){
        
    }

}
