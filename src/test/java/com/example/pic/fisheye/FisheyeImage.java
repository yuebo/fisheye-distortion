package com.example.pic.fisheye;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class FisheyeImage {
    private BufferedImage image;
    public FisheyeImage(File file) throws IOException{
        image = ImageIO.read(file);
    }
    public FisheyeImage(String file) throws Exception{
        this(new File(file));
    }
    public BufferedImage distortion() {
        int width_in  =  image.getWidth();
        int height_in  = image.getHeight();
        BufferedImage im_out = new BufferedImage(width_in,height_in,image.getType());
        double radius = Math.max(width_in, height_in)/2.0;
        double lens = radius*2.0/Math.PI;
        for (int i = 0; i < width_in; i++) {
            for (int j = 0; j < height_in; j++) {
                double x = i - width_in/2.0;
                double y = j - height_in/2.0;
                double r = Math.sqrt(x*x + y*y);
                double theta = Math.atan(r/radius);
                double k =theta<0.00001?1.0:lens*theta/r;
                double src_x = x*k;
                double src_y = y*k;
                src_x = src_x+width_in/2.0;
                src_y = src_y+height_in/2.0;
                im_out.setRGB(i,j,image.getRGB((int)src_x,(int)src_y));
            }
        }
        return im_out;
    }
    public void distortion(OutputStream outputStream) throws IOException{
        BufferedImage image = distortion();
        ImageIO.write(image,"jpg",outputStream);
    }
}
