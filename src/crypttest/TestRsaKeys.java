package crypttest;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import cryptography.RsaKeys;

public class TestRsaKeys {	 
  @Test
  public void testComputeGreatestCommonDivisor() {
    RsaKeys rsa = new RsaKeys();
    BigInteger numberM =  new BigInteger("3220");
    BigInteger numberE =  new BigInteger("79");
    BigInteger expResult =  new BigInteger("1019");  
    
    assertEquals(expResult,rsa.computeMultiInverseOfAModB(numberE, numberM));  
  }
  
  @Test
  public void testGeneratePrivateKeyD() {
    RsaKeys rsa = new RsaKeys();
    BigInteger numberM =  new BigInteger("3220");
    BigInteger numberE =  new BigInteger("79");
    BigInteger expResult =  new BigInteger("1019");
    
    assertEquals(expResult,rsa.generatePrivateKeyD(numberE, numberM));  
  }
  
}
