import javax.swing.*;

public class Gamegui {
    JFrame frame;
//    Car car;
    Road road;
    Gamegui(){
        frame=new JFrame("Car game");
//        frame.setSize(400,700);
//        car=new Car();
        road=new Road();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(car);
        frame.add(road);

        frame.pack();
        frame.setLayout(null);
        frame.addKeyListener(road);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
