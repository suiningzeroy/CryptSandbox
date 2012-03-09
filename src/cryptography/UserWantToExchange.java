package cryptography;

import java.math.BigInteger;

public class UserWantToExchange {
       private char name;
       private BigInteger randomNumber = BigInteger.ZERO;
       private BigInteger numbern = BigInteger.ZERO;
       private BigInteger numberg = BigInteger.ZERO;
       private BigInteger X = BigInteger.ZERO;
       private BigInteger K = BigInteger.ZERO;
       
       
       public void setName(char name)
       {
    	   this.name = name;

       }; 
       public char getName()
       {
    	   return this.name;

       };
       public void setrandomNumber(BigInteger randomNum)
       {
    	   this.randomNumber = randomNum;
;
       };
       
       public void setnumbern(BigInteger n)
       {
    	   this.numbern = n;

       };
       
       public void setNumberg(BigInteger g)
       {
    	   this.numberg = g;

       };
       public void setX(BigInteger X)
       {
    	   this.X = X;

       };
       public void setK(BigInteger K)
       {
    	   this.K = K;

       };
       
       public BigInteger getX()
       {
    	  return this.X;
       };
       
       public BigInteger getK()
       {
    	  return this.K;
       };
       
       public BigInteger getrandomNumber()
       {
    	  return randomNumber;
       };
       
       public BigInteger getnumbern()
       {
    	   return numbern;
       };
       
       public BigInteger getNumberg()
       {
    	  return numberg;
       };
       
       public void sendPublicNumber(UserWantToExchange user)
       {
    	   user.numberg = this.numberg;
    	   user.numbern = this.numbern;
       };
       
       
}
