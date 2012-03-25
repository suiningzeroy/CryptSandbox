package cryptography;

import java.math.*;
import cryptography.MessageForCommunicate;

import cryptography.RsaKeys;

public class EncryptionCommunication {

  public static void main(String[] args){
    EncryptionCommunication ecomm = new EncryptionCommunication();
    MessageForCommunicate MessageForEncrypt = new MessageForCommunicate("488262687");
    MessageForCommunicate EncryptedMessage = new MessageForCommunicate(MessageForEncrypt.getNumberOfBlocks());
    MessageForCommunicate DecryptedMessage = new MessageForCommunicate(MessageForEncrypt.getNumberOfBlocks());
    RsaKeys rsa = new RsaKeys();
    System.out.println("rsa-E:" + rsa.publicKeyE);
    System.out.println("rsa-N:" + rsa.publicKeyN);
    System.out.println("rsa-D:" + rsa.privateKeyD);

    EncryptedMessage = EncryptionCommunication.encryptMessage(MessageForEncrypt,rsa);
    DecryptedMessage = EncryptionCommunication.decryptMessage(EncryptedMessage, rsa);

    System.out.println("MessageForEncrypt:"+ MessageForEncrypt.getMessage());
    System.out.println("DecryptedMessage:"+ DecryptedMessage.getMessage());
    if(MessageForEncrypt.getMessage().intern()==DecryptedMessage.getMessage().intern()){
      System.out.println("Ok");
      }
    else{
      System.out.println("Ooh");
      }
  }

  public static MessageForCommunicate encryptMessage(MessageForCommunicate message,RsaKeys rsa){
	MessageForCommunicate  midMessage = new MessageForCommunicate (message.getNumberOfBlocks());

    int i;
    for(i = 0;i < message.getNumberOfBlocks();i++)
    {
    	midMessage.BigintegerBlocks[i] = encryptBlocks(message.BigintegerBlocks[i],rsa);
    }
    MessageForCommunicate outputMessage = new MessageForCommunicate(midMessage.BigintegerBlocks);
    return outputMessage; 
  } 

  public static BigInteger encryptBlocks(BigInteger blocks, RsaKeys rsa)
  {
	  BigInteger outputNumber = encryptAlgorithm(blocks, rsa);
	  return outputNumber;
  }
	   
  public static BigInteger encryptAlgorithm(BigInteger messageNumber, RsaKeys rsa){ 
    //  c = m^e mod n
    BigInteger outputNumber = BigInteger.ZERO;  
    outputNumber = messageNumber.pow(rsa.publicKeyE.intValue()).mod(rsa.publicKeyN);    
    return outputNumber;	  
  } 
	  
  public static MessageForCommunicate decryptMessage (MessageForCommunicate message, RsaKeys rsa){ 	  
    String[] decryptedBlocks = new String[message.getNumberOfBlocks()];
    MessageForCommunicate midMsf = new MessageForCommunicate(message.getNumberOfBlocks());
    
    int i;
    for(i=0 ; i < message.getNumberOfBlocks() ; i++)
    {
      midMsf.BigintegerBlocks[i] =  decryptAlgorithm(message.BigintegerBlocks[i],rsa);
    }
    MessageForCommunicate decryptedMessage = new MessageForCommunicate(midMsf.BigintegerBlocks);
    return decryptedMessage;	  
  } 
  
  public static BigInteger decryptAlgorithm(BigInteger numberC, RsaKeys rsa ){ 	
	// m = c^d mod n
    BigInteger outputNumber = BigInteger.ZERO;  
    outputNumber = numberC.pow(rsa.privateKeyD.intValue()).mod(rsa.publicKeyN);
    return outputNumber;	  	  
  } 
		 
}
