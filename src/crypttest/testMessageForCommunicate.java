package crypttest;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Ignore;
import org.junit.Test;

import cryptography.EncryptionCommunication;
import cryptography.MessageForCommunicate;

public class testMessageForCommunicate {
  @Test
  public void test(){
    MessageForCommunicate testMessage = new MessageForCommunicate("688232687966668003");
    System.out.println("MESSAGESTRING:" + testMessage.getMessage());
    int i;
    for(i=0;i<testMessage.getNumberOfBlocks();i++)
    {
      System.out.println("blocks"+ "[" +i+ "]" + ":"+testMessage.Blocks[i]);
      System.out.println("BigIntegerblocks"+ "[" +i+ "]" + ":"+testMessage.BigintegerBlocks[i]);
    }
  }
  @Test
  public void testGenerateNumberOfBlocks(){
    MessageForCommunicate mfc = new MessageForCommunicate("6882326879666680032");
    int expResult = 7;
    int result = mfc.generateNumberOfBlocks(mfc.getMessage());
    assertEquals(expResult,result);
  }
  
  @Test
  public void testSplitMessageIntoBlocks(){
	MessageForCommunicate testMessage = new MessageForCommunicate("688232687966668003");
    String[] result = new String[testMessage.getNumberOfBlocks()];
    String[] expResult = new String[testMessage.getNumberOfBlocks()];

    expResult[0] = "688";
    expResult[1] = new String("232");
    expResult[2] = new String("687");
    expResult[3] = new String("966");
    expResult[4] = new String("668");
    expResult[5] = new String("003");
    
    result = testMessage.Blocks;
    assertEquals(expResult, result);
  }
  @Test
  public void testCutOutBlocksFromMessage(){
    MessageForCommunicate mfc = new MessageForCommunicate("688232687966668003");
    String expResult = "268";
    String result = mfc.cutOutBlocksFromMessage(5,8, mfc.getMessage());
    assertEquals(expResult,result);
  }
  @Test
  public void testTransformStringToBigInteger(){
    MessageForCommunicate mfc = new MessageForCommunicate("688232687966668003");
    BigInteger expResult = new BigInteger("688232687966668003");
    assertEquals(expResult,mfc.transformStringToBigInteger(mfc.getMessage()));
  }
  @Test
  public void testTransformLongToBigInteger(){
    MessageForCommunicate mfc = new MessageForCommunicate("688232687966668003");
    BigInteger expResult = new BigInteger("688232687966668003");
    long longNumber = Long.parseLong(mfc.getMessage());
    assertEquals(expResult,mfc.transformLongToBigInteger(longNumber));
  }
  @Test
  public void testTransformStringToLong(){
    MessageForCommunicate mfc = new MessageForCommunicate("68823268");
    long expResult = 68823268;
    assertEquals(expResult,mfc.transformStringToLong(mfc.getMessage()));

  }
  @Test
  public void testTransformBigIntegerToString(){
    MessageForCommunicate mfc = new MessageForCommunicate("688232687966668003");
    BigInteger testNumber = new BigInteger("688232687966668003");
    String expResult = new String("688232687966668003");
    assertEquals(expResult,mfc.transformBigIntegerToString(testNumber));
  }

@Test
  public void testTransformBigIntegerBlocksToStringBlocks()
  {
     MessageForCommunicate mfc = new MessageForCommunicate("688232687966668300");
     BigInteger[] testBlocks = new BigInteger[6];
     testBlocks[0] = new BigInteger("688");
     testBlocks[1] = new BigInteger("232");
     testBlocks[2] = new BigInteger("687");
     testBlocks[3] = new BigInteger("966");
     testBlocks[4] = new BigInteger("668");
     testBlocks[5] = new BigInteger("300");
     String[] expResult = mfc.Blocks;
     String[] result =mfc.transformBigIntegerBlocksToStringBlocks(testBlocks);
     assertEquals(expResult,result);
  }

  @Test
  public void testTransformStringBlocksToBigIntegerBlocks()
  {
    MessageForCommunicate mfc = new MessageForCommunicate("688232687966668300");
    BigInteger[] expResult = new BigInteger[6];
    expResult[0] = new BigInteger("688");
    expResult[1] = new BigInteger("232");
    expResult[2] = new BigInteger("687");
    expResult[3] = new BigInteger("966");
    expResult[4] = new BigInteger("668");
    expResult[5] = new BigInteger("300");
    String[] testBlocks = mfc.Blocks;
    BigInteger[] result =mfc.transformStringBlocksToBigIntegerBlocks(testBlocks);
    assertEquals(expResult,result);
  }
  @Test
  public void testRecoverMessageFromBlocks(){
    MessageForCommunicate mfc = new MessageForCommunicate("688232687966668003");
    String[] testBlocks = new String[6];
    testBlocks[0] = "688";
    testBlocks[1] = new String("232");
    testBlocks[2] = new String("687");
    testBlocks[3] = new String("966");
    testBlocks[4] = new String("668");
    testBlocks[5] = new String("003");
    String expResult = mfc.getMessage();
    String result = mfc.recoverMessageFromBlocks(testBlocks);
    assertEquals(expResult,result);
  }
  @Test
  public void testFillBlocks(){
    MessageForCommunicate mfc = new MessageForCommunicate("23");
    String expResult = new String("023");
    String result = mfc.fillBlocks(mfc.getMessage());
    assertEquals(expResult,result);
  }
  @Test
  public void testIsBlocksShorterThanSystemDefined(){
    MessageForCommunicate mfc = new MessageForCommunicate("3");
    boolean expResult = true;
    boolean result = mfc.isBlocksShorterThanSystemDefined(mfc.getMessage());
    assertEquals(expResult,result);
  }
  @Test
  public void testAddZeroToShortBlocks(){
    MessageForCommunicate mfc = new MessageForCommunicate("3");
    String expResult = "003";
    String result = mfc.addZeroToShortBlocksOnLeft(mfc.getMessage());
    assertEquals(expResult,result);
  }
}
