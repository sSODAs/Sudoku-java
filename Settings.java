import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel implements ActionListener {
    private GameContext gameContext;
    private JButton CloseSettingBtn;
    private JButton OpenMusic;
    private JButton CloseMusic;
    private JButton toggleMusic;
    private JLabel SettingPopup;

    Settings(GameContext t) {
        this.gameContext = t;
        this.setLayout(null);
        CloseSettingBtn = new JButton(new ImageIcon("btn\\CloseSettingBtn.png"));
        CloseSettingBtn.setBounds(620, 680, 300, 100);
        CloseSettingBtn.setBorderPainted(false);
        CloseSettingBtn.setContentAreaFilled(false);
        CloseSettingBtn.addActionListener(this);
        this.add(CloseSettingBtn);

        // Audio
        // OpenMusic = new JButton(new ImageIcon("btn\\Open_On.png"));
        // OpenMusic.setBounds(850, 218, 320, 100);
        // OpenMusic.setBorderPainted(false);
        // OpenMusic.setContentAreaFilled(false);
        // OpenMusic.addActionListener(this);

        // CloseMusic = new JButton(new ImageIcon("btn\\Close_Off.png"));
        // CloseMusic.setBounds(1047, 218, 320, 100);
        // CloseMusic.setBorderPainted(false);
        // CloseMusic.setContentAreaFilled(false);
        // CloseMusic.addActionListener(this);

        toggleMusic = new JButton(new ImageIcon("btn\\Open_Off.png"));
        toggleMusic.setBounds(1047, 218, 320, 100);
        toggleMusic.setBorderPainted(false);
        toggleMusic.setContentAreaFilled(false);
        toggleMusic.addActionListener(this);

        // this.add(OpenMusic);
        // this.add(CloseMusic);
        this.add(toggleMusic);

        // if (!gameContext.isGameStarted()) {
        // OpenMusic.setIcon(new
        // ImageIcon(getClass().getResource("btn\\Close_On.png")));
        // CloseMusic.setIcon(new
        // ImageIcon(getClass().getResource("btn\\Open_Off.png")));
        // }
        if (!gameContext.isSoundOpen()) {
            toggleMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_On.png")));
        }

        SettingPopup = new JLabel(new ImageIcon("src\\SettingBackGround.png"), JLabel.CENTER);
        SettingPopup.setBounds(0, 0, this.gameContext.getScreenWidth(), this.gameContext.getScreenHeight());
        this.add(SettingPopup);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CloseSettingBtn) {
            // if (gameContext.isGameStarted()) {
            //     gameContext.toGame();
            // } else {
            //     gameContext.toMenu();
            // }
            gameContext.toPreventPage();
            System.out.println("CloseSetting");
        } else if (e.getSource() == toggleMusic) {
            gameContext.toggleSound();
            if (!gameContext.isSoundOpen()) {
                toggleMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_Off.png")));
            } else {
                toggleMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_On.png")));
            }

        } else if (e.getSource() == OpenMusic) {
            // gameContext.toggleSound();
            // OpenMusic.setIcon(new ImageIcon(getClass().getResource("btn\\Open_On.png")));
            // CloseMusic.setIcon(new
            // ImageIcon(getClass().getResource("btn\\Close_Off.png")));
        } else if (e.getSource() == CloseMusic) {
            // gameContext.toggleSound();
            // OpenMusic.setIcon(new
            // ImageIcon(getClass().getResource("btn\\Close_On.png")));
            // CloseMusic.setIcon(new
            // ImageIcon(getClass().getResource("btn\\Open_Off.png")));
        }
    }
}
