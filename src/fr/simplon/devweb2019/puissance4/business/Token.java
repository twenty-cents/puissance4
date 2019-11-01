package fr.simplon.devweb2019.puissance4.business;

import fr.simplon.devweb2019.puissance4.business.enums.Tokens;

public class Token {

//    public static final String EMPTY = "EMPTY";
//    public static final String RED = "RED";
//    public static final String YELLOW = "YELLOW";

    private String status = "";
    private String renderedStatus;
    private int row;
    private int col;

    public Token() {
        status = Tokens.EMPTY.toString();
        renderedStatus = Tokens.EMPTY.getRenderer();
    }

    public Token(int row, int col){
        this();

        this.row = row;
        this.col = col;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
        for(Tokens t : Tokens.values()){
            if(t.toString().compareTo(status) == 0){
                renderedStatus = t.getRenderer();
                break;
            }
        }
    }

    public String getRenderedStatus() {
        return renderedStatus;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return status;
    }
}
