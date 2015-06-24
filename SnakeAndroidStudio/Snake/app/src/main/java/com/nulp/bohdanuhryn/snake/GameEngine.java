package com.nulp.bohdanuhryn.snake;

import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.app.Activity;
import android.os.Looper;
import android.widget.TextView;
import android.os.Handler;
import android.content.Context;

import com.nulp.bohdanuhryn.snake.gui.MainMenuActivity;
import com.nulp.bohdanuhryn.snake.gui.SaveScoreActivity;

public class GameEngine {

    private Context context;

    private GameField gameField;

    private Snake snake;

    private int cellSize;

    private int moveX;

    private int moveY;

    private int gameSpeed;

    private int selectedLevelId;

    public GameEngine() {
        selectedLevelId = -1;
        TestField();
        moveX = 0;
        moveY = 0;
        gameSpeed = snake.GetSpeed();
    }

    public GameEngine(int _selectedLevelId) {
        selectedLevelId = _selectedLevelId;
        LevelResource level = LevelResource.Get(selectedLevelId);
        snake = level.snake;
        gameField = new GameField(level.width, level.height, level.walls, snake);
        moveX = 0;
        moveY = 0;
        gameSpeed = snake.GetSpeed();
    }

    public void InitActivity(final Activity _context) {
        context = _context;
        final TextView score = (TextView) _context.findViewById(R.id.score_text_view);
        score.post(new Runnable() {
            public void run() {
                String str = Integer.toString(snake.foodWeight).concat(" / ")
                        .concat(Integer.toString(ScoresManager.GetMaxScore()));
                score.setText(str);
            }
        });
    }

    public void Update() {
        gameField.Move(moveX, moveY);
        moveX = 0;
        moveY = 0;
    }

    public void UpdateUI(final Activity context) {
        switch (snake.GetState()) {
            case EAT_FOOD:
                ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.SNAKE_EAT_SOUND);
                final TextView score = (TextView) context.findViewById(R.id.score_text_view);
                score.post(new Runnable() {
                    public void run() {
                        String str = Integer.toString(snake.foodWeight).concat(" / ")
                                .concat(Integer.toString(ScoresManager.GetMaxScore()));
                        score.setText(str);
                    }
                });
                gameSpeed = snake.GetSpeed();
                break;
            case BITE_ITSELF:
            case KICK_WALL:
                ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.SNAKE_BITE_SOUND);
                Handler handler = new Handler(Looper.getMainLooper());
                if(ScoresManager.CheckScore(snake.foodWeight)) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(context, SaveScoreActivity.class);
                            intent.putExtra("score", snake.foodWeight);
                            intent.putExtra(
                                    context.getResources().getString(R.string.selected_level_id),
                                    selectedLevelId);
                            context.startActivity(intent);
                        }
                    });
                }
                else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(context, MainMenuActivity.class);
                            intent.putExtra(
                                    context.getResources().getString(R.string.set_game_end_menu),
                                    true);
                            intent.putExtra(
                                    context.getResources().getString(R.string.selected_level_id),
                                    selectedLevelId);
                            context.startActivity(intent);
                        }
                    });
                }
                break;
        }
    }

    public void Draw(Canvas canvas) {
        Paint p = new Paint();

        p.setARGB(255, 190, 190, 100);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), p);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall);

        Rect srcRect = new Rect();
        srcRect.top = 0;
        srcRect.left = 0;
        srcRect.bottom = bitmap.getHeight();
        srcRect.right = bitmap.getWidth();

        Rect destRect = new Rect();

        p.setARGB(255, 120, 80, 80);
        for(int i = 0; i < gameField.walls.size(); ++i) {
            destRect.top = gameField.walls.elementAt(i).y * cellSize;
            destRect.left = gameField.walls.elementAt(i).x * cellSize;
            destRect.bottom = (gameField.walls.elementAt(i).y + 1) * cellSize;
            destRect.right = (gameField.walls.elementAt(i).x + 1) * cellSize;
            canvas.drawBitmap(bitmap, srcRect, destRect, p);
        }

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.food);
        srcRect.top = 0;
        srcRect.left = 0;
        srcRect.bottom = bitmap.getHeight();
        srcRect.right = bitmap.getWidth();
        p.setARGB(255, 255, 0, 0);
        if(gameField.food != null) {
            destRect.top = gameField.food.y * cellSize;
            destRect.left = gameField.food.x * cellSize;
            destRect.bottom = (gameField.food.y + 1) * cellSize;
            destRect.right = (gameField.food.x + 1) * cellSize;
            canvas.drawBitmap(bitmap, srcRect, destRect, p);
        }


        p.setARGB(255, 0, 160, 0);
        Matrix rotMatrix;
        int snakeLength = snake.body.size();
        for(int i = 0; i < snakeLength; ++i) {
            rotMatrix = new Matrix();
            if(i == 0 || i == snakeLength - 1) {
                if(i == 0)
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.snake_head);
                else
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.snake_tail);
                srcRect.top = 0;
                srcRect.left = 0;
                srcRect.bottom = bitmap.getHeight();
                srcRect.right = bitmap.getWidth();
                int dh, dv;
                if(i == 0) {
                    dh = snake.body.elementAt(1).x - snake.body.elementAt(0).x;
                    dv = snake.body.elementAt(1).y - snake.body.elementAt(0).y;
                }
                else {
                    dh = snake.body.elementAt(i).x - snake.body.elementAt(i - 1).x;
                    dv = snake.body.elementAt(i).y - snake.body.elementAt(i - 1).y;
                }
                if(dh == -1)
                    rotMatrix.setRotate(90);
                else if(dh == 1)
                    rotMatrix.setRotate(270);
                else if(dv == -1)
                    rotMatrix.setRotate(180);
            }
            else {
                int dhb, dha, dvb, dva;
                dhb = snake.body.elementAt(i - 1).x - snake.body.elementAt(i).x;//horizontal delta
                dvb = snake.body.elementAt(i).y - snake.body.elementAt(i - 1).y;//vertical delta
                dha = snake.body.elementAt(i + 1).x - snake.body.elementAt(i).x;//horizontal delta
                dva = snake.body.elementAt(i).y - snake.body.elementAt(i + 1).y;//vertical delta
                if(dhb * dha + dvb * dva == 0) {// vactors are rectangular
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.snake_body_ang);
                    if((dhb == -1 && dva == -1) || (dvb == -1 && dha == -1))
                        rotMatrix.setRotate(90);
                    else if((dhb == -1 && dva == 1) || (dvb == 1 && dha == -1))
                        rotMatrix.setRotate(180);
                    else if((dhb == 1 && dva == 1) || (dvb == 1 && dha == 1))
                        rotMatrix.setRotate(270);
                }
                else {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.snake_body);
                    if(dhb == -1)
                        rotMatrix.setRotate(90);
                    else if(dhb == 1)
                        rotMatrix.setRotate(270);
                    else if(dvb == -1)
                        rotMatrix.setRotate(180);
                }
            }
            bitmap = Bitmap.createBitmap(
                    bitmap, 0, 0, srcRect.right, srcRect.bottom, rotMatrix, false);
            destRect.top = snake.body.elementAt(i).y * cellSize;
            destRect.left = snake.body.elementAt(i).x * cellSize;
            destRect.bottom = (snake.body.elementAt(i).y + 1) * cellSize;
            destRect.right = (snake.body.elementAt(i).x + 1) * cellSize;
            canvas.drawBitmap(bitmap, srcRect, destRect, p);
        }
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

    public int GetGameSpeed() {
        return gameSpeed;
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