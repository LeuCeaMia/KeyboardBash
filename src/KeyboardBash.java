
import java.awt.Dimension;
import com.golden.gamedev.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class KeyboardBash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        GameUI g = new GameUI();
        GameLoader game = new GameLoader();
        
        game.setup(g, new Dimension(640,480), false);
        game.start();
        // TODO code application logic here
    }
}
