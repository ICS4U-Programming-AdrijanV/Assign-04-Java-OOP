// Importing
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The main file.
 *
 * @author Adrijan Vranjkovic
 * @version 1.0
 * @since 2023-06-15
 */
public class Main {
    public static void main(String[] args) {
        // Name of the input file
        String inputFileName = "input.txt";
        // Name of the output file
        String outputFileName = "output.txt";

        // Create a RecyclingBin object
        RecyclingBin bin = new RecyclingBin();
        // Process the input file and update the recycling bin
        processInputFile(inputFileName, bin);
        // Generate the output file based on the recycling bin data
        generateOutputFile(outputFileName, bin);
    }

    public static void processInputFile(String inputFileName, RecyclingBin bin) {
        try {
            // Create a File object with the specified input file name
            File inputFile = new File(inputFileName);
            // Create a Scanner object to read from the input file
            Scanner scanner = new Scanner(inputFile);

            // Loop through each line in the input file
            while (scanner.hasNextLine()) {
                // Read the next line and remove leading/trailing whitespaces
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    // Skip blank lines
                    continue;
                }

                // Split the line by comma and space
                String[] parts = line.split(", ");

                if (parts.length != 2) {
                    // Invalid input format, add the line to the list of invalid inputs
                    bin.addInvalidInput(line);
                    continue;
                }
                // Extract the material (convert to lowercase for case-insensitive comparison)
                String material = parts[0].toLowerCase();
                // Extract the quantity as a string
                String quantityStr = parts[1];

                try {
                    // Convert the quantity string to an integer
                    int quantity = Integer.parseInt(quantityStr);

                    switch (material) {
                        case "paper":
                            // Add the quantity to the paper count in the recycling bin
                            bin.addPaper(quantity);
                            break;
                        case "plastic":
                            // Add the quantity to the plastic count in the recycling bin
                            bin.addPlastic(quantity);
                            break;
                        case "glass":
                            // Add the quantity to the glass count in the recycling bin
                            bin.addGlass(quantity);
                            break;
                        case "metal":
                            // Add the quantity to the metal count in the recycling bin
                            bin.addMetal(quantity);
                            break;
                        default:
                            // Invalid material type, add the line to the list of invalid inputs
                            bin.addInvalidInput(line);
                            break;
                    }
                } catch (NumberFormatException e) {
                    // Invalid quantity format, add the line to the list of invalid inputs
                    bin.addInvalidInput(line);

                }
            }
            // Close the Scanner object to release the file resources
            scanner.close();

        } catch (IOException e) {
            // Print the stack trace if an IOException occurs
            e.printStackTrace();
        }
    }

    public static void generateOutputFile(String outputFileName, RecyclingBin bin) {
        try {
            // Create a FileWriter object with the specified output file name
            FileWriter writer = new FileWriter(outputFileName);
            // Write the total recycled quantity to the output file
            writer.write("Total recycled: " + bin.calculateTotalRecycled() + "\n");

            // Calculate the percentage of each material recycled
            Map<String, Double> percentageMap = bin.calculatePercentageRecycled();
            // Create a DecimalFormat object for formatting decimal numbers
            DecimalFormat df = new DecimalFormat("#.00");

            for (Map.Entry<String, Double> entry : percentageMap.entrySet()) {
                // Get the material from the entry
                String material = entry.getKey();
                // Get the percentage from the entry
                double percentage = entry.getValue();

                // Format the percentage as a string with two decimal places
                String formattedPercentage = df.format(percentage);

                // Write the material and its percentage to the output file
                writer.write(material + " percentage: " + formattedPercentage + "%\n");
            }

            for (String invalidInput : bin.getInvalidInputs()) {
                // Write each invalid input line to the output file
                writer.write("Invalid input: " + invalidInput + "\n");
            }

            // Close the FileWriter object to save changes to the output file
            writer.close();
        } catch (IOException e) {
            // Print the stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}

class RecyclingBin {
    // Total quantity of paper recycled
    private int totalPaper;
    // Total quantity of plastic recycled
    private int totalPlastic;
    // Total quantity of glass recycled
    private int totalGlass;
    // Total quantity of metal recycled
    private int totalMetal;
    // List to store invalid input lines
    private List<String> invalidInputs;

    public RecyclingBin() {
        totalPaper = 0;
        totalPlastic = 0;
        totalGlass = 0;
        totalMetal = 0;
        // Create an empty ArrayList to store invalid input lines
        invalidInputs = new ArrayList<>();
    }

    public void addPaper(int quantity) {
        // Add the given quantity to the total paper count
        totalPaper += quantity;
    }

    public void addPlastic(int quantity) {
        // Add the given quantity to the total plastic count
        totalPlastic += quantity;
    }

    public void addGlass(int quantity) {
        // Add the given quantity to the total glass count
        totalGlass += quantity;
    }

    public void addMetal(int quantity) {
        // Add the given quantity to the total metal count
        totalMetal += quantity;
    }

    public int calculateTotalRecycled() {
        // Calculate the total quantity of all materials recycled
        return totalPaper + totalPlastic + totalGlass + totalMetal;

    }

    public Map<String, Double> calculatePercentageRecycled() {
        // Calculate the total quantity of all materials recycled
        int totalRecycled = calculateTotalRecycled();

        // Create a HashMap to store the material and its percentage
        Map<String, Double> percentageMap = new HashMap<>();

        if (totalRecycled > 0) {
            // Calculate and add the percentage of paper recycled
            percentageMap.put("paper", (totalPaper * 100.0) / totalRecycled);
            // Calculate and add the percentage of plastic recycled
            percentageMap.put("plastic", (totalPlastic * 100.0) / totalRecycled);
            // Calculate and add the percentage of glass recycled
            percentageMap.put("glass", (totalGlass * 100.0) / totalRecycled);
            // Calculate and add the percentage of metal recycled
            percentageMap.put("metal", (totalMetal * 100.0) / totalRecycled);
        }
        // Return the map containing material and its percentage
        return percentageMap;

    }

    public void addInvalidInput(String input) {
        // Add the given input line to the list of invalid inputs
        invalidInputs.add(input);

    }

    public List<String> getInvalidInputs() {
        // Return the list of invalid input lines
        return invalidInputs;

    }
}
