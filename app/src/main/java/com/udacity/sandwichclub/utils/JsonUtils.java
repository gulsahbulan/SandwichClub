package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            // Create a JSONObject from the json string
            JSONObject baseJsonResponse = new JSONObject(json);
            // Extract the JSONObject associated with the key called "name"
            JSONObject name = baseJsonResponse.getJSONObject(NAME);
            // Extract the value associated with the key "mainName"
            String mainName = name.getString(MAIN_NAME);

            // Extract the JSONArray associated with the key called "alsoKnownAs"
            JSONArray alsoKnownAsArray = name.getJSONArray(ALSO_KNOWN_AS);

            List<String> alsoKnownAs = convertJSONArrayToList(alsoKnownAsArray);

            // Extract the value associated with the key "placeOfOrigin"
            String placeOfOrigin = baseJsonResponse.getString(PLACE_OF_ORIGIN);

            // Extract the value associated with the key "description"
            String description = baseJsonResponse.getString(DESCRIPTION);

            // Extract the value associated with the key "image"
            String image = baseJsonResponse.getString(IMAGE);

            // Extract the JSONArray associated with the key called "ingredients"
            JSONArray ingredientsArray = baseJsonResponse.getJSONArray(INGREDIENTS);

            List<String> ingredients = convertJSONArrayToList(ingredientsArray);

            // Return a new Sandwich object with the mainName, alsoKnownAs, placeOfOrigin, description,
            // image and ingredients from the JSON response.
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash.
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> convertJSONArrayToList(JSONArray jsonArray) throws JSONException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.getString(i));
        }
        return result;
    }
}
