package fr.simplon.devweb2019.puissance4.business.enums;

public enum Tokens {
    EMPTY  ("[        ]"),
    RED    ("[  RED   ]"),
    YELLOW ("[ YELLOW ]");

    private String renderer;

    Tokens(String renderer){
        this.renderer = renderer;
    }

    public String getRenderer() {
        return renderer;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
