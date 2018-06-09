package together.tools;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageResize {
	/*
	 * Version 1
	 */
	int sizeMH, sizeMW;
	
	public ImageResize(int w, int h) {
		sizeMH = h;
		sizeMW = w;
	}
	
	public ImageIcon doResize(String filename) {
		Image inImage = Toolkit.getDefaultToolkit().createImage(filename);
		ImageIcon inImageIcon = new ImageIcon(filename);
		
		int imgH = inImageIcon.getIconHeight();
		int imgW = inImageIcon.getIconWidth();
		double scale;
		
		if(imgH<=sizeMH && imgW<=sizeMW) scale = 1;
		else if(imgH > imgW) scale = (double) sizeMH / (double) imgH;
		else scale = (double) sizeMW / (double) imgW;
		
		int scaledH = (int)(scale * imgH);
		int scaledW = (int)(scale * imgW);
		
		Image image = inImage.getScaledInstance(scaledW, scaledH, Image.SCALE_FAST);
		BufferedImage bufferedImage = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(image, 0, 0, null);
		
		return new ImageIcon(image);
	}
	public void setSize(int x, int y) {
		this.sizeMH = y;
		this.sizeMW = x;
	}
	
	public static void main(String[] args) {
		ImageResize imageResize = new ImageResize(480, 360);
		imageResize.setSize(400, 300);
		imageResize.doResize("./src/together/tools/SSTtemp/screenshot.png");
	}
}
