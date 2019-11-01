package fr.simplon.devweb2019.puissance4.business;

public class Player {

    public static final String HUMAN = "HUMAN";
    public static final String BOT = "BOT";

    public static final String RED = "RED";
    public static final String YELLOW = "YELLOW";

    private String name;
    private String type = Player.HUMAN;
    private String color;

    public Player() {
    }

    public Player(String name, String type, String color) {
        this.name = name;
        this.type = type;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static String getHUMAN() {
        return HUMAN;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
