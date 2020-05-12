package com.udacity.sandwichclub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.udacity.sandwichclub.databinding.IngredientsFragmentBinding;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.ArrayList;
import java.util.List;


public class IngredientsFragment extends Fragment {
    private static final String INGREDIENTS = "ingredients";
    private List<String> ingredientsList;

    public IngredientsFragment() {
    }

    static IngredientsFragment newInstance(Sandwich sandwich) {
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(INGREDIENTS, (ArrayList<String>) sandwich.getIngredients());
        ingredientsFragment.setArguments(bundle);
        return ingredientsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            ingredientsList = getArguments().getStringArrayList(INGREDIENTS);
        }
    }

    private void init(IngredientsFragmentBinding binding) {
        if (ingredientsList != null && !ingredientsList.isEmpty()) {
            // Convert the ingredientsList to a String separated with delimiter
            String ingredients = TextUtils.join("\n\n", capitalizeFirstLetter(ingredientsList));

            // Set the text on the ingredientsTv TextView
            binding.ingredientsTv.setText(ingredients);
        } else {
            // Set the view invisible without taking up space in the layout
            binding.ingredientsTv.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        IngredientsFragmentBinding binding = IngredientsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init(binding);
        return view;
    }

    private ArrayList<String> capitalizeFirstLetter(List<String> ingredients) {
        ArrayList<String> output = new ArrayList<String>();
        for (String ingredient : ingredients) {
            String item = "⦿ " + ingredient.substring(0, 1).toUpperCase() + ingredient.substring(1).toLowerCase();
            output.add(item);
        }
        return output;
    }
}
