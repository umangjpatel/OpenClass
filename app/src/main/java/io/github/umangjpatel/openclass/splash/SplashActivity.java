package io.github.umangjpatel.openclass.splash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import io.github.umangjpatel.openclass.R;
import io.github.umangjpatel.openclass.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        startActivity(MainActivity.getIntent(this));
        finish();
    }
}
