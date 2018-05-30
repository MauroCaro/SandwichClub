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

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject name = sandwichJson.getJSONObject(NAME);
            sandwich.setMainName(name.getString(MAIN_NAME));
            sandwich.setAlsoKnownAs(getListString(name.getJSONArray(ALSO_KNOWN_AS)));
            sandwich.setPlaceOfOrigin(sandwichJson.getString(PLACE_ORIGIN));
            sandwich.setDescription(sandwichJson.getString(DESCRIPTION));
            sandwich.setImage(sandwichJson.getString(IMAGE));
            sandwich.setIngredients(getListString(name.getJSONArray(INGREDIENTS)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    private static List<String> getListString(JSONArray jsonArray) {
        List<String> stringList = new ArrayList<>();
        if (jsonArray != null) {
            try {
                for (int i = 0; i <= jsonArray.length(); i++) {
                    stringList.add(jsonArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stringList;
    }
}
