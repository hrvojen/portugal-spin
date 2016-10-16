package USDAReader;

/**
 * Created by Wasif on 11/8/2014.
 * This class will hold all the constants relevant to the program. It also holds the FoodCategory enum, which contains
 * the different categories of food as declared by the USDA.
 */
public class USDA {
    public static final String WEBSITE = "http://ndb.nal.usda.gov";
    public static final String WEBSITE_TABLE = "/ndb/?format=&count=&max=10000";
    public static final String FOOD_PAGE = "http://ndb.nal.usda.gov/ndb/foods/show/";


    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String LINK_TAG = "a";
    public static final String LINK_ATTRIBUTE = "href";
    public static final String TABLE_TAG = "td";
    public static final String TABLE_ROW_TAG = "tr";
    public static final String NUTRITION_ATTRIBUTE = "proximates-0\"";

    private USDA () {}

    public enum FoodCategory {
        AMERICAN_INDIAN_OR_ALASKA_NATIVE_FOODS("American Indian/Alaska Native Foods"),
        BABY_FOODS("Baby Foods"),
        BAKED_PRODUCTS("Baked Products"),
        BEEF_PRODUCTS("Beef Products"),
        BEVERAGES("Beverages"),
        BREAKFAST_CEREALS("Breakfast Cereals"),
        CEREAL_GRAINS_AND_PASTA("Cereal Grains and Pasta"),
        DAIRY_AND_EGG_PRODUCTS("Dairy and Egg Products"),
        FAST_FOODS("Fast foods"),
        FATS_AND_OILS("Fats and Oils"),
        FINFISH_AND_SHELLFISH_PRODUCTS("Finfish and shellfish products"),
        FRUITS_AND_FRUIT_JUICES("Fruits and fruit juices"),
        LAMB_VEAL_AND_GAME_PRODUCTS("Lamb, Veal, and Game products"),
        LEGUMES_AND_LEGUME_PRODUCTS("Legumes and legume products"),
        MEALS_ENTREES_AND_SIDE_DISHES("Meals, Entrees, and Side Dishes"),
        NUT_AND_SEED_PRODUCTS("Nut and Seed Products"),
        PORK_PRODUCTS("Pork Products"),
        POULTRY_PRODUCTS("Poultry Products"),
        RESTAURANT_FOODS("Restaurant foods"),
        SAUSAGES_AND_LUNCHEON_MEATS("Sausages and luncheon meats"),
        SNACKS("Snacks"),
        SOUPS_SAUCES_AND_GRAVIES("Soups, Sauces, and Gravies"),
        SPICES_AND_HERBS("Spices and Herbs"),
        SWEETS("Sweets"),
        VEGETABLES_AND_VEGETABLE_PRODUCTS("Vegetables and Vegetable Products");

        private final String text;

        FoodCategory(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public static FoodCategory getFoodCategory (String text) {
            if (text != null) {
                for (FoodCategory foodCategory : FoodCategory.values()) {
                    if (foodCategory.getText().equalsIgnoreCase(text)) {
                        return foodCategory;
                    }
                }
            }
            return null;
        }
    }
}

