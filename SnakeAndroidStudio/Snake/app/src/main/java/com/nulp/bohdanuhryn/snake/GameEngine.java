package com.nulp.bohdanuhryn.snake;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by BohdanUhryn on 14.06.2015.
 */
public class GameEngine {
    public GameEngine() {

    }

    public void Update() {

    }

    public void Draw(Canvas canvas) {
        Paint p = new Paint();
        p.setARGB(255, 25, 35, 250);
        canvas.drawLine(0, 0, 100, 100, p);
    }
}
