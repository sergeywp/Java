package mmk.pack;

import java.util.Random;

public class MonteCarlo {
	public static double fun(double x){
		return x*x;
	}

	public static void main(String[] args) {
		int N=100;
		double sum=0;
		double I=0;

		double ryd, rxd;
		for(int i =0; i<N; i++){
			rxd =  Math.random()*100+1;
			ryd =  Math.random()*100+1;
			System.out.println("Rand: "+rxd+" , "+ ryd);
			if(ryd<fun(rxd))
				sum++;
						
		}
		I=sum/N;
		System.out.println("Integral : "+ I);
	}

}
