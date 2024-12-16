package com.mediafatigue.swinglogin;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UIFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private int x, y, height, width;
	
	private JPanel mainPanel, loginPanel, setupPanel;
	
	private JTextField uName, uNameSetup, pWordSetup, pWordConfirm;
	private JPasswordField pWord;
	
	private JLabel contextLabel;
	
	private JButton logButton, setButton, createButton, backButton;
	
	public UIFrame(int xC, int yC, String title) {
		x = xC;
		y = yC;
		height = 170;
		width = 300;
		setBounds(x, y, width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					DatabaseManager.writeToFile(LoginManager.getData(), LoginManager.getFileName());
					System.out.println("Wrapped up successfully");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(100, 100, 100));
		this.add(mainPanel);
		
		loginPanel = new JPanel();
		loginPanel.setBackground(mainPanel.getBackground());
		uName = new JTextField(24);
		pWord = new JPasswordField(24);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		uName.setToolTipText("Username");
		pWord.setToolTipText("Password");
		loginPanel.add(uName);
		loginPanel.add(pWord);
		logButton = new JButton("Log In");
		logButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createButton = new JButton("Create Account");
		createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginPanel.add(logButton);
		loginPanel.add(createButton);
		
		
		setupPanel = new JPanel();
		setupPanel.setBackground(mainPanel.getBackground());
		uNameSetup = new JTextField(24);
		pWordSetup = new JTextField(24);
		pWordConfirm = new JTextField(24);
		setupPanel.setLayout(new BoxLayout(setupPanel, BoxLayout.Y_AXIS));
		uNameSetup.setToolTipText("Username");
		pWordSetup.setToolTipText("Password");
		pWordConfirm.setToolTipText("Confirm Password");
		setupPanel.add(uNameSetup);
		setupPanel.add(pWordSetup);
		setupPanel.add(pWordConfirm);
		setButton = new JButton("Create Account");
		setButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton = new JButton("Back to Login");
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		setupPanel.add(setButton);
		setupPanel.add(backButton);
		
		Action attemptLogIn = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	String[][] data = LoginManager.getData();
				boolean found = false;
				for(String[] arr : data) {
					if(arr[0].equals(uName.getText()) && arr[1].equals(DatabaseManager.generateStackHash(String.copyValueOf(pWord.getPassword())) + "")) {
						found = true;
						break;
					}
				}
				if(found) {
					System.out.println("Logged in successfully. User: " + uName.getText());
					Toolkit tk = Toolkit.getDefaultToolkit();
			        EventQueue evtQ = tk.getSystemEventQueue();
			        evtQ.postEvent(new WindowEvent((Window) logButton.getParent().getParent().getParent().getParent().getParent().getParent(), WindowEvent.WINDOW_CLOSING));
				} else {
					contextLabel.setText("Incorrect username or password.");
					uName.setText("");
					pWord.setText("");
				}
		    }
		};
		
		Action attemptCreateAccount = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	String[][] data = LoginManager.getData();
				boolean found = false;
				for(String[] arr : data) {
					if(arr[0].equals(uNameSetup.getText())) {
						found = true;
						break;
					}
				}
				if(found) {
					uNameSetup.setText("");
					pWordSetup.setText("");
					pWordConfirm.setText("");
					contextLabel.setText("Username already in use.");
				} else if (!pWordSetup.getText().equals(pWordConfirm.getText())){
					uNameSetup.setText("");
					pWordSetup.setText("");
					pWordConfirm.setText("");
					contextLabel.setText("Passwords do not match.");
				} else if (pWordConfirm.getText().length() < 8){
					uNameSetup.setText("");
					pWordSetup.setText("");
					pWordConfirm.setText("");
					contextLabel.setText("Password must be at least 8 characters.");
				} else {
					LoginManager.getArrList().add(new String[] {uNameSetup.getText(), DatabaseManager.generateStackHash(pWordSetup.getText()) + ""});
					contextLabel.setText("Account created successfully.");
					System.out.println("Account created successfully.");
					backButton.getActionListeners()[0].actionPerformed(e);
				}
		    }
		};
		
		
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mainPanel.remove(loginPanel);
				mainPanel.add(setupPanel);
				setBounds(x, y, width, height + 20);
				setVisible(true);
			}
		});
		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mainPanel.remove(setupPanel);
				mainPanel.add(loginPanel);
				setBounds(x, y , width, height);
				setVisible(true);
			}
			
		});
		
		logButton.addActionListener(attemptLogIn);
		pWord.addActionListener(attemptLogIn);
		
		setButton.addActionListener(attemptCreateAccount);
		pWordConfirm.addActionListener(attemptCreateAccount);
		
		contextLabel = new JLabel("Please enter a username and password.");
		
		mainPanel.add(contextLabel);
		mainPanel.add(loginPanel);
		this.setVisible(true);
	}
}
