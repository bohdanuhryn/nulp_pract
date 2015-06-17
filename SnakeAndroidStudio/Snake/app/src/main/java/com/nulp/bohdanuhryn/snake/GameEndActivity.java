package com.nulp.bohdanuhryn.snake;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

public class GameEndActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        TextView header = (TextView)findViewById(R.id.menu_header);
        header.setText(R.string.game_end_header);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.button_click);

        LinearLayout content = (LinearLayout)findViewById(R.id.menu_content);
        Button button;

        button = new Button(this);
        button.setText(R.string.restart_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LevelActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(button);

        // Main menu button
        button = new Button(this);
        button.setText(R.string.main_menu_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainMenuActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(button);
    }
}