package com.nulp.bohdanuhryn.snake.gui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nulp.bohdanuhryn.snake.GameEngine;
import com.nulp.bohdanuhryn.snake.GameView;
import com.nulp.bohdanuhryn.snake.R;
import com.nulp.bohdanuhryn.snake.ResourceManager;
import com.nulp.bohdanuhryn.snake.ScoresManager;


public class LevelActivity extends Activity {

    private GameEngine gameEngine;

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_level);

        gameEngine = new GameEngine();
        gameEngine.InitActivity(this);
        gameView = new GameView(this, gameEngine);

        LinearLayout l = (LinearLayout)findViewById(R.id.level_linear_layout);
        l.addView(gameView);

        ScoresManager.Init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.Resume();
        ResourceManager.ResumeBackgroundMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.Suspend();
        ResourceManager.PauseBackgroundMusic();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void pauseOnClick(View v) {
        gameView.Suspend();
        Intent intent = new Intent(v.getContext(), MainMenuActivity.class);
        intent.putExtra(getResources().getString(R.string.set_pause_menu), true);
        ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.BUTTON_CLICK_SOUND);
        startActivity(intent);
    }
}
