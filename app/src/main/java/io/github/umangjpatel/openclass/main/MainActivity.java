package io.github.umangjpatel.openclass.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import io.github.umangjpatel.openclass.R;

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    return NavigationUI.onNavDestinationSelected(item, mNavController);
                }
            };

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView navigation = findViewById(R.id.bottom_nav);

        NavigationUI.setupWithNavController(navigation, mNavController);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FirebaseApp.initializeApp(this);

        mNavController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.questionsFragment:
                    navigation.setVisibility(View.GONE);
                    break;
                case R.id.resultFragment:
                    navigation.setVisibility(View.GONE);
                    break;
                default:
                    navigation.setVisibility(View.VISIBLE);

            }
        });

    }


}
