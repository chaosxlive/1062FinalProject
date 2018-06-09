package together.tools;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FrameShake extends Object {
	public static final int SHAKE_DISTANCE = 10;
	public static final double SHAKE_CYCLE = 80;
	public static final int SHAKE_DURATION = 300;
	public static final int SHAKE_UPDATE = 5;
	private Point naturalLocation;
	private long startTime;
	private Timer shakeTimer;
	private final double TWO_PI = Math.PI * 2.0;
	
	public void startShake(JFrame frame) {
		naturalLocation = frame.getLocation();
		startTime = System.currentTimeMillis();
		shakeTimer = new Timer(SHAKE_UPDATE, new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				long elapsed = System.currentTimeMillis() - startTime;
				double waveOffset = (elapsed % SHAKE_CYCLE) / SHAKE_CYCLE;
				double angle = waveOffset * TWO_PI;
				double angley = waveOffset * TWO_PI;
				int shakenX = (int) ((Math.sin(angle) * SHAKE_DISTANCE) + naturalLocation.x);
				int shakenY = (int) ((Math.sin(angley) * SHAKE_DISTANCE) + naturalLocation.y);
				frame.setLocation (shakenX, shakenY);
				frame.repaint();
				if (elapsed >= SHAKE_DURATION)
					stopShake(frame);
				}
			});
		shakeTimer.start();
	}
	
	public void stopShake(JFrame frame) {
		shakeTimer.stop();
		frame.setLocation (naturalLocation);
		frame.repaint();
	}  
	
//	public static void main(String asrg[]){
//		JFrame frame = new JFrame();
//		JButton button = new JButton("OPEN");
//		button.addActionListener(new ActionListener(){
//			
//			public void actionPerformed(ActionEvent arg0) {
//				frame.setVisible(true);
//				frame.setSize(200, 200);
//				FrameShake shake = new FrameShake();
//				shake.startShake(frame);
//			}
//		
//		});
//		frame.setSize(500,500);
//		frame.setMinimumSize(new Dimension(500,500));
//		frame.getContentPane().add(button);
//		frame.setVisible(true);
//	}
}  