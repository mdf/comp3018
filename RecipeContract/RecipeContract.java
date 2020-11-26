package uk.ac.nott.cs;

import android.net.Uri;

/**
 * Created by pszmdf on 19/11/20
 */

public class RecipeContract {

    public static final String AUTHORITY = "uk.ac.nott.cs.recipes";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final Uri RECIPE_URI = Uri.parse("content://"+AUTHORITY+"/recipe/");
    public static final Uri INGREDIENT_URI = Uri.parse("content://"+AUTHORITY+"/ingredient/");
    public static final Uri RECIPE_INGREDIENTS_URI = Uri.parse("content://"+AUTHORITY+"/recipeingredient/");

    //field names
    public static final String RECIPE_ID = "_id";
    public static final String RECIPE_NAME = "name";
    public static final String RECIPE_INSTRUCTIONS = "instructions";
    public static final String RECIPE_RATING = "rating";

    public static final String INGREDIENT_ID = "_id";
    public static final String INGREDIENT_NAME = "ingredientname";

    public static final String RECIPE_INGREDIENTS_RECIPE_ID = "recipe_id";
    public static final String RECIPE_INGREDIENTS_INGREDIENT_ID = "ingredient_id";

    public static final String CONTENT_TYPE_SINGLE = "vnd.android.cursor.item/recipes.data.text";
    public static final String CONTENT_TYPE_MULTIPLE = "vnd.android.cursor.dir/recipes.data.text";

}
 