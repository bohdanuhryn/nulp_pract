package com.nulp.bohdanuhryn.snake;

import java.util.Vector;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by BohdanUhryn on 14.06.2015.
 */
public class GameEngine {

    private GameField gameField;
    private Snake snake;
    private int cellSize;
    private int moveX;
    private int moveY;

    public GameEngine() {
        TestField();
        moveX = 0;
        moveY = 0;
    }

    public void Update() {
        gameField.Move(moveX, moveY);
        moveX = 0;
        moveY = 0;
    }

    public void Draw(Canvas canvas) {
        Paint p = new Paint();

        p.setARGB(255, 190, 190, 100);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), p);

        p.setARGB(255, 120, 80, 80);
        for(int i = 0; i < gameField.walls.size(); ++i) {
            canvas.drawRect(
                    gameField.walls.elementAt(i).x * cellSize,
                    gameField.walls.elementAt(i).y * cellSize,
                    (gameField.walls.elementAt(i).x + 1) * cellSize,
                    (gameField.walls.elementAt(i).y + 1) * cellSize,
                    p);
        }

        p.setARGB(255, 255, 0, 0);
        if(gameField.food != null)
            canvas.drawCircle(
                    gameField.food.x * cellSize + cellSize / 2,
                    gameField.food.y * cellSize + cellSize / 2,
                    cellSize / 2,
                    p);

        p.setARGB(255, 0, 160, 0);
        for(int i = 0; i < snake.body.size(); ++i) {
            canvas.drawRect(
                    snake.body.elementAt(i).x * cellSize,
                    snake.body.elementAt(i).y * cellSize,
                    (snake.body.elementAt(i).x + 1) * cellSize,
                    (snake.body.elementAt(i).y + 1) * cellSize,
                    p);
        }
    }

    public int GetScore() {
        return snake.foodWeight;// TODO: !!!!!!!!!!
    }

    public void SetMoveDirection(int _moveX, int _moveY) {
        moveX = _moveX;
        moveY = _moveY;
    }

    public void UpdateCellSize(int _width, int _height) {
        cellSize = _width / gameField.width;
        if(_height / gameField.height < cellSize)
            cellSize = _height / gameField.height;
    }

    private void TestField() {
        snake = new Snake(2, 2, 2);
        byte w = 10;
        byte h = 10;
        Vector<Wall> field = new Vector<Wall>();
        field.add(new Wall(0, 0));
        field.add(new Wall(0, 9));
        field.add(new Wall(9, 9));
        field.add(new Wall(9, 0));
        gameField = new GameField(w, h, field, snake);
    }
}