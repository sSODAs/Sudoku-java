import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.*;

public class Menu extends JPanel implements ActionListener {

    private JButton StartBtn;
    private JButton modeBtn_plus;
    private JButton modeBtn_minus;
    private JButton ExitBtn;
    private JLabel Logo;
    private JLabel label;
    private Consumer<ActionEvent> onStartClick;

    final int SCREEN_WIDTH = 1535;
    final int SCREEN_HEIGHT = 850;

    protected int mode = 1;

    GFrame frame;
    Game game;

    Menu(GFrame t) {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
        Start();
    }

    private void Start() {

        // Start
        StartBtn = new JButton("");
        StartBtn.setIcon(new ImageIcon("btn\\StartBtn.png"));
        StartBtn.setBorderPainted(false);
        StartBtn.setContentAreaFilled(false);
        StartBtn.setBounds(670, 500, 200, 80);
        StartBtn.addActionListener(this);

        // Mode
        modeBtn_plus = new JButton(new ImageIcon("btn\\\\PlusBtn_work.png"));
        modeBtn_minus = new JButton(new ImageIcon("btn\\\\MinusBtn_not.png"));
        modeBtn_plus.setBorderPainted(false);
        modeBtn_plus.setContentAreaFilled(false);
        modeBtn_minus.setBorderPainted(false);
        modeBtn_minus.setContentAreaFilled(false);
        modeBtn_plus.setBounds(980, 380, 80, 80);
        modeBtn_minus.setBounds(480, 380, 80, 80);

        modeBtn_minus.addActionListener(this);
        modeBtn_plus.addActionListener(this);

        this.setLayout(null);
        this.add(StartBtn);
        this.add(modeBtn_plus);
        this.add(modeBtn_minus);

        // Exit
        ExitBtn = new JButton(new ImageIcon("btn\\ExitBtn.png"));
        ExitBtn.setBounds(675, 570, 200, 80);
        ExitBtn.setBorderPainted(false);
        ExitBtn.setContentAreaFilled(false);
        ExitBtn.addActionListener(this);
        this.add(ExitBtn);

        Logo = new JLabel(new ImageIcon("src\\GameLogo2.png"));

        final int LOGO_X = (460);
        final int LOGO_Y = (-100);

        Logo.setBounds(LOGO_X, LOGO_Y, 600, 600);
        this.add(Logo);

        Gamemode();

    }

    // Mode
    private void Gamemode() {
        label = new JLabel(new ImageIcon("src\\Easy.png"), JLabel.LEFT);
        label.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(label);
    }

    public void setEventOnStartClick(Consumer<ActionEvent> e) {
        this.onStartClick = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == StartBtn) {
            onStartClick.accept(e);
        }

        // Plus Mode
        if (e.getSource() == modeBtn_plus) {
            mode += 1;
            if (mode >= 4) {
                mode = 3;
            }

            if (mode == 1) {
                label.setIcon(new ImageIcon(getClass().getResource("src\\Easy.png")));
            } else if (mode == 2) {
                label.setIcon(new ImageIcon(getClass().getResource("src\\Normal.png")));

            } else {
                label.setIcon(new ImageIcon(getClass().getResource("src\\Hard.png")));
            }

            if (mode != 1) {
                modeBtn_minus.setIcon(new ImageIcon(getClass().getResource("btn\\\\MinusBtn_work.png")));
            } else {
                modeBtn_minus.setIcon(new ImageIcon(getClass().getResource("btn\\\\MinusBtn_not.png")));
            }

            if (mode != 3) {
                modeBtn_plus.setIcon(new ImageIcon(getClass().getResource("btn\\\\PlusBtn_work.png")));
            } else {
                modeBtn_plus.setIcon(new ImageIcon(getClass().getResource("btn\\\\PlusBtn_not.png")));
            }

            System.out.println(mode);
        }

        // Minus Mode
        if (e.getSource() == modeBtn_minus) {
            mode -= 1;
            if (mode <= 0) {
                mode = 1;
            }

            if (mode == 1) {
                label.setIcon(new ImageIcon(getClass().getResource("src\\Easy.png")));
            } else if (mode == 2) {
                label.setIcon(new ImageIcon(getClass().getResource("src\\Normal.png")));
            } else {
                label.setIcon(new ImageIcon(getClass().getResource("src\\Hard.png")));
            }

            if (mode != 1) {
                modeBtn_minus.setIcon(new ImageIcon(getClass().getResource("btn\\\\MinusBtn_work.png")));
            } else {
                modeBtn_minus.setIcon(new ImageIcon(getClass().getResource("btn\\\\MinusBtn_not.png")));
            }

            if (mode != 3) {
                modeBtn_plus.setIcon(new ImageIcon(getClass().getResource("btn\\\\PlusBtn_work.png")));
            } else {
                modeBtn_plus.setIcon(new ImageIcon(getClass().getResource("btn\\\\PlusBtn_not.png")));
            }

            System.out.println(mode);
        }

        if (e.getSource() == ExitBtn) {
            System.exit(0); // Close the program
            System.out.println("Close");
        }

    }

}
