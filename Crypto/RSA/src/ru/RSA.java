package ru;

import java.util.Random;

import javax.swing.JOptionPane;

public class RSA {
	
	static PublcKey pubK = new PublcKey();
	static PrivateKey privK = new PrivateKey();
	
	public static Boolean checkNumber(int N){
		if(N%2==0)
			return false;
		for(int i = 3;i<N/2+1;i+=2){
			if(N%i==0)
				return false;
		}
		return true;
	}
	private static Boolean checkDoubleNumber(int x,int y){
		int len;
		if(x>y)
			len = x;
		else
			len = y;
		for(int i = 2 ; i<len;i++){
			if(x%i==0 && y%i==0)
				return false;
		}
		return true;
	}
	
	private static int FoundN(int p,int q){
		return p * q;
	}
	private int funckE(int p,int q){
		return (p-1)*(q-1);
	}
	private static int foundE(int N){
		Random rnd = new Random();
		int e = 2;
		e = rnd.nextInt(N);
		while(checkDoubleNumber(e, N) != true)
			e = rnd.nextInt(N);
		return e;
	}
	private static int foundD(int N, int e){
		int k = 1;
		int d = 0;
		while(d%1 != 0){
			k++;
			d = (k*foundE(N)+1)/e;
		}
		return k;
	}
	public static byte[] Coder(byte[] message, int p,int q){
		try{
			pubK.n = FoundN(p, q);
			pubK.e = foundE(pubK.n);
			byte[] c = new byte[message.length];
			for(int i = 0; i<message.length;i++){
				c[i] = (byte) (Math.pow((int)message[i], pubK.e)%pubK.n);
						
			}
			return c;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error Coder");
			return null;
		}
	}
	
	public static byte[] DeCoder(byte[] message){
		try{
			privK.n = pubK.n;
			privK.d = foundD(privK.n, pubK.e);
			byte[] c = new byte[message.length];
			for(int i = 0; i<message.length;i++){
				c[i] = (byte)(Math.pow((int)message[i], privK.d)%privK.n);
			}
			return c;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error DeCoder");
			return null;
		}
	}
}

class PublcKey{
	int e;
	int n;
}
class PrivateKey{
	int d;
	int n;
}