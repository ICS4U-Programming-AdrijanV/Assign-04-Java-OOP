// Importing required classes
import java.util.HashMap;
import java.util.Map;

public class RecyclingBin {
    // Class variables to track the quantities of different materials
    private int totalPaper;
    private int totalPlastic;
    private int totalGlass;
    private int totalMetal;

    // Constructor
    public RecyclingBin() {
        // Initialize the quantities to zero
        totalPaper = 0;
        totalPlastic = 0;
        totalGlass = 0;
        totalMetal = 0;
    }

    // Method to add a quantity of paper to the recycling bin
    public void addPaper(int quantity) {
        // Increment the total paper count by the given quantity
        totalPaper += quantity;
    }

    // Method to add a quantity of plastic to the recycling bin
    public void addPlastic(int quantity) {
        // Increment the total plastic count by the given quantity
        totalPlastic += quantity;
    }

    // Method to add a quantity of glass to the recycling bin
    public void addGlass(int quantity) {
        // Increment the total glass count by the given quantity
        totalGlass += quantity;
    }

    // Method to add a quantity of metal to the recycling bin
    public void addMetal(int quantity) {
        // Increment the total metal count by the given quantity
        totalMetal += quantity;
    }

    // Method to calculate the total quantity of all materials recycled
    public int calculateTotalRecycled() {
        // Calculate the sum of quantities for all materials
        int totalRecycled = totalPaper + totalPlastic + totalGlass + totalMetal;
        // Return the total quantity of recycled materials
        return totalRecycled;
    }

    // Method to calculate the percentage of each material recycled
    public Map<String, Double> calculatePercentageRecycled() {
        // Calculate the total quantity of all materials recycled
        int totalRecycled = calculateTotalRecycled();
        // Create a HashMap to store the material and its percentage
        Map<String, Double> percentageMap = new HashMap<>();

        // Check if any materials have been recycled
        if (totalRecycled > 0) {
            // Calculate and add the percentage of paper recycled
            double paperPercentage = (totalPaper * 100.0) / totalRecycled;
            percentageMap.put("paper", paperPercentage);

            // Calculate and add the percentage of plastic recycled
            double plasticPercentage = (totalPlastic * 100.0) / totalRecycled;
            percentageMap.put("plastic", plasticPercentage);

            // Calculate and add the percentage of glass recycled
            double glassPercentage = (totalGlass * 100.0) / totalRecycled;
            percentageMap.put("glass", glassPercentage);

            // Calculate and add the percentage of metal recycled
            double metalPercentage = (totalMetal * 100.0) / totalRecycled;
            percentageMap.put("metal", metalPercentage);
        }

        // Return the map containing material and its percentage
        return percentageMap;
    }
}
