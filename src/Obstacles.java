import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.ImageGraphicAttribute;

public class Obstacles  extends JPanel{

        private int speed;
        private int x;
        private int y;
        private int width;
        private  int height;

        Image ob1;
        Image ob2;




        public Obstacles(int x,int y,int width,int height,int speed) {
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
                    this.speed=speed;

        }

        public void paint(Graphics g)
        {
                ob1=new ImageIcon("ob1.png").getImage();
        }





        public void update() {
            y += speed;
        }



        public int getX() {
    return x;
        }

        public int getY() {
    return y;
        }

        public int getWidth() {
    return width;
        }

        public int getHeight() {
    return height;
        }
    }

