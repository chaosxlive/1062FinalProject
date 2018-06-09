package together;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class ImgReceiver {
	
	private int port;
	private String file;

	public ImgReceiver(int port) {
		this.port = port;
	}
	
    public void doReceive(String path, String format) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());

//        System.out.println("Reading: " + System.currentTimeMillis());

        byte[] sizeAr = new byte[4];
        inputStream.read(sizeAr);
        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
        byte[] imageAr = new byte[size];
        
        inputStream.readFully(imageAr);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
        inputStream.close();
        
//        System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
        ImageIO.write(image, format, new File(path + "." + format));
        file = path + "." + format;

        serverSocket.close();
    }
    
    public String getFile() {
    	return file;
    }

}