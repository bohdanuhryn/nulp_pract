package com.nulp.bohdanuhryn.snake.gui;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;

import com.nulp.bohdanuhryn.snake.R;
import com.nulp.bohdanuhryn.snake.ScoresManager;

public class SaveScoreActivity extends Activity {

    private int score;

    private EditText editPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        TextView header = (TextView)findViewById(R.id.menu_header);
        header.setText(R.string.save_score_header);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);

        LinearLayout content = (LinearLayout)findViewById(R.id.menu_content);

        editPlayerName = new EditText(this);
        editPlayerName.setHint(R.string.player_name_edit);
        content.addView(editPlayerName);

        Button saveButton;
        LinearLayout buttonLayout;
        buttonLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.menu_button, null);
        saveButton = (Button)buttonLayout.findViewById(R.id.menu_button);
        saveButton.setText(R.string.save_score_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoresManager.AddScore(editPlayerName.getText().toString(), score);
                Intent intent = new Intent(v.getContext(), MainMenuActivity.class);
                intent.putExtra(getResources().getString(R.string.set_game_end_menu), true);
                startActivity(intent);
            }
        });
        content.addView(buttonLayout);
    }

    @Override
    public void onBackPressed() {

    }
}