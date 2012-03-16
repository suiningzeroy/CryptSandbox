package cryptography;

public class test {

	void main()
	{
		int n,b,result;
	
		result = 0;
	    n = 3220;
	    b = 79;
	    result = ExtendedEuclid(n,b);
	    
		System.out.printf("乘法的逆元是：" + result);
	}
	
	public int ExtendedEuclid( int f,int d)
	{
		int x1,x2,x3,y1,y2,y3,t1,t2,t3,q;
		boolean ff = true;
		int result = 0;
	
		x1 = y2 = 1;
		x2 = y1 = 0;
		x3 = ( f>=d )?f:d;
		y3 = ( f>=d )?d:f;

		while( ff )
		{
			  if ( y3 == 0 ) 
			  {
			   result = x3; /* 两个数不互素则result为两个数的最大公约数，此时返回值为零 */
			   break ;
			  }
			  if ( y3 == 1 ) 
			  {
			   result = y2; /* 两个数互素则resutl为其乘法逆元，此时返回值为1 */
			   break ;
			  }
			  q = x3/y3;
			  t1 = x1 - q*y1;
			  t2 = x2 - q*y2;
			  t3 = x3 - q*y3;
			  x1 = y1;
			  x2 = y2;
			  x3 = y3;
			  y1 = t1;
			  y2 = t2;
			  y3 = t3;
		}
		return result;
	}

}
