package together.tools;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageChecker {
	public static boolean isImage(File resFile){  
        ImageInputStream iis = null;  
        try {  
            iis = ImageIO.createImageInputStream(resFile);  
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);   
            if (iter.hasNext()) {
                return true;  
            }   
              
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            if(iis!=null){  
                try {  
                    iis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return false;  
    }  
}
