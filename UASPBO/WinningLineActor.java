import greenfoot.*;

/**
 * WinningLineActor - Draws a winning line (horizontal, vertical, or diagonal).
 */
public class WinningLineActor extends Actor {
    public WinningLineActor(boolean isDiagonal, int rotation) {
        GreenfootImage lineImage;
        if (isDiagonal) {
            lineImage = new GreenfootImage(300, 10);  // Diagonal line
        } else {
            lineImage = new GreenfootImage(300, 10);  // Horizontal/vertical line
        }
        lineImage.setColor(Color.RED);
        lineImage.fillRect(0, 0, 300, 10);  // Thick red line
        setImage(lineImage);
        setRotation(rotation);  // Rotate for vertical or diagonal
    }

    public void act() {
        // No action needed
    }
}