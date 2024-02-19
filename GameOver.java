import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameOver extends JPanel implements ActionListener {

    private JLabel Win_Lose;
    private JLabel Background;
    private JButton PlayAgain;
    private JButton Menu;

    final int SCREEN_WIDTH = 1535;
    final int SCREEN_HEIGHT = 850;

    boolean state = true;
    GameContext gameContext;

    GameOver(GameContext t, boolean state) {
        this.state = state;
        this.gameContext = t;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
        this.setLayout(null);

        Stat();

        Button();

        Backgroundmode();

    }

    private void Button() {
        PlayAgain = new JButton("");
        PlayAgain.setIcon(new ImageIcon("btn\\PlayagainBtn.png"));
        PlayAgain.setBorderPainted(false);
        PlayAgain.setContentAreaFilled(false);
        PlayAgain.setBounds(620, 485, 300, 80);
        PlayAgain.addActionListener(this);

        Menu = new JButton("");
        Menu.setIcon(new ImageIcon("btn\\MenuBtn.png"));
        Menu.setBorderPainted(false);
        Menu.setContentAreaFilled(false);
        Menu.setBounds(670, 570, 200, 80);
        Menu.addActionListener(this);

        this.add(PlayAgain);
        this.add(Menu);
    }

    private void Stat() {
        Win_Lose = new JLabel(new ImageIcon("src\\YouLose.png"));
        Win_Lose.setBounds(460, 100, 600, 450);
        this.add(Win_Lose);

        if (state) {
            Win_Lose.setIcon(new ImageIcon("src\\YouWin.png"));
            Win_Lose.setBounds((this.gameContext.getScreenWidth() / 2) - 300, 100, 600, 450);
        }

    }

    private void Backgroundmode() {
        // Background
        Background = new JLabel(new ImageIcon("src\\BackgroundMenu.png"), JLabel.LEFT);
        Background.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(Background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == PlayAgain) {
            gameContext.toGame();
            gameContext.getGame().getTable().resetTable();
        }

        if (e.getSource() == Menu) {
            gameContext.toMenu();
            gameContext.endGame();
        }
    }
}