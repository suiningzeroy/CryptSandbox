package cryptography;

import java.math.*;
import cryptography.MessageForCommunication;

import cryptography.RsaKeys;

public class EncryptionCommunication {

  public static void main(String[] args) {
    EncryptionCommunication ec = new EncryptionCommunication();
    MessageForCommunication MessageForEncrypt = new MessageForCommunication();
    MessageForEncrypt.initializeMessageWithString("488262687011");
    MessageForCommunication EncryptedMessage = new MessageForCommunication();
    EncryptedMessage.initializeMessageWithIntegerNumber(MessageForEncrypt.getNumberOfBlocks());
    MessageForCommunication DecryptedMessage = new MessageForCommunication();
    DecryptedMessage.initializeMessageWithIntegerNumber(MessageForEncrypt.getNumberOfBlocks());
    RsaKeys rsa = new RsaKeys();
    rsa.generateKeySet();
    System.out.println("rsa-p:" + rsa.primeP);
    System.out.println("rsa-q:" + rsa.primeQ);
    System.out.println("rsa-E:" + rsa.publicKeyE);
    System.out.println("rsa-N:" + rsa.publicKeyN);
    System.out.println("rsa-D:" + rsa.privateKeyD);

    EncryptedMessage = ec.encryptMessage(MessageForEncrypt,rsa);
    DecryptedMessage = ec.decryptMessage(EncryptedMessage, rsa);

    System.out.println("MessageForEncryption:"+ MessageForEncrypt.getMessage());
    System.out.println("DecryptedMessage    :"+ DecryptedMessage.getMessage());
    if(MessageForEncrypt.getMessage().intern()==DecryptedMessage.getMessage().intern()){
      System.out.println("Ok");
    }
    else{
      System.out.println("Ooh");
    }
  }

  public MessageForCommunication encryptMessage(MessageForCommunication message,RsaKeys rsa) {
    MessageForCommunication  midMessage = new MessageForCommunication ();
    midMessage.initializeMessageWithIntegerNumber(message.getNumberOfBlocks());

    int i;
    for(i = 0;i < message.getNumberOfBlocks();i++)
    {
    	midMessage.BigintegerBlocks[i] = encryptBlocks(message.BigintegerBlocks[i],rsa);
    }
    MessageForCommunication outputMessage = new MessageForCommunication();
    outputMessage.initializeMessageWithBigintegerArray(midMessage.BigintegerBlocks);
    return outputMessage; 
  } 

  public BigInteger encryptBlocks(BigInteger blocks, RsaKeys rsa) {
    BigInteger outputNumber = encryptAlgorithm(blocks, rsa);
    return outputNumber;
  }
	   
  public BigInteger encryptAlgorithm(BigInteger messageNumber, RsaKeys rsa) { 
    //  c = m^e mod n
    BigInteger outputNumber = BigInteger.ZERO;  
    outputNumber = messageNumber.pow(rsa.publicKeyE.intValue()).mod(rsa.publicKeyN);    
    return outputNumber;	  
  } 
	  
  public MessageForCommunication decryptMessage(MessageForCommunication message, RsaKeys rsa) {
    MessageForCommunication midMessage = new MessageForCommunication();
    midMessage.initializeMessageWithIntegerNumber(message.getNumberOfBlocks());
    
    int i;
    for(i=0 ; i < message.getNumberOfBlocks() ; i++)
    {
      midMessage.BigintegerBlocks[i] =  decryptAlgorithm(message.BigintegerBlocks[i],rsa);
    }
    MessageForCommunication decryptedMessage = new MessageForCommunication();
    decryptedMessage.initializeMessageWithBigintegerArray(midMessage.BigintegerBlocks);
    return decryptedMessage;	  
  } 
  
  public BigInteger decryptAlgorithm(BigInteger numberC, RsaKeys rsa ) { 	
	// m = c^d mod n
    BigInteger outputNumber = BigInteger.ZERO;  
    outputNumber = numberC.pow(rsa.privateKeyD.intValue()).mod(rsa.publicKeyN);
    return outputNumber;	  	  
  } 
		 
}
