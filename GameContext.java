import java.awt.Component;

public class GameContext {
    // object of Frame, Menu and Settings
    private Frame frame;
    private Menu menu;
    private Settings setting;
    private Game game;
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
        for (Component c : frame.getContentPane().getComponents())
            if (c instanceof Menu)
                return false;

        this.updateComponent(this.menu);
        return true;
    }

    public boolean toSetting() {
        for (Component c : frame.getContentPane().getComponents())
            if (c instanceof Settings)
                return false;

        this.updateComponent(this.setting);
        return true;
    }

    public boolean newGame(int difficulty) {
        for (Component c : frame.getContentPane().getComponents())
            if (c instanceof Game)
                return false;

        if (!isGameStarted) {
            this.game = new Game(this, difficulty);
        }
        this.updateComponent(this.game);
        return true;
    }

    private boolean updateComponent(Component c) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(c);
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