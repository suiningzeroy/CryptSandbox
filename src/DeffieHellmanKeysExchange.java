
import java.math.BigInteger;
import java.util.*;

public class DeffieHellmanKeysExchange {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		BigInteger openNumberforAlice = BigInteger.ZERO; 
		BigInteger openNumberforBob = BigInteger.ZERO; 
		BigInteger randomforAlice = BigInteger.ZERO; 
		BigInteger randomforBob =  BigInteger.ZERO; 
		BigInteger primeNmber =  BigInteger.ZERO; 
		BigInteger openG =  BigInteger.ZERO; 
		BigInteger exchangeForAlice = BigInteger.ZERO; 
		BigInteger exchangeForBob = BigInteger.ZERO; 
		
		
		
		System.out.println("The  Dillie-Hellman :");
		randomforAlice = generateDiffieHellman_Random();
		randomforBob =   generateDiffieHellman_Random();
		openG        =   generateDiffieHellman_Random();
		primeNmber = generateDiffieHellman_prime();
		
		openNumberforAlice =  computeDiffieHellman_openNumber(randomforAlice,openG,primeNmber);
		openNumberforBob   =  computeDiffieHellman_openNumber(randomforBob,openG,primeNmber);
		
		System.out.println("randomforAlice:" + randomforAlice + ";randomforBob:" + randomforBob+ ";openG:" + openG+";primeNmber:" + primeNmber);
		System.out.println("openNumberforAlice:" + openNumberforAlice + ";openNumberforBob:"+openNumberforBob);
		
		exchangeForAlice = computeDiffieHellman_exchangeKey(randomforAlice,openNumberforBob,primeNmber);
		exchangeForBob = computeDiffieHellman_exchangeKey(randomforBob,openNumberforAlice,primeNmber);
		
		System.out.println("exchangeForAlice:" + exchangeForAlice + ";exchangeForBob:" + exchangeForBob);
		
		System.out.println("The variant of Dillie-Hellman :");
		
		exchangeForAlice = computeDiffieHellman_exchangeKey(randomforAlice,openG,primeNmber);
		openNumberforBob   =  computeDiffieHellman_openNumber(randomforBob,openG,primeNmber);
		openNumberforAlice =  computeDiffieHellman_openNumber(randomforAlice,openNumberforBob,primeNmber);

		
        BigInteger randomforBobpow = new BigInteger("1").divide(randomforBob);
        System.out.println("randomforBob" + randomforBob + ";randomforBobpow:" + randomforBobpow + "QQQQ:" + randomforAlice.divide(randomforBob));
		
		exchangeForBob = computeDiffieHellman_exchangeKey(randomforAlice.divide(randomforBob),openNumberforBob,primeNmber);
		
		System.out.println("randomforAlice:" + randomforAlice + ";randomforBob:" + randomforBob+ ";openG:" + openG+";primeNmber:" + primeNmber);
		System.out.println("exchangeForAlice:" + exchangeForAlice);	
		System.out.println("openNumberforAlice:" + openNumberforAlice + ";openNumberforBob:"+openNumberforBob);
		System.out.println("exchangeForBob:" + exchangeForBob);
		
		
		
		System.out.println("end");
		
		
	
	};
	
	private static BigInteger computeDiffieHellman_exchangeKey(BigInteger randomA,BigInteger openNum,BigInteger primeNum)
	{
		BigInteger exchangeKey = BigInteger.ZERO; 
		
		exchangeKey = openNum.modPow(randomA,primeNum);	
		
		return exchangeKey;
	};
	
	
	private static BigInteger computeDiffieHellman_openNumber(BigInteger randomA,BigInteger groudNum,BigInteger primeNum)
	{
		BigInteger openKey = BigInteger.ZERO;
		openKey = groudNum.modPow(randomA,primeNum);	
		return openKey;
	};
	
	private static BigInteger generateDiffieHellman_Random()
	{
		int bitlengthofRandomNumber = 10;
		BigInteger minRandom = new BigInteger("100");
		Random RDM = new java.util.Random();
		BigInteger RandomNum = new BigInteger(bitlengthofRandomNumber,10,RDM);
		RandomNum =RandomNum.add(minRandom);
		return RandomNum;
	};
	
	private static BigInteger generateDiffieHellman_prime()
	{
		int bitlengthofRandomNumber = 10;
		Random rdm = new java.util.Random();
		BigInteger	primeNum = new BigInteger(bitlengthofRandomNumber,10,rdm);	
		primeNum = primeNum.nextProbablePrime();
		return primeNum;
	};

	
	
}
