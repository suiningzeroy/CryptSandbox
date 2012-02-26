
import java.math.BigInteger;
import java.util.*;

public class DeffieHellmanKeysExchange {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		BigInteger OpenNumberforAlice = BigInteger.ZERO; 
		BigInteger OpenNumberforBob = BigInteger.ZERO; 
		BigInteger RandomforAlice = BigInteger.ZERO; 
		BigInteger RandomforBob =  BigInteger.ZERO; 
		BigInteger PrimeNmber =  BigInteger.ZERO; 
		BigInteger OpenG =  BigInteger.ZERO; 
		BigInteger ExchangeForAlice = BigInteger.ZERO; 
		BigInteger ExchangeForBob = BigInteger.ZERO; 
		
		
		RandomforAlice = DeffieHellman_RandomGenerate();
		RandomforBob =   DeffieHellman_RandomGenerate();
		OpenG        =   DeffieHellman_RandomGenerate();
		PrimeNmber = DeffieHellman_PrimerGenerate();
		
		OpenNumberforAlice =  DeffieHellman_OpenNumberCompute(RandomforAlice,OpenG,PrimeNmber);
		OpenNumberforBob   =  DeffieHellman_OpenNumberCompute(RandomforBob,OpenG,PrimeNmber);
		
		System.out.println("RandomforAlice:" + RandomforAlice + ";RandomforBob:" + RandomforBob+ ";OpenG:" + OpenG+";PrimeNmber:" + PrimeNmber);
		System.out.println("OpenNumberforAlice:" + OpenNumberforAlice + ";OpenNumberforBob:"+OpenNumberforBob);
		
		ExchangeForAlice = DeffieHellman_exchageCompute(RandomforAlice,OpenNumberforBob,PrimeNmber);
		ExchangeForBob = DeffieHellman_exchageCompute(RandomforBob,OpenNumberforAlice,PrimeNmber);
		
		System.out.println("ExchangeForAlice:" + ExchangeForAlice + ";ExchangeForBob:" + ExchangeForBob);
		
		
		System.out.println("end");
		
		
	
	};
	
	private static BigInteger DeffieHellman_exchageCompute(BigInteger RandomA,BigInteger OpenNum,BigInteger PrimerNum)
	{
		BigInteger ExchangeKey = BigInteger.ZERO; 
		
		ExchangeKey = OpenNum.modPow(RandomA,PrimerNum);	
		
		return ExchangeKey;
	};
	
	private static BigInteger DeffieHellman_OpenNumberCompute(BigInteger RandomA,BigInteger GroudNum,BigInteger PrimerNum)
	{
		BigInteger OpenKEY = BigInteger.ZERO;
		OpenKEY = GroudNum.modPow(RandomA,PrimerNum);	
		return OpenKEY;
	};
	
	private static BigInteger DeffieHellman_RandomGenerate()
	{
		int BitlengthofRandomNumber = 100;
		BigInteger Minrandom = new BigInteger("100");
		Random RDM = new java.util.Random();
		BigInteger RandomNum = new BigInteger(BitlengthofRandomNumber,10,RDM);
		RandomNum =RandomNum.add(Minrandom);
		return RandomNum;
	};
	
	private static BigInteger DeffieHellman_PrimerGenerate()
	{
		int BitlengthofPrimeNumber = 10;
		Random RDM = new java.util.Random();
		BigInteger	PrimeNum = new BigInteger(BitlengthofPrimeNumber,10,RDM);	
		PrimeNum = PrimeNum.nextProbablePrime();
		return PrimeNum;
	};
	
	
}
