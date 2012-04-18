package cryptography;

import java.math.BigInteger;

public class MessageForCommunication {
  private int NumberOfCharsPerBlock = 3;
  private int NumberOfBlocks;
  public String MessageString = new String();
  public String[] Blocks;
  public BigInteger[] BigintegerBlocks;
  
  public MessageForCommunication() {
  }
  
  public void initializeMessageWithIntegerNumber(int number) {
    setNumberOfBlocks(number);
    Blocks = new String[NumberOfBlocks];
    BigintegerBlocks = new BigInteger[NumberOfBlocks];
    MessageString = "";
  }
  
  public void initializeMessageWithString(String Message) {
    MessageString = Message;
    NumberOfBlocks = computeNumberOfBlocks(Message);
    Blocks = new String[NumberOfBlocks];
    BigintegerBlocks = new BigInteger[NumberOfBlocks];
    Blocks = splitMessageIntoBlocks(Message);
    BigintegerBlocks = transformStringBlocksToBigIntegerBlocks(Blocks);
  }
  
  public void initializeMessageWithStringArray(String[] blocks) {
    Blocks = blocks;
    NumberOfBlocks = blocks.length;
    MessageString = rebuildMessageFromBlocks(blocks);
    BigintegerBlocks = new BigInteger[NumberOfBlocks];
    BigintegerBlocks = transformStringBlocksToBigIntegerBlocks(Blocks);
  }
  
  public void initializeMessageWithBigintegerArray(BigInteger[] blocksOfBigInteger) {
    NumberOfBlocks = blocksOfBigInteger.length;
    BigintegerBlocks = new BigInteger[NumberOfBlocks];
    BigintegerBlocks = blocksOfBigInteger; 
    Blocks = new String[NumberOfBlocks];
    Blocks = transformBigIntegerBlocksToStringBlocks(blocksOfBigInteger);
    MessageString = rebuildMessageFromBlocks(Blocks);
  }
  
  public void setNumberOfBitsPerBlocks(int numbers) {
    NumberOfCharsPerBlock = numbers;
  }
  
  public int getNumberOfBitsPerBlocks() {
    return NumberOfCharsPerBlock;
  }
  
  public String getMessage() {
    return MessageString;
  }
  public void setNumberOfBlocks(int numbers) {
    NumberOfBlocks = numbers;
  }
  
  public int getNumberOfBlocks() {
    return NumberOfBlocks;
  } 
  
  public int computeNumberOfBlocks(String Message) {
    if(Message.length() % NumberOfCharsPerBlock == 0) {
      return Message.length() / NumberOfCharsPerBlock;
    }
    else {
      return Message.length() / NumberOfCharsPerBlock + 1;
    }
  }
  
  public String [] splitMessageIntoBlocks(String message) {  
    int endPointOfMessage = message.length();
    String blocks[] = new String[NumberOfBlocks];

    int j = 0;
    for(j = 0; j < NumberOfBlocks ; j++) {
      int startPoint = j*3;
      if(j == NumberOfBlocks-1) {
        blocks[j] = message.substring(startPoint,endPointOfMessage);
      }
      else {
        blocks[j] = message.substring(startPoint, startPoint+3);
      }
    }
    return blocks;  
  }
  
  public String[] transformBigIntegerBlocksToStringBlocks(BigInteger[] inputBigIntegerBlocks) {
    String[] outputStringBlocks = new String[inputBigIntegerBlocks.length];
    int i = 0;
    for(i=0;i<inputBigIntegerBlocks.length;i++){
      outputStringBlocks[i]= inputBigIntegerBlocks[i].toString();
    }
    return outputStringBlocks;
  }
  
  public BigInteger[] transformStringBlocksToBigIntegerBlocks(String[] inputStringBlocks) {
    BigInteger[] outputBigIntegerBlocks = new BigInteger[inputStringBlocks.length];
    int i = 0;
    for(i=0;i<inputStringBlocks.length;i++) {
      outputBigIntegerBlocks[i] = transformStringToBigInteger(inputStringBlocks[i]);
    }
    return outputBigIntegerBlocks;
  }
  
  public BigInteger transformStringToBigInteger(String inputString) {
    long midLongNumber = Long.parseLong(inputString);
    return BigInteger.valueOf(midLongNumber);
  }
  
  public String rebuildMessageFromBlocks(String[] inputBlocks){
    String message = new String() ;
    int i;
    for(i=0 ; i < NumberOfBlocks ; i++) {
      inputBlocks[i] = completeBlocks(inputBlocks[i]);
      message = message + inputBlocks[i];
    }   
    return message;  
  }
  
  public String completeBlocks(String inputBlocks) {
    if(isBlocksShorterThanSystemDefined(inputBlocks)) {
      return addZeroToShortBlocksOnLeft(inputBlocks);
    }
    else {
      return inputBlocks;
    }
  }
  
  public boolean isBlocksShorterThanSystemDefined(String blocks) {
    if(blocks.length() < NumberOfCharsPerBlock) {
      return true;
    }
    else {
      return false;
    }
  };

  public String addZeroToShortBlocksOnLeft(String blocks) {
    int numberOfZeroShouldAdd = NumberOfCharsPerBlock - blocks.length();
    int i = 0;
    for(i = 0; i < numberOfZeroShouldAdd; i++) {
      blocks = "0"+blocks;
    } 
    return blocks;
  }
  
}
