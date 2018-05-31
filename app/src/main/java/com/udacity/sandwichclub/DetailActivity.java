package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView alsoKnowAs, placeOfOrigin, description, ingredients;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        fetchInformation();
    }

    private void fetchInformation() {
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            // return;
        }
        loadUI();
        populateUI(sandwich);
    }

    /**
     * Load the component of the UI
     */
    private void loadUI() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        alsoKnowAs = findViewById(R.id.also_known_tv);
        placeOfOrigin = findViewById(R.id.place_origin_tv);
        description = findViewById(R.id.description_tv);
        ingredients = findViewById(R.id.description_tv);
        imageView = findViewById(R.id.image_iv);
    }

    /**
     * Build UI in base of Sandwich information
     *
     * @param sandwich
     */
    private void populateUI(Sandwich sandwich) {
        setTitle(sandwich.getMainName());
        Picasso.with(this)
               .load(sandwich.getImage())
               .into(imageView);
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin().toString());
        description.setText(sandwich.getDescription().toString());
        if (sandwich.getAlsoKnownAs() != null) {
            for (String item : sandwich.getAlsoKnownAs()) {
                alsoKnowAs.setText(item.toString());
            }
        }
        if (sandwich.getIngredients() != null) {
            for (String item : sandwich.getIngredients()) {
                ingredients.setText(item.toString());
            }
        }
    }

    /**
     * Finish the activity and show an alert for the user
     */
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
}
