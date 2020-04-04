package com.example.statsgaa;


public class Player  {

    // Adding Players

    private String playerName;
    private int playerHeight;
    private int playerWeight;
    private int playerAge;
    private int foreignKey;
    private String playerPosition;

    public Player() {
        this.playerName = playerName;
        this.playerHeight = playerHeight;
        this.playerWeight = playerWeight;
        this.playerAge = playerAge;
        this.playerPosition = playerPosition;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public int getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerWeight(int playerWeight) {
        this.playerWeight = playerWeight;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public int getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(int foreignKey) {
        this.foreignKey = foreignKey;
    }
}
