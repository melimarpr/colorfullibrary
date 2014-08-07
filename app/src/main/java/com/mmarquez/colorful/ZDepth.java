package com.mmarquez.colorful;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;

/**
 * Created by enrique on 8/6/14.
 */
public enum ZDepth {
    NONE,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;

    public static ZDepth intAttributeToZDepth(int attr){
        switch (attr){
            case 0:
                return NONE;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            default:
                return NONE;
        }
    }

    public static ShadowValue getShadowValues( ZDepth zDepth){

        switch (zDepth){
            case TWO:
                return new ShadowValue(12f, 0.0f, 4.0f, Color.argb(115, 0, 0, 0));
            case THREE:
                return new ShadowValue(16f, 0.0f, 6.0f, Color.argb(115, 0, 0, 0));
            case FOUR:
                return new ShadowValue(20f, 0.0f, 8.0f, Color.argb(130, 0, 0, 0));
            case FIVE:
                return new ShadowValue(24f, 0.0f, 10.0f, Color.argb(130, 0, 0, 0));
            default:
                return new ShadowValue(8f, 0.0f, 4.0f, Color.argb(100, 0, 0, 0));


        }


    }

    public static class ShadowValue{
        public float radius;
        public float dx;
        public float dy;
        public int color;

        public ShadowValue(float radius, float dx, float dy, int color) {
            this.radius = radius;
            this.dx = dx;
            this.dy = dy;
            this.color = color;
        }
    }



}
