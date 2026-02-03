package src;

import java.awt.Component;
import javax.swing.ImageIcon;

public class GameContext {

    private Frame frame;
    private Menu menu;
    private Settings setting;
    private Game game;
    private GameOver gameOver;
    private Component[] preventPage = new Component[2];

    private boolean isGameStarted = false;
    private int difficulty = 1;

    private boolean soundOpen = false;
    private String soundPath = "sound/Song1.wav";
    private int screenWidth = 1535;
    private int screenHeight = 850;

    GameContext(Frame f, int w, int h) {
        this.frame = f;
        this.screenWidth = w;
        this.screenHeight = h;
        this.menu = new Menu(this);
        this.setting = new Settings(this);
        init();
    }

    public boolean init() {
        updateComponent(menu);
        return true;
    }

    // ---------------- SOUND ----------------

    public boolean toggleSound() {
        soundOpen = !soundOpen;
        if (soundOpen) {
            frame.playSounds(soundPath);
        } else {
            frame.closeSounds();
        }
        return true;
    }

    public boolean isSoundOpen() {
        return soundOpen;
    }

    public boolean toMenu() {
        updateComponent(menu);
        return true;
    }

    public boolean toSetting() {
        updateComponent(setting);
        return true;
    }

    public boolean toGame(int difficulty) {

        this.difficulty = difficulty;

        if (!isGameStarted) {
            this.game = new Game(this, difficulty);
            isGameStarted = true;
            System.out.println("to game (difficulty = " + difficulty + ")");
        }

        updateComponent(game);
        return true;
    }

    public boolean toGame() {
        return toGame(this.difficulty);
    }

    public boolean setGameOver(boolean isWin) {
        this.gameOver = new GameOver(this, isWin);
        return true;
    }

    public boolean toGameOver() {
        if (gameOver == null)
            return false;
        updateComponent(gameOver);
        return true;
    }

    public boolean toPreventPage() {
        if (preventPage[0] != null) {
            updateComponent(preventPage[0]);
        } else {
            updateComponent(menu);
        }
        return true;
    }

    public boolean endGame() {
        if (isGameStarted) {
            isGameStarted = false;
            game = null;
            return true;
        }
        return false;
    }

    private void updateComponent(Component c) {
        preventPage[0] = preventPage[1];
        preventPage[1] = c;
        frame.getContentPane().removeAll();
        frame.getContentPane().add(c);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
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

    public int getDifficulty() {
        return difficulty;
    }

    public Frame getFrame() {
        return frame;
    }
}


interface SoundEvents {
    void playSounds(String soundPath);

    void closeSounds();
}

enum Number {
    EMPTY("number/0.png"),
    ONE("number/1.png"),
    TWO("number/2.png"),
    THREE("number/3.png"),
    FOUR("number/4.png"),
    FIVE("number/5.png"),
    SIX("number/6.png"),
    SEVEN("number/7.png"),
    EIGHT("number/8.png"),
    NINE("number/9.png");

    private ImageIcon icon;

    Number(String path) {
        this.icon = new ImageIcon(path);
    }

    public ImageIcon getIcon() {
        return icon;
    }
}

enum NumberPicked {
    EMPTY("number/0.png"),
    ONE("number/Pick1.png"),
    TWO("number/Pick2.png"),
    THREE("number/Pick3.png"),
    FOUR("number/Pick4.png"),
    FIVE("number/Pick5.png"),
    SIX("number/Pick6.png"),
    SEVEN("number/Pick7.png"),
    EIGHT("number/Pick8.png"),
    NINE("number/Pick9.png");

    private ImageIcon icon;

    NumberPicked(String path) {
        this.icon = new ImageIcon(path);
    }

    public ImageIcon getIcon() {
        return icon;
    }
}

enum NumberSet {
    EMPTY("number/0.png"),
    ONE("number/Set1.png"),
    TWO("number/Set2.png"),
    THREE("number/Set3.png"),
    FOUR("number/Set4.png"),
    FIVE("number/Set5.png"),
    SIX("number/Set6.png"),
    SEVEN("number/Set7.png"),
    EIGHT("number/Set8.png"),
    NINE("number/Set9.png");

    private ImageIcon icon;

    NumberSet(String path) {
        this.icon = new ImageIcon(path);
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
