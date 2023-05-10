import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
public class Car extends JPanel {
    public int xpos=155;
    public int ypos=540;

    public int rotation=0;

    Image image;


    public void draw(Graphics g)
    {
        Graphics2D g2d=(Graphics2D) g;

        image=new ImageIcon("p1.png").getImage();
//        g2d.drawImage(image,xpos,ypos,null);
        AffineTransform transform = new AffineTransform();
        transform.translate(xpos, ypos);
        transform.rotate(Math.toRadians(rotation), image.getWidth(null) / 2, image.getHeight(null) / 2);
        g2d.drawImage(image, transform, null);



    }

    public void move(int speed)
    {
        xpos=xpos+speed;


    }


    public void setRotation(int i) {
        System.out.println(rotation);
       if(i==8)
       {
           rotation+=8;
       } else if (i==-8) {
           rotation+=-8;
       }
       else {
           rotation=0;
       }
    }
}
