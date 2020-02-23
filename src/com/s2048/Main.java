package com.s2048;

import com.s2048.GameFrame.GameController;
import com.s2048.GameFrame.GameModel;
import com.s2048.GameFrame.GameView;

public class Main {
    public static void main(String[] args) {
        GameView view = new GameView("2048", 4, 4);
        GameController controller = new GameController();
        GameModel model = new GameModel(4, 4);
        model.setView(view);
        view.setController(controller);
        controller.setModel(model);
    }
}
