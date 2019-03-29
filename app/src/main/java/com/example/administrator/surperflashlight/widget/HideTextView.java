package com.example.administrator.surperflashlight.widget;


import android.content.Context;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;


import android.os.Handler;



public class HideTextView extends AppCompatTextView {
    public HideTextView(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }
   protected   Handler mHandler=new Handler() {
       @Override
       public void handleMessage(Message msg) {
           if(msg.what==0)
           {
               setVisibility(View.GONE);
           }
           else if(msg.what==1)
           {
               setVisibility(View.VISIBLE);
           }
       }
   };
    class  TextViewThread extends Thread{
        public void run()
        {
            mHandler.sendEmptyMessage(1);
            try {
                sleep(3000);
                mHandler.sendEmptyMessage(0);
            }catch (Exception e){

            }
        }
    }
    public  void hide()
    {
        new TextViewThread().start();
    }


}
