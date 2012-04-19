package crypttest;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import cryptography.RsaKeyszy;

public class TestRsaKeyszy {	 
  @Test
  public void testGenerateLargePrime(){
    RsaKeyszy rsa = new RsaKeyszy();	
    assertTrue(rsa.generateLargePrime(RsaKeyszy.BIT_LENGTH_OF_RANDOM_NUMBER).isProbablePrime(10));	
  }
  
  @Test
  public void testGeneratePublicKeyE() {
    RsaKeyszy rsa = new RsaKeyszy();
    rsa.generatePrimeP(RsaKeyszy.BIT_LENGTH_OF_RANDOM_NUMBER);
    rsa.generatePrimeQ(RsaKeyszy.BIT_LENGTH_OF_RANDOM_NUMBER);
    rsa.generatePublicKeyN();
    rsa.generatePositivePublicKeyE();
    BigInteger NUMBERONE = new BigInteger("1");	
    assertTrue(rsa.publicKeyE.gcd(rsa.publicKeyN).equals(NUMBERONE));
  }
  
  @Test
  public void testDoEuclideanAlgorithm() {
    RsaKeyszy rsa = new RsaKeyszy();
    BigInteger bigNmberA = new BigInteger("2312455435345");
    BigInteger bigNumberB = new BigInteger("312315466756");
    BigInteger expResult = bigNmberA.gcd(bigNumberB);
    
    assertEquals(expResult,rsa.doEuclideanAlgorithm(bigNmberA, bigNumberB));  	
  }
  
  @Test
  
  public void testComputeGreatestCommonDivisor() {
    RsaKeyszy rsa = new RsaKeyszy();
    BigInteger numberM =  new BigInteger("3220");
    BigInteger numberE =  new BigInteger("79");
    BigInteger result =  rsa.extendedEuclideanAlgorithm(numberM, numberE);
    BigInteger expResult =  new BigInteger("1019");  
    
    assertEquals(expResult,rsa.extendedEuclideanAlgorithm(numberM, numberE));  
  }
  
}
