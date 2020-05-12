package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_SANDWICH = "extra_sandwich";
    private ActivityDetailBinding binding;

    Sandwich sandwich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        if (savedInstanceState == null) {
            // Retrieve extended data from the intent
            sandwich = (Sandwich) intent.getSerializableExtra(EXTRA_SANDWICH);
        } else {
            sandwich = (Sandwich) savedInstanceState.getSerializable("sandwich");
        }

        populateUI(sandwich);
        assert sandwich != null;
        Picasso.get()
                .load(sandwich.getImage())
                .placeholder(R.drawable.ic_sandwich)
                .into(binding.imageIv);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("sandwich", sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        // Create an adapter that knows which fragment should be shown on each page
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(AboutFragment.newInstance(sandwich), "About");
        adapter.addFragment(IngredientsFragment.newInstance(sandwich), "Ingredients");

        // Set the adapter onto the view pager
        binding.viewpager.setAdapter(adapter);

        // Connect the tab layout with the view pager
        binding.slidingTabs.setupWithViewPager(binding.viewpager);

        binding.slidingTabs.getTabAt(0).setIcon(R.drawable.ic_about);
        binding.slidingTabs.getTabAt(1).setIcon(R.drawable.ic_ingredients);

        binding.slidingTabs.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.slidingTabs.setTabMode(TabLayout.MODE_FIXED);

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(sandwich.getMainName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
