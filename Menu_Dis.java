import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.function.Consumer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Menu_Dis extends JPanel implements ActionListener {

    private JButton StartBtn;
    private JButton modeBtn_plus;
    private JButton modeBtn_minus;
    private JButton ExitBtn;
    private JLabel Logo;
    private JLabel Background;
    private JLabel setMode;

    private JLabel SettingPopup;
    private JButton SettingBtn;
    private JButton CloseSettingBtn;
    private JButton OpenMusic;
    private JButton CloseMusic;

    private Consumer<ActionEvent> Play;

    final int SCREEN_WIDTH = 1535;
    final int SCREEN_HEIGHT = 850;

    private Clip clip;

    protected int mode = 1;
    boolean MusicPlay = false;
    boolean MusicPath;
    

    Frame frame;
    
    Menu_Dis(GameContext t) {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
        // toggleMusic();
        Start();
    }

    protected void Start() {
        Gamemode();

        // Start
        StartBtn = new JButton("");
        StartBtn.setIcon(new ImageIcon("btn\\StartBtn.png"));
        StartBtn.setBorderPainted(false);
        StartBtn.setContentAreaFilled(false);
        StartBtn.setBounds(670, 500, 200, 80);
        StartBtn.addActionListener(this);

        // Mode
        modeBtn_plus = new JButton(new ImageIcon("btn\\PlusBtn_work.png"));
        modeBtn_minus = new JButton(new ImageIcon("btn\\MinusBtn_not.png"));
        modeBtn_plus.setBorderPainted(false);
        modeBtn_plus.setContentAreaFilled(false);
        modeBtn_minus.setBorderPainted(false);
        modeBtn_minus.setContentAreaFilled(false);
        modeBtn_plus.setBounds(980, 370, 80, 80);
        modeBtn_minus.setBounds(480, 370, 80, 80);

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

        // Setting
        SettingBtn = new JButton(new ImageIcon("btn\\SettingBtn.png"));
        SettingBtn.setBorderPainted(false);
        SettingBtn.setContentAreaFilled(false);
        SettingBtn.setBounds(SCREEN_WIDTH - 80, 20, 60, 60);
        SettingBtn.addActionListener(this);
        this.add(SettingBtn);

        Logo = new JLabel(new ImageIcon("src\\GameLogo2.png"));

        final int LOGO_X = (460);
        final int LOGO_Y = (-100);

        Logo.setBounds(LOGO_X, LOGO_Y, 600, 600);
        this.add(Logo);

        Backgroundmode();

    }

    // Mode
    private void Gamemode() {
        setMode = new JLabel(new ImageIcon("src\\ModeEasy.png"));
        setMode.setBounds(570, 320, 400, 200);
        this.add(setMode);
    }

    // Background
    private void Backgroundmode() {

        Background = new JLabel(new ImageIcon("src\\BackgroundMenu.png"), JLabel.CENTER);
        Background.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(Background);
    }

    public void setPlayGame(Consumer<ActionEvent> e) {
        this.Play = e;
    }

    public void SettingPanel() {

        CloseSettingBtn = new JButton(new ImageIcon("btn\\CloseSettingBtn.png"));
        CloseSettingBtn.setBounds(620, 680, 300, 100);
        CloseSettingBtn.setBorderPainted(false);
        CloseSettingBtn.setContentAreaFilled(false);
        CloseSettingBtn.addActionListener(this);
        this.add(CloseSettingBtn);

        // Audio
        OpenMusic = new JButton(new ImageIcon("btn\\Open_On.png"));
        OpenMusic.setBounds(850, 218, 320, 100);
        OpenMusic.setBorderPainted(false);
        OpenMusic.setContentAreaFilled(false);
        OpenMusic.addActionListener(this);

        CloseMusic = new JButton(new ImageIcon("btn\\Close_Off.png"));
        CloseMusic.setBounds(1047, 218, 320, 100);
        CloseMusic.setBorderPainted(false);
        CloseMusic.setContentAreaFilled(false);
        CloseMusic.addActionListener(this);

        this.add(OpenMusic);
        this.add(CloseMusic);

        if (!MusicPlay) {
            OpenMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Close_On.png")));
            CloseMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_Off.png")));
        }

        SettingPopup = new JLabel(new ImageIcon("src\\SettingBackGround.png"), JLabel.CENTER);
        SettingPopup.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(SettingPopup);
    }

    public boolean isMusicPlay() {
        System.out.println("MusicPath = " + MusicPath);
        return MusicPath;
        
    }

    // Music import

    private void toggleMusic() {
        if (!MusicPlay) {
            playMusic("sound\\Song1.wav");
        } else {
            stopMusic();
        }
        
        MusicPlay = !MusicPlay;
    }

    protected void playMusic(String filepath) {
        try {
            File musicFile = new File(filepath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void stopMusic() {
        clip.stop();
        clip.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == StartBtn) {
            Play.accept(e);
            System.out.println("StartGame");
        }

        // Plus Mode
        if (e.getSource() == modeBtn_plus) {
            mode += 1;
            if (mode >= 4) {
                mode = 3;
            }

            // Change Mode setMode
            if (mode == 1) {
                setMode.setIcon(new ImageIcon(getClass().getResource("src\\ModeEasy.png")));
                setMode.setBounds(570, 320, 400, 200);
            } else if (mode == 2) {
                setMode.setIcon(new ImageIcon(getClass().getResource("src\\ModeNormal.png")));
                setMode.setBounds(580, 320, 400, 200);
            } else {
                setMode.setIcon(new ImageIcon(getClass().getResource("src\\ModeHard.png")));
                setMode.setBounds(570, 320, 400, 200);
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

            // Change Mode setMode
            if (mode == 1) {
                setMode.setIcon(new ImageIcon(getClass().getResource("src\\ModeEasy.png")));
                setMode.setBounds(570, 320, 400, 200);
            } else if (mode == 2) {
                setMode.setIcon(new ImageIcon(getClass().getResource("src\\ModeNormal.png")));
                setMode.setBounds(580, 320, 400, 200);
            } else {
                setMode.setIcon(new ImageIcon(getClass().getResource("src\\ModeHard.png")));
                setMode.setBounds(570, 320, 400, 200);
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

        if (e.getSource() == SettingBtn) {
            this.removeAll();
            SettingPanel();
            this.validate();
            this.repaint();
            System.out.println("SettingMenu");

        }

        if (e.getSource() == OpenMusic) {

            if (!MusicPlay) {
                OpenMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_On.png")));
                CloseMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Close_Off.png")));
                System.out.println("Music on");
                toggleMusic();
                MusicPath = true;

            }

        }

        if (e.getSource() == CloseMusic) {
            if (MusicPlay) {
                OpenMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Close_On.png")));
                CloseMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_Off.png")));
                System.out.println("Music off");
                toggleMusic();
                MusicPath = false;
            }
        }

        if (e.getSource() == CloseSettingBtn) {
            this.removeAll();
            Start();

            if (mode == 1) {
                setMode.setIcon(new ImageIcon(getClass().getResource("src\\ModeEasy.png")));
                setMode.setBounds(570, 320, 400, 200);
            } else if (mode == 2) {
                setMode.setIcon(new ImageIcon(getClass().getResource("src\\ModeNormal.png")));
                setMode.setBounds(580, 320, 400, 200);
            } else {
                setMode.setIcon(new ImageIcon(getClass().getResource("src\\ModeHard.png")));
                setMode.setBounds(570, 320, 400, 200);
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

            this.validate();
            this.repaint();
            System.out.println("MenuPage");
        }

        if (e.getSource() == ExitBtn) {
            System.out.println("Close");
            System.exit(0); // Close the program

        }
    }

}