package ru;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Form extends JFrame{
	JButton btnCoder,btnDecoder,btnofd;
	JTextField tbThePath,tbFirstNumber,tbSecondNumber;
	JLabel lFirstNumber,lSecondNumber; 
	eHandler handler = new eHandler();
	File file;
	
	//BigInteger p = new BigInteger(tbFirstNumber.getText()); 
	//BigInteger q = new BigInteger(tbFirstNumber.getText());
	BigInteger p = BigInteger.valueOf(2579);
	BigInteger q = BigInteger.valueOf(3557);
	BigInteger n = q.multiply(p);
	BigInteger e1 = BigInteger.valueOf(3);
	//BigInteger d = BigInteger.valueOf(6111579);
	BigInteger d = BodyRSA.eiler(e1, p, q);
	BigInteger msg1 = null,c = null,t = null;
	String cS = "";
	String mS = "";
	String tS = "";
	
	public Form(String s){
		super(s);
		this.setSize(320, 200);
		setLayout(new FlowLayout());
		btnofd = new JButton("Выбрать файл"); btnCoder = new JButton("Coder");
		btnDecoder = new JButton("Decoder"); tbThePath = new JTextField(15);
		tbFirstNumber = new JTextField(8); tbFirstNumber.setText("2579");
		tbThePath.setEditable(false); lFirstNumber = new JLabel("P = ");
		tbSecondNumber = new JTextField(8);tbSecondNumber.setText("3557");
		lSecondNumber = new JLabel("Q = "); btnDecoder.setEnabled(false);
		add(btnofd); add(tbThePath);add(lFirstNumber);add(tbFirstNumber);add(lSecondNumber);add(tbSecondNumber); add(btnCoder); add(btnDecoder);
		btnofd.addActionListener(handler); btnCoder.addActionListener(handler);btnDecoder.addActionListener(handler);
	}
	
	private class eHandler implements ActionListener{
		JFileChooser fc = new JFileChooser();
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource()==btnofd){
				int ret = fc.showDialog(null, "Открыть файл");
				if (ret == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					tbThePath.setText(file.getAbsolutePath());
				}
			}
			if(e.getSource() == btnCoder){	
				byte[] msg = ReadWriteFile.readBytes(tbThePath.getText());
				mS = new String(msg);
				cS = BodyRSA.coder(mS, msg1, c, n, e1);
				ReadWriteFile.writeBytes(cS.getBytes() , "F:\\хлам\\output.txt");
				btnDecoder.setEnabled(true);
				JOptionPane.showMessageDialog(null, "ok");
			}
			if(e.getSource() == btnDecoder){
				try{
					BigInteger[] Mass;
					Mass = BodyRSA.getMass();
					tS = BodyRSA.DeCoder(d, n, t,Mass);
					ReadWriteFile.writeBytes(tS.getBytes() , "F:\\хлам\\output2.txt");
					JOptionPane.showMessageDialog(null, "Ok");
					}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error DeCoder");
				}
			}
		}
	}
}