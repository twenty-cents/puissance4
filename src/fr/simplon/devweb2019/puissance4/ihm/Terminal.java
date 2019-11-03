package fr.simplon.devweb2019.puissance4.ihm;

import fr.simplon.devweb2019.puissance4.business.Player;
import fr.simplon.devweb2019.puissance4.business.Token;
import fr.simplon.devweb2019.puissance4.business.enums.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terminal {

    private final String playerInput = "Nom du joueur : ";
    private final String playerType = "HUMAN or BOT : ";
    private final String errorSelectcolumn = "Le numéro de colonne doit être compris entre 1 et %d";
    private final String errorInput = "Saisie incorrecte, veuillez ôter vos mouffles...";
    private final String errorSelectValue = "Saisie incorrecte, options possibles : %s";

    private static Scanner sc;
    static {
        sc = new Scanner(System.in);
    }

    /**
     * Constructor
     */
    public Terminal() {
    }

    /**
     * Input a player
     * @return
     */
    public Player getPlayer(){
        // Set types (human or bot)
        List<String> types = new ArrayList<>();
        for(Types type : Types.values()){
            types.add(type.toString());
        }

        Player player = new Player();
        player.setName(input(playerInput));
        player.setType(selectValue(types, playerType));

        // Get a new player
        return player;
    }

    /**
     *
     * @param colMax
     * @param message
     * @return
     */
    public int selectColumn(int colMax, String message){
        int column = -1;

        do{
            try{
                System.out.print(message);
                column = Integer.parseInt(sc.nextLine());
                if(column < 1 || column > colMax)
                    throw new Exception("");
            } catch (Exception e) {
                    System.out.println(String.format(errorSelectcolumn, colMax));
                    column = -1;
            }
        } while(column == -1);

        return --column;
    }

    /**
     * Display the game grid
     * @param grid
     */
    public void displayGrid(Token[][] grid){
        for(int row = grid.length-1; row >= 0; row--){
            for(int col = 0; col < grid[row].length; col++){
                System.out.print(grid[row][col].getRenderedStatus());
            }
            System.out.println();
        }
    }

    /**
     * Input a text in the console
     * @param msg : the message to display
     * @return
     */
    private String input(String msg){
        String in = "";

        do{
            try {
                System.out.print("\n>>> " + msg);
                in = sc.nextLine();
                if(in.compareTo("") == 0)
                    throw new Exception("");
            } catch (Exception e) {
                System.out.println(errorInput);
                in = "";
            }
        }while (in == "");

        return in;
    }

    /**
     * Display a text
     * @param text
     */
    public void display(String text){
        System.out.println(text);
    }

    /**
     * Select a value in a list of acceptable values
     * @param values : valid values
     * @param msg    : input display message
     * @return       : value selected
     */
    public String selectValue(List<String> values, String msg){
        String value = "";

        do{
            try {
                System.out.print("\n>>> " + msg + " : ");
                value = sc.nextLine().toUpperCase();
                if(values.contains(value) == false)
                    throw new Exception("");
            } catch (Exception e) {
                System.out.println(String.format(errorSelectValue, values));
                value = "";
            }
        }while (value == "");

        return value;
    }

}
