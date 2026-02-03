package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JPanel implements ActionListener {

    private JButton StartBtn;
    private JButton modeBtn_plus;
    private JButton modeBtn_minus;
    private JButton ExitBtn;
    private JLabel Logo;
    private JLabel Background;
    private JLabel setMode;
    private JButton SettingBtn;

    protected int mode = 1;
    GameContext gameContext;

    Menu(GameContext t) {
        this.gameContext = t;
        this.setPreferredSize(new Dimension(
                this.gameContext.getScreenWidth(),
                this.gameContext.getScreenHeight()));
        this.setFocusable(true);
        this.setLayout(null);
        Start();
    }

    private void Start() {

        Gamemode();

        StartBtn = new JButton(new ImageIcon("btn/StartBtn.png"));
        StartBtn.setBorderPainted(false);
        StartBtn.setContentAreaFilled(false);
        StartBtn.setBounds(670, 500, 200, 80);
        StartBtn.addActionListener(this);
        this.add(StartBtn);

        modeBtn_plus = new JButton(new ImageIcon("btn/PlusBtn_work.png"));
        modeBtn_minus = new JButton(new ImageIcon("btn/MinusBtn_not.png"));
        modeBtn_plus.setBorderPainted(false);
        modeBtn_plus.setContentAreaFilled(false);
        modeBtn_minus.setBorderPainted(false);
        modeBtn_minus.setContentAreaFilled(false);
        modeBtn_plus.setBounds(980, 370, 80, 80);
        modeBtn_minus.setBounds(480, 370, 80, 80);
        modeBtn_plus.addActionListener(this);
        modeBtn_minus.addActionListener(this);
        this.add(modeBtn_plus);
        this.add(modeBtn_minus);

        SettingBtn = new JButton(new ImageIcon("btn/SettingBtn.png"));
        SettingBtn.setBorderPainted(false);
        SettingBtn.setContentAreaFilled(false);
        SettingBtn.setBounds(this.gameContext.getScreenWidth() - 80, 20, 60, 60);
        SettingBtn.addActionListener(this);
        this.add(SettingBtn);

        ExitBtn = new JButton(new ImageIcon("btn/ExitBtn.png"));
        ExitBtn.setBounds(675, 570, 200, 80);
        ExitBtn.setBorderPainted(false);
        ExitBtn.setContentAreaFilled(false);
        ExitBtn.addActionListener(this);
        this.add(ExitBtn);

        Logo = new JLabel(new ImageIcon("pics/GameLogo2.png"));
        Logo.setBounds(460, -100, 600, 600);
        this.add(Logo);

        Backgroundmode();
    }

    private void Gamemode() {
        setMode = new JLabel(new ImageIcon("pics/ModeEasy.png"));
        setMode.setBounds(570, 320, 400, 200);
        this.add(setMode);
    }

    private void Backgroundmode() {
        Background = new JLabel(new ImageIcon("pics/BackgroundMenu.png"));
        Background.setBounds(
                0, 0,
                this.gameContext.getScreenWidth(),
                this.gameContext.getScreenHeight());
        this.add(Background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == StartBtn) {
            gameContext.toGame(mode);
        }

        if (e.getSource() == modeBtn_plus) {
            mode = Math.min(3, mode + 1);
            updateMode();
        }

        if (e.getSource() == modeBtn_minus) {
            mode = Math.max(1, mode - 1);
            updateMode();
        }

        if (e.getSource() == SettingBtn) {
            gameContext.toSetting();
        }

        if (e.getSource() == ExitBtn) {
            System.exit(0);
        }
    }

    private void updateMode() {
        switch (mode) {
            case 1 -> setMode.setIcon(new ImageIcon("pics/ModeEasy.png"));
            case 2 -> setMode.setIcon(new ImageIcon("pics/ModeNormal.png"));
            case 3 -> setMode.setIcon(new ImageIcon("pics/ModeHard.png"));
        }

        modeBtn_minus.setIcon(new ImageIcon(
                mode == 1 ? "btn/MinusBtn_not.png" : "btn/MinusBtn_work.png"));
        modeBtn_plus.setIcon(new ImageIcon(
                mode == 3 ? "btn/PlusBtn_not.png" : "btn/PlusBtn_work.png"));
    }
}
