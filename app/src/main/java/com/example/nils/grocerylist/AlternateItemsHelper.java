package com.example.nils.grocerylist;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Nils on 4/18/17.
 */

public class AlternateItemsHelper {

    ArrayList<Item> alternate;
    DatabaseHelper db;

    public AlternateItemsHelper (Context context){
        db = new DatabaseHelper(context);
        alternate = new ArrayList<Item>();
    }

    public void findAlternateItems(Item item) {
        ArrayList<Item> fulllist = db.getAllItems();
        String[] itemingredients = item.getIngredients();
        String[] ingredients;
        double percent = 0;

        if (!itemingredients[0].equals("")) {
            for (int k = 0; k < fulllist.size(); k++) {
                ingredients = fulllist.get(k).getIngredients();

                if (ingredients[0].equals("")) {
                    k++;
                } else {

                    for (int i = 0; i < ingredients.length; i++) {
                        for (int j = 0; j < itemingredients.length; j++) {
                            if (ingredients[i].contains(itemingredients[j])) {
                                percent = percent + 1;
                                Log.d("Percent: ", " ++ (" + percent + ")");
                                Log.d("Item added to alternate", fulllist.get(k).toString());
                            } else if (itemingredients[j].contains(ingredients[i])) {
                                percent = percent + 1;
                                Log.d("Percent: ", " ++ (" + percent + ")");
                                Log.d("Item added to alternate", fulllist.get(k).toString());
                            }

                        }
                    }
                }
                percent = percent / ingredients.length;
                if (percent >= 0.5) {
                    alternate.add(fulllist.get(k));
                    Log.d("Alternate items", alternate.toString());
                }
                percent = 0;
            }
            Log.d("Alternate items", alternate.toString());
        }
    }

    public ArrayList<Item> getAlternateItemsList() {
        return alternate;
    }
}