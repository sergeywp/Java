package ru;

import java.math.BigInteger;
import java.util.Random;

public class BodyRSA {	
	static BigInteger[] codText = new BigInteger[9999999];
	
	private static int count(BigInteger[] ct){
		int k = 0 ;
		for(int i = 0 ; i < ct.length;i++){
			if ((ct[i] != BigInteger.ZERO)&&(ct[i] != null)){
				k++;
			}
		}
		return k;
	}
	
	private static Boolean checkNumber(int x){
		if(x%2==0)
			return false;
		for(int i = 3;i<Math.sqrt(x);i+=2){
			if(x%i==0)
				return false;
		}
		return true;
	}
	
	private static Boolean checkTwoNember(int x,int y){
		int len;
		if(x>y)
			len=x;
		else
			len=y;
		for(int i = 3;i<len;i++){
			if((x%i==0)&&(y%i==0))
				return false;
		}
		return true;
	}
	
	private static int foundE(int Eiler){
		Random rnd = new Random();
		int e = rnd.nextInt(Eiler);
		while((checkNumber(e)!=true)&&(checkTwoNember(e, Eiler)!=true)){
			e=rnd.nextInt(Eiler);
		}
		return e;
	}
	
	private static int foundD(int p,int q,int e){
		int d = 0;
		int k = 0;
		int eiler = (p-1)*(q-1);
		do{
			k++;
			d = (k*eiler+1)/e;
		}
		while(d%1!=0);
		return d;
	}
	public static BigInteger eiler(BigInteger e,BigInteger p, BigInteger q){
		BigInteger p_1 = p.subtract(BigInteger.ONE);
		BigInteger q_1 = q.subtract(BigInteger.ONE);
		BigInteger d = e.modInverse(p_1.multiply(q_1));
		return d;
		
	}

	public static String DeCoder(BigInteger d,BigInteger n,BigInteger t,BigInteger[] mass){		
		String tS = "";
		int k = count(mass);
		for(int i = 3 ; i <= k;i++){
			t = mass[i].modPow(d, n);
			byte[] tb = t.toByteArray();
			String s2 = new String(tb);
			tS += s2;
		}
		return tS;
	}
	public static String coder(String mS,BigInteger msg,BigInteger c,BigInteger n,BigInteger e){
		char[] ch = mS.toCharArray();
		String s;
		String cS = "";
		for(int i = 1 ; i<mS.length();i++){
			s = ch[i] + "";
			byte[] mb = s.getBytes(); 
			msg = new BigInteger(mb);
			c = msg.modPow(e, n);
			codText[i] = c;
			byte[] cb = c.toByteArray();
			String s1 = new String(cb);
			cS += s1;
		}
		return cS;
	}
	
	public static BigInteger[] getMass(){
		return codText;
	}
}