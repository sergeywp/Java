package eventActionLinister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class eventOFD implements ActionListener{

	JTextField tfThePath; JButton btnStartHesh;
	public eventOFD(JTextField tfThePath,JButton btnStartHesh) {
		// TODO Auto-generated constructor stub
		this.tfThePath = tfThePath;
		this.btnStartHesh = btnStartHesh;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();
		File file;
		int ret = fc.showDialog(null, "Открыть файл");
		if(ret == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
			tfThePath.setText(file.getAbsolutePath());
			btnStartHesh.setEnabled(true);
		}
	}
}