import greenfoot.*;  // Import Greenfoot classes

/**
 * ResetButton - Resets scores in menu or game in play.
 */
public class ResetButton extends Actor {
    public ResetButton() {
        setImage("reset1.png");
        getImage().scale(150, 40);  // Use image from images folder
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            if (((TicTacToeWorld) getWorld()).isMenu()) {
                ((TicTacToeWorld) getWorld()).resetScores();
            } else {
                ((TicTacToeWorld) getWorld()).resetGame();
            }
        }
    }
}