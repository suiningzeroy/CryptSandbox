package crypttest;

import static org.junit.Assert.*;


import java.math.BigInteger;

import org.junit.Ignore;
import org.junit.Test;

import cryptography.EncryptionCommunication;
import cryptography.Rsa;

public class TestEncryptionCommunication {
 
  @Test
  public void testEncryptMessage() {		
    EncryptionCommunication ecomm = new EncryptionCommunication();
    BigInteger testMessage = new BigInteger("688"); 
    BigInteger result = BigInteger.ZERO; 
    BigInteger expResult =  new BigInteger("688"); 
 	
    result = ecomm.encryptMessage(testMessage);
    assertEquals(expResult, result);		
  }
  
  @Ignore
  @Test
  public void testSplitMessageIntoBlocks(){
    EncryptionCommunication ecomm = new EncryptionCommunication();
    BigInteger testMessage = new BigInteger("123456789123456789"); 
    BigInteger[] result = new BigInteger[ecomm.NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED];
    BigInteger[] expResult = new BigInteger[ecomm.NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED];
 	 		 
    int i ;
    for(i = 0 ; i < ecomm.NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED ; i ++)
    {
       result[i] = BigInteger.ZERO; 
    }
   
    int j ;
    for(j = 0 ; j < ecomm.NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED ; j ++){
      expResult[j] = BigInteger.ZERO; 
    }
 	 
    result = ecomm.splitMessageIntoBlocks(testMessage);		 
    assertEquals(expResult, result);			 		 
  }
 
  @Test
  public void testDecryptAlgorithm(){
    BigInteger testMessage = new BigInteger("1570"); 
    BigInteger expResult = new BigInteger("688");
    
    EncryptionCommunication ecomm = new EncryptionCommunication();
    Rsa rsa = new Rsa();
    rsa.testRsa();
    System.out.println("n:" + rsa.publicKeyN);
    System.out.println("e:" + rsa.publicKeyE);
    System.out.println("d:" + rsa.privateKeyD);
    System.out.println("testMessage:" + testMessage);
    System.out.println("result:" + ecomm.decryptAlgorithm(testMessage, rsa));

    assertEquals(expResult, ecomm.decryptAlgorithm(testMessage, rsa));
  }
  
  @Test
  public void testEncryptAlgorithm(){	  
    BigInteger testMessage = new BigInteger("688"); 
    BigInteger expResult = new BigInteger("1570");
    
    EncryptionCommunication ecomm = new EncryptionCommunication();
    Rsa rsa = new Rsa();
    rsa.testRsa();
  
    assertEquals(expResult, ecomm.encryptAlgorithm(testMessage, rsa));
  }
}
