package together;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Window {
	private static final String VERSION = "1.3.1";
	private JFrame frmTogetherInitialization;
	private final JPanel panel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Boolean isEnd = false;
	private String[] pushbackdata = {"Default", "127.0.0.1", "14487", "True"};
	private final JLabel lblNewLabel_6 = new JLabel("New label");
	/**
	 * Create the application.
	 */
	public String[] winRun() {
		initialize();
		this.frmTogetherInitialization.setVisible(true);
		
		while(!isEnd) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		};
		
		this.frmTogetherInitialization.dispose();
		
		return pushbackdata;
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmTogetherInitialization = new JFrame();
		frmTogetherInitialization.setResizable(false);
		frmTogetherInitialization.setTitle("Together - Initialization");
		frmTogetherInitialization.setBounds(100, 100, 325, 173);
		frmTogetherInitialization.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTogetherInitialization.getContentPane().setLayout(null);
		frmTogetherInitialization.setLocationRelativeTo(null);
		
		ButtonGroup bGroup = new ButtonGroup();
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 0, 321, 147);
		frmTogetherInitialization.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("華康粗圓體", Font.BOLD, 17));
		btnEnter.setBounds(10, 114, 301, 23);
		panel_1.add(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if((textField.getText().length()!=0 && textField_1.getText().length()!=0 && textField_2.getText().length()!=0) || (textField.getText().length()!=0 && pushbackdata[3]=="True")) {
					pushbackdata[0] = textField.getText();
					if(pushbackdata[3]=="False") {
						pushbackdata[2] = textField_2.getText();
						pushbackdata[1] = textField_1.getText();
					}else {
						if(textField_2.getText().length()!=0) {
							pushbackdata[2] = textField_2.getText();
						}
					}
					isEnd = true;
				}
			}
		});
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setBounds(73, 40, 12, 13);
		panel_1.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setEditable(false);	
				textField_1.setToolTipText("");
				textField_1.setText("");
				textField_2.setToolTipText("Set the port to be use, or leave a blank to use default port \"14487\".");
				pushbackdata[3] = "True";		
			}
		});
		bGroup.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		rdbtnNewRadioButton_1.setBounds(161, 40, 12, 13);
		panel_1.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField_1.setEditable(true);
				textField_1.setToolTipText("The server's IP.");
				textField_2.setToolTipText("The port that server using.");
				pushbackdata[3] = "False";
			}
		});
		bGroup.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel = new JLabel("Client");
		lblNewLabel.setBounds(178, 35, 92, 23);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("華康行楷體W5", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Nickname: ");
		lblNewLabel_1.setBounds(46, 64, 75, 15);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("華康竹風體W4", Font.PLAIN, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Please choose and enter the nickname.");
		lblNewLabel_2.setBounds(6, 10, 305, 15);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("華康粗黑體", Font.PLAIN, 12));
		
		JLabel lblNewLabel_3 = new JLabel("IP : Port: ");
		lblNewLabel_3.setBounds(46, 89, 75, 15);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("華康竹風體W4", Font.PLAIN, 12));
		
		JLabel lblNewLabel_4 = new JLabel("Server");
		lblNewLabel_4.setBounds(90, 35, 92, 23);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("華康行楷體W5", Font.PLAIN, 20));
		
		JLabel lblNewLabel_5 = new JLabel(":");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("標楷體", Font.BOLD, 14));
		lblNewLabel_5.setBounds(240, 89, 12, 15);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_6.setBounds(0, 0, 0, 0);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Version");
		lblNewLabel_7.setFont(new Font("華康鋼筆體W2", Font.ITALIC, 12));
		lblNewLabel_7.setToolTipText(VERSION);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(271, 132, 50, 15);
		panel_1.add(lblNewLabel_7);
		lblNewLabel_7.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2) {
					if(pushbackdata[3]=="True") { //Server
						textField.setText("DefaultServer");
						textField_1.setText("");
						textField_2.setText("");
					}
					else { //Client
						textField.setText("DefaultClient");
						textField_1.setText("127.0.0.1");
						textField_2.setText("14487");
					}
				}
			}
		});
		
		textField = new JTextField();
		textField.setBounds(119, 64, 120, 21);
		panel_1.add(textField);
		textField.setToolTipText("The nickname you want to use.");
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					btnEnter.doClick();
				}
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setBounds(119, 89, 120, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		textField_1.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					btnEnter.doClick();
				}
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("Set the port to be use, or leave a blank to use default port \"14487\".");
		textField_2.setBounds(253, 89, 50, 21);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		textField_2.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					btnEnter.doClick();
				}
			}
		});
		
		panel.setBounds(0, 0, 321, 147);
		frmTogetherInitialization.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 321, 147);
		label.setIcon(new ImageIcon(Window.class.getResource("/together/image/home.jpg")));
		panel.add(label);
		
	}
}
