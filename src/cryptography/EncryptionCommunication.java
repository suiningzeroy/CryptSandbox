package cryptography;

import static org.junit.Assert.assertEquals;

import java.math.*;

import cryptography.Rsa;

public class EncryptionCommunication {
  public static int NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED = 7;
  public static int NUMBER_OF_BITS_PER_BLOCK = 3;
  public static int BIT_LENGTH_OF_RANDOM_MESSAGE = 50;
  public static int NumberOfBlocks ;
 
  public static void main(String[] args){
	  EncryptionCommunication ecomm = new EncryptionCommunication();
	  String testMessage = new String("6882326879666680032");
	  String[] result = new String[6];
	  result = ecomm.splitMessageIntoBlocks(testMessage);
  }

  public static String[] encryptMessage(String message,Rsa rsa){ 	      
    NumberOfBlocks =   generateNumberOfBlocks(message.length());
    String [] blocks = new String[NumberOfBlocks];
    BigInteger[] encryptedBlocks = new BigInteger[NumberOfBlocks];
    
    blocks = splitMessageIntoBlocks(message);
    int i;
    for(i = 0,i < NumberOfBlocks, i++)
    {
    	encryptedBlocks[i] = encryptBlocks(blocks[i],rsa);
    }
    
    return splitedMessage;	    
  } 
  public static BigInteger encryptBlocks(String blocks, Rsa rsa)
  {
	  BigInteger outputNumber = decryptAlgorithm(blocks, rsa)
  }
public static String [] splitMessageIntoBlocks(String message){	  
    int endOfMessage = message.length();
    String stringGroup[] = new String[NumberOfBlocks];
    long longGroup[] = new long[NumberOfBlocks];
    BigInteger bigIntegerGroup[] = new BigInteger[NumberOfBlocks];

    int j = 0,startPoint;
    for(j = 0; j < NumberOfBlocks ; j++){
      startPoint = j*3;
      if(j == NumberOfBlocks-1){
        stringGroup[j] = cutoutBlocksFromMessage(startPoint,endOfMessage,message);
        longGroup[j] = transformStringToLong(stringGroup[j]);
        bigIntegerGroup[j] = transformLongToBigInteger(longGroup[j]);
      }
      else{
        stringGroup[j] = cutoutBlocksFromMessage(startPoint, startPoint+3,message);
        longGroup[j] = transformStringToLong(stringGroup[j]);
        bigIntegerGroup[j] = transformLongToBigInteger(longGroup[j]);
      }
    }
    return stringGroup;	  
  }
  public static int generateNumberOfBlocks(int lengthOfMessage)
  {
    int numberOfBlocks =lengthOfMessage / NUMBER_OF_BITS_PER_BLOCK;
    int remainder = lengthOfMessage % NUMBER_OF_BITS_PER_BLOCK;
    
    if(remainder == 0){
    }
    else{
      numberOfBlocks = numberOfBlocks + 1; 
    }
    return numberOfBlocks;
  }
  
  public static String cutoutBlocksFromMessage(int start, int end, String message)
  {
    String blocks;
    blocks =  message.substring(start, end);
    return blocks;
  }
  
  public static BigInteger transformLongToBigInteger(long inputNumber)
  {
    BigInteger outputNumber = BigInteger.valueOf(inputNumber);
    return outputNumber;
  }
  
  public static long transformStringToLong(String inputString)
  {
    long outputNumber =  Long.parseLong(inputString);
    return outputNumber;
  }
	   
  public static BigInteger encryptAlgorithm(BigInteger message, Rsa rsa){ 
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
