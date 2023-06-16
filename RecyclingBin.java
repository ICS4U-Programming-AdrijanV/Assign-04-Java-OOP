import java.util.*;

public class RecyclingBin {
    private int paper;
    private int plastic;
    private int glass;
    private int metal;
    private List<String> invalidInputs;

    public RecyclingBin() {
        paper = 0;
        plastic = 0;
        glass = 0;
        metal = 0;
        invalidInputs = new ArrayList<>();
    }

    public void addPaper(int quantity) {
        paper += quantity;
    }

    public void addPlastic(int quantity) {
        plastic += quantity;
    }

    public void addGlass(int quantity) {
        glass += quantity;
    }

    public void addMetal(int quantity) {
        metal += quantity;
    }

    public int calculateTotalRecycled() {
        return paper + plastic + glass + metal;
    }

    public Map<String, Double> calculatePercentageRecycled() {
        Map<String, Double> percentageMap = new HashMap<>();
        int totalRecycled = calculateTotalRecycled();

        if (totalRecycled == 0) {
            return percentageMap;
        }

        double paperPercentage = (double) paper / totalRecycled * 100;
        double plasticPercentage = (double) plastic / totalRecycled * 100;
        double glassPercentage = (double) glass / totalRecycled * 100;
        double metalPercentage = (double) metal / totalRecycled * 100;

        percentageMap.put("paper", paperPercentage);
        percentageMap.put("plastic", plasticPercentage);
        percentageMap.put("glass", glassPercentage);
        percentageMap.put("metal", metalPercentage);

        return percentageMap;
    }

    public List<String> getInvalidInputs() {
        return invalidInputs;
    }

    public void addInvalidInput(String input) {
        invalidInputs.add(input);
    }
}
