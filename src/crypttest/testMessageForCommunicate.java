package crypttest;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import cryptography.MessageForCommunication;

public class testMessageForCommunicate {
  @Test
  public void testGenerateNumberOfBlocks() {
    MessageForCommunication mfc = new MessageForCommunication();
    mfc.initializeMessageWithString("6882326879666680032");
    int expResult = 7;
    int result = mfc.computeNumberOfBlocks(mfc.getMessage());
    assertEquals(expResult,result);
  }
  
  @Test
  public void testSplitMessageIntoBlocks() {
    MessageForCommunication testMessage = new MessageForCommunication();
    testMessage.initializeMessageWithString("688232687966668003");
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
  public void testTransformStringToBigInteger() {
    MessageForCommunication mfc = new MessageForCommunication();
    mfc.initializeMessageWithString("688232687966668003");
    BigInteger expResult = new BigInteger("688232687966668003");
    assertEquals(expResult,mfc.transformStringToBigInteger(mfc.getMessage()));
  }

@Test
  public void testTransformBigIntegerBlocksToStringBlocks() {
     MessageForCommunication mfc = new MessageForCommunication();
     mfc.initializeMessageWithString("688232687966668300");
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
  public void testTransformStringBlocksToBigIntegerBlocks() {
    MessageForCommunication mfc = new MessageForCommunication();
    mfc.initializeMessageWithString("688232687966668300");
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
  public void testRecoverMessageFromBlocks() {
    MessageForCommunication mfc = new MessageForCommunication();
    mfc.initializeMessageWithString("688232687966668003");
    String[] testBlocks = new String[6];
    testBlocks[0] = "688";
    testBlocks[1] = new String("232");
    testBlocks[2] = new String("687");
    testBlocks[3] = new String("966");
    testBlocks[4] = new String("668");
    testBlocks[5] = new String("003");
    String expResult = mfc.getMessage();
    String result = mfc.rebuildMessageFromBlocks(testBlocks);
    assertEquals(expResult,result);
  }
  
  @Test
  public void testFillBlocks() {
    MessageForCommunication mfc = new MessageForCommunication();
    mfc.initializeMessageWithString("23");
    String expResult = new String("023");
    String result = mfc.completeBlocks(mfc.getMessage());
    assertEquals(expResult,result);
  }
  
  @Test
  public void testIsBlocksShorterThanSystemDefined() {
    MessageForCommunication mfc = new MessageForCommunication();
    mfc.initializeMessageWithString("3");
    boolean expResult = true;
    boolean result = mfc.isBlocksShorterThanSystemDefined(mfc.getMessage());
    assertEquals(expResult,result);
  }
  
  @Test
  public void testAddZeroToShortBlocks() {
    MessageForCommunication mfc = new MessageForCommunication();
    mfc.initializeMessageWithString("3");
    String expResult = "003";
    String result = mfc.addZeroToShortBlocksOnLeft(mfc.getMessage());
    assertEquals(expResult,result);
  }
}
