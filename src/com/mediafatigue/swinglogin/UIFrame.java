package com.mediafatigue.swinglogin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class UIFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private int x, y, height, width;
	
	private JPanel mainPanel, loginPanel, setupPanel;
	
	private JTextField uName, uNameSetup, pWordSetup, pWordConfirm;
	private JPasswordField pWord;
	
	private JLabel contextLabel;
	
	private JButton logButton, setButton;
	
	public UIFrame(int xC, int yC, String title) {
		x = xC;
		y = yC;
		height = 300;
		width = 300;
		this.setBounds(x, y, width, height);
		this.setTitle(title);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(100, 100, 100));
		this.add(mainPanel);
		
		loginPanel = new JPanel();
		loginPanel.setBackground(mainPanel.getBackground());
		uName = new JTextField(24);
		pWord = new JPasswordField(24);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		loginPanel.add(uName);
		loginPanel.add(pWord);
		
		setupPanel = new JPanel();
		setupPanel.setBackground(mainPanel.getBackground());
		uNameSetup = new JTextField();
		pWordSetup = new JTextField();
		pWordConfirm = new JTextField();
		
		mainPanel.add(contextLabel);
		mainPanel.add(loginPanel);
		this.setVisible(true);
	}
}
