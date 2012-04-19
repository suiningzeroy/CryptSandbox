package cryptography;

import java.math.BigInteger;
import java.util.*;

public class RsaKeyszy {
  public static int BIT_LENGTH_OF_RANDOM_NUMBER_E = 8;
  public static int BIT_LENGTH_OF_RANDOM_NUMBER = 10;
  public BigInteger NUMBER_ZERO = new BigInteger("0"); 
  public BigInteger NUMBER_ONE = new BigInteger("1"); 

  public BigInteger primeP = BigInteger.ZERO;  
  public BigInteger primeQ = BigInteger.ZERO;  
  public BigInteger publicKeyN = BigInteger.ZERO;  
  public BigInteger publicKeyE = BigInteger.ZERO;  
  public BigInteger privateKeyD = BigInteger.ZERO;  
  
  public RsaKeyszy() {
    this.generatePrimeP(RsaKeyszy.BIT_LENGTH_OF_RANDOM_NUMBER);
    this.generatePrimeQ(RsaKeyszy.BIT_LENGTH_OF_RANDOM_NUMBER);
    this.generatePublicKeyN();
    this.generatePositivePublicKeyE();
    this.generatePrivateKeyD();
  }
  
  public  void testRsa() { 
	  // method for test,will be deleted.
    this.publicKeyN = new BigInteger("3337"); 
    this.privateKeyD = new BigInteger("1019"); 
    this.publicKeyE = new BigInteger("79"); 
  }
  
  public void generatePrimeP (int bitlengthofRandomNumber) {
    primeP = this.generateLargePrime(BIT_LENGTH_OF_RANDOM_NUMBER);	 
  }

  public void generatePrimeQ(int bitlengthofRandomNumber) {
    primeQ = this.generateLargePrime(BIT_LENGTH_OF_RANDOM_NUMBER);
  }
  
  public void generatePublicKeyN () {	 
    // n =pq;
    this.publicKeyN = this.primeP.multiply(this.primeQ);
  }
  
  public void generatePositivePublicKeyE () {
    //relatively prime to (p - 1)(q - 1)
    BigInteger numberE = this.generateRandomNumber(RsaKeyszy.BIT_LENGTH_OF_RANDOM_NUMBER_E);
    BigInteger NUMBERONE = new BigInteger("1");

    do{
      numberE = this.generateRandomNumber(BIT_LENGTH_OF_RANDOM_NUMBER_E);
    }while(this.doEuclideanAlgorithm(numberE, this.primeP.subtract(NUMBERONE).multiply(this.primeQ.subtract(NUMBERONE))) == NUMBERONE);
	
    this.publicKeyE = numberE;
  }
  
  public BigInteger doEuclideanAlgorithm(BigInteger bigNmberA,BigInteger bigNumberB) {	  
    BigInteger remainder ;
    BigInteger numberA =  bigNmberA;
    BigInteger numberB =  bigNumberB;
	  
    do{		  
      remainder = numberA.remainder(numberB);
      numberA = numberB;
      numberB = remainder; 
    }while(remainder.equals(NUMBER_ZERO) == false);
    return numberA;	  
  }
  
  public BigInteger generateLargePrime (int bitlengthofRandomNumber) {
    Random rdm = new java.util.Random();	 
    BigInteger	RandomNumber = new BigInteger(bitlengthofRandomNumber,10,rdm);	
    BigInteger largePrime = RandomNumber.nextProbablePrime();
    return largePrime;
  }
  
  public BigInteger generateRandomNumber (int bitlengthofRandomNumber) {
    Random rdm = new java.util.Random();	 
    BigInteger RandomNumber = new BigInteger(bitlengthofRandomNumber,10,rdm);
    return RandomNumber;
  }
  
  public void generatePrivateKeyD () {
	  //d  e^-1 mod ((p - 1)(q - 1))
    BigInteger numberZ; //numberZ = (p - 1)(q - 1)
    numberZ = this.primeP.subtract(NUMBER_ONE).multiply(primeQ.subtract(NUMBER_ONE));
    
    do{
      generatePositivePublicKeyE ();
      this.privateKeyD = this.extendedEuclideanAlgorithm(numberZ, this.publicKeyE);
    }while(isNegativeNumber(privateKeyD));
  }
  
  public boolean isNegativeNumber(BigInteger inputNumber) {
    if(inputNumber.abs() == inputNumber){
      return false;
    }
    else{
      return true;
    }
  }
  
  public BigInteger extendedEuclideanAlgorithm(BigInteger bigNmberM,BigInteger bigNumberE) {
    BigInteger firstOfNumberGroupOne,secondOfNumberGroupOne,thirdOfNumberGroupOne;
    BigInteger firstOfNumberGroupTwo,secondOfNumberGroupTwo,thirdOfNumberGroupTwo;
    BigInteger firstOfNumberGroupThree,secondNumberOfTestGroupThree,thirdOfNumberGroupThree;
    BigInteger q;
    BigInteger inverseNumber = NUMBER_ZERO;
    boolean flagTrue = true;
		  
    firstOfNumberGroupOne = new BigInteger("1");
    secondOfNumberGroupTwo = new BigInteger("1");
    secondOfNumberGroupOne = new BigInteger("0");
    firstOfNumberGroupTwo = new BigInteger("0");
    thirdOfNumberGroupOne = (bigNmberM.compareTo(bigNumberE) == 1)?bigNmberM:bigNumberE;
    thirdOfNumberGroupTwo = (bigNmberM.compareTo(bigNumberE) == 1)?bigNumberE:bigNmberM;
    while( flagTrue ){
      if(thirdOfNumberGroupTwo.equals(NUMBER_ZERO)){
        inverseNumber = thirdOfNumberGroupOne; // gcd(bigNmberN,bigNmberE) != 1
        break ;
      }
      if(thirdOfNumberGroupTwo.equals(NUMBER_ONE)){
        inverseNumber = secondOfNumberGroupTwo; // gcd(bigNmberN,bigNmberE) == 1
        break ;
      }
      q = thirdOfNumberGroupOne.divide(thirdOfNumberGroupTwo);
      firstOfNumberGroupThree = firstOfNumberGroupOne.subtract(q.multiply(firstOfNumberGroupTwo));
      secondNumberOfTestGroupThree = secondOfNumberGroupOne.subtract(q.multiply(secondOfNumberGroupTwo));
      thirdOfNumberGroupThree = thirdOfNumberGroupOne.subtract(q.multiply(thirdOfNumberGroupTwo));
      firstOfNumberGroupOne = firstOfNumberGroupTwo;
      secondOfNumberGroupOne = secondOfNumberGroupTwo;
      thirdOfNumberGroupOne = thirdOfNumberGroupTwo;
      firstOfNumberGroupTwo = firstOfNumberGroupThree;
      secondOfNumberGroupTwo = secondNumberOfTestGroupThree;
      thirdOfNumberGroupTwo = thirdOfNumberGroupThree;
    }
    return inverseNumber;
  }
   
}
