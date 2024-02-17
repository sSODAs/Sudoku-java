
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame {


    Frame() {
        Menu menu = new Menu(this);
        menu.setPlayGame((e) -> {
            this.getContentPane().remove(menu); 
            this.getContentPane().add(new Game());
            this.getContentPane().validate(); // add new data
            this.getContentPane().repaint();
        });

        this.getContentPane().add(menu); 
        
        this.setTitle("SUDOKU");
        this.setIconImage(new ImageIcon("src\\Gameicon.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);

    }

}
