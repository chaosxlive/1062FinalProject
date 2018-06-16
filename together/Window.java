package together;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Window {
	
	static final String VERSION = "1.3.1";
	String[] pushbackdata = {"Default", "127.0.0.1", "14487", "True"};
	
	JFrame frmTogetherInitialization = new JFrame();
	
	JPanel panel = new JPanel();
	JTextField textField_NickName = new JTextField();
	JTextField textField_IP = new JTextField();
	JTextField textField_Port = new JTextField();
	Boolean isEnd = false;
	
	ButtonGroup bGroup = new ButtonGroup();
	
	JPanel panel_1 = new JPanel();
	JRadioButton radioButton_Server = new JRadioButton("");
	JRadioButton radioButton_Client = new JRadioButton("");
	JButton btnEnter = new JButton("Enter");
	
	JLabel label_Background = new JLabel("");
	JLabel label_Client = new JLabel("Client");
	JLabel label_Nickname = new JLabel("Nickname: ");
	JLabel label_Hint = new JLabel("Please choose and enter the nickname.");
	JLabel label_IPPort = new JLabel("IP : Port: ");
	JLabel label_Server = new JLabel("Server");
	JLabel label_Split = new JLabel(":");
	JLabel label_Version = new JLabel("Version");
	
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
		frmTogetherInitialization.setResizable(false);
		frmTogetherInitialization.setTitle("Together - Initialization");
		frmTogetherInitialization.setBounds(100, 100, 325, 173);
		frmTogetherInitialization.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTogetherInitialization.getContentPane().setLayout(null);
		frmTogetherInitialization.setLocationRelativeTo(null);
		frmTogetherInitialization.getContentPane().add(panel_1);
		
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 0, 321, 147);
		panel_1.setLayout(null);
		
		panel.setLayout(null);
		
		panel.setBounds(0, 0, 321, 147);
		btnEnter.setBounds(10, 114, 301, 23);
		radioButton_Server.setBounds(73, 40, 12, 13);
		radioButton_Client.setBounds(161, 40, 12, 13);
		label_Client.setBounds(178, 35, 92, 23);
		label_Nickname.setBounds(46, 64, 75, 15);
		label_Hint.setBounds(6, 10, 305, 15);
		label_IPPort.setBounds(46, 89, 75, 15);
		label_Server.setBounds(90, 35, 92, 23);
		label_Split.setBounds(240, 89, 12, 15);
		label_Version.setBounds(271, 132, 50, 15);
		textField_NickName.setBounds(119, 64, 120, 21);
		textField_IP.setBounds(119, 89, 120, 21);
		textField_Port.setBounds(253, 89, 50, 21);
		label_Background.setBounds(0, 0, 321, 147);
		
		radioButton_Server.setSelected(true);
		
		
		
		// FIXME Fonts
		try {
			btnEnter.setFont(Font.createFont(Font.TRUETYPE_FONT,  getClass().getResourceAsStream("/together/fonts/a.ttc")).deriveFont(Font.BOLD, 17));
			label_Client.setFont(Font.createFont(Font.TRUETYPE_FONT,  getClass().getResourceAsStream("/together/fonts/d.ttc")).deriveFont(Font.PLAIN, 20));
			label_Nickname.setFont(Font.createFont(Font.TRUETYPE_FONT,  getClass().getResourceAsStream("/together/fonts/c.ttc")).deriveFont(Font.PLAIN, 12));
			label_Hint.setFont(Font.createFont(Font.TRUETYPE_FONT,  getClass().getResourceAsStream("/together/fonts/b.ttc")).deriveFont(Font.PLAIN, 12));
			label_IPPort.setFont(Font.createFont(Font.TRUETYPE_FONT,  getClass().getResourceAsStream("/together/fonts/c.ttc")).deriveFont(Font.PLAIN, 12));
			label_Server.setFont(Font.createFont(Font.TRUETYPE_FONT,  getClass().getResourceAsStream("/together/fonts/d.ttc")).deriveFont(Font.PLAIN, 20));
			label_Split.setFont(Font.createFont(Font.TRUETYPE_FONT,  getClass().getResourceAsStream("/together/fonts/f.ttc")).deriveFont(Font.BOLD, 14));
			label_Version.setFont(Font.createFont(Font.TRUETYPE_FONT,  getClass().getResourceAsStream("/together/fonts/e.ttc")).deriveFont(Font.ITALIC, 12));
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}
		
		// FIXME Alignments
		radioButton_Server.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_Client.setHorizontalAlignment(SwingConstants.CENTER);
		label_Nickname.setHorizontalAlignment(SwingConstants.CENTER);
		label_Hint.setHorizontalAlignment(SwingConstants.CENTER);
		label_IPPort.setHorizontalAlignment(SwingConstants.CENTER);
		label_Split.setHorizontalAlignment(SwingConstants.CENTER);
		label_Version.setHorizontalAlignment(SwingConstants.CENTER);
		
		//FIXME TipText
		label_Version.setToolTipText(VERSION);
		textField_NickName.setToolTipText("The nickname you want to use.");
		textField_IP.setToolTipText("");
		textField_Port.setToolTipText("Set the port to be use, or leave a blank to use default port \"14487\".");
		
		textField_NickName.setColumns(10);
		textField_IP.setColumns(10);
		textField_IP.setEditable(false);
		
		textField_Port.setColumns(10);
		
		frmTogetherInitialization.getContentPane().add(panel);
		
		label_Background.setIcon(new ImageIcon(Window.class.getResource("/together/image/home.jpg")));

		bGroup.add(radioButton_Server);
		bGroup.add(radioButton_Client);
		
		panel_1.add(btnEnter);
		panel_1.add(radioButton_Server);
		panel_1.add(radioButton_Client);
		panel_1.add(label_Client);
		panel_1.add(label_Nickname);
		panel_1.add(label_Hint);
		panel_1.add(label_IPPort);
		panel_1.add(label_Server);
		panel_1.add(label_Split);
		panel_1.add(label_Version);
		panel_1.add(textField_NickName);
		panel_1.add(textField_IP);
		panel_1.add(textField_Port);
		panel.add(label_Background);
		
		// FIXME Actions
		btnEnter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if((textField_NickName.getText().length()!=0 && textField_IP.getText().length()!=0 && textField_Port.getText().length()!=0) || (textField_NickName.getText().length()!=0 && pushbackdata[3]=="True")) {
					pushbackdata[0] = textField_NickName.getText();
					if(pushbackdata[3]=="False") {
						pushbackdata[2] = textField_Port.getText();
						pushbackdata[1] = textField_IP.getText();
					}else {
						if(textField_Port.getText().length()!=0) {
							pushbackdata[2] = textField_Port.getText();
						}
					}
					isEnd = true;
				}
			}
		});

		radioButton_Server.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField_IP.setEditable(false);	
				textField_IP.setToolTipText("");
				textField_IP.setText("");
				textField_Port.setToolTipText("Set the port to be use, or leave a blank to use default port \"14487\".");
				pushbackdata[3] = "True";		
			}
		});
		
		radioButton_Client.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField_IP.setEditable(true);
				textField_IP.setToolTipText("The server's IP.");
				textField_Port.setToolTipText("The port that server using.");
				pushbackdata[3] = "False";
			}
			
		});
		label_Version.addMouseListener(new MouseListener() {
			
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
						textField_NickName.setText("DefaultServer");
						textField_IP.setText("");
						textField_Port.setText("");
					}
					else { //Client
						textField_NickName.setText("DefaultClient");
						textField_IP.setText("127.0.0.1");
						textField_Port.setText("14487");
					}
				}
			}
			
		});
		
		textField_NickName.addKeyListener(new KeyListener() {
			
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
		
		textField_IP.addKeyListener(new KeyListener() {
			
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
		
		textField_Port.addKeyListener(new KeyListener() {
			
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
	}
}
