package com.mmarquez.colorful;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

/**
 * Created by enrique on 7/18/14.
 */
public class ColorfulButtonAttr {

        //Constants
        public final static int CIRCLE = 0;
        public final static int RECTANGLE = 1;


        private ColorfulButtonAttr(int shape, ZDepth depth, int primaryColor, int pressedColor, boolean pressedAlpha,
                                   Drawable iconDrawable, String textTypeface) {
            this.shape = shape;
            this.depth = depth;
            this.primaryColor = primaryColor;
            this.pressedColor = pressedColor;
            this.pressedAlpha = pressedAlpha;
            this.iconDrawable = iconDrawable;
            this.textTypeface = textTypeface;

        }


        private ZDepth depth;
        private int shape;
        private int primaryColor;
        private int pressedColor;
        private boolean pressedAlpha;

        private Drawable iconDrawable;
        private String textTypeface;



        public static ColorfulButtonAttr getDefaultInstance(){

            return new ColorfulButtonAttr(
                    CIRCLE,
                    ZDepth.ONE,
                    Color.CYAN,
                    Color.WHITE,
                    true,
                    null,
                    ""
            );
        }

        public int getShape() {
            return shape;
        }

        public void setShape(int shape) {
            this.shape = shape;
        }

        public Drawable getIconDrawable() {
            return iconDrawable;
        }

        public void setIconDrawable(Drawable iconDrawable) {
            this.iconDrawable = iconDrawable;
        }

        public String getTextTypeface() {
            return textTypeface;
        }

        public void setTextTypeface(String textTypeface) {
            this.textTypeface = textTypeface;
        }

        public int getPrimaryColor() {
            return primaryColor;
        }

        public void setPrimaryColor(int primaryColor) {
            this.primaryColor = primaryColor;
        }

        public int getPressedColor() {
            return pressedColor;
        }

        public void setPressedColor(int pressedColor) {
            this.pressedColor = pressedColor;
        }

        public boolean isPressedAlpha() {
            return pressedAlpha;
        }

        public void setPressedAlpha(boolean pressedAlpha) {
            this.pressedAlpha = pressedAlpha;
        }

        public ZDepth getDepth() {
            return depth;
        }

        public void setDepth(ZDepth depth) {
            this.depth = depth;
        }
}
