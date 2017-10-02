package ru;
import javax.swing.JFrame;

import ru.Form;
public class MainRun {

	public static void main(String[] args) {
		Form f = new Form("RSA");
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
