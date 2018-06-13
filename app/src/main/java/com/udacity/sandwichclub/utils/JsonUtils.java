package com.udacity.sandwichclub.utils;

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
    private static final String PLACE_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    /**
     * Parsing data that comes from JsonString into a model
     *
     * @param json
     *
     * @return Sandwich model
     */
    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject name = sandwichJson.getJSONObject(NAME);
            sandwich.setMainName(name.optString(MAIN_NAME));
            sandwich.setAlsoKnownAs(getListString(name.getJSONArray(ALSO_KNOWN_AS)));
            sandwich.setPlaceOfOrigin(sandwichJson.optString(PLACE_ORIGIN));
            sandwich.setDescription(sandwichJson.optString(DESCRIPTION));
            sandwich.setImage(sandwichJson.optString(IMAGE));
            sandwich.setIngredients(getListString(sandwichJson.getJSONArray(INGREDIENTS)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    /**
     * Get list of strings from a Json array
     *
     * @param jsonArray
     *
     * @return List of Strings
     */
    private static List<String> getListString(JSONArray jsonArray) {
        List<String> stringList = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i <= jsonArray.length(); i++) {
                try {
                    stringList.add(jsonArray.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringList;
    }
}
