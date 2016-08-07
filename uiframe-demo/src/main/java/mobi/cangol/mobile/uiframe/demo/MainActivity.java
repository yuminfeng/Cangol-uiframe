package mobi.cangol.mobile.uiframe.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import mobi.cangol.mobile.logging.Log;
import mobi.cangol.mobile.navigation.TabNavigationFragmentActivity;
import mobi.cangol.mobile.uiframe.demo.fragment.HomeFragment;
import mobi.cangol.mobile.uiframe.demo.fragment.MenuFragment;

@SuppressLint("ResourceAsColor")
public class MainActivity extends TabNavigationFragmentActivity {
	private static long back_pressed;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//this.setStatusBarTintColor(R.color.red);
		//this.setNavigationBarTintColor(R.color.black);
		setContentView(R.layout.activity_main);
		this.setStatusBarTintColor(getResources().getColor(R.color.actionbar_background));
		this.getCustomActionBar().setBackgroundResource(R.color.red);
		if (savedInstanceState == null) {
			this.setMenuFragment(MenuFragment.class,null);
			this.setContentFragment(HomeFragment.class, "HomeFragment", null);
		}
		findViews();
		initViews(savedInstanceState);
		initData(savedInstanceState);
		//this.setFloatActionBarEnabled(true);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void findViews() {
	}
	@Override
	public void initViews(Bundle savedInstanceState) {
		
	}
	@Override
	public void initData(Bundle savedInstanceState) {
		Log.d(TAG,"initData");
	}

	@Override
	public void onBack() {
		if(back_pressed+2000>System.currentTimeMillis()){
			super.onBack();
			app.exit();
		}else{
			back_pressed=System.currentTimeMillis();
            showToast("Please on back");
		}
	}

	public int getContentFrameId() {
		return R.id.main_frame;
	}
}
