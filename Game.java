import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Game extends JPanel implements ActionListener {
    Frame frame;


    JButton[] numButtons = new JButton[81];

    private JLabel Background;
    private JLabel Table;
    private JButton MenuBtn;
    private JButton ClearBtn;
    private JButton ResetBtn;
    private JButton SummitBtn;

    private JLabel SettingPopup;
    private JButton SettingBtn;
    private JButton CloseSettingBtn;
    private JButton OpenMusic;
    private JButton CloseMusic;

    final int SCREEN_WIDTH = 1535;
    final int SCREEN_HEIGHT = 850;

    Game() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
        this.setLayout(null);

        running();

        System.out.println(MusicPath);
    }

    private void running() {

        if(!MusicPath){
            menu.stopMusic();
        }

        Board();

        // Home
        MenuBtn = new JButton(new ImageIcon("btn\\MenuBtn.png"));
        MenuBtn.setBorderPainted(false);
        MenuBtn.setContentAreaFilled(false);
        MenuBtn.setBounds(50, 50, 200, 80);
        MenuBtn.addActionListener(this);
        // Clear
        ClearBtn = new JButton(new ImageIcon("btn\\ClearBtn.png"));
        ClearBtn.setBorderPainted(false);
        ClearBtn.setContentAreaFilled(false);
        ClearBtn.setBounds(50, 120, 200, 80);
        ClearBtn.addActionListener(this);
        // New
        ResetBtn = new JButton(new ImageIcon("btn\\ResetBtn.png"));
        ResetBtn.setBorderPainted(false);
        ResetBtn.setContentAreaFilled(false);
        ResetBtn.setBounds(50, 190, 200, 80);
        ResetBtn.addActionListener(this);
        // Summit
        SummitBtn = new JButton(new ImageIcon("btn\\SummitBtn.png"));
        SummitBtn.setBorderPainted(false);
        SummitBtn.setContentAreaFilled(false);
        SummitBtn.setBounds(50, 260, 200, 80);
        SummitBtn.addActionListener(this);

        this.add(MenuBtn);
        this.add(ClearBtn);
        this.add(ResetBtn);
        this.add(SummitBtn);

        // Setting
        SettingBtn = new JButton(new ImageIcon("btn\\SettingBtn.png"));
        SettingBtn.setBorderPainted(false);
        SettingBtn.setContentAreaFilled(false);
        SettingBtn.setBounds(SCREEN_WIDTH - 80, 20, 60, 60);
        SettingBtn.addActionListener(this);
        this.add(SettingBtn);

        Backgroundmode();
    }

    private void Board() {

        final int x = 80;
        final int y = 80;

        int LayX = 0;
        int LayY = 0;

        int Numinbox = 0;

        for (int row = 0; row < 9; row++) {
            LayY = row * y;

            for (int col = 0; col < 9; col++) {

                Numinbox = row + col;

                LayX = col * x;

                for (int i = 0; i < 9; i++) {

                    numButtons[i] = new JButton(String.valueOf(Numinbox));
                    numButtons[i].addActionListener(this);
                    numButtons[i].setBounds(440 + LayX, 60 + LayY, 60, 60);
                    numButtons[i].setContentAreaFilled(false);
                    numButtons[i].setFocusable(false);

                    this.add(numButtons[i]);
                }
            }
        }

    }

    private void SettingPanel(boolean MusicPlay) {
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

        if (!MusicPath) {
            OpenMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Close_On.png")));
            CloseMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_Off.png")));
        }

        SettingPopup = new JLabel(new ImageIcon("src\\SettingBackGround.png"), JLabel.CENTER);
        SettingPopup.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(SettingPopup);
    }

    private void Backgroundmode() {
        // Background
        Table = new JLabel(new ImageIcon("src\\Table.png"), JLabel.LEFT);
        Table.setBounds(0, -30, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(Table);

        Background = new JLabel(new ImageIcon("src\\BackgroundMenu.png"), JLabel.LEFT);
        Background.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(Background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == MenuBtn) {
            this.removeAll();
            this.validate();
            this.repaint();
            System.out.println("GetMenu");
        }

        if (e.getSource() == SettingBtn) {
            this.removeAll();
            SettingPanel(MusicPath);
            this.validate();
            this.repaint();
            System.out.println("SettingMenu");
        }

        if (e.getSource() == OpenMusic) {
            if (!MusicPath) {
                OpenMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_On.png")));
                CloseMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Close_Off.png")));
                System.out.println("Music on");
                menu.playMusic("C:\\Users\\Acer\\Desktop\\SUDOKU\\sound\\Song1.wav");
                MusicPath = true;
                System.out.println(MusicPath);
            }
        }

        if (e.getSource() == CloseMusic) {
            if (MusicPath) {
                OpenMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Close_On.png")));
                CloseMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_Off.png")));
                System.out.println("Music off");
                menu.stopMusic();
                MusicPath = false;
                System.out.println(MusicPath);
            }
        }

        if (e.getSource() == CloseSettingBtn) {
            this.removeAll();
            running();
            this.validate();
            this.repaint();
            System.out.println("Gamepage");
        }
    }

}
