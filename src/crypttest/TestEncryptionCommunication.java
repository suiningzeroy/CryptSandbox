package crypttest;

import static org.junit.Assert.*;


import java.math.BigInteger;

import org.junit.Ignore;
import org.junit.Test;

import cryptography.EncryptionCommunication;
import cryptography.MessageForCommunicate;
import cryptography.RsaKeys;

public class TestEncryptionCommunication {
  @Ignore
  @Test
  public void testEncryptMessage() {		
    EncryptionCommunication ecomm = new EncryptionCommunication();
    RsaKeys rsa = new RsaKeys();
    rsa.testRsa();
    MessageForCommunicate testMessage = new MessageForCommunicate("688232687966668003");
    MessageForCommunicate result = new MessageForCommunicate(testMessage.getNumberOfBlocks()); 
    String[] expResult = new String[testMessage.getNumberOfBlocks()]; 
    
    expResult[0] = new String("1570");
    expResult[1] = new String("2756");
    expResult[2] = new String("2091");
    expResult[3] = new String("2276");
    expResult[4] = new String("2423");
    expResult[5] = new String("158");
    MessageForCommunicate expResultMFC = new MessageForCommunicate(expResult);
    result = EncryptionCommunication.encryptMessage(testMessage,rsa);
    assertEquals(expResultMFC, result);	
  }
  

  @Test(timeout = 5000)  
  public void testDecryptAlgorithm(){
    BigInteger testMessage = BigInteger.ZERO;
    BigInteger expResult = new BigInteger("12345");
    
    EncryptionCommunication ecomm = new EncryptionCommunication();
    RsaKeys rsa = new RsaKeys();
    testMessage = EncryptionCommunication.encryptAlgorithm(expResult, rsa);
    

    //rsa.testRsa();
    System.out.println("n:" + rsa.publicKeyN);
    System.out.println("e:" + rsa.publicKeyE);
    System.out.println("d:" + rsa.privateKeyD);
    System.out.println("testMessage:" + testMessage);
    //System.out.println("result:" + ecomm.decryptAlgorithm(testMessage, rsa));

    assertEquals(expResult, EncryptionCommunication.decryptAlgorithm(testMessage, rsa));
  }
  @Test
  public void testEncryptAlgorithm(){	  
    BigInteger testMessage = new BigInteger("688"); 
    BigInteger expResult = new BigInteger("1570");
    RsaKeys rsa = new RsaKeys();
    rsa.testRsa();
  
    assertEquals(expResult, EncryptionCommunication.encryptAlgorithm(testMessage, rsa));
  }
  @Ignore
  @Test
  public void testDecryptMessage(){
    EncryptionCommunication ecomm = new EncryptionCommunication();
    RsaKeys rsa = new RsaKeys();
    rsa.testRsa();
    MessageForCommunicate expResult = new MessageForCommunicate("688232687966668003");
    String[] encryptBlocks = new String[6];     
    encryptBlocks[0] = new String("1570");
    encryptBlocks[1] = new String("2756");
    encryptBlocks[2] = new String("2091");
    encryptBlocks[3] = new String("2276");
    encryptBlocks[4] = new String("2423");
    encryptBlocks[5] = new String("158");
    MessageForCommunicate testMessage = new MessageForCommunicate(encryptBlocks);
    
    MessageForCommunicate result = EncryptionCommunication.decryptMessage(testMessage, rsa);
    assertEquals(expResult,result);
    
  }

}
