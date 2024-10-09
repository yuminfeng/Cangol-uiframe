package mobi.cangol.mobile.handler;

import android.os.HandlerThread;
import android.os.Looper;

public class GlobalHandlerThread {

    private static final String GLOBAL_HANDLER_THREAD = "GLOBAL_HANDLER_THREAD";
    private static volatile GlobalHandlerThread instance;

    private HandlerThread mHandlerThread;

    public static GlobalHandlerThread getInstance() {
        if (instance == null) {
            synchronized (GlobalHandlerThread.class) {
                if (instance == null) {
                    instance = new GlobalHandlerThread();
                }
            }
        }
        return instance;
    }

    private Looper getLooper() {
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread(GLOBAL_HANDLER_THREAD);
            mHandlerThread.start();
        }
        return mHandlerThread.getLooper();
    }


    public SafeHandler createHandler(IMsgHandler msgHandler) {
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread(GLOBAL_HANDLER_THREAD);
            mHandlerThread.start();
        }
        return new SafeHandler(msgHandler, getLooper());
    }



}
