package com.nulp.bohdanuhryn.snake;

import java.util.Vector;
import android.graphics.Point;

/**
 * Created by BohdanUhryn on 13.06.2015.
 */
public class Snake {

    public Bone head;
    public Bone tail;
    public Vector<Bone> body;

    public Snake(int x, int y, int length) {
        body = new Vector<Bone>();
        for(byte i = 0; i < length; ++i)
            body.add(new Bone(x, y + i));
        head = body.firstElement();
        tail = body.lastElement();
    }
}
