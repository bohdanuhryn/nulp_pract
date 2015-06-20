package com.nulp.bohdanuhryn.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.widget.TextView;
import android.app.Activity;

public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;

    private GameEngine gameEngine;

    private Activity context;

    private Paint backgroundPaint;

    private boolean isRun;

    private boolean isPause;

    long  timeDelta = 500;

    public GameThread(SurfaceHolder _surfaceHolder, GameEngine _gameEngine, Activity _context) {
        surfaceHolder = _surfaceHolder;
        gameEngine = _gameEngine;
        context = _context;

        backgroundPaint = new Paint();
        backgroundPaint.setARGB(255, 0, 0, 0);
        isRun = true;
        isPause = false;
    }

    @Override
    public void run() {
        super.run();
        while (isRun) {
            if(isPause) continue;

            gameEngine.Update();

            gameEngine.UpdateUI(context);

            Canvas canvas = surfaceHolder.lockCanvas(null);

            if (canvas != null) {
                synchronized (surfaceHolder) {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    gameEngine.Draw(canvas);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            try {
                Thread.sleep(gameEngine.GetGameSpeed());
            }
            catch (InterruptedException ex) {
            }
        }
    }

    public boolean IsPaused() {
        return isPause;
    }

    public void SetPause(boolean set) {
        isPause = set;
    }

    public boolean IsRunning() {
        return isRun;
    }

    public void SetIsRunning(boolean state) {
        isRun = state;
    }
}
