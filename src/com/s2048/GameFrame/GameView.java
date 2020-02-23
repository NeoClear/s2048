package com.s2048.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameView extends JFrame implements KeyListener {
    private JLabel[][] labels;
    private int row, col;
    private GameController controller;

    public GameView(String title, int row, int col) {
        super(title);
        labels = new JLabel[row][col];
        this.row = row;
        this.col = col;
        this.setContentPane(new JPanel(new GridLayout(row, col)));
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                JLabel label = new JLabel("", JLabel.CENTER);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setFont(new Font("JetBrains Mono", 1, 48));
                label.setBackground(GameColorFactory.getColor(0));
                this.getContentPane().add(label);
                labels[r][c] = label;
            }
        }
        this.addKeyListener(this);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setLabel(Integer value, int row, int col) {
        if (row < 0 || row >= this.row ||
            col < 0 || col >= this.col) {
            throw new UnsupportedOperationException();
        }
        labels[row][col].setText(value == 0 ? "" : value.toString());
        labels[row][col].setBackground(GameColorFactory.getColor(value));
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
//        System.out.println(keyEvent.toString());
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                controller.goUp();
                break;
            case KeyEvent.VK_DOWN:
                controller.goDown();
                break;
            case KeyEvent.VK_LEFT:
                controller.goLeft();
                break;
            case KeyEvent.VK_RIGHT:
                controller.goRight();
                break;
            default:
                // do nothing
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }

    public void gameEnds(String msg) {
        JOptionPane.showMessageDialog(this, msg);
        System.exit(0);
    }
}
