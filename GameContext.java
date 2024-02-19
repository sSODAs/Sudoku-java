import java.awt.Component;

public class GameContext {
    // object of Frame, Menu and Settings
    private Frame frame;
    private Menu menu;
    private Settings setting;
    private Game game;
    private GameOver gameOver;
    // state of the game
    private boolean isGameStarted = false;
    private int difficulty = -1;
    // game board
    private boolean soundOpen = true;
    private String soundPath = "sound\\Song1.wav";
    private int screenWidth = 1535;
    private int screenHeight = 850;

    GameContext(Frame f, int w, int h) {
        this.frame = f;
        this.screenWidth = w;
        this.screenHeight = h;
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

    public Game getGame() {
        return game;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
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

    public boolean toGame(int difficulty) {
        for (Component c : frame.getContentPane().getComponents())
            if (c instanceof Game)
                return false;

        if (!this.isGameStarted) {
            this.game = new Game(this, difficulty);
            this.isGameStarted = true;
            System.out.println("to game");
        }
        this.updateComponent(this.game);
        return true;
    }

    public boolean toGame() {
        return toGame(this.difficulty);
    }

    public boolean toGameOver() {
        for (Component c : frame.getContentPane().getComponents())
            if (c instanceof GameOver)
                return false;

        if (this.gameOver == null)
            return false;
        this.updateComponent(this.gameOver);
        return true;
    }

    public boolean setGameOver(boolean isWin) {
        for (Component c : frame.getContentPane().getComponents())
            if (c instanceof GameOver)
                return false;

        this.gameOver = new GameOver(this, isWin);
        return true;
    }

    public boolean endGame() {
        System.out.println("...");

        if (this.isGameStarted) {
            System.out.println("end game!!");
            this.isGameStarted = false;
            this.game = null;
            // this.updateComponent(this.menu);
            return true;
        }
        return false;
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