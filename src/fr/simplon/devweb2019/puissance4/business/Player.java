package fr.simplon.devweb2019.puissance4.business;

import fr.simplon.devweb2019.puissance4.business.enums.Types;

public class Player {

    private String name;
    private String type = Types.HUMAN.toString();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
