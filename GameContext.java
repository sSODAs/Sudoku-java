import javax.swing.*;

public class GameContext {
    // object of Frame, Menu and Settings
    private Frame frame;
    private Menu menu;
    private Settings setting;
    // state of the game
    private boolean isGameStarted = false;
    private int difficulty = -1;
    // game board
    private boolean soundOpen = false;
    private String soundPath = "sound\\Song1.wav";

    GameContext(Frame f) {
        frame = f;
        menu = new Menu(this);
        setting = new Settings(this);
        init();
    }

    public boolean init() {
        frame.getContentPane().add(menu);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        return true;
    }

    public boolean toggleSound() {
        soundOpen = !soundOpen;
        if (soundOpen) {
            frame.playSounds(soundPath);
        } else {
            frame.closeSounds();
        }
        return true;
    }

    public boolean toMenu() {
        if (menu instanceof Menu)
            return false;
        frame.getContentPane().remove(setting);
        frame.getContentPane().add(menu);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        return true;
    }

    public boolean toSetting() {
        for (Components c : frame.getContentPane().getComponents()) {
            if (c instanceof Setting) {
                return false;
            }
        }
        frame.getContentPane().remove(menu);
        frame.getContentPane().add(setting);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        return true;
    }

    public Frame getFrame() {
        return frame;
    }

    public Menu getMenu() {
        return menu;
    }

    public Settings getSetting() {
        return setting;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isSoundOpen() {
        return soundOpen;
    }

    public String getSoundPath() {
        return soundPath;
    }

}

interface SoundEvents {
    void playSounds(String soundPath);

    void closeSounds();
}