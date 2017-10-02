package ru;

import java.math.BigInteger;

public class Hesh {
	public static byte[] hashLy(byte[] msgin){
		int hash = 0;
		for(int i = 0 ; i < msgin.length;i++){
			hash = hash * 1664525 + msgin[i] + 1013904223;
		}
		BigInteger m = BigInteger.valueOf(hash);
		byte[] msgout = m.toByteArray();
		return msgout;
	}
}