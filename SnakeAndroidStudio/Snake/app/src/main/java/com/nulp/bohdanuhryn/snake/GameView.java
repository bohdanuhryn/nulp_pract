package com.nulp.bohdanuhryn.snake;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;

/**
 * Created by BohdanUhryn on 14.06.2015.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder surfaceHolder;
    Context context;

    private GameEngine gameEngine;
    private GameThread gameThread;

    public GameView(Context _context, GameEngine _gameEngine) {
        super(_context);

        gameEngine = _gameEngine;
        context = _context;

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        gameThread = new GameThread(holder, gameEngine, context);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
       if(!gameThread.IsRunning())
           gameThread = new GameThread(getHolder(), gameEngine, context);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameThread.SetIsRunning(false);
    }
}
