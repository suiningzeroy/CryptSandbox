import java.math.BigInteger;


public class TestforFuction {
	
	public static void TestForBigIntegerClassMethod()
	{
		BigInteger testBIg_a = new BigInteger("4");
		BigInteger testBIg_b = new BigInteger("3");
		BigInteger testBIg_c = new BigInteger("2");
		
		
		System.out.println("Addition operate:" + testBIg_a.add(testBIg_b));
		System.out.println("ABS opertate:" + testBIg_a.abs());
		System.out.println("Negate opertate:" + testBIg_b.negate());
		System.out.println("Signum opertate:" + testBIg_b.signum());
		System.out.println("Modulus opertate:" + testBIg_b.mod(testBIg_a));
		System.out.println("Remainder opertate:" + testBIg_b.remainder(testBIg_a));
		System.out.println("Modpow opertate:" + testBIg_b.modPow(testBIg_c, testBIg_a));
		System.out.println("ModInverse opertate:" + testBIg_b.modInverse(testBIg_c));
		System.out.println("ShiftLeft opertate:" + testBIg_c.shiftLeft(2));
		System.out.println("ShiftRight opertate:" + testBIg_c.shiftRight(1));
		System.out.println("AND opertate:" + testBIg_c.and(testBIg_a));
		System.out.println("OR opertate:" + testBIg_c.or(testBIg_a));
		System.out.println(testBIg_b);
		System.out.println("NOT opertate:" + testBIg_b.not());
		System.out.println("Subtraction opertate:" + testBIg_a.subtract(testBIg_b));
		System.out.println("Multiply opertate:" + testBIg_a.multiply(testBIg_b.not()));
		System.out.println("Divide opertate:" + testBIg_a.divide(testBIg_b));
		System.out.println("Max opertate:" + testBIg_a.max(testBIg_b));
		System.out.println("Min opertate:" + testBIg_a.min(testBIg_b));
		System.out.println("GCD opertate:" + testBIg_a.gcd(testBIg_c));
		BigInteger Result[] = testBIg_c.divideAndRemainder(testBIg_a);
		System.out.println("shang:" + Result[0] + ";reminder:" + Result[1]);
	};
}
