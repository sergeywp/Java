package ru;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class WriteReadFile {
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
			 JOptionPane.showMessageDialog(null, "Ошибка в чтений");
		 }
	      return buffer;
	   }
	  public static void writeBytes(byte[] data, String out){
		  try{
			  FileOutputStream fos = new FileOutputStream(out);
			  fos.write(data);
			  fos.close();
		  }
		  catch(IOException e){
			  JOptionPane.showMessageDialog(null, "Ошибка при записи");
		  }
	   }
}