package com.example.second_display;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.Display;

public class SecondaryDisplayActivity extends Activity {

    private FlutterPresentation presentation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayManager displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();

        if (displays.length > 1) {
            Display secondaryDisplay = displays[1];
            presentation = new FlutterPresentation(this, secondaryDisplay);
            presentation.show();

            // Finish the activity so that it doesn't show up in the main display
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presentation != null) {
            presentation.dismiss();
            presentation = null;
        }
    }
}
