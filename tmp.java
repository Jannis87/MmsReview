import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

public class PptToPng {
   
   public static void main(String args[]) throws IOException{
      
      //creating an empty presentation
      File file=new File("/Users/Jannis/Documents/Präsentation1.pptx");
      XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
      
      //getting the dimensions and size of the slide 
      Dimension pgsize = ppt.getPageSize();
      List<XSLFSlide> slide = ppt.getSlides();
      
      for (int i = 0; i < slide.size(); i++) {
         BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,BufferedImage.TYPE_INT_RGB);
         Graphics2D graphics = img.createGraphics();

         //clear the drawing area
         graphics.setPaint(Color.white);
         graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

         //render
         slide.get(i).draw(graphics);
         
         FileOutputStream out = new FileOutputStream("/Users/Jannis/Documents/ppt_image_"+i+".png");
         javax.imageio.ImageIO.write(img, "png", out);
         out.close();
         //ppt.write(out);
      }
      
      //creating an image file as output

      
      System.out.println("Image successfully created");
      //out.close();	
   }
}
