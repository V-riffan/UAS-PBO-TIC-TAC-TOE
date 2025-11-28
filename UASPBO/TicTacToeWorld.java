import greenfoot.*;  // Import Greenfoot classes

/**
 * TicTacToeWorld - Tic Tac Toe game with menu, scoring, and separate X/O Actors.
 */
public class TicTacToeWorld extends World {
    private String currentPlayer = "X";
    private int scoreX = 0;
    private int scoreO = 0;
    private Cell[][] board = new Cell[3][3];
    private boolean gameOver = false;
    private boolean isMenu = true;  // Flag for menu state
    private WinningLineActor winningLine;  // For drawing winning line
    private static final int MAX_SCORE = 5;
    private boolean isVsBot = false;  // Flag for game mode: false = 1vs1, true = 1vsBot

    public TicTacToeWorld() {
        super(600, 400, 1);
        prepareMenu();  // Start with menu
    }

    private void prepareMenu() {
        isMenu = true;
        showText("TicTacToe Menu", 300, 50);
        showText("Choose mode:", 300, 70);
        addObject(new Play1vs1Button(), 200, 200);  // Button for 1vs1 mode
        addObject(new PlayVsBotButton(), 400, 200);  // Button for 1vsBot mode
        addObject(new ResetButton(), 300, 300);  // Reset scores in menu
        updateScoreDisplay();
    }

    private void prepareGame() {
        isMenu = false;
        showText("", 300, 50);
        showText("", 300, 70);
        // Create and place cells in a 3x3 grid (centered)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new Cell(row, col);
                addObject(board[row][col], 150 + col * 100, 100 + row * 100);  // Adjusted for centering
            }
        }
        addObject(new ResetButton(), 500, 300);  // Reset game in play
    }

    public void act() {
        if (!isMenu && !gameOver) {
            checkWin();
            checkDraw();
            // If vs bot and it's bot's turn (O), make bot move
            if (isVsBot && currentPlayer.equals("O")) {
                makeBotMove();
            }
        }
    }

    // Method to switch turns
    public void switchTurn() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    // Get current player
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    // Place X or O on a Cell (without removing the cell)
    public void placeSymbol(Cell cell, String symbol) {
        int x = cell.getX();
        int y = cell.getY();
        if (symbol.equals("X")) {
            addObject(new XActor(), x, y);
        } else if (symbol.equals("O")) {
            addObject(new OActor(), x, y);
        }
        cell.setOccupied(true);  // Mark cell as occupied
        switchTurn();
    }

    // Check for a win
    private void checkWin() {
        for (int i = 0; i < 3; i++) {
            if (getSymbolAt(i, 0).equals(getSymbolAt(i, 1)) &&
                getSymbolAt(i, 1).equals(getSymbolAt(i, 2)) &&
                !getSymbolAt(i, 0).equals("")) {
                declareWinner(getSymbolAt(i, 0), "row", i);
                return;
            }
            if (getSymbolAt(0, i).equals(getSymbolAt(1, i)) &&
                getSymbolAt(1, i).equals(getSymbolAt(2, i)) &&
                !getSymbolAt(0, i).equals("")) {
                declareWinner(getSymbolAt(0, i), "col", i);
                return;
            }
        }
        if (getSymbolAt(0, 0).equals(getSymbolAt(1, 1)) &&
            getSymbolAt(1, 1).equals(getSymbolAt(2, 2)) &&
            !getSymbolAt(0, 0).equals("")) {
            declareWinner(getSymbolAt(0, 0), "diag1", 0);
            return;
        }
        if (getSymbolAt(0, 2).equals(getSymbolAt(1, 1)) &&
            getSymbolAt(1, 1).equals(getSymbolAt(2, 0)) &&
            !getSymbolAt(0, 2).equals("")) {
            declareWinner(getSymbolAt(0, 2), "diag2", 0);
            return;
        }
    }

    private String getSymbolAt(int row, int col) {
        java.util.List<Actor> actors = getObjectsAt(150 + col * 100, 100 + row * 100, null);
        for (Actor actor : actors) {
            if (actor instanceof XActor) return "X";
            if (actor instanceof OActor) return "O";
        }
        return "";
    }

    // Check for a draw (count filled cells based on symbols)
    private void checkDraw() {
        int filledCount = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (!getSymbolAt(row, col).equals("")) {
                    filledCount++;
                }
            }
        }
        if (filledCount == 9) {
            showText("It's a draw!", 500, 100);
            gameOver = true;
        }
    }

    // Declare winner and update score
    private void declareWinner(String winner, String type, int index) {
        showText(winner + " wins!", 500, 100);
        if (winner.equals("X")) {
            scoreX++;
        } else {
            scoreO++;
        }
        updateScoreDisplay();
        gameOver = true;
        // Draw winning line
        drawWinningLine(type, index);
        
        if (scoreX == MAX_SCORE || scoreO == MAX_SCORE) {
            showText(winner + " is the series winner!", 500, 150);
            resetScores();  // Reset scores
            gameOver = true;
        }
    }

    // Draw winning line based on type
    private void drawWinningLine(String type, int index) {
        int x, y;
        boolean isDiagonal = false;
        int rotation = 0;
        if (type.equals("row")) {
            x = 250;  // Center of grid
            y = 100 + index * 100;
            rotation = 0;  // Horizontal
        } else if (type.equals("col")) {
            x = 150 + index * 100;
            y = 200;  // Center of grid
            rotation = 90;  // Vertical
        } else if (type.equals("diag1")) {
            x = 250;
            y = 200;
            rotation = 45;  // Diagonal \
            isDiagonal = true;
        } else {  // diag2
            x = 250;
            y = 200;
            rotation = -45;  // Diagonal /
            isDiagonal = true;
        }
        winningLine = new WinningLineActor(isDiagonal, rotation);
        addObject(winningLine, x, y);
    }

    // Update score display
    private void updateScoreDisplay() {
        showText("Score X: " + scoreX + " | Score O: " + scoreO, 300, 30);
    }

    // Reset the game
    public void resetGame() {
        currentPlayer = "X";
        gameOver = false;
        removeObjects(getObjects(XActor.class));
        removeObjects(getObjects(OActor.class));
        if (winningLine != null) {
            removeObject(winningLine);
            winningLine = null;
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setOccupied(false);  // Reset occupied flag
            }
        }
        showText("", 300, 50);
        showText("", 500, 100);  // Clear win/draw message
        showText("", 500, 150);  // Clear series winner message
    }

    // Reset scores (for menu)
    public void resetScores() {
        scoreX = 0;
        scoreO = 0;
        updateScoreDisplay();
    }

    // Start the game with selected mode
    public void startGame(boolean vsBot) {
        isVsBot = vsBot;
        removeObjects(getObjects(null));
        prepareGame();
        resetGame();
    }

    // Bot makes a random move on an empty cell
    private void makeBotMove() {
        java.util.List<Cell> emptyCells = new java.util.ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (!board[row][col].isOccupied()) {
                    emptyCells.add(board[row][col]);
                }
            }
        }
        if (!emptyCells.isEmpty()) {
            Cell randomCell = emptyCells.get(Greenfoot.getRandomNumber(emptyCells.size()));
            placeSymbol(randomCell, "O");
        }
    }

    // Getter for gameOver
    public boolean isGameOver() {
        return gameOver;
    }

    // Getter for isMenu
    public boolean isMenu() {
        return isMenu;
    }
}