package cryptography;


import java.math.BigInteger;
import java.util.*;

public class DiffieHellmanKeysExchange {
	public static BigInteger publicRandomPrimeNumbern =  BigInteger.ZERO; 
	public static BigInteger publicRandomg =  BigInteger.ZERO; 
	
	static int BITLENGTHOFRANDOMNUMBER = 30;
	static int BITLENGTHOFNUMBERG = 7;
	static BigInteger MINRANDOM = new BigInteger("100"); 

	/**
	 * @param args
	 */
	public static void main(String[] args){
		UserWantToExchange alice = new UserWantToExchange();
		UserWantToExchange bob = new UserWantToExchange();
		
		establishConnect(alice,bob);
	};
	
	public static void establishConnect(UserWantToExchange userA,UserWantToExchange userB)
	{
		
		generatepublicNumber();
		broadcastPublicNumber(userA, userB);
		generatePrivateNumber(userA);
		generatePrivateNumber(userB);
		sendPublicNumberX(userA,userB);
		
		if(exchangeCheck(userA,userB)){
			System.out.println("success");
		}
		else{System.out.println("fail");
		};
	};
	
	private static void generatepublicNumber()
	{		
		publicRandomPrimeNumbern = generateDiffieHellmanprime(BITLENGTHOFRANDOMNUMBER);
        publicRandomg =   generateDiffieHellmanNumberg(BITLENGTHOFNUMBERG,publicRandomPrimeNumbern);
        
        System.out.println("publicRandomprimeNumbern:" + publicRandomPrimeNumbern);
        System.out.println("publicRandomg:" + publicRandomg);
	};
	
	private static void broadcastPublicNumber(UserWantToExchange a,UserWantToExchange b){
		a.setNumberg(publicRandomg);
		a.setnumbern(publicRandomPrimeNumbern);
		b.setNumberg(publicRandomg);
		b.setnumbern(publicRandomPrimeNumbern);
	};
	
	private static void generatePrivateNumber(UserWantToExchange a){
	   BigInteger randomNumberx = BigInteger.ZERO;
	   
	   randomNumberx =   generateDiffieHellmanRandom(BITLENGTHOFRANDOMNUMBER,MINRANDOM);
       a.setrandomNumber(randomNumberx);
       System.out.println("randomNumberx:" + randomNumberx);

	};
	
	private static void sendPublicNumberX(UserWantToExchange a,UserWantToExchange b){
	   BigInteger publicNumberX = BigInteger.ZERO;
	   BigInteger publicNumberY = BigInteger.ZERO;
	   
	   publicNumberX =  computeDiffieHellmanPublicNumber(a.getrandomNumber(),a.getNumberg(),a.getnumbern());
       b.setX(publicNumberX);
       publicNumberY =  computeDiffieHellmanPublicNumber(b.getrandomNumber(),b.getNumberg(),b.getnumbern());
       a.setX(publicNumberY);
	};
	
	private static boolean exchangeCheck(UserWantToExchange a,UserWantToExchange b){
		BigInteger exchangeK = BigInteger.ZERO; 
		BigInteger  exchangeKprime = BigInteger.ZERO; 
		
		exchangeK = computeDiffieHellmanexchangeKey(a.getrandomNumber(),a.getX(),a.getnumbern());
		a.setK(exchangeK);
		System.out.println("a:exchangeK:" + a.getK());
		exchangeKprime = computeDiffieHellmanexchangeKey(b.getrandomNumber(),b.getX(),b.getnumbern());
		b.setK(exchangeKprime);
		System.out.println("b:exchangeK:" + b.getK());
		if (a.getK().equals(b.getK())){
			return true;
		}
		else{
			return false;
		}
	};
	
	
	private static BigInteger computeDiffieHellmanexchangeKey(BigInteger randomA,BigInteger openNum,BigInteger primeNum){
		BigInteger exchangeKey = BigInteger.ZERO; 
		
		exchangeKey = openNum.modPow(randomA,primeNum);	
		return exchangeKey;
	};
	
	
	private static BigInteger computeDiffieHellmanPublicNumber(BigInteger randomA,BigInteger groudNum,BigInteger primeNum){
		BigInteger openKey = BigInteger.ZERO;
		
		openKey = groudNum.modPow(randomA,primeNum);	
		return openKey;
	};
	
	private static BigInteger generateDiffieHellmanRandom(int bitlengthofRandomNumber,BigInteger minRandom){

		Random RDM = new java.util.Random();
		
		BigInteger RandomNum = new BigInteger(bitlengthofRandomNumber,10,RDM);
		RandomNum =RandomNum.add(minRandom);
		return RandomNum;
	};
	
	private static BigInteger generateDiffieHellmanprime(int bitlengthofRandomNumber){
		
		BigInteger primeNum =  BigInteger.ZERO; 
		BigInteger primeNumMinusoneDividtwo =  BigInteger.ZERO; 
	    BigInteger NUMBERONE = new BigInteger("1");
		BigInteger NUMBERTWO = new BigInteger("2");
		
		do{
			primeNum = generatRandomPrime(bitlengthofRandomNumber);
			primeNumMinusoneDividtwo = primeNum.subtract(NUMBERONE).divide(NUMBERTWO);
		}
		while ( primeNumMinusoneDividtwo.isProbablePrime(10) == false);
			
		return primeNum;
	
	};
	
	private static BigInteger generatRandomPrime(int bitlengthofRandomNumber){
		Random rdm = new java.util.Random();
		BigInteger	primeNum = new BigInteger(bitlengthofRandomNumber,10,rdm);	
		primeNum = primeNum.nextProbablePrime();
	
		return primeNum;
	};

	private static BigInteger generateDiffieHellmanNumberg(int bitlengthofNumberg,BigInteger primeNumbern){

		Random RDM = new java.util.Random();
		BigInteger Numberg = new BigInteger(bitlengthofNumberg,10,RDM);
		return Numberg;
	};

	
}
