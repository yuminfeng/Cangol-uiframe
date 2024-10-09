package mobi.cangol.mobile.handler;

import android.os.Handler;

import java.util.HashSet;
import java.util.Set;

public class ThreadHandlerProxy {

    private SafeHandler mHandler;
    private final IMsgHandler mMsgHandler;
    private final Set<Runnable> runnableSet = new HashSet<>();

    public ThreadHandlerProxy(IMsgHandler handler) {
        this.mMsgHandler = handler;
    }

    public Handler getHandler() {
        if (mHandler == null) {
            mHandler = GlobalHandlerThread.getInstance().createHandler(mMsgHandler);
        }
        return mHandler;
    }


    public void post(Runnable runnable, boolean cancelable) {
        if (cancelable) {
            runnableSet.add(runnable);
        }
        getHandler().post(runnable);
    }

    public void removeCallbacks() {
        for (Runnable runnable : runnableSet) {
            getHandler().removeCallbacks(runnable);
        }
        runnableSet.clear();
    }

}
