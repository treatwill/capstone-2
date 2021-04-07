package ui;


import game.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Frames extends JFrame {

    private static final int Height = 600;
    private static final int Width = 300;


    private Board board = new Board();


    public Frames() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayMenu();
        setLocation(100, 100);
        setSize(Width, Height);
        add(board);
        setResizable(false);
    }

    public void displayMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new GameMenu());
        setJMenuBar(menuBar);
        menuBar.add(new Instructions());
        setJMenuBar(menuBar);

    }


    private class GameMenu extends JMenu {
        public GameMenu() {
            super("Game");
            JMenuItem startGameMI = new JMenuItem("Start", 'S');
            startGameMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
            JMenuItem pauseMI = new JMenuItem("Pause", 'P');
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
    private class Instructions extends JMenu{
        public Instructions(){
            super("Instructions");
            JMenuItem movePaddle = new JMenuItem("Move Paddle", 'M');
            JMenuItem hitBall = new JMenuItem("Hit Ball", 'H');
            JMenuItem breakBrick = new JMenuItem("Break Brick", 'B');
            add(movePaddle);
            add(hitBall);
            add(breakBrick);
        }
    }
}

