package cryptography;

import java.math.BigInteger;
import java.util.*;

public class Rsa {		
  public static int BIT_LENGTH_OF_RANDOM_NUMBER = 20;
	
  public BigInteger primeP = BigInteger.ZERO; 
  public BigInteger primeQ = BigInteger.ZERO; 
  public BigInteger publicKeyN = BigInteger.ZERO;  
  public BigInteger publicKeyE = BigInteger.ZERO;  
  public BigInteger privateKeyD = BigInteger.ZERO;  
  
  public Rsa(){
    this.setPrimeP(this.BIT_LENGTH_OF_RANDOM_NUMBER);
	this.setPrimeQ(this.BIT_LENGTH_OF_RANDOM_NUMBER);
	this.setPublicKeyN();
	this.setPublicKeyE();
	this.setPrivateKeyD();	    
  }
  
  public  void testRsa(){ 
    this.publicKeyN = new BigInteger("3337"); 
    this.privateKeyD = new BigInteger("1019"); 
    this.publicKeyE = new BigInteger("79"); 
  }
  
  public void setPrimeP (int bitlengthofRandomNumber){
    primeP = this.generateLargePrime(BIT_LENGTH_OF_RANDOM_NUMBER);	 
  }
  
  public void setPrimeQ(int bitlengthofRandomNumber){
    primeQ = this.generateLargePrime(BIT_LENGTH_OF_RANDOM_NUMBER);
  }
  
  public void setPublicKeyN (){	 
	  // n =pq;
    this.publicKeyN = this.primeP.multiply(this.primeQ);
  }
  
  public void setPublicKeyE (){
	  //relatively prime to (p - 1)(q - 1)
    int BIT_LENGTH_OF_NUMBER_E = 10;
    BigInteger numberE = this.generateRandomNumber(BIT_LENGTH_OF_NUMBER_E);
    BigInteger NUMBERONE = new BigInteger("1");
	
    do{
    	 numberE = this.generateRandomNumber(BIT_LENGTH_OF_NUMBER_E);
    }while(this.euclideanAlgorithm(numberE, this.primeP.subtract(NUMBERONE).multiply(this.primeQ.subtract(NUMBERONE))) == NUMBERONE);
	
    this.publicKeyE = numberE;
  }
  
  public BigInteger euclideanAlgorithm(BigInteger bigNmberA,BigInteger bigNumberB){	  
    BigInteger k ;
    BigInteger numberA =  bigNmberA;
    BigInteger numberB =  bigNumberB;
    BigInteger numberZero = new BigInteger("0");
	  
    do{		  
	  k = numberA.remainder(numberB);
      numberA = numberB;
      numberB = k; 
    }while(k.equals(numberZero) == false);
    return numberA;	  
  }
  
  public BigInteger generateLargePrime (int bitlengthofRandomNumber){
    Random rdm = new java.util.Random();	 
    BigInteger	RandomNumber = new BigInteger(bitlengthofRandomNumber,10,rdm);	
    BigInteger largePrime = RandomNumber.nextProbablePrime();
    return largePrime;
  }
  
  public BigInteger generateRandomNumber (int bitlengthofRandomNumber){
    Random rdm = new java.util.Random();	 
    BigInteger RandomNumber = new BigInteger(bitlengthofRandomNumber,10,rdm);
	
    return RandomNumber;
  }
  
  public void setPrivateKeyD (){
	  //d  e^-1 mod ((p - 1)(q - 1))
    BigInteger numberZ; //numberZ = (p - 1)(q - 1)
    BigInteger NUMBERONE = new BigInteger("1");
		
    numberZ = this.primeP.subtract(NUMBERONE).multiply(primeQ.subtract(NUMBERONE));
    this.privateKeyD = this.extendedEuclideanAlgorithm(numberZ, this.publicKeyE);
  }
  
  public BigInteger extendedEuclideanAlgorithm(BigInteger bigNmberM,BigInteger bigNumberE){
	BigInteger x1,x2,x3,y1,y2,y3,t1,t2,t3,q;
    BigInteger inverseNumber = new BigInteger("0");
    BigInteger NUMBER_ZERO = new BigInteger("0");
    BigInteger NUMBER_ONE = new BigInteger("1");
    boolean ff = true;
		  
    x1 = y2 = new BigInteger("1");
    x2 = y1 = new BigInteger("0");
    x3 = (bigNmberM.compareTo(bigNumberE) == 1)?bigNmberM:bigNumberE;
    y3 = (bigNmberM.compareTo(bigNumberE) == 1)?bigNumberE:bigNmberM;
    
    while( ff ){
      if(y3.equals(NUMBER_ZERO)){
        inverseNumber = x3; // gcd(bigNmberN,bigNmberE) != 1
        break ;
      }
      if(y3.equals(NUMBER_ONE)){
        inverseNumber = y2; // gcd(bigNmberN,bigNmberE) == 1
        break ;
      }
      q = x3.divide(y3);
      t1 = x1.subtract(q.multiply(y1));
      t2 = x2.subtract(q.multiply(y2));
      t3 = x3.subtract(q.multiply(y3));
      x1 = y1;
      x2 = y2;
      x3 = y3;
      y1 = t1;
      y2 = t2;
      y3 = t3;
    }
    return inverseNumber;
  }
   
}
