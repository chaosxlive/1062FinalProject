package together;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import together.tools.FrameShake;
import together.tools.ImageChecker;
import together.tools.ImageResize;
import together.tools.ScreenShotTool;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.border.LineBorder;

public class ServerCode extends java.lang.Thread {

	private JFrame frmServer;
	
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JPanel emjpanel = new JPanel();
	private JTextField textField = new JTextField();
	private JTextPane textPane = new JTextPane();
	private JLabel lblNewLabel = new JLabel("");
	private JLabel EmojiTitle = new JLabel("Emoji");
	private boolean emjisvisable = false;
	private JButton emojiswtich = new JButton("");
	private JButton emoji01 = new JButton("");
	private JButton emoji02 = new JButton("");
	private JButton emoji03 = new JButton("");
	private JButton emoji04 = new JButton("");
	private JButton emoji05 = new JButton("");
	private JButton emoji06 = new JButton("");
	private JButton emoji07 = new JButton("");
	private JButton emoji08 = new JButton("");
	private JButton emoji09 = new JButton("");
	private JButton emoji10 = new JButton("");
	private JButton emoji11 = new JButton("");
	private JButton emoji12 = new JButton("");
	private JButton btnNewButton = new JButton("Enter");
	private JButton snapbtn = new JButton("");
	private JButton imgbtn = new JButton("");
	private JButton shockbtn = new JButton("");
	private JScrollPane scrollPane = new JScrollPane(textPane);
	
	private ScreenShotTool sTool = new ScreenShotTool();
	private FrameShake frameshake = new FrameShake();
	
//	private AudioClip se_DingDong = Applet.newAudioClip(getClass().getResource("/together/se/ding-dong.mp3"));
	private AudioInputStream audioInputStream;
 	private AudioFormat audioFormat;
 	private Clip se;
 	
	@SuppressWarnings("unused")
	private ImgSender snd;
	private ImgReceiver rcv;
	private int picnum = 0;
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	
	private void initialize() {
		
		frmServer = new JFrame();
		frmServer.setTitle("Server - " + name);
		frmServer.setBounds(100, 100, 450, 300);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		frmServer.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				if(frmServer.getWidth()>1920) {
					frmServer.setSize(1920, frmServer.getHeight());
				}
				if (frmServer.getHeight()>1080) {
					frmServer.setSize(frmServer.getWidth(), 1080);
				}
				if(frmServer.getWidth()<450) {
					frmServer.setSize(450, frmServer.getHeight());
				}
				if (frmServer.getHeight()<300) {
					frmServer.setSize(frmServer.getWidth(), 300);
				}
				panel_1.setSize(frmServer.getSize());
				lblNewLabel.setSize(frmServer.getSize());
				lblNewLabel.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/bg1.jpg")));
				
				refreshNodes();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		
		panel.setOpaque(false);
//		panel.setBounds(0, 0, 444, 272);
		frmServer.getContentPane().add(panel);
		panel.setLayout(null);
//		btnNewButton.setBounds(347, 240, 87, 23);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()!=0) {
					try {
						if(detectCmd()) {
							runCmd();
						}else {
							writer.println(textField.getText());
							addDoc(name + ": " + textField.getText() + "\n");
							textPane.setCaretPosition(textPane.getDocument().getLength());
							writer.flush();
							textField.setText("");												
						}							
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textPane.setFont(new Font("華康歐陽詢體W5", Font.PLAIN, 12));
		textPane.setBackground(Color.WHITE);
		textPane.setEditable(false);
		panel.add(scrollPane);
		
		panel.add(textField);
		textField.setEditable(false);
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
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
				}
			}
		});
		
		/*
		 * Emoji Setting
		 */
		emojiswtich.setEnabled(false);
		emojiswtich.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/IMO_0.png")));
		emojiswtich.setBounds(340, 10, 32, 32);
		emojiswtich.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(emjisvisable) {
					emjpanel.setVisible(false);
					emjisvisable = false;
					
				}
				else {
					emjpanel.setVisible(true);
					emjisvisable = true;
					emoji01.repaint();
				}
			}
		});
		panel.add(emojiswtich);
		
		emjpanel.setVisible(false);
		panel.add(emjpanel);
		emjpanel.setLayout(null);
		
		EmojiTitle.setBorder(new LineBorder(new Color(0, 255, 255), 2));
		EmojiTitle.setFont(new Font("華康竹風體W4", Font.BOLD, 13));
		EmojiTitle.setHorizontalAlignment(SwingConstants.CENTER);
		EmojiTitle.setBounds(0, 0, 100, 20);
		//e1
		emjpanel.add(EmojiTitle);
		emoji01.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_1.png")));
		emoji01.setBackground(Color.LIGHT_GRAY);
		emoji01.setBounds(2, 28, 32, 32);
		emjpanel.add(emoji01);
		emoji01.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_1.png")));
				writer.println("/emoji 01");
				writer.flush();
			}
		});
		//--
		//e2
		emoji02.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_2.png")));
		emoji02.setBackground(Color.LIGHT_GRAY);
		emoji02.setBounds(34, 28, 32, 32);
		emjpanel.add(emoji02);
		emoji02.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_2.png")));
				writer.println("/emoji 02");
				writer.flush();
			}
		});
		//--
		//e3
		emoji03.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_3.png")));
		emoji03.setBackground(Color.LIGHT_GRAY);
		emoji03.setBounds(66, 28, 32, 32);
		emjpanel.add(emoji03);
		emoji03.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_3.png")));
				writer.println("/emoji 03");
				writer.flush();
			}
		});
		//--
		//e4
		emoji04.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_4.png")));
		emoji04.setBackground(Color.LIGHT_GRAY);
		emoji04.setBounds(2, 68, 32, 32);
		emjpanel.add(emoji04);
		emoji04.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_4.png")));
				writer.println("/emoji 04");
				writer.flush();
			}
		});
		//--
		//e5
		emoji05.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_5.png")));
		emoji05.setBackground(Color.LIGHT_GRAY);
		emoji05.setBounds(34, 68, 32, 32);
		emjpanel.add(emoji05);
		emoji05.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_5.png")));
				writer.println("/emoji 05");
				writer.flush();
			}
		});
		//--
		//e6
		emoji06.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_6.png")));
		emoji06.setBackground(Color.LIGHT_GRAY);
		emoji06.setBounds(66, 68, 32, 32);
		emjpanel.add(emoji06);
		emoji06.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_6.png")));
				writer.println("/emoji 06");
				writer.flush();
			}
		});
		//--
		//e7
		emoji07.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_7.png")));
		emoji07.setBackground(Color.LIGHT_GRAY);
		emoji07.setBounds(2, 108, 32, 32);
		emjpanel.add(emoji07);
		emoji07.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_7.png")));
				writer.println("/emoji 07");
				writer.flush();
			}
		});
		//--
		//e8
		emoji08.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_8.png")));
		emoji08.setBackground(Color.LIGHT_GRAY);
		emoji08.setBounds(34, 108, 32, 32);
		emjpanel.add(emoji08);
		emoji08.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_8.png")));
				writer.println("/emoji 08");
				writer.flush();
			}
		});
		//--
		//e9
		emoji09.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_9.png")));
		emoji09.setBackground(Color.LIGHT_GRAY);
		emoji09.setBounds(66, 108, 32, 32);
		emjpanel.add(emoji09);
		emoji09.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_9.png")));
				writer.println("/emoji 09");
				writer.flush();
			}
		});
		//--
		//e10
		emoji10.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_10.png")));
		emoji10.setBackground(Color.LIGHT_GRAY);
		emoji10.setBounds(2, 148, 32, 32);
		emjpanel.add(emoji10);
		emoji10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_10.png")));
				writer.println("/emoji 10");
				writer.flush();
			}
		});
		//--
		//e11
		emoji11.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_11.png")));
		emoji11.setBackground(Color.LIGHT_GRAY);
		emoji11.setBounds(34, 148, 32, 32);
		emjpanel.add(emoji11);
		emoji11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_11.png")));
				writer.println("/emoji 11");
				writer.flush();
			}
		});
		//--
		//e12
		emoji12.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/Imo_12.png")));
		emoji12.setBackground(Color.LIGHT_GRAY);
		emoji12.setBounds(66, 148, 32, 32);
		emjpanel.add(emoji12);
		
		emoji12.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addDoc(name + ": ");
				addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_12.png")));
				writer.println("/emoji 12");
				writer.flush();
			}
		});
		//--
		snapbtn.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/snap_30.png")));
		snapbtn.setEnabled(false);
		panel.add(snapbtn);
		snapbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				picnum = 0;
				File picFile;
				do {
					picnum++;
					picFile = new File(sTool.getPath() + "Pic_" + picnum + "." + sTool.getFormat());
				}while(picFile.exists());
				sTool.setFileName("Pic_" + picnum);
				Thread thread = new Thread(sTool);
				thread.start();
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							thread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						addDoc(name + ": ");
						addClickableAndResizedDoc(sTool.getPath() + "Pic_" + picnum + "." + sTool.getFormat());
						writer.println("/image");
						writer.flush();
						snd = new ImgSender(port, socket.getInetAddress().toString().substring(1), sTool.getPath() + "Pic_" + picnum, sTool.getFormat());
					}
				}).start();
				addDoc(sTool.getPath());
			}
		});
		
		imgbtn.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/img30.png")));
		imgbtn.setEnabled(false);
		panel.add(imgbtn);
		imgbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int revar = fileChooser.showOpenDialog(null);
				if(revar==JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if(ImageChecker.isImage(file)) {
						addDoc(name + ": ");
						addClickableAndResizedDoc(file.getPath());
						writer.println("/image");
						writer.flush();
						snd = new ImgSender(port, socket.getInetAddress().toString().substring(1), file.getPath());
					}
				}
			}
		});

		shockbtn.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/DingDong_30.png")));
		shockbtn.setEnabled(false);
		panel.add(shockbtn);
		shockbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				writer.println("/shake");
				writer.flush();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		frmServer.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel.setIcon(new ImageIcon(ServerCode.class.getResource("/together/image/bg1.jpg")));
		panel_1.add(lblNewLabel);
		
		refreshNodes();
	}
	
	private void resetSe() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(ServerCode.class.getResource("/together/se/ding-dong.wav"));
		audioFormat = audioInputStream.getFormat();
		int bufferSize = (int) Math.min(audioInputStream.getFrameLength() * audioFormat.getFrameSize(), Integer.MAX_VALUE); 
		DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat, bufferSize);
		se = (Clip) AudioSystem.getLine(dataLineInfo);
		se.open(audioInputStream);
	}
	
	/*
	private void addDoc(String string, Color c) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
	    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
	                                        StyleConstants.Foreground, c);
	   
	    int len = textPane.getDocument().getLength(); // same value as getText().length();
	    textPane.setCaretPosition(len);  // place caret at the end (with no selection)
	    textPane.setCharacterAttributes(aset, false);
	    textPane.replaceSelection(string); // there is no selection, so inserts at caret
	}	*/
	private void addDoc(String string) {
		javax.swing.text.Document document = textPane.getDocument();
		try {
			document.insertString(document.getLength(), string, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	private void addDoc(Icon icon) {
		StyleContext context = new StyleContext();
		javax.swing.text.Document document = textPane.getDocument();
		Style labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);
	    JLabel label = new JLabel(icon);
	    StyleConstants.setComponent(labelStyle, label);
		try {
			document.insertString(document.getLength(), "Ignored", labelStyle);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		addDoc("\n");
		textPane.setCaretPosition(textPane.getDocument().getLength());
	}
	
	private void refreshNodes() {
		scrollPane.setBounds(10, 10, panel.getWidth()-170, panel.getHeight()-51);
		panel.setBounds(0, 0, frmServer.getWidth()-6, frmServer.getHeight()-28);
		textField.setBounds(10, panel.getHeight()-37, textPane.getWidth(), 21);
		btnNewButton.setBounds(textPane.getWidth()+20, panel.getHeight()-37, 87, 23);
		emojiswtich.setBounds(textPane.getWidth()+33, 10, 32, 32);
		snapbtn.setBounds(textPane.getWidth()+66, 10, 32, 32);
		imgbtn.setBounds(textPane.getWidth()+99, 10, 32, 32);
		shockbtn.setBounds(textPane.getWidth()+132, 10, 32, 32);
		emjpanel.setBounds(textPane.getWidth()+32, 42, 100, 188);
	}
	
	private boolean isOpened = false;
	private void addClickableAndResizedDoc(String string) {
		ImageResize imageResize = new ImageResize(240, 160);
		
		Icon icon = imageResize.doResize(string);
		StyleContext context = new StyleContext();
		javax.swing.text.Document document = textPane.getDocument();
		Style labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);
	    JLabel label = new JLabel(icon);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
	    label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isOpened) {
					isOpened = true;
					JFrame photoFrame = new JFrame("Together - Photo");
					ImageIcon imageIcon = new ImageIcon(string);
					photoFrame.setLocation(frmServer.getX() + frmServer.getWidth(), frmServer.getY());
					photoFrame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
					photoFrame.getContentPane().add(new JLabel(imageIcon));
					photoFrame.setResizable(false);
					photoFrame.setVisible(true);
					photoFrame.addWindowListener(new WindowListener() {
												
						@Override
						public void windowOpened(WindowEvent e) {
						}
						
						@Override
						public void windowIconified(WindowEvent e) {
						}
						
						@Override
						public void windowDeiconified(WindowEvent e) {
						}
						
						@Override
						public void windowDeactivated(WindowEvent e) {
						}
						
						@Override
						public void windowClosing(WindowEvent e) {
							isOpened = false;
						}
						
						@Override
						public void windowClosed(WindowEvent e) {
							isOpened = false;
							photoFrame.dispose();
						}
						
						@Override
						public void windowActivated(WindowEvent e) {
						}
					});
				}
			}
		});
	    StyleConstants.setComponent(labelStyle, label);
		try {
			document.insertString(document.getLength(), "Ignored", labelStyle);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		addDoc("\n");
		textPane.setCaretPosition(textPane.getDocument().getLength());
	}
	
	private ServerSocket serverSocket;
	private Socket socket;
	private String name, fname;
	private int port;
	public ServerCode(int port, String name) {
		this.name = name;
		this.port = port;
		initialize();
		frmServer.setVisible(true);
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private PrintStream writer;
	private BufferedReader reader;
	@Override
	public void run() {
		try {
			addDoc("Server started... \nWaiting for connecting.\n");
			try { 
				InetAddress localhost = InetAddress.getLocalHost(); 
				addDoc("LocalIP: "+ localhost.getHostAddress() + ":" + port + "\n");
			} catch(UnknownHostException uhe) { 
				addDoc("Can't get IP.\n");
			}
			socket = serverSocket.accept();
			textField.setEditable(true);
			emojiswtich.setEnabled(true);
			snapbtn.setEnabled(true);
			imgbtn.setEnabled(true);
			shockbtn.setEnabled(true);
			serverSocket.close();
			
			writer = new PrintStream(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			fname = reader.readLine();
			writer.println(name);
			writer.flush();
			
			addDoc(fname + " Connected.\n");
			while(true) {
				System.out.println();
				Thread.sleep(100);
				String rcvtext = reader.readLine();
				if(detectFCmd(rcvtext)) {
					runFCmd(rcvtext);
				}else {
					addDoc(fname + ": " + rcvtext + "\n");
					textPane.setCaretPosition(textPane.getDocument().getLength());					
				}
			}			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean detectCmd() throws BadLocationException {
		char[] text = textField.getText().toCharArray();
		if(text[0]=='/') {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean detectFCmd(String rcvtext) throws IOException {
		char[] rcvtextch = rcvtext.toCharArray();
		if(rcvtextch[0]=='/') {
			return true;
		}else {
			return false;
		}
	}
	
	public void runCmd() throws IOException, BadLocationException {
		if(textField.getText().equals("/help")) {
			addDoc("->This is a command helper.\n");
			addDoc("*******************************************************\n");
			addDoc("* /help - Command helper.\n");
			addDoc("* /background [num(0~3)] - Change background.\n");
			addDoc("* /emoji [num(01~12)] - Send emoji.\n");
			addDoc("* /shake - Shake the chat of your friend.\n");
			addDoc("* /exit - Leave the chat room and stop program.\n");
			addDoc("*******************************************************\n");
			textPane.setCaretPosition(textPane.getDocument().getLength());
		}
		else if(textField.getText().equals("/background 0")) {
			lblNewLabel.setIcon(null);
			panel_1.repaint();
			panel.repaint();
		}
		else if(textField.getText().equals("/background 1")) {
			lblNewLabel.setIcon(new ImageIcon(ClientCode.class.getResource("/together/image/bg1.jpg")));
			panel_1.repaint();
			panel.repaint();
		}
		else if(textField.getText().equals("/background 2")) {
			lblNewLabel.setIcon(new ImageIcon(ClientCode.class.getResource("/together/image/bg2.jpg")));
			panel_1.repaint();
			panel.repaint();
		}
		else if(textField.getText().equals("/background 3")) {
			lblNewLabel.setIcon(new ImageIcon(ClientCode.class.getResource("/together/image/bg3.jpg")));
			panel_1.repaint();
			panel.repaint();
		}
		else if(textField.getText().equals("/exit")) {
			writer.println(textField.getText());
			writer.flush();
			socket.close();
			frmServer.dispose();
		}
		else if(textField.getText().equals("/emoji 01")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_1.png")));
			writer.println("/emoji 01");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 02")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_2.png")));
			writer.println("/emoji 02");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 03")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_3.png")));
			writer.println("/emoji 03");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 04")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_4.png")));
			writer.println("/emoji 04");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 05")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_5.png")));
			writer.println("/emoji 05");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 06")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_6.png")));
			writer.println("/emoji 06");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 07")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_7.png")));
			writer.println("/emoji 07");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 08")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_8.png")));
			writer.println("/emoji 08");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 09")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_9.png")));
			writer.println("/emoji 09");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 10")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_10.png")));
			writer.println("/emoji 10");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 11")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_11.png")));
			writer.println("/emoji 11");
			writer.flush();
		}
		else if(textField.getText().equals("/emoji 12")) {
			addDoc(name + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_12.png")));
			writer.println("/emoji 12");
			writer.flush();
		}
		else if(textField.getText().equals("/shake")) {
			writer.println("/shake");
			writer.flush();
		}
		else {
			addDoc("->Unknown command.\n");
		}
		textField.setText("");	
	}
	
	
	public void runFCmd(String text) throws IOException, BadLocationException {
		if(text.equals("/exit")) {
			socket.close();
			addDoc("####################################\n");
			addDoc("Your friend has left.\n");
			addDoc("Program will be closed in 3 seconds.\n");
			textPane.setCaretPosition(textPane.getDocument().getLength());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			frmServer.dispose();
		}
		else if(text.equals("/emoji 01")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_1.png")));
		}
		else if(text.equals("/emoji 02")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_2.png")));
		}
		else if(text.equals("/emoji 03")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_3.png")));
		}
		else if(text.equals("/emoji 04")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_4.png")));
		}
		else if(text.equals("/emoji 05")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_5.png")));
		}
		else if(text.equals("/emoji 06")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_6.png")));
		}
		else if(text.equals("/emoji 07")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_7.png")));
		}
		else if(text.equals("/emoji 08")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_8.png")));
		}
		else if(text.equals("/emoji 09")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_9.png")));
		}
		else if(text.equals("/emoji 10")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_10.png")));
		}
		else if(text.equals("/emoji 11")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_11.png")));
		}
		else if(text.equals("/emoji 12")) {
			addDoc(fname + ": ");
			addDoc(new ImageIcon(ServerCode.class.getResource("/together/image/ImoSv_12.png")));
		}
		else if(text.equals("/image")) {
			picnum = 0;
			File picFile;
			do {
				picnum++;
				picFile = new File(new File("Together").getAbsolutePath() + "/Pic_" + picnum + "." + sTool.getFormat());
			}while(picFile.exists());
			rcv = new ImgReceiver(port);
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						rcv.doReceive(new File("Together").getAbsolutePath() + "/Pic_" + picnum, "png");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			try {
				t.start();
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			addDoc(fname + ": ");
			addClickableAndResizedDoc(rcv.getFile());
		}
		else if(text.equals("/shake")) {
			if(!frmServer.isFocused()) {
				boolean isMaxed = (frmServer.getExtendedState() == JFrame.MAXIMIZED_BOTH) ? true : false;
				frmServer.setExtendedState(JFrame.ICONIFIED);
				frmServer.setExtendedState((isMaxed) ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);
			}
			try {
				resetSe();
			} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
				e.printStackTrace();
			}
			se.start();
			
			frameshake.startShake(frmServer);
		}
	}
}
