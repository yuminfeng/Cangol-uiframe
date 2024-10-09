package mobi.cangol.mobile.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class SafeHandler extends Handler {
    private final WeakReference<IMsgHandler> mHandlerRef;

    public SafeHandler(IMsgHandler handler, Looper looper) {
        super(looper);
        mHandlerRef = new WeakReference<>(handler);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        IMsgHandler handler = mHandlerRef.get();
        if (handler != null) {
            handler.handleMessage(msg);
        }
    }
}


