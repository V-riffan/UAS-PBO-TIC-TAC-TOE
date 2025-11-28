import greenfoot.*;  // Import Greenfoot classes

/**
 * StartButton - Starts the game from menu.
 */
public class StartButton extends Actor {
    public void act() {
        if (Greenfoot.mouseClicked(this) && ((TicTacToeWorld) getWorld()).isMenu()) {
            ((TicTacToeWorld) getWorld()).startGame(false);  // Default to 1vs1 mode (false = not vs bot)
        }
    }
}
