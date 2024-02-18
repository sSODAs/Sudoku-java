import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
// import java.awt.MouseInfo;

import java.awt.event.*;

public class Game extends JPanel {
    private GameContext gameContext;
    private SudoTable table;
    private int SCREEN_WIDTH = 1535;
    private int SCREEN_HEIGHT = 850;
    private Difficulty difficulty = Difficulty.EASY;

    Game(GameContext c, int d) {
        this.gameContext = c;
        this.difficulty = Difficulty.values()[d - 1];
        running();
        resetTable();
    }

    private void running() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);

        this.setLayout(null);
        this.setBackground(Color.BLUE);
    }

    private void resetTable() {
        if (this.table != null) {
            this.remove(this.table);
        }
        table = new SudoTable(this.gameContext, this, 9, difficulty.Random(), 100, 100);
        this.add(table);
        this.repaint();
    }

    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }
}

class SudoTable extends JPanel {
    private GameContext gameContext;
    private Algorithm mySudoku;
    private int[][] myMat;
    // private SudoButton curButton;
    private SudoButton curNumber;
    // private SudoPad pad;
    private Game gamePanel;
    private SudoNumberPad numberPad;
    private SudoControl control;
    private int SCREEN_WIDTH = 500;
    private int SCREEN_HEIGHT = 500;
    private int xPos;
    private int yPos;

    SudoTable(GameContext c, Game gn, int size, int missingDigits, int x, int y, int width, int height) {
        try {
            this.gameContext = c;
            this.gamePanel = gn;
            this.xPos = x;
            this.yPos = y;
            this.SCREEN_WIDTH = width;
            this.SCREEN_HEIGHT = height;
            this.mySudoku = new Algorithm(size, missingDigits);

            this.numberPad = new SudoNumberPad();
            this.control = new SudoControl();
            running();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    SudoTable(GameContext c, Game gn, int size, int missingDigits, int x, int y) {
        this(c, gn, size, missingDigits, x, y, 500, 500);
    }

    protected void running() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);

        this.setLayout(new GridLayout(3, 3));
        this.setBounds(this.xPos, this.yPos, this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
        this.setBackground(Color.WHITE);

        gamePanel.add(this.control);
        resetTable();
    }

    private void resetTable() {
        this.mySudoku.generate();
        this.mySudoku.print();
        this.myMat = this.mySudoku.getMat();

        for (Component c : this.getComponents()) {
            if (c instanceof SudoBox)
                this.remove(c);
        }

        for (int i = 0; i < this.mySudoku.getSizePerBox(); i++) {
            for (int j = 0; j < this.mySudoku.getSizePerBox(); j++) {
                SudoBox box = new SudoBox(mySudoku.getBox(i, j), i, j);
                this.add(box);
            }
        }

        if (this.numberPad != null) {
            curNumber = null;
            gamePanel.remove(this.numberPad);
        }

        this.numberPad = new SudoNumberPad();
        gamePanel.add(this.numberPad);
        gamePanel.validate();
        gamePanel.repaint();
    }

    private ActionListener numberPadListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            SudoButton btn = (SudoButton) e.getSource();

            if (curNumber != null) {
                curNumber.setContentAreaFilled(false);
                curNumber.setBackground(null);
                if (curNumber.getNumber() == btn.getNumber()) {
                    curNumber = null;
                    return;
                }
            }

            curNumber = btn;
            curNumber.setContentAreaFilled(true);
            curNumber.setBackground(Color.RED);
        }
    };

    private ActionListener btnListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            SudoButton btn = (SudoButton) e.getSource();

            if (curNumber != null && btn.isPuzzle()) {
                btn.setNumber(curNumber.getNumber());
            } else {

            }

        }
    };

    public void clearTable() {
        for (Component c : this.getComponents()) {
            SudoBox box = (SudoBox) c;
            for (Component c2 : box.getComponents()) {
                if (c2 instanceof SudoButton) {
                    SudoButton btn = (SudoButton) c2;
                    if (btn.isPuzzle()) {
                        btn.setNumber(0);
                    }
                }
            }
        }
        gamePanel.validate();
        gamePanel.repaint();
    }

    public boolean checkTable() {
        try {
            int[][] thismat = new int[this.mySudoku.getSize()][this.mySudoku.getSize()];

            for (Component c : this.getComponents()) {
                SudoBox box = (SudoBox) c;
                for (Component c2 : box.getComponents()) {
                    if (c2 instanceof SudoButton) {
                        SudoButton btn = (SudoButton) c2;
                        thismat[btn.getRow()][btn.getCol()] = btn.getNumber();
                    }
                }
            }

            return this.mySudoku.isTrue(thismat);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private class SudoNumberPad extends JPanel {
        SudoNumberPad() {
            this.setLayout(new GridLayout(1, 9));
            this.setBackground(Color.WHITE);
            // this.setOpaque(false);

            for (int i : new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }) {
                SudoButton btn = new SudoButton(i, -1, -1);
                btn.addEventOnNumberClick(numberPadListener);
                this.add(btn);
            }
            System.out.println();
            this.setBounds(xPos, yPos + SCREEN_HEIGHT + 30, SCREEN_WIDTH, 50);
        }
    }

    private class SudoBox extends JPanel {
        private int[][] matBox;
        private int row;
        private int col;

        SudoBox(int[][] numBox, int row, int col) {
            System.out.println("SudoBox");
            this.setOpaque(false);
            this.setBorder(new EmptyBorder(10, 10, 10, 10));
            // this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            this.setLayout(new GridLayout(numBox.length, numBox.length));

            for (int i = 0; i < numBox.length; i++) {
                for (int j = 0; j < numBox.length; j++) {
                    SudoButton btn = new SudoButton(numBox[i][j], (row * numBox.length) + i, (col * numBox.length) + j);
                    btn.addEventOnNumberClick(btnListener);
                    this.add(btn);
                }
            }
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    private class SudoControl extends JPanel {
        SudoControl() {
            this.setLayout(new GridLayout(1, 2));
            this.setBackground(Color.WHITE);
            this.setOpaque(false);

            JButton btn1 = new JButton("Reset");
            btn1.addActionListener((l) -> {
                resetTable();
            });

            JButton btn2 = new JButton("Clear");
            btn2.addActionListener((l) -> {
                clearTable();
            });

            JButton btn3 = new JButton("Submit");
            btn3.addActionListener((l) -> {
                if (checkTable()) {
                    JOptionPane.showMessageDialog(null, "You win!");
                } else {
                    JOptionPane.showMessageDialog(null, "You lose!");
                }
            });

            this.add(btn1);
            this.add(btn2);
            this.add(btn3);
            this.setBounds(xPos, yPos + SCREEN_HEIGHT + 100, SCREEN_WIDTH, 50);
        }
    }
}

class SudoButton extends JButton {
    private int number;
    private int row;
    private int col;
    private boolean isPuzzle = false;

    SudoButton(int number, int row, int col) {
        this.row = row;
        this.col = col;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setNumber(number);
        this.isPuzzle = number == 0;
    }

    public int getNumber() {
        return number;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isPuzzle() {
        return isPuzzle;
    }

    public void setNumber(int number) {
        this.number = number;
        if (this.number == 0) {
            this.setText("");
        } else {
            this.setText(String.valueOf(this.number));
        }
    }

    public void addEventOnNumberClick(ActionListener listener) {
        this.addActionListener(listener);
    }
}

enum Difficulty {
    EASY(35, 45), MEDIUM(46, 49), HARD(50, 53);

    private int min;
    private int max;

    Difficulty(int min, int max) {
        this.min = min;
        this.max = max;
    }

    int Random() {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}