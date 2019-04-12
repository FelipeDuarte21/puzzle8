package core;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Felipe Duarte
 */
public class ImageManager {
    
    private static ImageManager instance;
    
    private HashMap<String,BufferedImage> images;
    
    private ImageManager(){
        images = new HashMap<>();
    }
    
    public static ImageManager getInstance(){
        if(instance == null){
            instance = new ImageManager();
        }
        return instance;
    }
    
    public BufferedImage loadImage(String fileName) throws IOException{
        
        URL url = getClass().getResource("/" + fileName);
        
        if(url == null){
            throw new 
            RuntimeException("A imagem " + fileName + " não foi encontrada!");
        }else{
            
            String path = url.getPath();
            
            if(images.containsKey(path)){
                return images.get(path);
            }else{
                BufferedImage img = ImageIO.read(url);
                images.put(path, img);
                return img;
            }
            
        }
        
    }
    
    public BufferedImage loadImage(String fileName,int x,int y,int width,int height)
    throws IOException{
        BufferedImage sheet = loadImage(fileName);
        BufferedImage img = sheet.getSubimage(x, y, width, height);
        return img;
    }
    
}
