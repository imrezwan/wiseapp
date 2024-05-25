package com.imrezwan.wise_brewer;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupToolbar();
        setupBottomNavigationBar();
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, new HomeFragment2(), "home").commit();
        else
            onBackStackChanged();
    }

    private void setupBottomNavigationBar() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeFragment2(), "home").addToBackStack(null).commit();
                    return true;
                case R.id.nav_profiles:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ProfilesFragment(), "profiles").addToBackStack(null).commit();
                    return true;
            }
            return false;
        });

    }

    private void setupToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    public void init() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    @Override
    public void onBackStackChanged() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount()>0);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
