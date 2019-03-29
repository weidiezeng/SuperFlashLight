package com.example.administrator.superflashlight;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class Settings extends PoliceLight implements OnSeekBarChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSeekBarPolicelight.setOnSeekBarChangeListener(this);
        mSeekBarWarninglight.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch(seekBar.getId()){
            case R.id.seekbar_warning_light:
                mCurrentWarningLightInterval=progress+100;
                break;
            case R.id.seekbar_police_light:
                mCurrentPoliceLightInterval=progress+50;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
   /* private boolean shorcutInScreen()
    {
        Cursor cursor=getContentResolver().query(Uri.parse("content://com.cyanogenmod.trebuchet.settings/favorites"),null,
                "intent like ?",new String[]{"component=com.example.administrator.superflashlight/.MainActivity%"},null);
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void onClick_AddShortCut(View view){
        if(!shorcutInScreen()) {
            Intent installShortcut = new Intent("com.android.launcher.action.INSTALL_SHROTCUT");
            installShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");
            Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.icon);
            Intent flashLightIntent = new Intent();
            flashLightIntent.setClassName("com.example.administrator.superflashlight", "com.example.administrator.superflashlight.MainActivity");
            flashLightIntent.setAction(Intent.ACTION_MAIN);
            flashLightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            installShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashLightIntent);

            sendBroadcast(installShortcut);
            Toast.makeText(this, "已成功将快键方式添加到桌面", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"快捷方式已经存在，无法再添加",Toast.LENGTH_LONG).show();
        }
    }
    public void onClick_RemoveShortCut(View view) {
        if (shorcutInScreen()) {
            Intent uninstallShortcut = new Intent("com.android.launcher.action.UNINSTALL_SHROTCUT");
            uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");
            Intent flashLightIntent = new Intent();
            flashLightIntent.setClassName("com.example.administrator.superflashlight", "com.example.administrator.superflashlight.MainActivy");
            uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashLightIntent);
            flashLightIntent.setAction(Intent.ACTION_MAIN);
            flashLightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            sendBroadcast(uninstallShortcut);
        }
        else {
            Toast.makeText(this,"没有快键方式，无法删除",Toast.LENGTH_LONG).show();
        }
    }*/


}
