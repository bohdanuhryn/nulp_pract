package com.nulp.bohdanuhryn.snake;

import java.util.Map;
import java.util.HashMap;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by BohdanUhryn on 17.06.2015.
 */
public class ScoresManager {

    private static Context context;

    private static Map<String, Integer> scores;

    public static void Init(Context _context) {
        context = _context;
    }

    private static void CheckScoresMap() {
        if (scores == null) {
            scores = ReadScores();
        }
    }

    public static Map<String, Integer> GetScores() {
        CheckScoresMap();
        return scores;
    }

    public static Map<String, Integer> ReadScores() {
        SharedPreferences scoresFile = context.getSharedPreferences(
                context.getString(R.string.scores_file), Context.MODE_PRIVATE);
        Map<String, ?> m = scoresFile.getAll();
        scores = new HashMap<String, Integer>();
        for(Map.Entry<String, ?> entry : m.entrySet()) {
            scores.put(entry.getKey(), (Integer) entry.getValue());
        }
        return scores;
    }

    public static void WriteScores() {
        CheckScoresMap();
        SharedPreferences scoresFile = context.getSharedPreferences(
                context.getString(R.string.scores_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = scoresFile.edit();
        editor.clear();
        for(Map.Entry<String, Integer> entry : scores.entrySet()) {
            editor.putInt(entry.getKey(), entry.getValue());
        }
        editor.commit();
    }

    public static void AddScore(String key, int value) {
        CheckScoresMap();
        if(CheckScore(value)) {
            scores.put(key, value);
            // only top-10
            if(scores.size() > 10) {
                Map.Entry<String, Integer> minEntry = null;
                int min = Integer.MAX_VALUE;
                for(Map.Entry<String, Integer> entry : scores.entrySet()) {
                    if(entry.getValue() < min) {
                        min = entry.getValue();
                        minEntry = entry;
                    }
                }
                if(minEntry != null)
                    scores.remove(minEntry.getKey());
            }
        }
        WriteScores();
    }

    public static boolean CheckScore(int value) {
        ReadScores();
        int min = Integer.MAX_VALUE;
        for(Map.Entry<String, Integer> entry : scores.entrySet()) {
            if(entry.getValue() < min)
                min = entry.getValue();
        }
        return (value >= min) || (min == Integer.MAX_VALUE);
    }

    public static int GetMaxScore() {
        CheckScoresMap();
        int max = 0;
        for(Map.Entry<String, Integer> entry : scores.entrySet()) {
            if(entry.getValue() > max)
                max = entry.getValue();
        }
        return max;
    }
}