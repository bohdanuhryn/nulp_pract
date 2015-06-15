package com.nulp.bohdanuhryn.snake;

import java.util.Vector;
import java.lang.Math;
import android.graphics.Point;

/**
 * Created by BohdanUhryn on 13.06.2015.
 */
public class GameField {

    public byte width;
    public byte height;
    public Vector<FieldCell> field;

    private Snake snake;

    public Food food;

    public GameField(
            byte _width,
            byte _height,
            Vector<FieldCell> _field,
            Snake _snake) {
        width = _width;
        height = _height;
        field = _field;
        snake = _snake;
        food = null;
        GenerateFood();
    }

    public void GenerateFood() {
        Vector<Point> fieldItems = new Vector<Point>();
        for(int i = 0; i < field.size(); ++i) {
            fieldItems.add((Point)field.elementAt(i));
        }
        for(int i = 0; i < snake.body.size(); ++i) {
            fieldItems.add((Point)snake.body.elementAt(i));
        }
    }

    public void Move(int moveX, int moveY) {
        int dx = snake.body.elementAt(0).x - snake.body.elementAt(1).x;
        int dy = snake.body.elementAt(0).y - snake.body.elementAt(1).y;
        if(Math.abs(dx) > 1)
            dx = dx / Math.abs(dx) * -1;
        if(Math.abs(dy) > 1)
            dy = dy / Math.abs(dy) * -1;
        Bone newHead;
        if((moveX == 0 && moveY == 0) || (moveX == dx || moveY == dy) || (moveX * -1 == dx || moveY * -1 == dy))
            newHead = new Bone(snake.body.elementAt(0).x + dx, snake.body.elementAt(0).y + dy);
        else
            newHead = new Bone(snake.body.elementAt(0).x + moveX, snake.body.elementAt(0).y + moveY);

        if(newHead.x < 0) newHead.x = width - 1;
        if(newHead.y < 0) newHead.y = height - 1;
        if(newHead.x >= width) newHead.x = 0;
        if(newHead.y >= height) newHead.y = 0;

        for(int i = 0; i < field.size(); ++i) {
            if(field.elementAt(i).x == newHead.x && field.elementAt(i).y == newHead.y)
                return; //TODO:!!!!!!!!!!!
        }
        for(int i = 0; i < snake.body.size(); ++i) {
            if(snake.body.elementAt(i).x == newHead.x && snake.body.elementAt(i).y == newHead.y)
                return; //TODO:!!!!!!!!!!!
        }

        snake.body.add(0, newHead);
        snake.body.remove(snake.body.lastElement());
    }
}