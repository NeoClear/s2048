package com.s2048.GameFrame;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;

public class GameModel {
    GameView view;
    int[][] blocks;
    int row, col;
    int score = 0;
    public GameModel(int row, int col) {
        blocks = new int[row][col];
        this.row = row;
        this.col = col;
        blocks[1][1] = 2;
    }

    public void setView(GameView view) {
        this.view = view;
        updateView();
    }
    private void placeValueAtRandom(int value, ArrayList<Map.Entry<Integer, Integer>> emptyBlocks) {
        Random random = new Random();
        Map.Entry<Integer, Integer> pos = emptyBlocks.get(random.nextInt(emptyBlocks.size()));
        blocks[pos.getKey()][pos.getValue()] = value;
    }
    private boolean inBound(int x, int y) {
        return x >= 0 && x < row &&
               y >= 0 && y < col;
    }
    private void goMerge(int row, int col, Map.Entry<Integer, Integer> dir) {
        int tx = row + dir.getKey(), ty = col + dir.getValue();
        if (inBound(tx, ty)) {
            if (blocks[tx][ty] == 0) {
                blocks[tx][ty] = blocks[row][col];
                blocks[row][col] = 0;
                goMerge(tx, ty, dir);
            } else if (blocks[tx][ty] == blocks[row][col]) {
                blocks[tx][ty] += blocks[row][col];
                blocks[row][col] = 0;
            }
        }
    }
    private boolean canMerge(int row, int col, Map.Entry<Integer, Integer> dir) {
        int tx = row + dir.getKey(), ty = col + dir.getValue();
        return inBound(tx, ty) &&
               (blocks[tx][ty] == 0 || blocks[tx][ty] == blocks[row][col]);
    }
    private void goDirection(Map.Entry<Integer, Integer> dir) {
        ArrayList<Map.Entry<Integer, Integer>> emptyBlocks = new ArrayList();

        int rInitial = dir.getKey() + dir.getValue() == 1 ? row - 1 : 0;
        int rFinal = dir.getKey() + dir.getValue() == 1 ? -1 : row;
        int rUpdate = dir.getKey() + dir.getValue() == 1 ? -1 : 1;

        int cInitial = dir.getKey() + dir.getValue() == 1 ? col - 1 : 0;
        int cFinal = dir.getKey() + dir.getValue() == 1 ? -1 : col;
        int cUpdate = dir.getKey() + dir.getValue() == 1 ? -1 : 1;

        boolean canBeMoved = false;

        for (int r = rInitial; r != rFinal; r += rUpdate) {
            for (int c = cInitial; c != cFinal; c += cUpdate) {
                if (blocks[r][c] != 0 && canMerge(r, c, dir)) {
                    goMerge(r, c, dir);
                    canBeMoved = true;
                }
            }
        }
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (blocks[r][c] == 0)
                    emptyBlocks.add(new AbstractMap.SimpleEntry(r, c));
            }
        }
        if (emptyBlocks.size() == 0) {
            view.gameEnds("Game Ends with " + score + " scores.");
        }
        if (canBeMoved) {
            placeValueAtRandom(2, emptyBlocks);
            score = countScore();
            System.out.println("Current score is: " + score);
            updateView();
        }
    }
    public void goUp() {
        goDirection(new AbstractMap.SimpleEntry(-1, 0));
    }
    public void goDown() {
        goDirection(new AbstractMap.SimpleEntry(1, 0));
    }
    public void goLeft() {
        goDirection(new AbstractMap.SimpleEntry(0, -1));

    }
    public void goRight() {
        goDirection(new AbstractMap.SimpleEntry(0, 1));
    }
    private void updateView() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                view.setLabel(blocks[r][c], r, c);
            }
        }
    }
    private int countScore() {
        int cnt = 0;
        for (int r = 0; r < row; r++)
            for (int c = 0; c < col; c++)
                cnt += blocks[r][c];
        return cnt;
    }
}
