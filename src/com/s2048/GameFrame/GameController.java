package com.s2048.GameFrame;

public class GameController {
    GameModel model;
    public void setModel(GameModel model) {
        this.model = model;
    }
    public void goUp() {
        model.goUp();
    }
    public void goDown() {
        model.goDown();
    }
    public void goLeft() {
        model.goLeft();
    }
    public void goRight() {
        model.goRight();
    }
}
