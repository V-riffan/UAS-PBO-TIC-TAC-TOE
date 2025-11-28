import greenfoot.*;

public class Play1vs1Button extends Actor {
    public Play1vs1Button() {
        setImage("1vs1.png");
        getImage().scale(150, 40);  // Use image from images folder
    }
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            TicTacToeWorld world = (TicTacToeWorld) getWorld();
            world.startGame(false);  // Start 1vs1 mode
        }
    }
}