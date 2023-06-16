package com.example.second_display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.StandardMessageCodec;

public final class MainChannel implements MethodChannel.MethodCallHandler {
    private static MainChannel instance;

    private final MethodChannel channel;

    public static MainChannel getInstance() {
        assert instance != null;

        return instance;
    }

    private MainChannel(FlutterEngine engine) {
        channel = new MethodChannel(engine.getDartExecutor().getBinaryMessenger(), "channel");
        channel.setMethodCallHandler(this);
    }

    public static void initialise(FlutterEngine flutterEngine) {
        instance = new MainChannel(flutterEngine);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        result.notImplemented();
    }

    public interface Result {
        /**
         * Handles a successful result.
         *
         * @param result The result, possibly null. The result must be an Object type supported by the
         *               codec. For instance, if you are using {@link StandardMessageCodec} (default), please see
         *               its documentation on what types are supported.
         */
        void success(@Nullable Object result);

        /**
         * Handles an error result.
         *
         * @param errorCode    An error code String.
         * @param errorMessage A human-readable error message String, possibly null.
         * @param errorDetails Error details, possibly null. The details must be an Object type
         *                     supported by the codec. For instance, if you are using {@link StandardMessageCodec}
         *                     (default), please see its documentation on what types are supported.
         */
        void error(
                @NonNull String errorCode, @Nullable String errorMessage, @Nullable Object errorDetails);

        /**
         * Handles a call to an unimplemented method.
         */
        void notImplemented();
    }
}
