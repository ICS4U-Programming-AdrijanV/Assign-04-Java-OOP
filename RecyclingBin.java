import java.util.*;

public class RecyclingBin {
    // Private member variables
    private int paper;
    private int plastic;
    private int glass;
    private int metal;
    private List<String> invalidInputs;

    // Constructor for creating a new RecyclingBin object
    public RecyclingBin() {
        // Initialize the quantities of each material to zero
        paper = 0;
        plastic = 0;
        glass = 0;
        metal = 0;
        // Initialize the list of invalid inputs
        invalidInputs = new ArrayList<>();
    }

    // Method for adding quantity of paper to the recycling bin
    public void addPaper(int quantity) {
        paper += quantity;
    }

    // Method for adding quantity of plastic to the recycling bin
    public void addPlastic(int quantity) {
        plastic += quantity;
    }

    // Method for adding quantity of glass to the recycling bin
    public void addGlass(int quantity) {
        glass += quantity;
    }

    // Method for adding quantity of metal to the recycling bin
    public void addMetal(int quantity) {
        metal += quantity;
    }

    // Method for calculating the total quantity of recycled materials
    public int calculateTotalRecycled() {
        return paper + plastic + glass + metal;
    }

    // Method for calculating the percentage of each material recycled
    public Map<String, Double> calculatePercentageRecycled() {
        Map<String, Double> percentageMap = new HashMap<>();
        int totalRecycled = calculateTotalRecycled();

        // If no materials have been recycled, return an empty map
        if (totalRecycled == 0) {
            return percentageMap;
        }

        // Calculate the percentage of each material recycled
        double paperPercentage = (double) paper / totalRecycled * 100;
        double plasticPercentage = (double) plastic / totalRecycled * 100;
        double glassPercentage = (double) glass / totalRecycled * 100;
        double metalPercentage = (double) metal / totalRecycled * 100;

        // Add the percentages to the map
        percentageMap.put("paper", paperPercentage);
        percentageMap.put("plastic", plasticPercentage);
        percentageMap.put("glass", glassPercentage);
        percentageMap.put("metal", metalPercentage);

        return percentageMap;
    }

    // Method for retrieving the list of invalid inputs
    public List<String> getInvalidInputs() {
        return invalidInputs;
    }

    // Method for adding an invalid input to the list
    public void addInvalidInput(String input) {
        invalidInputs.add(input);
    }
}
