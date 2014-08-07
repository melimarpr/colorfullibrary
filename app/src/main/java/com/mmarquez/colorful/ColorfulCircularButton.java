package com.mmarquez.colorful;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by enrique on 7/17/14.
 */
public class ColorfulCircularButton extends View {

    //Instance Fields
    /**
     * Paint Styles For Button and Icon
     */
    private Paint button, icon;
    /**
     * Bitmap For Icon
     */
    private Bitmap iconBitmap;
    /**
     *  Button Attributes
     */
    private ColorfulButtonAttr attr;

    /* ===== Programing Constructors ===== */
    public ColorfulCircularButton(Context context) {
        super(context);
        this.attr = ColorfulButtonAttr.getDefaultInstance();
        init(this.attr);
    }

    public ColorfulCircularButton(Context context, ColorfulButtonAttr attr){
        super(context);
        this.attr = attr;
        init(this.attr);

    }


    /* ===== XML Constructors ===== */
    public ColorfulCircularButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr = getAttrs(context, attrs);
        init(this.attr);
    }

    public ColorfulCircularButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr = getAttrs(context, attrs);
        init(this.attr);
    }

    private ColorfulButtonAttr getAttrs(Context context, AttributeSet attributeSet){
        TypedArray attributesArray = context.obtainStyledAttributes(attributeSet, R.styleable.ColorfulButton);
        ColorfulButtonAttr retAttrs = ColorfulButtonAttr.getDefaultInstance(); //Default Just In Case One Is Missing

        //Check All
        if(attributesArray.hasValue(R.styleable.ColorfulButton_shape)){
            retAttrs.setShape(attributesArray.getInt(R.styleable.ColorfulButton_shape, ColorfulButtonAttr.CIRCLE));
        }

        if(attributesArray.hasValue(R.styleable.ColorfulButton_zdepth)){
            retAttrs.setDepth(ZDepth.intAttributeToZDepth(attributesArray.getInt(R.styleable.ColorfulButton_zdepth, 0)));
        }


        if(attributesArray.hasValue(R.styleable.ColorfulButton_primary_color)){
            retAttrs.setPrimaryColor(attributesArray.getInt(R.styleable.ColorfulButton_primary_color, Color.WHITE));
        }

        if(attributesArray.hasValue(R.styleable.ColorfulButton_pressed_color)){
            retAttrs.setPressedColor(attributesArray.getInt(R.styleable.ColorfulButton_pressed_color, Color.WHITE));
        }

        if(attributesArray.hasValue(R.styleable.ColorfulButton_pressed_alpha)){
            retAttrs.setPressedAlpha(attributesArray.getBoolean(R.styleable.ColorfulButton_pressed_alpha, true));
        }

        if(attributesArray.hasValue(R.styleable.ColorfulButton_icon_drawable)){
            retAttrs.setIconDrawable(attributesArray.getDrawable(R.styleable.ColorfulButton_icon_drawable));
        }



        return retAttrs;
    }

    private void init(ColorfulButtonAttr attr) {
        setWillNotDraw(false); //Make Drawable Draw On Invalidate

        //Set Button

        button = new Paint(Paint.ANTI_ALIAS_FLAG);
        button.setColor(attr.getPrimaryColor());
        button.setStyle(Paint.Style.FILL);

        if(attr.getDepth() != ZDepth.NONE){ //Shadow If Needed
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                this.setLayerType(View.LAYER_TYPE_SOFTWARE, null); //Software Accelaration for Post Honeycomb
            }
            ZDepth.ShadowValue shadowValue = ZDepth.getShadowValues(attr.getDepth());
            button.setShadowLayer(shadowValue.radius, shadowValue.dx, shadowValue.dy, shadowValue.color);

        }
        if(attr.getIconDrawable() != null) { //Icons
            icon = new Paint(Paint.ANTI_ALIAS_FLAG);
            iconBitmap = ((BitmapDrawable) attr.getIconDrawable()).getBitmap();
        }
        invalidate();

    }

    public ColorfulButtonAttr getColorfulButtonAttributes(){
        return attr;
    }

    public void setColorfulButtonAttributes(ColorfulButtonAttr attr){
        this.attr = attr;
        init(this.attr);
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setClickable(true); //Make it a Button
        //Draw Shape
        switch (attr.getShape()){
            case ColorfulButtonAttr.CIRCLE:
                float radius = getWidth()/2;
                if(attr.getDepth() != ZDepth.NONE){
                    radius = (float) (getWidth()/2.5);
                }
                canvas.drawCircle(getWidth()/2, getHeight()/2, radius, button);
                break;
            case ColorfulButtonAttr.RECTANGLE:
                float x = getWidth();
                float y = getHeight();
                if(attr.getDepth() != ZDepth.NONE){
                    x = x - 5;
                    y = y - 5;
                }

                canvas.drawRect(0, 0, x, y, button);
                break;
        }
        if(iconBitmap != null) {
            canvas.drawBitmap(iconBitmap, (getWidth() - iconBitmap.getWidth()) / 2, (getHeight() - iconBitmap.getHeight()) / 2, icon);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            if(attr.isPressedAlpha()) {
                setAlpha(0.9f);
            }
            else{
                button.setColor(attr.getPrimaryColor());
                invalidate();
            }
        }
        else if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(attr.isPressedAlpha()) {
                setAlpha(0.5f);
            }
            else{
                button.setColor(attr.getPressedColor());
                invalidate();
            }

        }
        return super.onTouchEvent(event);
    }


}
