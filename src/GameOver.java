package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameOver extends JPanel implements ActionListener {

    private JLabel Win_Lose;
    private JLabel Background;
    private JButton PlayAgain;
    private JButton Menu;
    private JButton SettingBtn;

    GameContext gameContext;
    boolean state;

    GameOver(GameContext t, boolean state) {
        this.state = state;
        this.gameContext = t;
        this.setPreferredSize(new Dimension(
                gameContext.getScreenWidth(),
                gameContext.getScreenHeight()));
        this.setLayout(null);

        Stat();
        Button();
        Backgroundmode();
    }

    private void Button() {
        PlayAgain = new JButton(new ImageIcon("btn/PlayagainBtn.png"));
        PlayAgain.setBounds(620, 485, 300, 80);
        PlayAgain.setBorderPainted(false);
        PlayAgain.setContentAreaFilled(false);
        PlayAgain.addActionListener(this);
        this.add(PlayAgain);

        SettingBtn = new JButton(new ImageIcon("btn/SettingBtn.png"));
        SettingBtn.setBounds(gameContext.getScreenWidth() - 80, 20, 60, 60);
        SettingBtn.setBorderPainted(false);
        SettingBtn.setContentAreaFilled(false);
        SettingBtn.addActionListener(this);
        this.add(SettingBtn);

        Menu = new JButton(new ImageIcon("btn/MenuBtn.png"));
        Menu.setBounds(670, 570, 200, 80);
        Menu.setBorderPainted(false);
        Menu.setContentAreaFilled(false);
        Menu.addActionListener(this);
        this.add(Menu);
    }

    private void Stat() {
        Win_Lose = new JLabel(
                new ImageIcon(state ? "pics/YouWin.png" : "pics/YouLose.png"));
        Win_Lose.setBounds(
                (gameContext.getScreenWidth() / 2) - 300,
                100, 600, 450);
        this.add(Win_Lose);
    }

    private void Backgroundmode() {
        Background = new JLabel(new ImageIcon("pics/BackgroundMenu.png"));
        Background.setBounds(
                0, 0,
                gameContext.getScreenWidth(),
                gameContext.getScreenHeight());
        this.add(Background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == PlayAgain) {
            gameContext.toGame();
            gameContext.getGame().getTable().resetTable();
        } else if (e.getSource() == SettingBtn) {
            gameContext.toSetting();
        } else if (e.getSource() == Menu) {
            gameContext.toMenu();
            gameContext.endGame();
        }
    }
}
