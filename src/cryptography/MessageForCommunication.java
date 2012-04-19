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
    MessageString = null;
  }
  
  public void initializeMessageWithString(String Message) {
    MessageString = Message;
    NumberOfBlocks = computeNumberOfBlocks(Message);
    Blocks = splitMessageIntoBlocks(Message);
    BigintegerBlocks = transformStringBlocksToBigIntegerBlocks(Blocks);
  }
  
  public void initializeMessageWithStringArray(String[] blocks) {
    Blocks = blocks;
    NumberOfBlocks = blocks.length;
    MessageString = rebuildMessageFromBlocks(blocks);
    BigintegerBlocks = transformStringBlocksToBigIntegerBlocks(Blocks);
  }
  
  public void initializeMessageWithBigintegerArray(BigInteger[] blocksOfBigInteger) {
    NumberOfBlocks = blocksOfBigInteger.length;
    BigintegerBlocks = blocksOfBigInteger; 
    Blocks = transformBigIntegerBlocksToStringBlocks(blocksOfBigInteger);
    MessageString = rebuildMessageFromBlocks(Blocks);
  }
  
  public void setNumberOfBitsPerBlocks(int numbers) {
    this.NumberOfCharsPerBlock = numbers;
  }
  
  public int getNumberOfBitsPerBlocks() {
    return this.NumberOfCharsPerBlock;
  }
  
  public String getMessage() {
    return this.MessageString;
  }
  
  public void setNumberOfBlocks(int numbers) {
    this.NumberOfBlocks = numbers;
  }
  
  public int getNumberOfBlocks() {
    return this.NumberOfBlocks;
  } 
  
  public int computeNumberOfBlocks(String Message) {
    int numberOfBlocks = Message.length() / NumberOfCharsPerBlock;
    int remainder = Message.length() % NumberOfCharsPerBlock;
    
    if(remainder == 0){
      return numberOfBlocks;
    }
    else{
      return numberOfBlocks + 1; 
    }
  }
  
  public String [] splitMessageIntoBlocks(String message) {  
    int endpointOfMessage = message.length();
    String stringBlocks[] = new String[NumberOfBlocks];

    int startPoint;
    for(int j = 0; j < NumberOfBlocks ; j++){
      startPoint = j*3;
      if(j == NumberOfBlocks-1){
        stringBlocks[j] = message.substring(startPoint,endpointOfMessage);
      }
      else{
        stringBlocks[j] = message.substring(startPoint, startPoint+3);
      }
    }
    return stringBlocks;  
  }
  
  public String[] transformBigIntegerBlocksToStringBlocks(BigInteger[] inputBigIntegerBlocks) {
    String[] outputStringBlocks = new String[inputBigIntegerBlocks.length];
    int i = 0;
    for(i=0;i<inputBigIntegerBlocks.length;i++){
      outputStringBlocks[i]=inputBigIntegerBlocks[i].toString();
    }
    return outputStringBlocks;
  }
  
  public BigInteger[] transformStringBlocksToBigIntegerBlocks(String[] inputStringBlocks) {
    BigInteger[] outputBigIntegerBlocks = new BigInteger[inputStringBlocks.length];
    int i = 0;
    for(i=0;i<inputStringBlocks.length;i++){
    outputBigIntegerBlocks[i] = transformStringToBigInteger(inputStringBlocks[i]);
    }
    return outputBigIntegerBlocks;
  }
  
  public  BigInteger transformStringToBigInteger(String inputString) {
    return BigInteger.valueOf(Long.parseLong(inputString));
  }
  
  public  String rebuildMessageFromBlocks(String[] inputBlocks){
    String message = new String() ;
    int i;
    for(i=0 ; i < this.NumberOfBlocks ; i++)
    {
      inputBlocks[i] = completeBlocks(inputBlocks[i]);
      message = message + inputBlocks[i];
    }   
    return message;  
  }
  
  public  String completeBlocks(String inputBlocks) {
    if(isBlocksShorterThanSystemDefined(inputBlocks)){
      return addZeroToShortBlocksOnLeft(inputBlocks);
    }
    else{
      return inputBlocks;
    }
  }
  
  public boolean isBlocksShorterThanSystemDefined(String blocks) {
    if(blocks.length() < NumberOfCharsPerBlock){
      return true;
    }
    else{
      return false;
    }
   
  };

  public String addZeroToShortBlocksOnLeft(String blocks) {
    int numberOfZeroShouldAdd = NumberOfCharsPerBlock - blocks.length();
    int i = 0;
    for(i = 0; i < numberOfZeroShouldAdd; i++){
      blocks = "0"+blocks;
    } 
    return blocks;
  }
  
}