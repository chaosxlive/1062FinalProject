package together.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ScreenShotTool implements Runnable {
	/*
	 * Version 4
	 */
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private String imgFormat = "png";
	private String fileName = "screenshot";
	private String path = new File("Together").getAbsolutePath() + "\\";
	

	public void getPos() {
		JFrame demo = new JFrame();
		demo.setExtendedState(JFrame.MAXIMIZED_BOTH);
		demo.setAlwaysOnTop(true);
		demo.setResizable(false);
		demo.setUndecorated(true);
	}
	
	public void snapshot() {
		try {
			BufferedImage bufferedImage = (new Robot()).createScreenCapture(new Rectangle(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight()));
			String name = this.fileName + "." + this.imgFormat;
			File file = new File(path + name);
			ImageIO.write(bufferedImage, this.imgFormat, file);
		} catch (Exception e) {
		}
	}
	
	public void snapshot_Part() {
		SnapFrame snapFrame = new SnapFrame();
		try {
			snapFrame.getRectangle(true);
			if(!snapFrame.isCanceled()) {
				BufferedImage bufferedImage = (new Robot()).createScreenCapture(snapFrame.getRectangle(false));
				String name = this.fileName + "." + this.imgFormat;
				File file = new File(path + name);
				ImageIO.write(bufferedImage, this.imgFormat, file);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setImgFormat(String imgFormat) {
		this.imgFormat = imgFormat;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getFormat() {
		return this.imgFormat;
	}
	
	public String getPath() {
		return this.path;
	}

	@Override
	public void run() {
		snapshot_Part();
	}
	
//	public static void main(String[] args) {
//		ScreenShotTool rb = new ScreenShotTool();
//		
//		rb.snapshot_Part();
//	}
}

class SnapFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int Xt, Yt, X, Y, W, H;
	private JPanel jPanel = new JPanel();
	private boolean snapDone = false;
	private boolean canceled = false;
	
	public SnapFrame() {
		this.canceled = false;
		this.setLayout(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setUndecorated(true);
		com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.5f);
		this.setVisible(true);
		//Lost Focus
		this.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				closeFrame();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
			}
		});
		//KeyBoardEvent
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				closeFrame();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		//MouseMotion
		this.getContentPane().addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(Xt<=e.getX()) {
					X = Xt;
					W = e.getX()-Xt;
				}
				else {
					X = e.getX();
					W = Xt - e.getX();
				}
				
				if(Yt<=e.getY()) {
					Y = Yt;
					H = e.getY()-Yt;
				}
				else {
					Y = e.getY();
					H = Yt - e.getY();
				}
				jPanel.setBounds(X, Y, W, H);
				jPanel.repaint();
			}
		});
		//MouseEvent
		this.getContentPane().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				snapDone = true;
				closeFrame();
			}
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3) {
					canceled = true;
					closeFrame();
				}
				else if(e.getButton()==MouseEvent.BUTTON1) {
					Xt = e.getX();
					Yt = e.getY();
					jPanel.setBounds(Xt, Yt, 0, 0);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		this.add(jPanel);
		jPanel.setBounds(0, 0, 0, 0);
		jPanel.setBorder(new LineBorder(Color.RED));
	}
	
	public Rectangle getRectangle(boolean wait) {
		if(wait) {
			while(!snapDone) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			snapDone = false;			
		}
		return new Rectangle(X, Y, W, H);
	}
	
	public boolean isCanceled() {
		return canceled;
	}
	
	private void closeFrame() {
		this.dispose();
	}
}