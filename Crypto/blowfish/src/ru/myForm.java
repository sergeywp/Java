package ru;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ru.WriteReadFile;

public class myForm extends JFrame{
	JButton btnCoder,btnDecoder,btnofd;
	JTextField tbThePath,tbKey;
	JLabel lKey; 
	eHandler handler = new eHandler();
	File file;
	
	public myForm(String s){
		super(s);
		this.setSize(350, 200);
		setLayout(new FlowLayout());
		btnofd = new JButton("Выбрать файл"); btnCoder = new JButton("Coder");
		btnDecoder = new JButton("Decoder"); tbThePath = new JTextField(15);
		tbKey = new JTextField(8); tbKey.setText("12345678");
		tbThePath.setEditable(false); lKey = new JLabel("Key = ");
		add(btnofd); add(tbThePath);add(lKey);add(tbKey); add(btnCoder); add(btnDecoder);
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
				   blowfish key = new blowfish(tbKey.getText().getBytes());
				   byte[] message = WriteReadFile.readBytes(tbThePath.getText());
				   byte[] msg = key.padding(message, ' ');
				   key.decipher(msg);
				   WriteReadFile.writeBytes(msg, "F:\\хлам\\output.txt");
				   JOptionPane.showMessageDialog(null, "Ok");
			}
			if(e.getSource() == btnDecoder){
				blowfish key = new blowfish(tbKey.getText().getBytes());
				byte[] message = WriteReadFile.readBytes("F:\\хлам\\output.txt");
				byte[] msg = key.padding(message, ' ');
				key.encipher(msg);
				WriteReadFile.writeBytes(msg, "F:\\хлам\\output2.txt");
				JOptionPane.showMessageDialog(null, "Ok");
			}
		}
	}
}