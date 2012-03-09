package cryptography;

import java.math.BigInteger;
import java.util.*;

public class RSA {	
	
	BigInteger RANDOMMESSAGE;
	static int NUMBEROFBLOCKSTHEMESSAGEWASSPLITTED = 3;
	static int BITLENGTHOFRANDOMMESSAGE = 50;
	static int BITLENGTHOFRANDOMNUMBER = 30;
	static BigInteger MINRANDOM = new BigInteger("100"); 
	
	BigInteger primep = BigInteger.ZERO; 
	BigInteger primeq = BigInteger.ZERO; 
	BigInteger publicKeyN = BigInteger.ZERO;  
	BigInteger publicKeyE = BigInteger.ZERO;  
	BigInteger privateKeyD = BigInteger.ZERO;  
	
		
 public static BigInteger encryptingMessage(BigInteger message){ 	  
  
	  return message;	    
  } 
  
 private BigInteger [] splitMessageIntoBlocks(BigInteger message){
	  
	  BigInteger[] blocks = new BigInteger[NUMBEROFBLOCKSTHEMESSAGEWASSPLITTED];
	  
	  int i ;
	  for(i = 0 ; i < NUMBEROFBLOCKSTHEMESSAGEWASSPLITTED ; i ++)
	  {
		  blocks[i] = BigInteger.ZERO; 
	  }
	  return blocks;	  
  }
   
  private BigInteger encrypteAlgorithm(BigInteger m){ 
		//  c = m^e mod n
      BigInteger outputNumber = BigInteger.ZERO;  
      
	  return outputNumber;	  
  } 

  
  private BigInteger decryptingMessage (BigInteger message){ 	  
      BigInteger encrypedMessage = BigInteger.ZERO;  
      
	  return encrypedMessage;	  
  } 
    
  private BigInteger recoverMessageFromBlocks(BigInteger[] inputBlocks){
	  BigInteger message = BigInteger.ZERO; 
	  
	  return message;	  
  }
  
  private BigInteger decrypteAlgorithm(BigInteger inputNumber){ 	
	//  m = c^d mod n
      BigInteger outputNumber = BigInteger.ZERO;  
      
	  return outputNumber;	  	  
  } 
  
  private	BigInteger computePrivateKeyD (){
	  //d  e^-1 mod ((p - 1)(q - 1))
		BigInteger	privateKeyD = BigInteger.ZERO; 
		
		return privateKeyD;
  }
  
  private	BigInteger generatPublicKeyN (BigInteger p,BigInteger q){
	 //product of two primes, p and q (p and q must remain secret)
	BigInteger	publicKeyN = BigInteger.ZERO; 
	
	return publicKeyN;
  }
  
  private	BigInteger generatPublicKeyE (BigInteger p,BigInteger q){
	  //relatively prime to (p - 1)(q - 1)
	BigInteger	publicKeyE = BigInteger.ZERO; 
	
	return publicKeyE;
  }
  
  private	BigInteger generatLargePrime (int bitlengthofRandomNumber){
	BigInteger	largePrime = BigInteger.ZERO; 
	
	return largePrime;
  }
  
  private	BigInteger generatRandomNumber (int bitlengthofRandomNumber){
	BigInteger	RandomNumber = BigInteger.ZERO; 
	
	return RandomNumber;
  }
  
  private BigInteger generatRandomPrime (int bitlengthofRandomNumber){
	BigInteger	primeNum = BigInteger.ZERO; 
	
	return primeNum;
  }
  
  private BigInteger doEuclideanAlgorithm(BigInteger bigNmberA,BigInteger bigNumberB){	  
	  BigInteger greatestCommonDenominator = BigInteger.ZERO; 
	  
	  return greatestCommonDenominator;	  
  }

  
}
