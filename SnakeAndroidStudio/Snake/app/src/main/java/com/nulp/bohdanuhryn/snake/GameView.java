package com.nulp.bohdanuhryn.snake;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.app.Activity;

/**
 * Created by BohdanUhryn on 14.06.2015.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder surfaceHolder;

    Activity context;

    private GameEngine gameEngine;

    private GameThread gameThread;

    public GameView(Activity _context, GameEngine _gameEngine) {
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

        setOnTouchListener(new OnSwipeTouchListener(context) {
            public void onSwipeTop() {
                gameEngine.SetMoveDirection(0, -1);
            }

            public void onSwipeRight() {
                gameEngine.SetMoveDirection(1, 0);
            }

            public void onSwipeLeft() {
                gameEngine.SetMoveDirection(-1, 0);
            }

            public void onSwipeBottom() {
                gameEngine.SetMoveDirection(0, 1);
            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        gameEngine.UpdateCellSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameThread.SetIsRunning(false);
    }

    public void Suspend() {
        gameThread.SetPause(true);
    }

    public void Resume() {
        gameThread.SetPause(false);
    }
}
