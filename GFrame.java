
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GFrame extends JFrame {
    GFrame() {
        Menu menu = new Menu(this);
        menu.setEventOnStartClick((e) -> {
            this.remove(menu);
            this.add(new Game());
            this.validate();
            this.repaint();
        });

        this.getContentPane().add(menu);
        this.setTitle("Sudoku");
        this.setIconImage(new ImageIcon("src\\Gameicon.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);

    }

}
