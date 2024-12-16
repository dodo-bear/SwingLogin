package com.mediafatigue.swinglogin;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * 
 */
public class UIFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private int x, y, height, width;
	
	private JPanel mainPanel, loginPanel, setupPanel;
	
	private JTextField uName, uNameSetup, pWordSetup, pWordConfirm;
	private JPasswordField pWord;
	
	private JLabel contextLabel;
	
	private JButton logButton, setButton, createButton, backButton;
	
	/**
	 * Initializes a complete login interface JFrame, with account login and creation functionality.
	 * @param xC The x-coordinate in screen space that the top left corner of the window should occupy.
	 * @param yC The y-coordinate in screen space that the top left corner of the window should occupy.
	 * @param title The title of the window, used as a parameter for <code>JFrame.setTitle()</code>.
	 */
	public UIFrame(int xC, int yC, String title) {
		x = xC;
		y = yC;
		height = 180;
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
		mainPanel.setBackground(new Color(120, 120, 120));
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
		logButton.setFocusPainted(false);
		logButton.setBackground(new Color(59, 89, 182));
		logButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		logButton.setForeground(Color.white);
		createButton.setFocusPainted(false);
		createButton.setBackground(new Color(59, 89, 182));
		createButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		createButton.setForeground(Color.white);
		
		
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
		setButton.setFocusPainted(false);
		setButton.setBackground(new Color(59, 89, 182));
		setButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		setButton.setForeground(Color.white);
		backButton.setFocusPainted(false);
		backButton.setBackground(new Color(59, 89, 182));
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setForeground(Color.white);
		
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
				setBounds(x, y, width, height + 25);
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
		
		addCustomBorder(logButton, 5, 1f);
		addCustomBorder(uName, 5, 1f);
		addCustomBorder(uNameSetup, 5, 1f);
		addCustomBorder(pWordConfirm, 5, 1f);
		addCustomBorder(backButton, 5, 1f);
		
		contextLabel = new JLabel("Please enter a username and password.");
		contextLabel.setForeground(Color.white);
		contextLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		mainPanel.add(contextLabel);
		mainPanel.add(loginPanel);
		this.setVisible(true);
	}
	
	/**
	 * Adds a custom margin to a JComponent, with variable transparency to the component's background.
	 * @param component The object to add the margin to.
	 * @param thickness The width of the margin.
	 * @param transparency How opaque the margin should be, from 0.0f to 1.0f.
	 */
	public static void addCustomBorder(JComponent component, int thickness, float transparency) {

        Border transparentBorder = new CustomBorder(thickness, transparency);

        if (component.getBorder() != null) {
            component.setBorder(BorderFactory.createCompoundBorder(transparentBorder, component.getBorder()));
        } else {
            component.setBorder(transparentBorder);
        }
	}
	
	/**
     * Custom border class that paints a border, because Swing layouts do not understand what margins are.
     */
    static class CustomBorder implements Border {
        private final int thickness;
        private final float transparency;

        public CustomBorder(int thickness, float transparency) {
            this.thickness = thickness;
            this.transparency = transparency;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();

            Color parentColor = getParentBackgroundColor(c);
            if (parentColor == null) {
                parentColor = Color.LIGHT_GRAY; // Fallback color
            }
            
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
            g2d.setColor(parentColor);

            g2d.fillRect(x, y, width, thickness); // Top
            g2d.fillRect(x, y + height - thickness, width, thickness); // Bottom
            g2d.fillRect(x, y, thickness, height); // Left
            g2d.fillRect(x + width - thickness, y, thickness, height); // Right

            g2d.dispose();
        }
        
        private Color getParentBackgroundColor(Component c) {
            Container parent = c.getParent();
            if (parent != null) {
                return parent.getBackground();
            }
            return null;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness, thickness, thickness, thickness);
        }

        @Override
        public boolean isBorderOpaque() {
            return false; // Because it's transparent
        }
    }
}
