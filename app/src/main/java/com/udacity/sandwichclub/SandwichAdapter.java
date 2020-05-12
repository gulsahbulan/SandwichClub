package com.udacity.sandwichclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.SandwichListItemBinding;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

public class SandwichAdapter extends ArrayAdapter {
    public SandwichAdapter(@NonNull Context context, List<Sandwich> sandwiches) {
        super(context, 0, sandwiches);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse
        SandwichListItemBinding binding;
        if (convertView == null) {
            binding = SandwichListItemBinding.inflate(LayoutInflater.from(getContext()), parent, false);
            convertView = binding.getRoot();
        } else {
            binding = (SandwichListItemBinding) convertView.getTag();
        }
        convertView.setTag(binding);

        // Find the sandwich at the given position in the list of sandwiches
        Sandwich currentSandwich = (Sandwich) getItem(position);

        // Get the Main Name from the currentSandwich object and set this text on
        // the sandwichTextView
        binding.sandwichTextView.setText(currentSandwich.getMainName());

        Picasso.get()
                .load(currentSandwich.getImage())
                .placeholder(R.drawable.ic_sandwich)
                .into(binding.image);

        // Return the convertView that is now showing the appropriate data
        return convertView;
    }
}
