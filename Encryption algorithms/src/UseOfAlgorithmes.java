public class UseOfAlgorithmes {
	public static void EncryptBlF(String str)
	{
		/*
		 * lowfish key = new blowfish(tbKey.getText().getBytes());
				   byte[] message = WriteReadFile.readBytes(tbThePath.getText());
				   byte[] msg = key.padding(message, ' ');
				   key.decipher(msg);
				   WriteReadFile.writeBytes(msg, "F:\\õëàì\\output.txt");
				   JOptionPane.showMessageDialog(null, "Ok");
		 */
			BlowFish bl = new BlowFish();
			bl.blowfish(str.getBytes());
			byte[] message = "dfgdfgdfgdfg".getBytes();
			byte[] msg = bl.padding(message, ' ');
			System.out.print(msg.toString());
			bl.encipher(msg);
			System.out.print(msg.toString());
			bl.decipher(msg);
			System.out.print(msg.toString());
	}
	public static void main(String[] args) {
		String str = "fdsgdfgsdfgdsgsdgsdf";
		BlowFish bl = new BlowFish();
		bl.blowfish(str.getBytes());
		byte[] message = "dfgdfgdfgdfg".getBytes();
		byte[] msg = bl.padding(message, ' ');
		//System.out.print(bl.encipher(msg));
		//System.out.print(bl.decipher(msg));
		
	}

}
