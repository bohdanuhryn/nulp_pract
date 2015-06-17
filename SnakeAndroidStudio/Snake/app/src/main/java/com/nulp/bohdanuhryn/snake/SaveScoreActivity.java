package com.nulp.bohdanuhryn.snake;

import java.util.Vector;
import java.util.Map;
import java.util.HashMap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.EditText;

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

        Button saveButton = new Button(this);
        saveButton.setText(R.string.save_score_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoresManager.AddScore(editPlayerName.getText().toString(), score);
                Intent intent = new Intent(v.getContext(), GameEndActivity.class);
                startActivity(intent);
            }
        });
        content.addView(saveButton);
    }

    @Override
    public void onBackPressed() {

    }
}