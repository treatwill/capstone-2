package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Frames extends JFrame {

    private static final int Height = 600;
    private static final int Width = 488;

    private Panel panel = new Panel();

    public Frames() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayMenu();
        setLocation(100, 100);
        setSize(Width, Height);
        add(panel);
        setResizable(false);
    }

    public void displayMenu(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new GameMenu());
        menuBar.add(new ColorMenu());
        menuBar.add(new SpeedMenu());
        setJMenuBar(menuBar);
    }


    private class GameMenu extends JMenu {
        public GameMenu(){
            super("Game");
            JMenuItem startGameMI = new JMenuItem("Start" , 'S');
            startGameMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
            JMenuItem pauseMI = new JMenuItem("Pause" , 'P');
            pauseMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
            JMenuItem quitMI = new JMenuItem("Quit");
          quitMI.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });



            add(startGameMI);
            add(pauseMI);
            add(quitMI);
        }
    }

    private class ColorMenu extends JMenu{
        public ColorMenu(){
            super("Ball Color");
            for (BallColor color : BallColor.values()){
                JMenuItem menuItem = new JMenuItem(color.name()+ "Ball");
                menuItem.addActionListener(new BallColorListener(color));
                add(menuItem);
            }
        }
    }
    private class BallColorListener implements ActionListener{
        private BallColor color;

        public void actionPerformed(ActionEvent e){
            panel.changeBallColor(color);
        }
        public BallColorListener(BallColor color) {
            this.color = color;
        }
    }

    private class SpeedMenu extends JMenu{
        public SpeedMenu(){
            super("Ball Speed");
            for (BallSpeed s : BallSpeed.values()){
                JMenuItem menuItem = new JMenuItem(s.name());
                menuItem.addActionListener(new BallSpeedListener(s.speed()));
                add(menuItem);
            }
        }
    }

    private class BallSpeedListener implements ActionListener{
        private int speed;

        public void actionPerformed(ActionEvent e){
            panel.changeBallSpeed(speed);
        }
        public BallSpeedListener(int speed){
            this.speed = speed;
        }
    }
}