package com.example.administrator.superflashlight;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;





public class FlashLight extends BaseActivity {
    //protected CameraManager manager;
    //public static boolean kaiguan = true; // 定义开关状态，状态为false，打开状态，状态为true，关闭状态
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageViewFlashLight.setTag(false);
        Point point=new Point();
        //manager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        getWindowManager().getDefaultDisplay().getSize(point);
        LayoutParams laparams=(LayoutParams)mImageViewFlashLightController.getLayoutParams();
        laparams.height=point.y*3/4;
        laparams.width=point.x/3;
        mImageViewFlashLightController.setLayoutParams(laparams);
        // check Android 6 permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("TEST","Granted");

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},1);//1 can be another integer
        }

    }
    public void onClick_FlashLight(View view)
    {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {
            Toast.makeText(this,"当前设备没有闪关灯",Toast.LENGTH_LONG).show();
            return ;
        }
        if(((boolean)mImageViewFlashLight.getTag())==false) {
            openFlashLight();
        }
        else
        {
            closeFlashLight();
        }
    }
//    打开闪关灯
    protected  void openFlashLight(){
        TransitionDrawable drawable=(TransitionDrawable)mImageViewFlashLight.getDrawable();
        drawable.startTransition(20);
        mImageViewFlashLight.setTag(true);


        try{
            try {
                if(mCamera!=null)
                {
                    mCamera.release();
                }
                Log.d("smile","camera打开");
                mCamera= Camera.open();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Camera被占用，请先关闭", Toast.LENGTH_SHORT).show();
            }

            mCamera.setPreviewTexture(new SurfaceTexture(0));
            mParameters = mCamera.getParameters();
            mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mParameters);
            mCamera.startPreview();

        }catch (Exception e){

        }
    }

//    关闭闪关灯
    protected  void closeFlashLight(){
        TransitionDrawable drawable=(TransitionDrawable)mImageViewFlashLight.getDrawable();
        if(((boolean)mImageViewFlashLight.getTag()))
        {
            drawable.reverseTransition(20);
            mImageViewFlashLight.setTag(false);
            if(mCamera!=null)
            {
                mParameters=mCamera.getParameters();
                mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(mParameters);
                mCamera.stopPreview();
                mCamera.release();
                mCamera=null;
            }
        }
    }
   /* private boolean isLOLLIPOP() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
    }*/
    @Override
    protected void onPause() {
        super.onPause();
        closeFlashLight();
    }
}
