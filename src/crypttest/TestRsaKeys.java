package crypttest;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import cryptography.RsaKeys;

public class TestRsaKeys {	 
  @Test
  public void testGenerateLargePrime(){
    RsaKeys rsa = new RsaKeys();	
    assertTrue(rsa.generateLargePrime(RsaKeys.BIT_LENGTH_OF_RANDOM_NUMBER).isProbablePrime(10));	
  }
  
  @Test
  public void testGeneratePublicKeyE() {
    RsaKeys rsa = new RsaKeys();
    rsa.generatePrimeP(RsaKeys.BIT_LENGTH_OF_RANDOM_NUMBER);
    rsa.generatePrimeQ(RsaKeys.BIT_LENGTH_OF_RANDOM_NUMBER);
    rsa.generatePublicKeyN();
    rsa.generatePositivePublicKeyE();
    BigInteger NUMBERONE = new BigInteger("1");	
    assertTrue(rsa.publicKeyE.gcd(rsa.publicKeyN).equals(NUMBERONE));
  }
  
  @Test
  public void testDoEuclideanAlgorithm() {
    RsaKeys rsa = new RsaKeys();
    BigInteger bigNmberA = new BigInteger("2312455435345");
    BigInteger bigNumberB = new BigInteger("312315466756");
    BigInteger expResult = bigNmberA.gcd(bigNumberB);
    
    assertEquals(expResult,rsa.doEuclideanAlgorithm(bigNmberA, bigNumberB));  	
  }
  
  @Test
  
  public void testDoExtendedEuclideanAlgorithm() {
    RsaKeys rsa = new RsaKeys();
    BigInteger numberM =  new BigInteger("3220");
    BigInteger numberE =  new BigInteger("79");
    BigInteger result =  rsa.extendedEuclideanAlgorithm(numberM, numberE);
    BigInteger expResult =  new BigInteger("1019");  
    
    assertEquals(expResult,rsa.doEuclideanAlgorithm(expResult, result));  
  }
  
}
