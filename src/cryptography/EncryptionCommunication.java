package cryptography;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import cryptography.Rsa;

public class EncryptionCommunication {
  public static int NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED = 3;
  public static int BIT_LENGTH_OF_RANDOM_MESSAGE = 50;
 
  public static void main(String[] args){
   // Rsa rsa = new Rsa();
    
  }
  
  public static BigInteger encryptMessage(BigInteger message){ 	  
	  
		return message;	    
  } 
	  
  public BigInteger [] splitMessageIntoBlocks(BigInteger message){
	  
	BigInteger[] blocks = new BigInteger[NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED];
	  
	int i ;
	for(i = 0 ; i < NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED ; i ++)
	{
	  blocks[i] = BigInteger.ZERO; 
	}
	return blocks;	  
  }
	   
  public BigInteger encryptAlgorithm(BigInteger message, Rsa rsa){ 
		//  c = m^e mod n
    BigInteger outputNumber = BigInteger.ZERO;  
    
    outputNumber = message.pow(rsa.publicKeyE.intValue()).mod(rsa.publicKeyN);    
	return outputNumber;	  
  } 
	  
  public BigInteger decryptMessage (BigInteger m, Rsa rsa){ 	  
	BigInteger encrypedMessage = BigInteger.ZERO;  
	
	return encrypedMessage;	  
  } 
    
  private BigInteger recoverMessageFromBlocks(BigInteger[] inputBlocks){
	BigInteger message = BigInteger.ZERO; 
	  
	return message;	  
  }
  
  public BigInteger decryptAlgorithm(BigInteger m, Rsa rsa ){ 	
	//  m = c^d mod n
    BigInteger outputNumber = BigInteger.ZERO;  
    outputNumber = m.pow(rsa.privateKeyD.intValue()).mod(rsa.publicKeyN);
      
	return outputNumber;	  	  
  } 
		 
}
