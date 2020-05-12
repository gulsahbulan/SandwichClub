package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.udacity.sandwichclub.databinding.ActivityMainBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Sandwich> sandwichList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Retrieve the string array associated with sandwich_details resource ID
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        sandwichList = new ArrayList<>();
        for (String sandwich : sandwiches) {
            sandwichList.add(JsonUtils.parseSandwichJson(sandwich));
        }

        // Create an SandwichAdapter, whose data source is a list of sandwiches
        SandwichAdapter adapter = new SandwichAdapter(this, sandwichList);
        // Simplification: Using a ListView instead of a RecyclerView
        ListView listView = binding.sandwichesListview;
        // Set the adapter on the listView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                launchDetailActivity(position);
            }
        });
    }


    private void launchDetailActivity(int position) {
        // Create a new intent to start the DetailActivity
        Intent intent = new Intent(this, DetailActivity.class);
        // Add the Serializable data to the intent
        intent.putExtra(DetailActivity.EXTRA_SANDWICH, sandwichList.get(position));
        // Start an instance of the DetailActivity that's specified by the Intent
        startActivity(intent);
    }
}
