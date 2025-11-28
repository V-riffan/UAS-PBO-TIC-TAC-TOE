import greenfoot.*;  // Import Greenfoot classes

/**
 * Cell - Represents an empty cell in the Tic Tac Toe grid.
 * When clicked, places an X or O Actor on it.
 *
 * Revised: Uses GreenfootImage to draw the cell dynamically
 * instead of loading "cell.png".
 */
public class Cell extends Actor {
    private int row, col;
    private boolean occupied = false;  // Flag to check if cell is occupied
    private static final int CELL_SIZE = 100;  // Adjusted size for better fit

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;

        // Create a new image (canvas) with the desired size
        GreenfootImage cellImage = new GreenfootImage(CELL_SIZE, CELL_SIZE);
        
        // Set background color (e.g., white or light gray)
        cellImage.setColor(Color.WHITE);
        cellImage.fill();  // Fill the entire image with white
        
        // Set color for border (e.g., black)
        cellImage.setColor(Color.BLACK);
        
        // Draw the box as a border
        cellImage.drawRect(0, 0, CELL_SIZE - 1, CELL_SIZE - 1);

        // Set the newly created image as the Actor's image
        setImage(cellImage);
    }

    /**
     * Act method - check for mouse clicks.
     * Logic remains the same as your original code.
     */
    public void act() {
        // Check if mouse is clicked on this cell and it's not occupied
        if (Greenfoot.mouseClicked(this) && !occupied) {
            // Get reference to the World
            TicTacToeWorld world = (TicTacToeWorld) getWorld();
            
            // Only proceed if game is not over and not in menu
            if (!world.isGameOver() && !world.isMenu()) {
                // Call method in World to place symbol (X or O)
                world.placeSymbol(this, world.getCurrentPlayer());
            }
        }
    }

    // Getter for row
    public int getRow() {
        return row;
    }

    // Getter for col
    public int getCol() {
        return col;
    }

    // Setter for occupied
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    // Getter for occupied
    public boolean isOccupied() {
        return occupied;
    }
}