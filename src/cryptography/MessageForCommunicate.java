package cryptography;

import java.math.BigInteger;

public class MessageForCommunicate {
  private int NumberOfCharsPerBlock = 3;
  private int NumberOfBlocks;
  public String MessageString = new String();
  public String[] Blocks;
  public BigInteger[] BigintegerBlocks;

  public MessageForCommunicate(int number){
    this.setNumberOfBlocks(number);
    Blocks = new String[NumberOfBlocks];
    BigintegerBlocks = new BigInteger[NumberOfBlocks];
    this.MessageString = "";
  }
  public MessageForCommunicate(String Message){
    this.MessageString = Message;
    NumberOfBlocks = generateNumberOfBlocks(Message);
    Blocks = new String[NumberOfBlocks];
    BigintegerBlocks = new BigInteger[NumberOfBlocks];
    Blocks = splitMessageIntoBlocks(Message);
    BigintegerBlocks = this.transformStringBlocksToBigIntegerBlocks(Blocks);
  }
  
  public MessageForCommunicate(String[] blocks){
    Blocks = blocks;
    NumberOfBlocks = blocks.length;
    this.MessageString = this.recoverMessageFromBlocks(blocks);
    BigintegerBlocks = new BigInteger[NumberOfBlocks];
    BigintegerBlocks = this.transformStringBlocksToBigIntegerBlocks(Blocks);
  }
  
  public MessageForCommunicate(BigInteger[] blocksOfBigInteger){
    NumberOfBlocks = blocksOfBigInteger.length;
    BigintegerBlocks = new BigInteger[NumberOfBlocks];
    BigintegerBlocks = blocksOfBigInteger; 
    Blocks = new String[NumberOfBlocks];
    Blocks = this.transformBigIntegerBlocksToStringBlocks(blocksOfBigInteger);
    this.MessageString = this.recoverMessageFromBlocks(Blocks);
  }
  
  public void setNumberOfBitsPerBlocks(int numbers){
    this.NumberOfCharsPerBlock = numbers;
  }
  
  public int getNumberOfBitsPerBlocks(){
    int number = this.NumberOfCharsPerBlock;
    return number;
  }
  
  public String getMessage(){
  String message = this.MessageString;
    return message;
  }
  public void setNumberOfBlocks(int numbers){
  this.NumberOfBlocks = numbers;
  }
  
  public int getNumberOfBlocks(){
    int number = this.NumberOfBlocks;
    return number;
  } 
  public int generateNumberOfBlocks(String Message)
  {
    int numberOfBlocks = Message.length() / NumberOfCharsPerBlock;
    int remainder = Message.length() % NumberOfCharsPerBlock;
    
    if(remainder == 0){
    }
    else{numberOfBlocks = numberOfBlocks + 1; 
    }
    return numberOfBlocks;
  }
  
  private String [] splitMessageIntoBlocks(String message){  
    int endOfMessage = message.length();
    String stringGroup[] = new String[NumberOfBlocks];

    int j = 0,startPoint;
    for(j = 0; j < NumberOfBlocks ; j++){
      startPoint = j*3;
      if(j == NumberOfBlocks-1){
        stringGroup[j] = cutOutBlocksFromMessage(startPoint,endOfMessage,message);
      }
      else{
        stringGroup[j] = cutOutBlocksFromMessage(startPoint, startPoint+3,message);
      }
    }
    return stringGroup;  
  }
  
  public String cutOutBlocksFromMessage(int start, int end, String message)
  {
    String blocks;
    blocks =  message.substring(start, end);
    return blocks;
  }
  public String[] transformBigIntegerBlocksToStringBlocks(BigInteger[] inputBigIntegerBlocks){
    int lengthOfBlocks = inputBigIntegerBlocks.length;
    String[] outputStringBlocks = new String[lengthOfBlocks];
    int i = 0;
    for(i=0;i<lengthOfBlocks;i++){
      outputStringBlocks[i]=this.transformBigIntegerToString(inputBigIntegerBlocks[i]);
    }
    return outputStringBlocks;
  }
  
  public BigInteger[] transformStringBlocksToBigIntegerBlocks(String[] inputStringBlocks){
    int lengthOfBlocks = inputStringBlocks.length;
    BigInteger[] outputBigIntegerBlocks = new BigInteger[lengthOfBlocks];
    int i = 0;
    for(i=0;i<lengthOfBlocks;i++){
    outputBigIntegerBlocks[i]=this.transformStringToBigInteger(inputStringBlocks[i]);
    }
    return outputBigIntegerBlocks;
  }
  public  BigInteger transformStringToBigInteger(String inputString){
    long midLongNumber =  transformStringToLong(inputString);
    BigInteger outputNumber = transformLongToBigInteger(midLongNumber);
    return outputNumber;
  }

  public  BigInteger transformLongToBigInteger(long inputNumber)
  {
    BigInteger outputNumber = BigInteger.valueOf(inputNumber);
    return outputNumber;
  }
  
  public  long transformStringToLong(String inputString)
  {
    long outputNumber =  Long.parseLong(inputString);
    return outputNumber;
  }
  
  public  String transformBigIntegerToString(BigInteger inputNumber)
  {
    String outputString =  inputNumber.toString();
    return outputString;
  }
  
  public  String recoverMessageFromBlocks(String[] inputBlocks){
    String message = new String() ;
    int i;
    for(i=0 ; i < this.NumberOfBlocks ; i++)
    {
      inputBlocks[i] = fillBlocks(inputBlocks[i]);
      message = addBlockToMessage(message,inputBlocks[i]);
    }   
    return message;  
  }
  public  String fillBlocks(String inputBlocks){
    String filledBlocks = new String();
    if(isBlocksShorterThanSystemDefined(inputBlocks)){
      filledBlocks = addZeroToShortBlocksOnLeft(inputBlocks);
    }
    else{
      filledBlocks = inputBlocks;
    }
    return filledBlocks;
  }
  public String addBlockToMessage(String message, String block){
  message = message + block;
  return message;
  }
  public boolean isBlocksShorterThanSystemDefined(String blocks)
  {
    if(blocks.length() < this.NumberOfCharsPerBlock){
      return true;
    }
    else{
      return false;
    }
  };

  public String addZeroToShortBlocksOnLeft(String blocks){
    int numberOfZeroShouldAdd = 0;
    numberOfZeroShouldAdd = this.NumberOfCharsPerBlock - blocks.length();
    int i = 0;
    for(i = 0; i < numberOfZeroShouldAdd; i++)
    {
      blocks = "0"+blocks;
    } 
    return blocks;
  }
  
}
