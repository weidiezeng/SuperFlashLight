package com.example.administrator.superflashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.administrator.superflashlight.ColorPickerDialog.OnColorChangedListener;

import com.example.administrator.surperflashlight.widget.HideTextView;

public class ColorLight extends Bulb implements OnColorChangedListener {
    protected int mCurrentColorLight= Color.RED;
    protected HideTextView mHideTextViewColorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHideTextViewColorLight=(HideTextView)findViewById(R.id.textview_hide_color_light);
    }

    @Override
    public void colorChanged(int color) {
        mUIColorLight.setBackgroundColor(color);
        mCurrentColorLight=color;
    }

    public void onClick_ShowColorPicker(View view)
    {
        try
        {
            new ColorPickerDialog(this,this,Color.RED).show();
        }catch (Exception e)
        {
            Toast.makeText(this,"错误",Toast.LENGTH_LONG).show();//有错误
            e.printStackTrace();
        }
    }
}
