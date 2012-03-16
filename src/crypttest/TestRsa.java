package crypttest;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Ignore;
import org.junit.Test;

import cryptography.Rsa;

public class TestRsa {
	 
  @Test
  public void testGenerateLargePrime(){
    Rsa rsa = new Rsa();
	
    assertTrue(rsa.generateLargePrime(Rsa.BIT_LENGTH_OF_RANDOM_NUMBER).isProbablePrime(10));	
  }
  
  @Test
  public void testSetPublicKeyE(){
    Rsa rsa = new Rsa();
    rsa.setPrimeP(Rsa.BIT_LENGTH_OF_RANDOM_NUMBER);
    rsa.setPrimeQ(Rsa.BIT_LENGTH_OF_RANDOM_NUMBER);
    rsa.setPublicKeyN();
    rsa.setPublicKeyE();
    BigInteger NUMBERONE = new BigInteger("1");
	
    assertTrue(rsa.publicKeyE.gcd(rsa.publicKeyN).equals(NUMBERONE));
  }
  
  @Test
  public void testEuclideanAlgorithm(){
    Rsa rsa = new Rsa();
    BigInteger bigNmberA = new BigInteger("2312455435345");
    BigInteger bigNumberB = new BigInteger("312315466756");
    BigInteger expResult = bigNmberA.gcd(bigNumberB);
	
    assertEquals(expResult,rsa.euclideanAlgorithm(bigNmberA, bigNumberB));  
	
  }
  
  @Test
  public void testExtendedEuclideanAlgorithm()
  {
    Rsa rsa = new Rsa();
    BigInteger numberM =  new BigInteger("3220");
    BigInteger numberE =  new BigInteger("79");
    BigInteger result =  rsa.extendedEuclideanAlgorithm(numberM, numberE);
    BigInteger expResult =  new BigInteger("1019");
	  
    assertEquals(expResult,rsa.euclideanAlgorithm(expResult, result));  
  }
  
}
