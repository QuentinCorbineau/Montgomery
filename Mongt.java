import java.math.BigInteger;

public class Mongt {
	
	private static int V;
	static BigInteger n = BigInteger.valueOf(1081);
	static BigInteger e = BigInteger.valueOf(173);
	static BigInteger d = BigInteger.valueOf(117);
	static BigInteger k,r,v = BigInteger.ZERO;

	public static void main(String args[]) {
		
		
		
		//Init Montgomery
		 
		 k = BigInteger.valueOf((int) (Math.log(n.longValue())/Math.log(2))+1);
		 r = BigInteger.valueOf(1<<k.longValue());
		 System.out.println("r : "+r); 
		 v = v.subtract(BigInteger.valueOf(myModInverse(n.intValue(),r.intValue())));
		 //v= BigInteger.valueOf(myModInverse(n.intValue(),r.intValue()-1));
		 System.out.println("v : "+v); 
		 V = v.intValue();
		 
		 BigInteger m = BigInteger.valueOf(999); // the value of m must be less than the value of n
		 System.out.println("message..... : " + m);

		 BigInteger M = BigInteger.valueOf((m.multiply(r)).longValue()%n.longValue());
		 System.out.println("M........... = " + M);
		 
		 BigInteger C = myModPow(M,e,n);
		 System.out.println("C........... = " + C);

		 BigInteger D = myModPow(C,d,n);
		 System.out.println("D........... = " + D);

		 BigInteger dd= Montg(D,BigInteger.valueOf(1),n);/*BigInteger.valueOf((m.multiply(BigInteger.valueOf(1))).longValue()%n.longValue())*/;
		 System.out.println("return mesage : " + dd);

	}
	
	private static BigInteger Montg(BigInteger a,BigInteger b,BigInteger n){
		 BigInteger s,t,m,u=BigInteger.ZERO;
		 BigInteger v = BigInteger.valueOf(V);
		 s=a.multiply(b);
		 //System.out.println("s : " + s);
		 t=BigInteger.valueOf((s.longValue()*v.longValue()) & (r.longValue()-1));
		 //System.out.println("t : " + t);
		 m= BigInteger.valueOf(s.longValue()+(t.longValue()*n.longValue()));
		 ///System.out.println("m : " + m);
		 u=BigInteger.valueOf(m.longValue() >> k.longValue());
		 //System.out.println("u : " + u +"\n");
		 if(u.compareTo(n)>=0){
			 return u.subtract(n);
		 
		 };
		 /*int x= u.longValue()%n.longValue();
		 return BigInteger.valueOf(x);*/
		 return u;
	}
	
	
	private static BigInteger myModPow(BigInteger a,BigInteger b,BigInteger c) {
		BigInteger X = r.subtract(n); //Neutre pour le produit de Montgomery
		BigInteger Y = BigInteger.valueOf((long) (Math.log(b.longValue())/Math.log(2))+1);
		for(long i = Y.longValue()-1; i >= 0 ; i--){
			long cpt = b.longValue()>>>i & 1;
			X = Montg(X,X,c);
			if(cpt == 1){
				X = Montg(X,a,c);
			}
		}
		return X;
	}
	
	private static long myModInverse(int w, int m)
    {
    	int ww = w;
	     int mm = m;
	     int t0 = 0;
	     int t = 1;
	     int q = (int) mm/ww;
	     int r = mm - q * ww;
	     int tmp = 0;
	 
	   while(r > 0)
	   {
	        tmp = t0 - q * t;
	 
	        if (tmp >= 0)
		 {
	              tmp = tmp % m;
	         }
	         else
	         {
	             tmp = m - ((-tmp) % m);
	         }
	 
		 t0 = t;
		 t = tmp;
		 mm = ww;
		 ww = r;
		 q = (int) mm/ww;
		 r = mm - q * ww;
	    }
	   
	   return t;
    }

}
