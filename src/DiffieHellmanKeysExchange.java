
import java.math.BigInteger;
import java.util.*;

public class DiffieHellmanKeysExchange {
	
	//public static BigInteger randomNumberx = BigInteger.ZERO; 
	//public static BigInteger randomNumbery =  BigInteger.ZERO; 
	public static BigInteger publicRandomprimeNumbern =  BigInteger.ZERO; 
	public static BigInteger publicRandomg =  BigInteger.ZERO; 
	
	static int bitlengthofRandomNumber = 30;
	static int bitlengthofNumberg = 7;
	static BigInteger minRandom = new BigInteger("100"); 
	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		
		
		UserWantToExchange alice = new UserWantToExchange();
		UserWantToExchange bob = new UserWantToExchange();
		
		
		
		establishedConnet(alice,bob);
		
	
	};
	
	public static void establishedConnet(UserWantToExchange userA,UserWantToExchange userB)
	{
		
		generatepublicNumber();
		
		broadcastPublicNumber(userA, userB);
		generatePrivateNumber(userA);
		generatePrivateNumber(userB);
		sendPublicNumberX(userA,userB);
		if(exchangeCheck(userA,userB))
			{System.out.println("success");
			}
		else
			{System.out.println("fail");
			};
	};
	
	private static void generatepublicNumber()
	{		
		publicRandomprimeNumbern = generateDiffieHellmanprime(bitlengthofRandomNumber);
        publicRandomg =   generateDiffieHellmanNumberg(bitlengthofNumberg,publicRandomprimeNumbern);
        System.out.println("publicRandomprimeNumbern:" + publicRandomprimeNumbern);
        System.out.println("publicRandomg:" + publicRandomg);
	};
	
	private static void broadcastPublicNumber(UserWantToExchange a,UserWantToExchange b)
	{
		a.setNumberg(publicRandomg);
		a.setnumbern(publicRandomprimeNumbern);
		b.setNumberg(publicRandomg);
		b.setnumbern(publicRandomprimeNumbern);
	};
	
	private static void generatePrivateNumber(UserWantToExchange a)
	{
	   BigInteger randomNumberx = BigInteger.ZERO;
	   
	   randomNumberx =   generateDiffieHellmanRandom(bitlengthofRandomNumber,minRandom);
       a.setrandomNumber(randomNumberx);
       System.out.println("randomNumberx:" + randomNumberx);
       
       
	};
	
	private static void sendPublicNumberX(UserWantToExchange a,UserWantToExchange b)
	{
	   BigInteger publicNumberX = BigInteger.ZERO;
	   BigInteger publicNumberY = BigInteger.ZERO;
	   
	   publicNumberX =  computeDiffieHellmanPublicNumber(a.randomNumber,a.getNumberg(),a.getnumbern());
       b.setX(publicNumberX);
       publicNumberY =  computeDiffieHellmanPublicNumber(b.randomNumber,b.getNumberg(),b.getnumbern());
       a.setX(publicNumberY);
	};
	
	private static boolean exchangeCheck(UserWantToExchange a,UserWantToExchange b)
	{
		BigInteger exchangeK = BigInteger.ZERO; 
		BigInteger  exchangeKprime = BigInteger.ZERO; 
		
		exchangeK = computeDiffieHellmanexchangeKey(a.getrandomNumber(),a.getX(),a.getnumbern());
		a.setK(exchangeK);
		System.out.println("a:exchangeK:" + a.getK());
		exchangeKprime = computeDiffieHellmanexchangeKey(b.getrandomNumber(),b.getX(),b.getnumbern());
		b.setK(exchangeKprime);
		System.out.println("b:exchangeK:" + b.getK());
		if (a.getK().equals(b.getK()))
		{
			System.out.println("true");
			return true;
		}
		else
		{
			System.out.println("false");
			return false;

		}
	};
	
	
	private static BigInteger computeDiffieHellmanexchangeKey(BigInteger randomA,BigInteger openNum,BigInteger primeNum)
	{
		BigInteger exchangeKey = BigInteger.ZERO; 
		
		exchangeKey = openNum.modPow(randomA,primeNum);	
		
		return exchangeKey;
	};
	
	
	private static BigInteger computeDiffieHellmanPublicNumber(BigInteger randomA,BigInteger groudNum,BigInteger primeNum)
	{
		BigInteger openKey = BigInteger.ZERO;
		openKey = groudNum.modPow(randomA,primeNum);	
		return openKey;
	};
	
	private static BigInteger generateDiffieHellmanRandom(int bitlengthofRandomNumber,BigInteger minRandom)
	{

		Random RDM = new java.util.Random();
		BigInteger RandomNum = new BigInteger(bitlengthofRandomNumber,10,RDM);
		RandomNum =RandomNum.add(minRandom);
		return RandomNum;
	};
	
	private static BigInteger generateDiffieHellmanprime(int bitlengthofRandomNumber)
	{
		Random rdm = new java.util.Random();
		BigInteger	primeNum = new BigInteger(bitlengthofRandomNumber,10,rdm);	
		primeNum = primeNum.nextProbablePrime();
		return primeNum;
	};

	private static BigInteger generateDiffieHellmanNumberg(int bitlengthofNumberg,BigInteger primeNumbern)
	{

		Random RDM = new java.util.Random();
		BigInteger Numberg = new BigInteger(bitlengthofNumberg,10,RDM);
		return Numberg;
	};

	
}
