import greenfoot.*;  // Import Greenfoot classes

/**
 * XActor - Represents the X symbol as a separate Actor.
 */
public class XActor extends Actor {
    public XActor() {
        setImage("X.png");  // Use image from images folder
        getImage().scale(70, 70);  // Adjusted for new cell size
    }

    public void act() {
        // No action needed for XActor
    }
}