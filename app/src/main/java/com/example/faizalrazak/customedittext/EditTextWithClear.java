package com.example.faizalrazak.customedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class EditTextWithClear extends AppCompatEditText {

    Drawable mClearButtonImage;

    public EditTextWithClear(Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){

        mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_black_24dp, null);

        // TODO: If the clear (X) button is tapped, clear the text.

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((getCompoundDrawablesRelative()[2] != null)) {

                    float clearButtonStart;
                    boolean clearButtonClicked = false;
                    clearButtonStart = (getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());

                    if (event.getX() > clearButtonStart){
                        clearButtonClicked = true;
                    }

                    if (clearButtonClicked){
                        if (event.getAction() == MotionEvent.ACTION_DOWN){
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_black_24dp, null);
                            showClearButton();
                        }else if (event.getAction() == MotionEvent.ACTION_UP){
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_clear_24dp,null);
                            hideClearButton();
                            getText().clear();
                            return true;
                        }
                    }
                }
                return false;
            }
        });
        // TODO: If the text changes, show or hide the clear (X) button.
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                hideClearButton();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
                showClearButton();
            }
        });
    }

    private void showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null);
    }

    private void hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
