package com.example.administrator.superflashlight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WarningLight extends FlashLight {
    protected  boolean mWarningLightFlicker;//true:闪烁
    protected boolean mWarningLightState;//true on-off

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWarningLightFlicker=true;

    }

    class WarningLightThread extends  Thread{
        public void run(){
            mWarningLightFlicker=true;
            while(mWarningLightFlicker)
            {
                try{
                    Thread.sleep(mCurrentWarningLightInterval);
                    mWarningHandler.sendEmptyMessage(0);
                }
                catch (Exception e){

                }
            }
        }
    }
    private Handler mWarningHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(mWarningLightState)
            {
                mImageViewWanrningLight1.setImageResource(R.drawable.warning_light_on);
                mImageViewWanrningLight2.setImageResource(R.drawable.warning_light_off);
                mWarningLightState=false;
            }
            else
            {
                mImageViewWanrningLight1.setImageResource(R.drawable.warning_light_off);
                mImageViewWanrningLight2.setImageResource(R.drawable.warning_light_on);
                mWarningLightState=true;
            }
        }
    };


}
