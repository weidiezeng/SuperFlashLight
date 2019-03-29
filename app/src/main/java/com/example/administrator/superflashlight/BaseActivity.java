package com.example.administrator.superflashlight;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import  android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;


public class BaseActivity extends Activity {
    protected  enum  UIType
    {
        UI_TYPE_MAIN,UI_TYPE_FLASHLIGHT,UI_TYPE_WARNINGLIGHT,
        UI_TYPE_MORSE,UI_TYPE_BLUB,UI_TYPE_COLOR,UI_TYPE_POLICE,
        UI_TYPE_SETTINGS
    }
    protected int mCurrentWarningLightInterval=500;
    protected int mCurrentPoliceLightInterval=100;

    protected ImageView mImageViewFlashLight;
    protected ImageView mImageViewFlashLightController;
    protected  ImageView mImageViewWanrningLight1;
    protected  ImageView mImageViewWanrningLight2;
    protected EditText mEditTextMorseCode;
    protected  ImageView mImageViewBulb;
    protected SeekBar mSeekBarWarninglight;
    protected SeekBar mSeekBarPolicelight;
    protected Button mButtonAddShortCut;
    protected Button mButtonRemoveShortCut;
    protected Camera mCamera;
    protected Parameters mParameters;

    protected FrameLayout mUIFlashLight;
    protected LinearLayout mUIMain;
    protected  LinearLayout mUIWarnigLight;
    protected  LinearLayout mUIMorse;
    protected FrameLayout mUIBulb;
    protected FrameLayout mUIColorLight;
    protected FrameLayout mUIPoliceLight;
    protected LinearLayout mUISettings;

    protected UIType mCurrentUIType=UIType.UI_TYPE_FLASHLIGHT;
    protected UIType mLastUIType=UIType.UI_TYPE_FLASHLIGHT;

    protected int mdefaultScreenBrightness;

    protected int mFinishCount=0;
    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUIFlashLight=(FrameLayout)findViewById(R.id.framelayout_flashlight) ;
        mUIMain=(LinearLayout)findViewById(R.id.LinearLayout_main);
        mUIWarnigLight=(LinearLayout)findViewById(R.id.LinearLayout_warning_light);
        mUIMorse=(LinearLayout)findViewById(R.id.LinearLayout_morse);
        mUIBulb=(FrameLayout)findViewById(R.id.framelayout_bulb);
        mUIColorLight=(FrameLayout)findViewById(R.id.framelayout_color_light);
        mUIPoliceLight=(FrameLayout)findViewById(R.id.framelayout_police_light);
        mUISettings=(LinearLayout)findViewById(R.id.LinearLayout_settings) ;
        mImageViewFlashLight=(ImageView)findViewById(R.id.imageview_flashlight);
        mImageViewFlashLightController=(ImageView)findViewById(R.id.imageview_flashlight_controller);
        mImageViewWanrningLight1=(ImageView)findViewById(R.id.imageview_warning_light1);
        mImageViewWanrningLight2=(ImageView)findViewById(R.id.imageview_warning_light2);
        mEditTextMorseCode=(EditText)findViewById(R.id.edittext_morse_code);
        mImageViewBulb=(ImageView)findViewById(R.id.imageview_bulb);
        mSeekBarPolicelight=(SeekBar)findViewById(R.id.seekbar_police_light);
        mSeekBarWarninglight=(SeekBar)findViewById(R.id.seekbar_warning_light);

        //mButtonAddShortCut=(Button)findViewById(R.id.button_add_shortcut);
       // mButtonRemoveShortCut=(Button)findViewById(R.id.button_remove_shortcut);
        mSharedPreferences=getSharedPreferences("config",Context.MODE_PRIVATE);
        mdefaultScreenBrightness=getScreenBrightness();
        mSeekBarPolicelight.setProgress(mCurrentPoliceLightInterval-50);
        mSeekBarWarninglight.setProgress(mCurrentWarningLightInterval-100);
        mCurrentWarningLightInterval=mSharedPreferences.getInt("warning_light_interval",200);
        mCurrentPoliceLightInterval=mSharedPreferences.getInt("police_kight_interval",100);
    }
    protected  void hideALLUI()
    {
        mUIMain.setVisibility(View.GONE);
        mUIFlashLight.setVisibility(View.GONE);
        mUIWarnigLight.setVisibility(View.GONE);
        mUIMorse.setVisibility(View.GONE);
        mUIBulb.setVisibility(View.GONE);
        mUIColorLight.setVisibility(View.GONE);
        mUIPoliceLight.setVisibility(View.GONE);
        mUISettings.setVisibility(View.GONE);
    }
    protected  void  screenBrightness(float value)
    {
        try {
            WindowManager.LayoutParams layout=getWindow().getAttributes();
            layout.screenBrightness=value;
            getWindow().setAttributes(layout);
        }catch (Exception e){

        }
    }
    protected int getScreenBrightness()
    {
        int value=0;
        try{
            value=android.provider.Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        }catch (Exception e){

        }
        return value;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mFinishCount=0;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void finish()
    {
        mFinishCount++;
        if(mFinishCount==1)
        {
            Toast.makeText(this,"再按一次退出",Toast.LENGTH_LONG).show();

        }
        else if(mFinishCount==2)
        {
            super.finish();
        }
    }
}
