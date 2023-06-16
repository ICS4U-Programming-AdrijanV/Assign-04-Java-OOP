import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";

        RecyclingBin bin = new RecyclingBin();

        // Update the recycling bin
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
                            bin.addPaper(quantity);
                            break;
                        case "plastic":
                            bin.addPlastic(quantity);
                            break;
                        case "glass":
                            bin.addGlass(quantity);
                            break;
                        case "metal":
                            bin.addMetal(quantity);
                            break;
                        default:
                            bin.addInvalidInput(line);
                            break;
                    }
                } catch (NumberFormatException e) {
                    // Invalid quantity format, add the line to the list of invalid inputs
                    bin.addInvalidInput(line);
                }
            }

            // Close the scanner
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while processing the input file: " + e.getMessage());
        }
    }

    public static void generateOutputFile(String outputFileName, RecyclingBin bin) {
        try {
            // Create a FileWriter object with the specified output file name
            FileWriter writer = new FileWriter(outputFileName);

            // Calculate the total recycled quantity
            int totalRecycled = bin.calculateTotalRecycled();
            // Calculate the percentage of each material recycled
            Map<String, Double> percentageMap = bin.calculatePercentageRecycled();

            // Write the total recycled quantity to the output file
            writer.write("Total Recycled: " + totalRecycled + " items\n");
            writer.write("\n");

            // Write the percentage of each material recycled to the output file
            DecimalFormat df = new DecimalFormat("0.00");
            writer.write("Percentage Recycled:\n");
            for (Map.Entry<String, Double> entry : percentageMap.entrySet()) {
                String material = entry.getKey();
                double percentage = entry.getValue();
                writer.write(material + ": " + df.format(percentage) + "%\n");
            }
            writer.write("\n");

            // Write the invalid inputs to the output file
            List<String> invalidInputs = bin.getInvalidInputs();
            writer.write("Invalid Inputs:\n");
            for (String invalidInput : invalidInputs) {
                writer.write(invalidInput + "\n");
            }

            // Close the writer
            writer.close();

            System.out.println("Output file generated successfully: " + outputFileName);
        } catch (IOException e) {
            System.out.println("An error occurred while generating the output file: " + e.getMessage());
        }
    }
}
