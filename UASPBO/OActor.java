import greenfoot.*;  // Import Greenfoot classes

/**
 * OActor - Represents the O symbol as a separate Actor.
 */
public class OActor extends Actor {
    public OActor() {
        setImage("O.png");  // Use image from images folder
        getImage().scale(70, 70);  // Adjusted for new cell size
    }

    public void act() {
        // No action needed for OActor
    }
}