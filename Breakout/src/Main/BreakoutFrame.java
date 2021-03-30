package Main;

import javax.swing.*;

public class BreakoutFrame extends JFrame {

    private static final int Height = 600;
    private static final int Width = 488;

    private BreakoutPanel panel = new BreakoutPanel();

    public BreakoutFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(100, 100);
        setSize(Width, Height);
        add(panel);
        setResizable(false);
    }
}
