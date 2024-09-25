package proyect_1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main class for processing sales data and generating a report.
 */
public class Main {

	public static void main(String[] args) {
		  // Initialize GenerateInfoFiles to retrieve product data
		GenerateInfoFiles data = new GenerateInfoFiles();
		
		 // Retrieve product names, prices, and IDs from GenerateInfoFiles
		 String[] nameProductos = data.nameProducts;
		 int [] prices = data.priceProducts;
		 int [] idsProducts = data.idProducts;
		 
		 // Specify the folder containing sales files
	        File folder = new File("inputFiles");
	        File[] files = folder.listFiles();

	        // Map to store total sales by seller
	        Map<String, Integer> salesBySeller = new HashMap<>();

	     // Process each file in the specified directory
	        if (files != null) {
	            for (File file : files) {
	            	// Filter files that end with "_sales.txt"
	                if (file.isFile() && file.getName().endsWith("_sales.txt")) {
	                	 // Extract seller's name from the file name
	                    String nameSeller = file.getName().replace("_sales.txt", "");
	                    try {
	                    	// Calculate total sales from the sales file
	                        int totalSales = (int) processSalesFiles(file, idsProducts, prices);
	                     // Store total sales in the map with seller's name as the key
	                        salesBySeller.put(nameSeller, totalSales);
	                    } catch (IOException e) {
	                        System.out.println("Error processing sales file: " + file.getName());
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }

	        // Sort sellers by total sales in descending order
	        List<Map.Entry<String, Integer>> sortedList = salesBySeller.entrySet()
	                .stream()
	                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	                .collect(Collectors.toList());

	        // Write the sales report to a text file
	        try (PrintWriter writer = new PrintWriter(new File("Sales_report.txt"))) {
	            for (Map.Entry<String, Integer> entry : sortedList) {
	            	  // Write each seller's name and total sales separated by a ";"
	                writer.println(entry.getKey() + ";" + entry.getValue());
	            }
	        } catch (IOException e) {
	            System.out.println("Error writing CSV file");
	            e.printStackTrace();
	        }

	        System.out.println("CSV file generated successfully.");
	    }
	
	 
	//Start number 3 of what was requested for delivery
	
	/**
	 * Method to process each sales file and calculate the total sales amount.
	 *
	 * This method reads the sales data from a specified file, extracts the product IDs and quantities sold, 
	 * and computes the total sales amount based on the prices of the products.
	 *
	 * @param file The sales file to be processed.
	 * @param idsProductos An array containing all product IDs for reference.
	 * @param prices An array containing the prices corresponding to each product ID.
	 * @return The total sales amount calculated from the sales data in the file.
	 * @throws IOException If an error occurs while reading the file.
	 */
	 // Method to process each sales file and calculate the total sales
    private static int processSalesFiles(File file, int[] idsProductos, int[] prices) throws IOException {
        int total = 0;
        List<String> lines = Files.readAllLines(file.toPath());

        // Process lines starting from the second (where products and quantities are listed)
        for (int i = 1; i < lines.size(); i++) {
        	String[] parts = lines.get(i).split(";");  // Split the product ID and quantity
            if (parts.length == 2) {
                int idProducts = Integer.parseInt(parts[0]);
                int quantitySold = Integer.parseInt(parts[1]);

                // Find the index of the product based on its ID
                int index = searchIndexById(idProducts, idsProductos);

                if (index != -1) {
                                                        
                    int subtotal = prices[index] * quantitySold;  // Calculate sub-total
                    total += subtotal;  // Add sub-total to total
                    
                  }
            }
        }
        return total;
    }
    
    /**
     * Method to find the index of a product based on its ID.
     *
     * @param idProduct The ID of the product to search for.
     * @param idsProducts An array containing all product IDs.
     * @return The index of the product if found; otherwise, returns -1.
     */
    private static int searchIndexById(int idProduct, int[] idsProducts) {
    	// Iterate through the array of product IDs
        for (int i = 0; i < idsProducts.length; i++) {
        	// Check if the current product ID matches the given ID
            if (idsProducts[i] == idProduct) {
                return i;  // If a match is found, return the index of the product
            }
        }
        return -1;  // If no match is found, return -1 to indicate the product ID does not exist
    }
  //Completion number 3 of what was requested for delivery
}