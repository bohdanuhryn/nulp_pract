package com.nulp.bohdanuhryn.snake;

//import java.lang;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by BohdanUhryn on 14.06.2015.
 */
public class GameThread extends Thread {

    public GameThread(SurfaceHolder _surfaceHolder, GameEngine _gameEngine, Context _context) {
        surfaceHolder = _surfaceHolder;
        gameEngine = _gameEngine;
        context = _context;

        backgroundPaint = new Paint();
        backgroundPaint.setARGB(255, 120, 210, 0);
        isRun = true;
    }

    private SurfaceHolder surfaceHolder;
    private GameEngine gameEngine;
    private Context context;

    private Paint backgroundPaint;

    private boolean isRun;

    long _sleepTime;
    final long  DELAY = 4;

    @Override
    public void run() {
        super.run();
        while (isRun)
        {
            gameEngine.Update();

            Canvas canvas = surfaceHolder.lockCanvas(null);

            if (canvas != null)
            {
                synchronized (surfaceHolder)
                {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    gameEngine.Draw(canvas);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            try
            {
                Thread.sleep(DELAY);
            }
            catch (InterruptedException ex)
            {
            }
        }
    }

    public boolean IsRunning() { return isRun; }

    public void SetIsRunning(boolean state) { isRun = state; }
}
