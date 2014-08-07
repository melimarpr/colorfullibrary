package com.mmarquez.colorful;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Class Created as Compliment for ColorfulTextButton
 */
public class ColorfulRectDrawable extends Drawable {

    //Instance Fields
    private ColorfulButtonAttr attr;
    private Paint button;
    private float wight;
    private float height;

    public ColorfulRectDrawable(ColorfulButtonAttr attr, float wight, float height){
        this.attr = attr;
        this.wight = wight;
        this.height = height;
        init(this.attr);
    }

    private void init(ColorfulButtonAttr attr) {
        button = new Paint(Paint.ANTI_ALIAS_FLAG);
        button.setColor(attr.getPrimaryColor());
        button.setStyle(Paint.Style.FILL);
        if(attr.getDepth() != ZDepth.NONE){ //Shadow If Needed
            ZDepth.ShadowValue shadowValue = ZDepth.getShadowValues(attr.getDepth());
            button.setShadowLayer(shadowValue.radius, shadowValue.dx, shadowValue.dy, shadowValue.color);
        }
        invalidateSelf();
    }

    public void changeFillColor(int color){
        button.setColor(color);
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {
        //Adjust Wight and Height if Needed
        float x = wight;
        float y = height;
        if(attr.getDepth() != ZDepth.NONE){
            x = x - 5;
            y = y - 5;
        }
        canvas.drawRect(0,0,x,y, button);
    }

    @Override
    public void setAlpha(int alpha) {
        //No-Op
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        //No-Op
    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
