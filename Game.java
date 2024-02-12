import java.awt.*;
import javax.swing.*;

public class Game extends JPanel {

    Game(){
        // this.setBackground(Color.BLUE);
        Easy();
    }


    protected void Easy() {
        final int SCREEN_WIDTH = 1535;
        final int SCREEN_HEIGHT = 850;
        
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
    }

    protected void Normal() {

    }

    protected void Hard() {

    }
}
