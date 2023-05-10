import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Title extends JFrame implements ActionListener {





    private JButton startButton;
    //private JButton settingsButton;
    private JButton exitButton;

    JLabel label = new JLabel();

    Title() {
        setTitle("GAME");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        //mainPanel.setBackground(Color.BLUE);
        mainPanel.setBounds(0,32,500,500);
        mainPanel.setLayout(null);
        // mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel titleLabel = new JLabel("2-D CAR GAME");
        titleLabel.setForeground(Color.darkGray);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 36));
        titleLabel.setBounds(370,27,300,50);
        mainPanel.add(titleLabel);
        //titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        //JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Verdana",Font.BOLD,15));
        startButton.setBounds(400,100,200,50);
        exitButton = new JButton("EXIT");
        startButton.setFont(new Font("Verdana",Font.BOLD,15));
        exitButton.setBackground(Color.red);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBounds(400,200,200,50);
        startButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        mainPanel.add(startButton);
        //mainPanel.add(settingsButton);
        mainPanel.add(exitButton);
        ImageIcon image = new ImageIcon();
        label.setIcon(loadImage("CAR.jpg"));
        label.setBounds(0,0,this.getWidth(),this.getHeight());
        mainPanel.add(label);
        getContentPane().add(mainPanel);
        setVisible(true);
    }
    private ImageIcon loadImage(String path){
        Image image = new ImageIcon("CAR.jpg").getImage();
        Image scaled = image.getScaledInstance(1000,500 , Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            Gamegui gui= new Gamegui();

//            gui.setVisible(true);

        }
        else if (e.getSource() == exitButton) {
            int confirmExit = JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (confirmExit == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }


}



