package together;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import together.tools.ImgFormatGetter;

public class ImgSender {
	
	private int port;
	private String ip, file;
	
	public ImgSender(int port, String ip, String path, String format) {
		this.ip = ip;
		this.port = port;
		
		try {
			doSend(path, format);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ImgSender(int port, String ip, String path) {
		this.ip = ip;
		this.port = port;
		
		try {
			doSend(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private void doSend(String path, String format) throws Exception {
        Socket socket = new Socket(ip, port);
        OutputStream outputStream = socket.getOutputStream();

        BufferedImage image = ImageIO.read(new File(path + "." + format));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, format, byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.flush();
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
//        System.out.println("Flushed: " + System.currentTimeMillis());

        Thread.sleep(120000);
//        System.out.println("Closing: " + System.currentTimeMillis());
        socket.close();
    }
    
    private void doSend(String path) throws Exception {
        Socket socket = new Socket(ip, port);
        OutputStream outputStream = socket.getOutputStream();

        BufferedImage image = ImageIO.read(new File(path));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, ImgFormatGetter.start(new File(path)), byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.flush();
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
//        System.out.println("Flushed: " + System.currentTimeMillis());

//        System.out.println("Closing: " + System.currentTimeMillis());
        socket.close();
    }
    
    public String getFile() {
    	return file;
    }
}
