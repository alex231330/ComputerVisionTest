package com.example.xelon.computervisiontest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.xelon.computervisiontest.libs.CameraData;
import com.example.xelon.computervisiontest.libs.CameraListener;
import com.example.xelon.computervisiontest.libs.CameraView;

/**
 * Created by xelon on 29.08.17.
 */

public class SettingActivity extends Activity {
    int RED = 0, GREEN = 0, BLUE = 0;
    Bitmap bitmap = null;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_VOLUME_UP) {
            event.startTracking();
            int[] arr = {RED, GREEN, BLUE};
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Colors", arr);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        FrameLayout fl = (FrameLayout) findViewById(R.id.fl2);
        Button calib = (Button) findViewById(R.id.calibrate_button);
        CameraView cv = new CameraView(this);
        fl.addView(cv);
        cv.setCameraListener(new CameraListener() {
            @Override
            public void onCameraFrame(CameraData data, int cameraDisplayOrientation, Canvas canvas) {
                int centerx = data.getWidth() / 2, centery = data.getHeight() / 2;
                Paint paint = new Paint(Color.RED);
                canvas.drawCircle(centerx, centery, 10, paint);
            }

            @Override
            public void onCameraFrame(byte[] data, int width, int height, int cameraDisplayOrientation, Canvas canvas) {

            }

            @Override
            public void onSizeChange(int width, int height, int cameraDisplayOrientation) {

            }
        });

    }
}
