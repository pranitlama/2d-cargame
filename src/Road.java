import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.awt.geom.AffineTransform;

public class Road extends JPanel implements ActionListener, KeyListener {
    private int width=400;
    private  int height=700;

    File scoreFile;

    FileWriter fileWriter;
    int highscore;

    private boolean movingright=false;
    private boolean movingleft=false;




    private int maxspeed=9; //obstacles max speed
    private int minspeed=6; //obstacles min speed


    Random random=new Random();
    JLabel score;

    int scorevalue;
    int y1velocity=0; //lane1 y
    int y2velocity=150; //lane 2 y
    int y3velocity=300; //lane 3 y
    int y4velocity=450; //lane 4 y

    //    int xpos=width/2-45;
//    int ypos=550;
    Car car;

    Obstacles obj1;
    Obstacles obj2;

    Obstacles obj3;

    Obstacles obj4;
    Image p1;

    Image ob1;
    Image ob2;

    int speed=1;

    Timer timer;


    int count=350;
    int scorecount=0;
    Road() throws IOException {
        car =new Car();
        score=new JLabel("Score:");


        obj1=new Obstacles(30,-100,60,90,3); //30
        obj2=new Obstacles(30,-100,60,90,3);
        obj3=new Obstacles(220,-100,60,90,5);//120
        obj4=new Obstacles(220,-100,60,90,5);
        this.setPreferredSize(new Dimension(width,height));


        this.add(score);


        score.setFont(new Font("Arial", Font.BOLD, 25));


        score.setBounds(0,400,50,50);

        this.setBackground(Color.black);



        p1=new ImageIcon("p1.png").getImage();
        ob1=new ImageIcon("ob1.png").getImage();
        ob2=new ImageIcon("ob2.png").getImage();
        getHighScore();
        timer=new Timer(1,this);


//        this.add(car);



        timer.start();

    }




    public void paint(Graphics g)

    {

        createobstacles();
        super.paint(g); //paint background
        Graphics2D g2d=(Graphics2D) g;

//        g2d.setColor(Color.red);
//        g2d.fillRect(obj1.getX(),obj1.getY(),obj1.getWidth(),obj1.getHeight());

        g2d.drawImage(ob1,obj1.getX(),obj1.getY(),null);
        g.setFont(new Font("Arial",Font.BOLD,15));
        g2d.drawString("Highscore:"+highscore,280,25);





        g2d.drawImage(ob1,obj2.getX(),obj2.getY(),null);



        g2d.drawImage(ob2,obj3.getX(),obj3.getY(),null);


        g2d.drawImage(ob2,obj4.getX(),obj4.getY(),null);


        g2d.setColor(Color.white);
        g2d.fillRect(width/2,y1velocity,10,50);

        g2d.setColor(Color.white);
        g2d.fillRect(width/2,y2velocity,10,50);

        g2d.setColor(Color.white);
        g2d.fillRect(width/2,y3velocity,10,50);

        g2d.setColor(Color.white);
        g2d.fillRect(width/2,y4velocity,10,50);

//        g2d.setColor(Color.RED);
//        g2d.fillRect(width/2-35,550,70,100);


//        g.setColor(Color.white);
//        g.fillRect(width/2,y5velocity,10,50);
//        g2d.drawImage(p1,xpos,ypos, null);

        car.draw(g2d);



        Rectangle carBounds = new Rectangle(car.xpos, car.ypos, p1.getWidth(null), p1.getHeight(null));
        Rectangle objBounds1 = new Rectangle(obj1.getX(), obj1.getY(), obj1.getWidth(), obj1.getHeight());
        Rectangle objBounds2 = new Rectangle(obj2.getX(), obj2.getY(), obj2.getWidth(), obj2.getHeight());
        Rectangle objBounds3 = new Rectangle(obj3.getX(), obj3.getY(), obj3.getWidth(), obj3.getHeight());
        Rectangle objBounds4 = new Rectangle(obj4.getX(), obj4.getY(), obj4.getWidth(), obj4.getHeight());

//        if (carBounds.intersects(objBounds1) ||carBounds.intersects(objBounds2)||carBounds.intersects(objBounds3)||carBounds.intersects(objBounds4)) {
//            // there is a collision between obj1 and the car image, do something
//            // for example, repaint the screen with a red background to indicate the collision
//            g2d.setColor(Color.RED);
//            g2d.fillRect(0, 0, width, height);
//            timer.stop();
//            System.out.println(scorevalue);
//        }

        if (carBounds.intersects(objBounds1) || carBounds.intersects(objBounds2) || carBounds.intersects(objBounds3) || carBounds.intersects(objBounds4)) {
            // there is a collision between the car image and one of the objects, do something
            // for example, repaint the screen with a red background to indicate the collision
            setHighscore();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, width, height);
            g2d.setColor(Color.WHITE);

            g.setFont(new Font("Arial",Font.BOLD,35));
            g2d.drawString("Your Score:"+scorevalue,80,300);
            g.setFont(new Font("Arial",Font.BOLD,35));
            g2d.drawString("Press R to retry",80,350);

            // get the intersection rectangle
            Rectangle intersection = carBounds.intersection(objBounds1);
            if (intersection.isEmpty()) {
                intersection = carBounds.intersection(objBounds2);
            }
            if (intersection.isEmpty()) {
                intersection = carBounds.intersection(objBounds3);
            }
            if (intersection.isEmpty()) {
                intersection = carBounds.intersection(objBounds4);
            }

            // get the center point of the intersection rectangle
            int centerX = intersection.x + intersection.width / 2;
            int centerY = intersection.y + intersection.height / 2;

            // draw a circle at the intersection point
//            g2d.setColor(Color.YELLOW);
//            g2d.fillOval(centerX - 5, centerY - 5, 10, 10);

            timer.stop();
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
//if(y5velocity>=700){
//    y5velocity=0;
//}

        if(movingright)
        {
            if(car.xpos<=330) {
                car.move(3);
                if(!(car.rotation>=8)) {
                    car.setRotation(8);
                }

            }
        } else if (movingleft) {
            if(car.xpos>=-3) {
                car.move(-3);
                if(!(car.rotation<=-8)) {
                    car.setRotation(-8);
                }

            }
        }


        score.setText("Score:" + scorevalue);

        if (y4velocity>=650) {
            y4velocity = 0;
            increase();
            repaint();
        }
        else if(y3velocity>=650)
        {
            y3velocity=0;
            increase();
            repaint();
        } else if (y2velocity>=650) {
            y2velocity=0;
            increase();
            repaint();
        } else if (y1velocity>=650) {
            y1velocity=0;
            increase();
            repaint();

        } else {

            obj1.update();
            obj2.update();
            obj3.update();
            obj4.update();

            y1velocity+=speed;
            y2velocity+=speed;
            y3velocity+=speed;
            y4velocity+=speed;
            count--;
//     System.out.println(count);

//            y5velocity+=speed;

            repaint();


        }

    }

    public  void increase(){
//        System.out.println(speed);
        scorevalue++;
        if(speed>=15) {

        }
        else{
            speed++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println(xpos);

        if(e.getKeyCode()==37)
        {
//            if(xpos>=0) {

//                xpos -= 12;
            movingleft=true;

//            }

        } else if (e.getKeyCode()==39) {
//            if(xpos<=290) {
//                xpos += 12;
            movingright = true;

//            }

        }else if(e.getKeyCode()==82){

            System.out.println("hello");
            try {
                reset();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37)
        {
            movingleft=false;
            car.setRotation(0);
        } else if (e.getKeyCode()==39) {
            movingright=false;
            car.setRotation(0);
        }
    }

    public void createobstacles(){
//        int r=random.nextInt(4);
        int randnum =(int)(Math.random() * 8 + 1 );
        int randspeed=random.nextInt((maxspeed-minspeed)+1)+minspeed;



        if(count==0)
        {

            switch (randnum){
                case 1:
                    obj1=new Obstacles(30,-100,60,90,5);
                    obj3=new Obstacles(220,-100,60,90,randspeed);




                    break;
                case 2:
                    obj2=new Obstacles(120,-100,60,90,5);
                    obj4=new Obstacles(310,-100,60,90,randspeed);

                    break;
                case 3:
                    obj3=new Obstacles(220,-100,60,90,randspeed);
                    obj2=new Obstacles(120,-100,60,90,5);

                    break;
                case 4:
                    obj4=new Obstacles(310,-100,60,90,randspeed);
                    obj1=new Obstacles(30,-100,60,90,5);

                    break;
                case 5:
                    obj4=new Obstacles(310,-100,60,90,randspeed);
                    obj1=new Obstacles(30,-100,60,90,5);
                    obj2=new Obstacles(120,-100,60,90,5);

                    break;
                case 6:
                    obj4=new Obstacles(310,-100,60,90,randspeed);
                    obj1=new Obstacles(30,-100,60,90,5);
                    obj3=new Obstacles(220,-100,60,90,randspeed+1);

                    break;
                case 7:
                    obj3=new Obstacles(220,-100,60,90,randspeed);
                    obj2=new Obstacles(120,-100,60,90,5);
                    obj1=new Obstacles(30,-100,60,90,5);

                    break;
                case 8:
                    obj3=new Obstacles(220,-100,60,90,randspeed);
                    obj2=new Obstacles(120,-100,60,90,5);
                    obj4=new Obstacles(310,-100,60,90,randspeed-1);

                    break;
                default:
                    System.out.println("error");
                    break;
            }




            count=150;
        }
    }


    public void reset() throws IOException {
        getHighScore();
        car.xpos=155;
        car.ypos=540;
        scorevalue=0;
        obj1=new Obstacles(30,-100,60,90,3); //30
        obj2=new Obstacles(30,-100,60,90,3);
        obj3=new Obstacles(220,-100,60,90,5);//120
        obj4=new Obstacles(220,-100,60,90,5);
        count=350;
        speed=1;

        timer.start();
    }


    public void getHighScore() throws IOException {
         scoreFile = new File("score.txt");
        BufferedReader reader =new BufferedReader(new FileReader(scoreFile));
        String Int_line;
        Int_line = reader.readLine();
        int value = Integer.parseInt(Int_line);
        highscore = value;
        System.out.println(highscore);
    }


    public  void setHighscore(){
        if(scorevalue > highscore)
        {
            try {

                scoreFile.delete();
                scoreFile.createNewFile();
                fileWriter = new FileWriter(scoreFile);
                fileWriter.write(scorevalue + "");
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
