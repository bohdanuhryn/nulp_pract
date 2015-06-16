package com.nulp.bohdanuhryn.snake;

//import java.lang;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.widget.TextView;
import android.app.Activity;

/**
 * Created by BohdanUhryn on 14.06.2015.
 */
public class GameThread extends Thread {

    public GameThread(SurfaceHolder _surfaceHolder, GameEngine _gameEngine, Activity _context) {
        surfaceHolder = _surfaceHolder;
        gameEngine = _gameEngine;
        context = _context;

        backgroundPaint = new Paint();
        backgroundPaint.setARGB(255, 0, 0, 0);
        isRun = true;
    }

    private SurfaceHolder surfaceHolder;
    private GameEngine gameEngine;
    private Activity context;

    private Paint backgroundPaint;

    private boolean isRun;

    long _sleepTime;
    final long  DELAY = 1000;

    @Override
    public void run() {
        super.run();
        while (isRun)
        {
            gameEngine.Update();

            final TextView score = (TextView)context.findViewById(R.id.score_text_view);
            score.post(new Runnable() {
                           public void run() {
                               score.setText(Integer.toString(gameEngine.GetScore()));
                           }
                       });

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
