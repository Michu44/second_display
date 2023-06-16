package com.example.second_display;

import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.dart.DartExecutor;

public class FlutterPresentation extends Presentation {
    public static final String DYNKE_STATUS = "com.ava.dynke.STATUS";
    public static final String IS_ALIVE = "isAlive";
    private FlutterView flutterView;
    private FlutterEngine flutterEngine;
    private final Context mContext;


    public FlutterPresentation(@NonNull Context outerContext, Display display) {
        super(outerContext, display);
        mContext = outerContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_layout);


        flutterView = findViewById(R.id.flutter_view);

        flutterEngine = new FlutterEngine(getContext());
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );

        flutterView.attachToFlutterEngine(flutterEngine);

        MainChannel.initialise(flutterEngine);
    }

    @Override
    protected void onStop() {
        if (flutterView != null) {
            flutterView.detachFromFlutterEngine();
        }

        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        flutterEngine.getLifecycleChannel().appIsResumed();
    }
}
