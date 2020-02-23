package com.s2048.GameFrame;

import java.awt.*;

public class GameColorFactory {
    public static Color getColor(int value) {
        switch (value) {
            case 0:
                return new Color(205, 193, 180);
            case 2:
                return new Color(238, 228, 218);
            case 4:
                return new Color(237, 224, 200);
            case 8:
                return new Color(242, 177, 121);
            case 16:
                return new Color(245, 149, 99);
            case 32:
                return new Color(246, 124, 95);
            case 64:
                return new Color(246, 94, 59);
            case 128:
                return new Color(237, 207, 114);
            case 256:
                return new Color(237, 204, 97);
            case 512:
                return new Color(237, 201, 81);
            case 1024:
                return new Color(37, 197, 63);
            case 2048:
                return new Color(32, 191, 66);
            case 4096:
            case 8192:
            case 16384:
            case 32768:
                return new Color(61, 58, 51);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
