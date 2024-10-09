/*
 * Copyright (c) 2013 Cangol
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mobi.cangol.mobile.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import mobi.cangol.mobile.uiframe.R;

public class SlidingMenuLayout extends PagerEnabledSlidingPaneLayout {
    private static final String TAG = "SlidingMenuLayout";
    private FrameLayout mContentView;
    private FrameLayout mMenuView;
    private float mMenuWidth = 0.75f;
    private boolean isFloatActionBarEnabled;
    private boolean mMenuEnable = true;

    public SlidingMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMenuView = new FrameLayout(context);
        mContentView = new FrameLayout(context);

        int width = (int) (mMenuWidth * context.getResources().getDisplayMetrics().widthPixels);
        ViewGroup.LayoutParams lp1 = new ViewGroup.LayoutParams(width, LayoutParams.MATCH_PARENT);
        mMenuView.setId(R.id.menu_view);
        this.addView(mMenuView, lp1);

        ViewGroup.LayoutParams lp2 = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mContentView.setId(R.id.content_view);
        this.addView(mContentView, lp2);

        mContentView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }

        });

        this.setSliderFadeColor(Color.TRANSPARENT);

    }

    public int getMenuFrameId() {
        return mMenuView.getId();
    }

    public int getContentFrameId() {
        return mContentView.getId();
    }

    public ViewGroup getMenuView() {
        return mMenuView;
    }

    public ViewGroup getContentView() {
        return mContentView;
    }

    public void setContentView(View v) {
        mContentView.removeAllViews();
        mContentView.addView(v);
    }

    public void showMenu(boolean show) {
        if (show) {
            this.openPane();
        } else {
            this.closePane();
        }
    }

    public boolean isShowMenu() {
        return this.isOpen();
    }

    public void setMenuEnable(boolean enable) {
        mMenuEnable = enable;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mMenuEnable) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mMenuEnable) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        if (isFloatActionBarEnabled) {
            fitPadding(insets);
            fitDecorChild(this);
        }
        return true;
    }

    private void fitDecorChild(View view) {
        ViewGroup contentView = (ViewGroup) view.findViewById(R.id.actionbar_content_view);
        if (contentView != null) {
            ViewGroup decorChild = (ViewGroup) contentView.getChildAt(0);
            if (decorChild != null) {
                WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) decorChild.getLayoutParams();
                switch (manager.getDefaultDisplay().getRotation()) {
                    case Surface.ROTATION_90:
                        layoutParams.rightMargin = 0;
                        break;
                    case Surface.ROTATION_180:
                        layoutParams.topMargin = 0;
                        break;
                    case Surface.ROTATION_270:
                        layoutParams.leftMargin = 0;
                        break;
                    default:
                        layoutParams.bottomMargin = 0;
                        break;
                }
                decorChild.setLayoutParams(layoutParams);
            }
        }
    }

    private void fitPadding(Rect rect) {
        boolean hasNavigationBar = checkDeviceHasNavigationBar();
        if (hasNavigationBar) {
            WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            switch (manager.getDefaultDisplay().getRotation()) {
                case Surface.ROTATION_90:
                    rect.right += getNavBarWidth();
                    break;
                case Surface.ROTATION_180:
                    rect.top += getNavBarHeight();
                    break;
                case Surface.ROTATION_270:
                    rect.left += getNavBarWidth();
                    break;
                default:
                    rect.bottom += getNavBarHeight();
            }
        }
        mContentView.setPadding(rect.left, rect.top, rect.right, rect.bottom);
        mMenuView.setPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    /**
     * 检测是否具有底部导航栏
     *
     * @return
     */
    private boolean checkDeviceHasNavigationBar() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        display.getRealMetrics(realDisplayMetrics);
        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;
        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }

    private int getNavBarWidth() {
        return getNavBarDimen("navigation_bar_width");
    }

    private int getNavBarHeight() {
        return getNavBarDimen("navigation_bar_height");
    }

    private int getNavBarDimen(String resourceString) {
        Resources r = getResources();
        int id = r.getIdentifier(resourceString, "dimen", "android");
        if (id > 0) {
            return r.getDimensionPixelSize(id);
        } else {
            return 0;
        }
    }

    public void attachToActivity(Activity activity, boolean isFloatActionBarEnabled) {
        this.isFloatActionBarEnabled = isFloatActionBarEnabled;

        if (isFloatActionBarEnabled) {
            ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
            ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
            if (decorChild.getBackground() != null) {
                this.setBackgroundDrawable(decorChild.getBackground());
                decorChild.setBackgroundDrawable(null);
            }
            decor.removeView(decorChild);
            decor.addView(this.getRootView(), 0);
            getContentView().addView(decorChild);
        } else {
            ViewGroup contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
            ViewGroup content = (ViewGroup) contentParent.getChildAt(0);
            contentParent.removeView(content);
            contentParent.addView(this, 0);
            getContentView().addView(content);
        }
    }
}
