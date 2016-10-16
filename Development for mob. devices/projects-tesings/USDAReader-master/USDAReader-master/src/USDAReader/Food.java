package USDAReader;

import USDAReader.USDA.FoodCategory;
/**
 * Created by Wasif on 11/7/2014.
 * Food class holds the important information for each food object.
 * The data for each food object will be extracted from the USDA website.
 * Pertitnent information for the app will be the food name, food category, calories, protein, carbohydrates, and fiber.
 */
class Food {
    //DEFAULT VALUE is the initial value for any numeric variable. All values will later be 0 or greater if a valid
    //food is entered
    private static final int DEFAULT_VALUE = -1;
    //kcal is eqivalent to Calories

    //ndbNumber is the ID of each food on the USDA database
    private int ndbNumber;

    private String name;
    private FoodCategory foodCategory;
    private int calories;
    private double protein;
    private double carbohydrates;
    private double fiber;

    public Food () {
        ndbNumber = DEFAULT_VALUE;
        name = "";
        foodCategory = null;
        calories = DEFAULT_VALUE;
        protein = DEFAULT_VALUE;
        carbohydrates = DEFAULT_VALUE;
        fiber = DEFAULT_VALUE;
    }

    public Food (String name, double protein, double carbohydrates,
                 double fiber, int calories, FoodCategory foodCategory) {
        this.name = name;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
        this.calories = calories;
        this.foodCategory = foodCategory;

    }

    /*
    * Standard getters and setters for instance variables
    * */

    public int getCalories() {
        return calories;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getFiber() {
        return fiber;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public String getName() {
        return name;
    }

    public int getNdbNumber() {
        return ndbNumber;
    }

    public double getProtein() {
        return protein;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNdbNumber(int ndbNumber) {
        this.ndbNumber = ndbNumber;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String toString () {
        String food = "";

        food += "NDF Number: " + ndbNumber + " ";
//        food += "\n";
        food += "Food Name: " + name + " ";
//        food += "\n";
        String category = (foodCategory != null) ? foodCategory.getText() : null;
        food += "Food Category: " + category + " ";
//        food += "\n";
        food += "Calories: " + calories + " ";
//        food += "\n";
        food += "Protein: " + protein + " ";
//        food += "\n";
        food += "Carbohydrates: " + carbohydrates + " ";
//        food += "\n";
        food += "Fiber: " + fiber + " ";

        return food;
    }

    /**
     * Checks to see if all values of the Food object are valid.
     * @return true if Food has been initialized with all valid values. Returns false otherwise.
     */
    public boolean isComplete () {
        return !this.name.isEmpty() &&
                foodCategory != null &&
                calories != -1 &&
                protein != -1 &&
                carbohydrates != -1 &&
                fiber != -1;
    }

    /**
     * Checks to see if the Food has valid nutritional values. USDA database has separate page for each food.
     * If that page hasn't been accessed and used to set values, this will return false.
     * @return true if Food has valid nutritional values. False otherwise.
     */
    public boolean hasNutrition () {
        return calories != - 1 &&
                protein != -1 &&
                carbohydrates != -1 &&
                fiber != -1;
    }
}