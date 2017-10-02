package ru;
import eventActionLinister.eventActionLinister;
import eventActionLinister.eventOFD;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Form extends JFrame {
	JButton btnStartHesh, btnOFD;
	JTextField tfThePath; File file;
	public Form(String s){
		super(s);
		setLayout(new FlowLayout()); this.setSize(270, 200); this.setLocation(400, 300);
		btnStartHesh = new JButton("Hesh");		btnOFD = new JButton("The Path");
		tfThePath = new JTextField(15); btnStartHesh.setEnabled(false);
		btnOFD.addActionListener(new eventOFD(tfThePath, btnStartHesh)); btnStartHesh.addActionListener(new eventActionLinister(tfThePath));
		this.add(btnOFD);this.add(tfThePath);this.add(btnStartHesh);
	}
}