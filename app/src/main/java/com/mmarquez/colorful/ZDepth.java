package com.mmarquez.colorful;

import android.graphics.Color;

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

    public static ShadowValue getShadowValues(ZDepth zDepth){
        //TODO Change Demo
        return new ShadowValue(5f, 0.0f, 5.0f, Color.argb(100, 0, 0, 0));
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
