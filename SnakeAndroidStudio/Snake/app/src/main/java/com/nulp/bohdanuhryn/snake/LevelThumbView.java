package com.nulp.bohdanuhryn.snake;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.content.res.TypedArray;
import android.content.Context;

import java.util.Vector;

/**
 * Created by BohdanUhryn on 23.06.2015.
 */
public class LevelThumbView extends View {

    private int width;

    private int height;

    private Vector<Wall> walls;

    private Snake snake;

    private int cellSize;

    private int levelId;

    public LevelThumbView(Context context) {
        super(context);
    }

    public LevelThumbView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        UpdateCellSize(w, h);
    }

    public void setLevelId(int id) {
        levelId = id;
        LevelResource level = LevelResource.Get(levelId);
        width = level.width;
        height = level.height;
        snake = level.snake;
        walls = level.walls;
    }

    public int getLevelId() {
        return levelId;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       /* Paint p = new Paint();
        p.setARGB(255, 190, 190, 100);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), p);

        if(walls != null) {
            p.setARGB(255, 120, 80, 80);
            for (int i = 0; i < walls.size(); ++i) {
                if(cellSize * width > cellSize * height)
                    canvas.drawRect(
                            walls.elementAt(i).x * cellSize,
                            walls.elementAt(i).y * cellSize,
                            (walls.elementAt(i).x + 1) * cellSize,
                            (walls.elementAt(i).y + 1) * cellSize,
                            p);
                else
                    canvas.drawRect(
                            walls.elementAt(i).y * cellSize,
                            walls.elementAt(i).x * cellSize,
                            (walls.elementAt(i).y + 1) * cellSize,
                            (walls.elementAt(i).x + 1) * cellSize,
                            p);
            }
        }
        if(snake != null) {
            p.setARGB(255, 0, 160, 0);
            for (int i = 0; i < snake.body.size(); ++i) {
                canvas.drawRect(
                        snake.body.elementAt(i).x * cellSize,
                        snake.body.elementAt(i).y * cellSize,
                        (snake.body.elementAt(i).x + 1) * cellSize,
                        (snake.body.elementAt(i).y + 1) * cellSize,
                        p);
            }
        }*/
    }

    public void UpdateCellSize(int _width, int _height) {
        if(width != 0)
            cellSize = _width / width;
        if(height != 0)
            if(_height / height < cellSize)
                cellSize = _height / height;
    }
}