package com.udacity.sandwichclub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.udacity.sandwichclub.databinding.AboutFragmentBinding;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String ORIGIN = "origin";
    private static final String DESCRIPTION = "description";

    private List<String> alsoKnownAsList;
    private String origin;
    private String description;

    public AboutFragment() {
    }

    static AboutFragment newInstance(Sandwich sandwich) {
        AboutFragment aboutFragment = new AboutFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ALSO_KNOWN_AS, (ArrayList<String>) sandwich.getAlsoKnownAs());
        bundle.putString(ORIGIN, sandwich.getPlaceOfOrigin());
        bundle.putString(DESCRIPTION, sandwich.getDescription());
        aboutFragment.setArguments(bundle);
        return aboutFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            alsoKnownAsList = getArguments().getStringArrayList(ALSO_KNOWN_AS);
            origin = getArguments().getString(ORIGIN);
            description = getArguments().getString(DESCRIPTION);
        }
    }

    private void init(AboutFragmentBinding binding) {
        if (alsoKnownAsList != null && !alsoKnownAsList.isEmpty()) {
            // Convert the alsoKnownAsList to a comma separated String
            String alsoKnownAs = android.text.TextUtils.join(", ", alsoKnownAsList);
            // Set the text on the alsoKnownAsTv TextView
            binding.alsoKnownTv.setText(alsoKnownAs);
        } else {
            // Set the view invisible without taking up space in the layout
            binding.alsoKnownLabelTv.setVisibility(View.GONE);
            binding.alsoKnownTv.setVisibility(View.GONE);
        }

        if (origin != null && !origin.isEmpty()) {
            // Set the text on the originTv TextView
            binding.originTv.setText(origin);
        } else {
            // Set the view invisible without taking up space in the layout
            binding.originLabelTv.setVisibility(View.GONE);
            binding.originTv.setVisibility(View.GONE);
        }

        if (description != null && !description.isEmpty()) {
            // Set the text on the descriptionTv TextView
            binding.descriptionTv.setText(description);
        } else {
            // Set the view invisible without taking up space in the layout
            binding.descriptionLabelTv.setVisibility(View.GONE);
            binding.descriptionTv.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AboutFragmentBinding binding = AboutFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init(binding);
        return view;
    }
}
