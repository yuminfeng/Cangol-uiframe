package mobi.cangol.mobile.uiframe.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import mobi.cangol.mobile.base.BaseContentFragment;
import mobi.cangol.mobile.base.CustomFragmentTransaction;
import mobi.cangol.mobile.uiframe.demo.R;

public class SwitchAnimFragment extends BaseContentFragment {

    private Button mButton1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_switch, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragmentStack(R.id.switch_container);
        initViews(savedInstanceState);
        initData(savedInstanceState);
        switchFragment();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void findViews(View view) {
        mButton1 = (Button) view.findViewById(R.id.button1);

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        this.setTitle(this.getClass().getSimpleName());
        mButton1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                switchFragment();
            }

        });

    }

    private boolean mIsDownload = true;

    protected void switchFragment() {
        if (!mIsDownload) {
            Bundle args = new Bundle();
            args.putString("flag", "Up");
            CustomFragmentTransaction transaction = new CustomFragmentTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom);
            replaceChildFragment(ItemFragment.class, "ItemFragment1", args, transaction);
        } else {
            Bundle args = new Bundle();
            args.putString("flag", "Down");
            CustomFragmentTransaction transaction = new CustomFragmentTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
            replaceChildFragment(ItemFragment.class, "ItemFragment2", args, transaction);
        }
        mIsDownload = !mIsDownload;
    }

    @Override
    public boolean onSupportNavigateUp() {
        popBackStack();
        return true;
    }

    @Override
    public boolean onBackPressed() {
        popBackStack();
        return true;
    }
}
