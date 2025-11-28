import greenfoot.*;

public class PlayVsBotButton extends Actor {
    public PlayVsBotButton() {
        setImage("vsbot.png");
        getImage().scale(150, 40);  // Use image from images folder
    }
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            TicTacToeWorld world = (TicTacToeWorld) getWorld();
            world.startGame(true);  // Start vs Bot mode
        }
    }
}