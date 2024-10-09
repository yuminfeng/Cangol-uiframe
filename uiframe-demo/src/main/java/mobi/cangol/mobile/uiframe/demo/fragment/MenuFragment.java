package mobi.cangol.mobile.uiframe.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import mobi.cangol.mobile.base.BaseMenuFragment;
import mobi.cangol.mobile.uiframe.demo.R;

public class MenuFragment extends BaseMenuFragment {
    public static final int MODULE_HOME = 0;
    public static final int MODULE_CLEAN = 1;

    public TextView textView1;
    public TextView textView2;

    private boolean isBottom = true;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isBottom = getArguments().getBoolean("isBottom");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(isBottom ? R.layout.fragment_menu_bottom : R.layout.fragment_menu_left, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            updateFocus(this.getCurrentModuleId());
        } else {
            this.setCurrentModuleId(this.getCurrentModuleId());
        }
    }

    @Override
    protected void findViews(View v) {
        textView1 = (TextView) v.findViewById(R.id.textView1);
        textView2 = (TextView) v.findViewById(R.id.textView2);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        textView1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentFragment(HomeFragment.class, "TestFragment", null, MODULE_HOME);
            }

        });
        textView2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentFragment(CleanFragment.class, "CleanFragment", null, MODULE_CLEAN);
            }

        });
    }

    @Override
    protected void onContentChange(int moduleId) {
        if (this.getView() != null) {
            updateFocus(moduleId);
        }
    }

    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClosed() {

    }

    private void updateFocus(int moduleId) {
        textView1.setSelected(MODULE_HOME == moduleId);
        textView2.setSelected(MODULE_CLEAN == moduleId);
    }
}
