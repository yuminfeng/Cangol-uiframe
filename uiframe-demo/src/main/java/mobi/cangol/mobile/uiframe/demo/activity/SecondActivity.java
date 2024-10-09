package mobi.cangol.mobile.uiframe.demo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import mobi.cangol.mobile.uiframe.demo.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
