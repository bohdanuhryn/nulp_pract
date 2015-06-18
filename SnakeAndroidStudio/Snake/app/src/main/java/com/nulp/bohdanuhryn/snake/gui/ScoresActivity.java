package com.nulp.bohdanuhryn.snake.gui;

import java.util.ArrayList;
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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.TableLayout;
import android.util.Pair;

import com.nulp.bohdanuhryn.snake.R;
import com.nulp.bohdanuhryn.snake.ScoresManager;

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

        TableLayout table = new TableLayout(this);
        TableRow row;
        TextView text;

        row = new TableRow(this);
        text = new TextView(this);
        text.setText("#");
        text.setWidth(60);
        row.addView(text);
        text = new TextView(this);
        text.setText("Player");
        text.setWidth(200);
        row.addView(text);
        text = new TextView(this);
        text.setText("Score");
        text.setWidth(100);
        row.addView(text);
        table.addView(row);

        ArrayList<Pair<String, Integer>> orderedScores = new ArrayList<Pair<String, Integer>>();

        int size = scores.size();
        int max;
        Map.Entry<String, Integer> maxEntry;
        for(int i = 0; i < size; ++i) {
            max = 0;
            maxEntry = null;
            for(Map.Entry<String, Integer> entry : scores.entrySet()) {
                if(entry.getValue() > max) {
                    max = entry.getValue();
                    maxEntry = entry;
                }
            }
            if(maxEntry == null) continue;
            orderedScores.add(new Pair<String, Integer>(maxEntry.getKey(), maxEntry.getValue()));
            scores.remove(maxEntry.getKey());
        }

        for(int i = 0; i < orderedScores.size(); ++i) {
            row = new TableRow(this);
            text = new TextView(this);
            text.setText(Integer.toString(i + 1));
            row.addView(text);
            text = new TextView(this);
            text.setText(orderedScores.get(i).first);
            row.addView(text);
            text = new TextView(this);
            text.setText(orderedScores.get(i).second.toString());
            row.addView(text);
            table.addView(row);
        }

        content.addView(table);
    }
}
