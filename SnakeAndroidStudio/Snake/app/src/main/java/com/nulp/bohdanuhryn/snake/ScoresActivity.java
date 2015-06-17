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

public class ScoresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        TextView header = (TextView)findViewById(R.id.menu_header);
        header.setText(R.string.scores_header);

        LinearLayout content = (LinearLayout)findViewById(R.id.menu_content);

        ScoresManager.Init(this);
        Map<String, Integer> scores = ScoresManager.ReadScores();

        Vector<String> adapterData = new Vector<String>();

        adapterData.add("#");
        adapterData.add("Player");
        adapterData.add("Score");

        int i = 1;
        for(Map.Entry<String,?> entry : scores.entrySet()) {
            adapterData.add(Integer.toString(i));
            adapterData.add(entry.getKey());
            adapterData.add(entry.getValue().toString());
            ++i;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.score_cell, R.id.score_cell_text, adapterData);

        GridView gv = new GridView(this);
        gv.setNumColumns(3);
        gv.setColumnWidth(90);
        gv.setVerticalSpacing(5);
        gv.setHorizontalSpacing(5);
        gv.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
        gv.setAdapter(adapter);

        content.addView(gv);
    }
}
