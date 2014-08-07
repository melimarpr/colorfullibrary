package com.mmarquez.colorful;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by enrique on 7/18/14.
 */
@SuppressWarnings("deprecation")
public class ColorfulTextButton extends Button {

    private Context context;
    //private ShapeDrawable d;
    private ColorfulRectDrawable colorfulRectDrawable;
    private ColorfulButtonAttr attr;

    /* ===== Programing Constructors ===== */
    public ColorfulTextButton(Context context) {
        super(context);
        this.context = context;
        this.attr = ColorfulButtonAttr.getDefaultInstance();
        init(this.attr);
    }

    public ColorfulTextButton(Context context, ColorfulButtonAttr attr){
        super(context);
        this.context = context;
        this.attr = attr;
        init(this.attr);

    }

    /* ===== XML Constructors ===== */
    public ColorfulTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attr = getAttrs(context, attrs);
        init(this.attr);
    }

    public ColorfulTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attr = getAttrs(context, attrs);
        init(this.attr);
    }

    private ColorfulButtonAttr getAttrs(Context context, AttributeSet attrs) {
        TypedArray attributesArray = context.obtainStyledAttributes(attrs, R.styleable.ColorfulButton);

        ColorfulButtonAttr retAttrs = ColorfulButtonAttr.getDefaultInstance();
        //Check All
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

        if(attributesArray.hasValue(R.styleable.ColorfulButton_typeface_path)){
            retAttrs.setTextTypeface(attributesArray.getString(R.styleable.ColorfulButton_typeface_path));
        }

        return retAttrs;
    }
    private void init(ColorfulButtonAttr attr){
        setWillNotDraw(false);

        if(attr.getDepth() != ZDepth.NONE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){ //Shadow If Needed
               this.setLayerType(View.LAYER_TYPE_SOFTWARE, null); //Software Accelaration for Post Honeycomb
        }

        //Change Typeface
        if(!attr.getTextTypeface().isEmpty()){
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), attr.getTextTypeface()); //Typeface
            setTypeface(typeface);
        }

        invalidate();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        colorfulRectDrawable = new ColorfulRectDrawable(attr, getWidth(), getHeight()); //Create Custom Drawable
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(colorfulRectDrawable);
        }
        else{
            setBackground(colorfulRectDrawable);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            if(attr.isPressedAlpha()) {
                setAlpha(0.9f);
            }
            else{
                colorfulRectDrawable.changeFillColor(attr.getPrimaryColor());
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    setBackgroundDrawable(colorfulRectDrawable);
                }
                else{
                    setBackground(colorfulRectDrawable);
                }
                invalidate();
            }
        }
        else if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(attr.isPressedAlpha()) {
                setAlpha(0.5f);
            }
            else{
                colorfulRectDrawable.changeFillColor(attr.getPressedColor());
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    setBackgroundDrawable(colorfulRectDrawable);
                }
                else{
                    setBackground(colorfulRectDrawable);
                }
                invalidate();
            }

        }
        return super.onTouchEvent(event);
    }


}
