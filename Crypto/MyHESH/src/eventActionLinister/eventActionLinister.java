package eventActionLinister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ru.readWriteFile;

public class eventActionLinister implements ActionListener {
	JTextField tfThePath;
	public eventActionLinister(JTextField tfThePath) {
		// TODO Auto-generated constructor stub
		this.tfThePath = tfThePath;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		byte[] msg = readWriteFile.readBytes(tfThePath.getText());
		byte[] msgout = ru.Hesh.hashLy(msg);
		readWriteFile.writeBytes(msgout, "F:\\хлам\\output.txt");
		JOptionPane.showMessageDialog(null, "OK");
	}
}
