package com.example.second_display;

import android.app.Activity;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayManager displayManager = (DisplayManager) getSystemService(DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();

        int numDisplays = displays.length;
        Intent intent;

        if (numDisplays == 1) {
            intent = new Intent(this, MainDisplayActivity.class);
        } else {
            intent = new Intent(this, SecondaryDisplayActivity.class);
        }

        startActivity(intent);

        if (numDisplays != 1) {
            finish();
        }
    }
}
