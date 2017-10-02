import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

import javax.swing.JOptionPane;

public class RSA {	
	static BigInteger[] codText = new BigInteger[9999999];
	
	/**
	 * computing length of the BigInteger
	 * @param ct
	 * @return
	 */
	private static int count(BigInteger[] ct){
		int k = 0 ;
		for(int i = 0 ; i < ct.length;i++){
			if ((ct[i] != BigInteger.ZERO)&&(ct[i] != null)){
				k++;
			}
		}
		return k;
	}
	/**
	 * finding of close key (private exponent)
	 * @param e - public exponent
	 * @param p - simple number
	 * @param q - simple number
	 * @return
	 */
	public static BigInteger eiler(BigInteger e,BigInteger p, BigInteger q){
		BigInteger p_1 = p.subtract(BigInteger.ONE);
		BigInteger q_1 = q.subtract(BigInteger.ONE);
		BigInteger d = e.modInverse(p_1.multiply(q_1));
		return d;
		
	}
	/**
	 * Decoding method
	 * @param d - private key (private exponent)
	 * @param n - module of the key (p*q)
	 * @param t - 
	 * @param mass - Coded array to Decode
	 * @return
	 */
	public static String DeCoder(BigInteger d,BigInteger n,BigInteger t,BigInteger[] mass){	
		//decopher string
		String tS = "";
		//compute length of input BigInteger array
		int k = count(mass);
		
		for(int i = 1 ; i <= k;i++){
			//decipher with private key
			t = mass[i].modPow(d, n);
			//converting deciphered symbol into Byte
			byte[] tb = t.toByteArray();
			//converting deciphered Bytes into string 
			String s2 = new String(tb);
			tS += s2;
		}
		return tS;
	}
	/**
	 * coding method
	 * 
	 * @param mS - input string to code
	 * @param msg
	 * @param c
	 * @param n - module of the key (p*q)
	 * @param e - open key (public exponent)
	 * @return
	 */
	public static String coder(String mS,BigInteger msg,BigInteger c,BigInteger n,BigInteger e){
		//conversation input string into char array
		mS=" "+mS;
		char[] ch = mS.toCharArray();
		//intermediate string, that holds every char, to convert into bytes
		String s;
		//enciphering string cS
		String cS = "";
		for(int i = 1 ; i<mS.length();i++){
			s = ch[i] + "";
			//covnertion every char into bytes
			byte[] mb = s.getBytes(); 
			//convert to BigInteger
			msg = new BigInteger(mb);
			//encipher with open key (e,n)
			c = msg.modPow(e, n);
			codText[i] = c;
			//converts into byte array
			byte[] cb = c.toByteArray();
			//converts to string
			String s1 = new String(cb);
			cS += s1;
		}
		return cS;
	}
	
	public static BigInteger[] getMass(){
		return codText;
	}
	public static byte[] readBytes(String in) {
		 byte[] buffer;
		 try{
	      FileInputStream fis = new FileInputStream(in);
	      int numOfBytes = fis.available();
	      buffer = new byte[numOfBytes];
	      fis.read(buffer);
	      fis.close();
		 }
		 catch(IOException e){
			 buffer = new byte[1];
			 JOptionPane.showMessageDialog(null, "Îøèáêà â ÷òåíèé");
		 }
	      return buffer;
	   }
	public static void main(String[] args) {
		String str="The American paddlefish is one of the largest freshwater fish in North America. Closely related to the sturgeon, it is one of only two extant taxa in the paddlefish family (the other being the Chinese paddlefish). Fossil records of paddlefish date back over 300 million years, nearly 50 million years before dinosaurs appeared. The American paddlefish is referred to as a relict species (because it retains some morphological characteristics of its early ancestors) even though it is highly derived with evolutionary adaptations specifically for filter feeding. The rostrum and cranium of American paddlefish are covered with tens of thousands of sensory receptors for locating swarms of zooplankton, their primary fThe apadtteg fdg;lkdfg gd;hl,kvbkjbhvkjbgkbggkjhbnknhgkghkhkjbnhkbg kjg dhkg jkgds gkifds gkdsh ffgk dshbkg gkjg fd";
		BigInteger p = BigInteger.valueOf(2579);
		BigInteger q = BigInteger.valueOf(3557);
		BigInteger n = q.multiply(p);
		BigInteger e1 = BigInteger.valueOf(3);
		//BigInteger d = BigInteger.valueOf(6111579);
		BigInteger d = eiler(e1, p, q);
		BigInteger msg1 = null,c = null,t = null;
		String cS = "";
		String mS = "";
		String tS = "";
		byte[] msg = readBytes("/home/sergey/dec");
		mS = new String(msg);
		cS = coder(mS, msg1, c, n, e1);
		//System.out.print(cS);
		cS = DeCoder(d, n, t,getMass());
			//System.out.print(codText[i]+"  ");
		System.out.print(cS);
	}
}