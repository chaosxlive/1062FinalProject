/*
 * Program name: Together
 * Made by: Jason Weng
 */

package together;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		String[] data = null;
		Window wins = new Window();
		data = wins.winRun();
		
		String name = data[0], ip = data[1];
		int port = Integer.parseInt(data[2]);
		boolean isServer = Boolean.parseBoolean(data[3]);
		
		File file = new File("Together");
		if(!file.exists())
		{
		    file.mkdirs();
		}
		
		if(isServer) {
			ServerCode server = new ServerCode(port, name);
			server.start();
		}else {
			ClientCode client = new ClientCode(ip, port, name);
			client.start();
		}
	}
}