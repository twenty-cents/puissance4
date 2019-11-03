package fr.simplon.devweb2019.puissance4.business;

import fr.simplon.devweb2019.puissance4.business.enums.Types;
import fr.simplon.devweb2019.puissance4.ihm.Terminal;
import fr.simplon.devweb2019.puissance4.business.enums.Tokens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Puissance4 {

    private final String displayNewTurn = "Joueur %s (%s), à vous de jouer !";
    private final String displaySelectColumn = "Quelle colonne pour ce nouveau coup : ";
    private final String displayColumnError = "Impossible, cette colonne est pleine!";
    private final String displayVictory = "%s (%s) a gagné la partie !";
    private final String askNewGame = "Voulez-vous faire une autre partie (O/N) ? ";
    private final String drawGame = "Match nul !";

    private int gridRows = 6;
    private int gridCols = 7;
    private int turnsToPlay = gridRows * gridCols;

    private Player player1;
    private Player player2;

    private Token[][] grid;

    private Terminal terminal;

    /**
     * Constructor
     */
    public Puissance4() {
        terminal = new Terminal();
    }

    /**
     * Constructor
     * @param gridRows
     * @param gridCols
     */
    public Puissance4(int gridRows, int gridCols){
        // Call the default constructor
        this();

        // Adapt game grid size
        this.gridRows = gridRows;
        this.gridCols = gridCols;
        turnsToPlay = gridRows * gridCols;
    }

    /**
     * Run the game
     */
    public void run(){

        while(true){
            // Init game
            initGame();
            // Start a new game
            newGame();

            // Ask for a new game
            List<String> askValues = new ArrayList<>();
            askValues.add("O");
            askValues.add("N");
            String ask = terminal.selectValue(askValues, askNewGame);
            if(ask.compareTo("N") == 0){
                break;
            }
        }
    }

    /**
     * Initialize a new game
     */
    private void initGame(){
        initGrid();
        initPlayers();
        terminal.displayGrid(grid);
    }

    /**
     * Initialize a new grid with empty tokens
     */
    private void initGrid(){
        // Init game : 6 rows / 7 columns
        grid = new Token[gridRows][gridCols];

        // Init each token as empty
        for(int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Token(row, col);
            }
        }
    }

    /**
     * Initialize the two players
     */
    private void initPlayers(){
        // Get players
        player1 = terminal.getPlayer();
        player1.setColor(Tokens.RED.toString());
        player2 = terminal.getPlayer();
        player2.setColor(Tokens.YELLOW.toString());
    }

    /**
     * Start a new game
     */
    private void newGame(){
        boolean winner = false;

        do{
            // Play a new turn
            if(turnsToPlay % 2 == 0){
                newTurn(player1);
                // Check if a player has win the game
                winner = checkVictory(player1);
            } else {
                newTurn(player2);
                // Check if a player has win the game
                winner = checkVictory(player2);
            }
            turnsToPlay--;

        } while(!winner || turnsToPlay == 0);

        if(turnsToPlay == 0){
            System.out.println(drawGame);
        }
    }

    /**
     * Play a turn
     * @param player
     */
    private void newTurn(Player player){
        terminal.display(String.format(displayNewTurn, player.getColor(), player.getName()));

        if(player.getType().compareToIgnoreCase(Types.HUMAN.toString()) == 0){
            newTurnHuman(player);
        } else {
            newTurnBot(player);
        }

        // Display the grid
        terminal.displayGrid(grid);
    }

    /**
     * Play a turn (human)
     * @param player
     */
    private void newTurnHuman(Player player){
        boolean done = false;

        do {
            int colSelected = terminal.selectColumn(gridCols, displaySelectColumn);
            done = play(colSelected, player.getColor());

            if(done == false){
                terminal.display(displayColumnError);
            }
        } while(done == false);

    }

    /**
     * Play a turn (bot)
     * @param player
     */
    private void newTurnBot(Player player){
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = ThreadLocalRandom.current().nextInt(0, 6 + 1);
        // Play
        play(randomNum, player.getColor());
    }

    /**
     * Play a turn
     * @param col : column where the token must be added
     * @param color : player color
     * @return : true / false if the turn has been played
     */
    private boolean play(int col, String color){
        boolean isDone = false;

        for(int row=0; row < gridRows; row++){
            if(grid[row][col].toString().compareTo(Tokens.EMPTY.toString()) == 0){
                grid[row][col].setStatus(color);
                isDone = true;
                break;
            }
        }

        return isDone;
    }

    /**
     * Check if a player has win the game
     * @param player
     * @return
     */
    private boolean checkVictory(Player player){
        boolean victory = false;

        String color = player.getColor();

        // Check for four consecutive tokens on a row
        for(int row = 0; row < gridRows; row++){
            for(int col = 0; col < gridCols; col++){
                int ph = 0;
                int pv = 0;
                int x1 = 0;
                int x2 = 0;
                int x3 = 0;
                int x4 = 0;

                if(grid[row][col].getStatus().compareTo(player.getColor()) == 0) {
                    // Check victory on a row
                    ph++;
                    ph += checkTocken(row, col+1, color)
                            + checkTocken(row, col+2, color)
                            + checkTocken(row, col+3, color);

                    // Check victory on a col
                    pv++;
                    pv += checkTocken(row+1, col, color)
                            + checkTocken(row+2, col, color)
                            + checkTocken(row+3, col, color);

                    // Check victory on a diagonal
                    x1++;
                    x1 += checkTocken(row+1, col+1, color)
                            + checkTocken(row+2, col+2, color)
                            + checkTocken(row+3, col+3, color);

                    x2++;
                    x2 += checkTocken(row+1, col-1, color)
                            + checkTocken(row+2, col-2, color)
                            + checkTocken(row+3, col-3, color);

                    x3++;
                    x3 += checkTocken(row-1, col+1, color)
                            + checkTocken(row-2, col+2, color)
                            + checkTocken(row-3, col+3, color);

                    x4++;
                    x4 += checkTocken(row-1, col-1, color)
                            + checkTocken(row-2, col-2, color)
                            + checkTocken(row-3, col-3, color);

                }

                if(ph == 4 || pv == 4 || x1 == 4 || x2 == 4 || x3 == 4 || x4 == 4){
                    victory = true;
                    break;
                }
            }
        }

        if(victory){
            System.out.println(String.format(displayVictory, player.getName(), player.getColor()));
        }
        return victory;
    }

    /**
     * Check the content of a token
     * @param row
     * @param col
     * @param color
     * @return
     */
    private int checkTocken(int row, int col, String color){
        int points = 0;

        if(row >= gridRows || row < 0)
            return points;

        if(col >= gridCols || col < 0)
            return points;

        if(grid[row][col].getStatus().compareTo(color) == 0){
            points++;
        }
        return points;
    }

}
