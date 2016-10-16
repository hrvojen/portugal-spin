package USDAReader;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import USDAReader.USDA.FoodCategory;
/**
 * This class will hold the table for all Food objects obtained. The class is a hash map where each food will be stored
 * based on its NDB number.
 *
 * This class will parse websites using JSoup, an HTML parser. The parses can be found in the lib folder of this project.
 */
final class FoodTable extends HashMap<Integer, Food> {
    //DEFAULT text file to save URL to
    private static final String TEXT_FILE = "usda.txt";

    private static File textFile = null;

    /**
     * This method will load the URL once, and read and save it into a text file. If the text file already exists
     * then the method will terminate early.
     * The HTML page will be saved to whatever text file designated by the TEXT_FILE variable.
     */
    void loadURL() {
        try {
             textFile = new File(TEXT_FILE);
            if (textFile.exists())  //No need to recreate the file if it already exists.
                return;
            URL usda = new URL(USDA.WEBSITE + USDA.WEBSITE_TABLE);
            BufferedReader br = new BufferedReader(new InputStreamReader(usda.openStream()));
            String inputLine;
            StringBuilder outputLine = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                inputLine = inputLine.trim();
                if (inputLine.isEmpty())
                    continue;
                outputLine.append(inputLine).append("\n");
            }
            FileWriter fw = new FileWriter(textFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(outputLine.toString());
            bw.close();
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will parse the USDA text file, which will contain the table of all foods. Each food will have
     * an NDB number, a name, and a food category. There will be a link associated with each NDB number and each food name.
     * However, both links point to the same web page.
     *
     * The file will be parsed and the NDB number, food name, and food category will be extracted.
     * While parsing the file for the NDB number, the link will also be extracted.
     *
     * A new connection will be made to the URl for the item in the table to extract nutritional information such as
     * calories, protein, fiber, and carbohydrates.
     *
     * Once all food items have been added to the FoodTable the method will terminate.
     */
    public void parseHTMLFile() {
        final int NDB_NUMBER_INDEX = 0;
        final int FOOD_NAME_INDEX = 1;
        final int FOOD_CATEGORY_INDEX = 2;
        loadURL();
        int ndbNumber;
        String foodName;
        String foodLink;
        Document foodDocument;
        Elements tableItems;
        Elements foodTable;
        FoodCategory foodCategory;
        Food food;
        String regExpDouble = "[\\x00-\\x20]*[+-]?(((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
                "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
                "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
                "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*";

        try {
            foodDocument = Jsoup.parse(textFile, USDA.DEFAULT_CHARSET);

            tableItems = foodDocument.select(USDA.TABLE_ROW_TAG);

            for (Element tableItem : tableItems) {
                if (tableItem.children().isEmpty() || tableItem.children().size() < FOOD_CATEGORY_INDEX) {
                    continue;
                }
                food = new Food();

                //FIRST GET THE NDB NUMBER
                Element ndbElement = tableItem.children().get(NDB_NUMBER_INDEX);

                foodLink = ndbElement.getElementsByTag(USDA.LINK_TAG).attr(USDA.LINK_ATTRIBUTE);
                String ndbString = ndbElement.text();

                if (!ndbString.matches("\\d+")) {
                    continue;
                }


                Connection usdaConnection = Jsoup.connect(USDA.WEBSITE + foodLink);

                Thread.sleep(1000);
                usdaConnection.timeout(0);
                foodDocument = usdaConnection.get();
                foodTable = foodDocument.select(USDA.TABLE_ROW_TAG);

                for (Element foodElement : foodTable) {
                    if (foodElement.hasAttr(USDA.NUTRITION_ATTRIBUTE)) {
                        String foodText = foodElement.text();
                        //proximate contains the basic nutritional values
                        if (foodText.contains("Energy")) {
                            String energyValues[] = foodText.split("\\s");
                            for (String s : energyValues) {
                                if (s.matches("\\d+")) {
                                    food.setCalories(Integer.parseInt(s));
                                    break;
                                }
                            }
                        } else if (foodText.contains("Protein")) {
                            String proteinValues[] = foodText.split("\\s");
                            for (String s : proteinValues) {
                                if (s.matches(regExpDouble)) {
                                    food.setProtein(Double.parseDouble(s));
                                    break;
                                }
                            }
                        } else if (foodText.contains("Carbohydrate")) {
                            String carbValues[] = foodText.split("\\s");
                            for (String s : carbValues) {
                                if (s.matches(regExpDouble)) {
                                    food.setCarbohydrates(Double.parseDouble(s));
                                }
                            }
                        } else if (foodText.contains("Fiber")) {
                            String fiberValues[] = foodText.split("\\s");
                            for (String fiberValue : fiberValues) {
                                if (fiberValue.matches(regExpDouble)) {
                                    food.setFiber(Double.parseDouble(fiberValue));
                                }
                            }
                        }
                    }
                }


                ndbNumber = Integer.parseInt(ndbString);
                food.setNdbNumber(ndbNumber);

                foodName = tableItem.children().get(FOOD_NAME_INDEX).text();
                food.setName(foodName);

                String category = tableItem.children().get(FOOD_CATEGORY_INDEX).text();

                if (category.equals(FoodCategory.BABY_FOODS.getText())) {
                    continue;
                }

                foodCategory = FoodCategory.getFoodCategory(category);
                food.setFoodCategory(foodCategory);

                //Food will be added to the table once all values have been set. It will be printed to console
                //to show which food was added.
                if(food.isComplete()) {
                    System.out.println(food.toString());
                    put(food.getNdbNumber(), food);
                }
            }

        }
        catch (IOException ignored) {}
        catch (InterruptedException ignored) {}
    }
}