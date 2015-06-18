package com.nulp.bohdanuhryn.snake;

import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
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
import android.graphics.drawable.Drawable;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // fullscreen on
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set layout
        setContentView(R.layout.activity_menu);
        // set header
        TextView header = (TextView)findViewById(R.id.menu_header);
        header.setText(R.string.main_menu_header);
        // content fill
        LinearLayout content = (LinearLayout)findViewById(R.id.menu_content);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.button_click);
        Button button;
        LinearLayout buttonLayout;
        // Game button
        buttonLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.menu_button, null);
        button = (Button)buttonLayout.findViewById(R.id.menu_button);
        button.setText(R.string.game_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LevelActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(buttonLayout);
        // Scores button
        buttonLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.menu_button, null);
        button = (Button)buttonLayout.findViewById(R.id.menu_button);
        button.setText(R.string.scores_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ScoresActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(buttonLayout);
        // Sound button
        buttonLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.menu_button, null);
        button = (Button)buttonLayout.findViewById(R.id.menu_button);
        button.setText(R.string.sound_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SoundActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(buttonLayout);
        // About button
        buttonLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.menu_button, null);
        button = (Button)buttonLayout.findViewById(R.id.menu_button);
        button.setText(R.string.about_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AboutActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(buttonLayout);
        // exit app
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
