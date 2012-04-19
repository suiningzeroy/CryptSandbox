package cryptography;

import java.math.BigInteger;
import java.util.*;
import cryptography.RsaUtil;

public class RsaKeys {
  public static int BIT_LENGTH_PUBLIC_KEY_E = 8;
  public static int BIT_LENGTH_PRIME_NUMBER = 10;
  public static String PUBLIC_KEY_N = "Public_Key_N";
  public static String PUBLIC_KEY_E = "Public_Key_E";
  public static String PRIVATE_KEY_D = "Private_Key_D";
  
  public BigInteger primeP;
  public BigInteger primeQ;
  public BigInteger publicKeyN;
  public BigInteger publicKeyE;
  public BigInteger privateKeyD;
  
  public RsaKeys() {
  }

  public void generateKeySet() {
    this.primeP  = generatePrime(RsaKeys.BIT_LENGTH_PRIME_NUMBER);
    this.primeQ = generatePrime(RsaKeys.BIT_LENGTH_PRIME_NUMBER);
    this.publicKeyN = this.primeP.multiply(this.primeQ);
    BigInteger pMinus1TimesQMinus1 = this.primeP.subtract(RsaUtil.BIGINTEGER_ONE)
                                           .multiply(this.primeQ.subtract(RsaUtil.BIGINTEGER_ONE));
    this.publicKeyE = generatePositivePublicKeyE(pMinus1TimesQMinus1);
    this.privateKeyD = generatePrivateKeyD(this.publicKeyE, pMinus1TimesQMinus1);
  }

  public BigInteger generatePositivePublicKeyE(BigInteger pm1TimesQm1) {
    //relatively prime to (p - 1)(q - 1)
    BigInteger keyE = generateRandomNumber(BIT_LENGTH_PUBLIC_KEY_E);

    while(!computeGreatestCommonDivisor(keyE, pm1TimesQm1).equals(RsaUtil.BIGINTEGER_ONE)){
    keyE = keyE.nextProbablePrime();
    }
    return keyE;
  }
  
  public BigInteger computeGreatestCommonDivisor(BigInteger biA, BigInteger biB) {  
    BigInteger bigIntA =  biA;
    BigInteger bigIntB =  biB;
    BigInteger remainder = bigIntA;
     
    while(remainder.equals(RsaUtil.BIGINTEGER_ZERO) == false){
      remainder = bigIntA.remainder(bigIntB);
      bigIntA = bigIntB;
      bigIntB = remainder; 
    }
    return bigIntA;  
  }
  
  protected BigInteger generatePrime(int bitlengthofRandomNumber) {
    Random rnd = new java.util.Random(); 
    BigInteger randomBigInt = new BigInteger(bitlengthofRandomNumber, rnd);
    BigInteger nextPrime = randomBigInt.nextProbablePrime();
    return nextPrime;
  }
  
  private BigInteger generateRandomNumber(int bitlengthofRandomNumber) {
    Random rnd = new java.util.Random(); 
    return new BigInteger(bitlengthofRandomNumber, rnd);
  }
  
  //protected BigInteger generatePrivateKeyD(BigInteger keyE, BigInteger pm1TimesQm1) {
  public BigInteger generatePrivateKeyD(BigInteger keyE, BigInteger pm1TimesQm1) {
  //d = e^-1 mod ((p - 1)(q - 1))
    return computeMultiInverseOfAModB(keyE, pm1TimesQm1);
  }
  
  public BigInteger computeMultiInverseOfAModB(BigInteger bigIntA, BigInteger bigIntB) {

    BigInteger x = RsaUtil.BIGINTEGER_ZERO;
    BigInteger y = RsaUtil.BIGINTEGER_ONE;
    BigInteger lastX = RsaUtil.BIGINTEGER_ONE;
    BigInteger lastY = RsaUtil.BIGINTEGER_ZERO;
    while(bigIntB.equals(RsaUtil.BIGINTEGER_ZERO) == false){
      BigInteger quotient = bigIntA.divide(bigIntB);
      BigInteger temp = bigIntA;
      bigIntA = bigIntB;
      bigIntB = temp.remainder(bigIntB);
      temp = lastX;
      lastX = x;
      x = temp.subtract(quotient.multiply(x));
      temp = lastY;
      lastY = y;
      y = temp.subtract(quotient.multiply(y));
    }
    return lastX;
  }
  
  public Map<String, BigInteger> getKeyHashMap(){
    Map<String, BigInteger> keys = new HashMap<String, BigInteger>();
    keys.put(PUBLIC_KEY_N, this.publicKeyN);
    keys.put(PUBLIC_KEY_E, this.publicKeyE);
    keys.put(PRIVATE_KEY_D, this.privateKeyD);
    return keys;
  }
   
}