package proyect_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * The GenerateInfoFiles class generates files with information related to sellers, products, and sales.
 * 
 * <p>This class provides methods to create files containing information about sellers, products
 * and sales, using randomly generated data.</p>
 * 
 * @version 1.0
 */

public class GenerateInfoFiles {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 GenerateInfoFiles generator = new GenerateInfoFiles();
		 
		 		generator.createSalesManInfoFile(6);  // The number of sellers you want to create is passed as a parameter.
	            generator.createProductsFile(9); //You must select a parameter between 1 and 13
	            generator.createSalesMenFile(4, "inputFiles/salesmanInfo.txt");  //Generate sales file for Lucia Rodriguez
	    }	

	String[] name = {"Juan", "Pedro", "Luis", "Carlos", "Ana", "Maria", "Lucia", "Edwin", "Antonio", "Guillermo", "Milena", "Monica", "David"};
    String[] lastName = {"Perez", "Gomez", "Rodriguez", "Garcia", "Lopez", "Navas", "Jimenez", "Elizondo", "Montoya", "Vargas", "Higuera"};
    String[] nameProducts = {"Televisor 50 pulg", "Teatro en casa sony", "Licuadora Oster", "Cafetera Oster", "Air Friyer Ninja", "Lavadora Samsung", "Horno Micro ondas", "Secador De Pelo", "Plancha Para Cabello", "Extractor De Jugos", "Cafetera Samurai", "Televisor 32 pulg", "Taladro"};
	int [] idProducts = {10120, 10234, 10134, 10123, 103453, 39483, 27364, 938475, 12738, 93847, 17263, 948556, 123533};
	int [] priceProducts = {1300000, 1460000, 230000, 657000, 1400000, 280000, 300000, 950000, 1150000, 734000, 560000, 900000, 1100000}; // Set of arrangements where all the required data will be stored.
 
	    
//Literal A requested in the project
	/**
	 * Generates a sales file for a specific salesperson.
	 * 
	 * <p>This method creates a sales file in the "inputFiles" folder with the name and sales
	 * from the specified seller, selecting random products.</p>
	 *
	 * @param name The name of the seller.
	 * @param id The seller's identification number.
	 * @throws IOException If case a file reading error occurs
	 */	
	public void createSalesMenFile(int randomSalesCount, String filepath) throws IOException {
		
		 try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
			 String line;
			 
			 while ((line = reader.readLine()) != null) {
				 String[] data = line.split("; ");
				 if (data.length == 4) { // Make sure the line has all the expected information
	                    String id = data[1].trim();
	                    String firstName = data[2].trim(); // Get the seller's name
	                    String lastName = data[3].trim();  // Get the seller's last name
	                    
	                    String fullName = firstName + " " + lastName; // Combine first and last name correctly
	                    
	                    // Generate sales file for each seller
	                    generateSalesFileForSalesman(randomSalesCount, fullName, Integer.parseInt(id));
	                }
	            }
				 
				 
				 
			 }catch (IOException e) {
		         System.out.println("An error occurred while generating the sales file: " + e.getMessage()); // Print an error message if an exception occurs while generating the file
		     }
	}
	/**
    * Generate the sales file for each specific seller.
    * 
    * @param randomSalesCount Number of sales.
    * @param name Seller name.
    * @param id ID of the seller.
    * @throws IOException In case of writing error.
    */
private void generateSalesFileForSalesman(int randomSalesCount, String fullName, int id) {
		// TODO Auto-generated method stub
	 	Random random = new Random();
	 	
	 	try (BufferedWriter writer = new BufferedWriter(new FileWriter("inputFiles/" + fullName + "_sales.txt"))) {
           writer.write("CC;" + id + "\n"); // Write the header with the seller identification
           
           for (int i = 0; i < randomSalesCount; i++) {
               int randomIndex = random.nextInt(nameProducts.length); // Random product
               int selectedProductId = idProducts[randomIndex];
               int quantitySold = random.nextInt(5) + 1; // Random quantity sold between 1 and 5

               writer.write(selectedProductId + ";" + quantitySold + "\n");
           }
           System.out.println("Sales file generated for " + fullName);
           
	}catch (IOException e) {
       System.out.println("Error generating file: " + e.getMessage());
   }
}
//Literal completion A requested in the project
	
//Literal B requested in the project	
		/**
		 * The internal class Product represents a product with name, ID and price.
		 */
		static class Product {
			private String name;
			private int id;
			private int price; //Set of attributes that stores the product data
			
		/**
	    * Constructor that initializes a product with the specified name, ID, and price.
	    * 
	    * @param name The name of the product.
	    * @param id The product ID.
	    * @param price The price of the product.
	    */	
        public Product(String name, int id, int price) { // Constructor that initializes the name, id and price attributes
            this.name = name;
            this.id = id;
            this.price = price; 
        }
        
        /**
         * Returns the name of the product.
         * 
         * @return The name of the product.
         */
        public String getName() { // Method to get the product name
            return name; 
        }
        
        /**
         * Returns the product ID.
         * 
         * @return The product ID.
         */
        public int getId() { // Method to get the product Id
            return id;
        }
        
        /**
         * Returns the price of the product.
         * 
         * @return The price of the product.
         */
        public int getPrice() { // Method to get the product price
            return price;
        }

        /**
         * Returns a string representation of the product.
         * 
         * @return A string with the ID, name and price of the product.
         */
        @Override
        public String toString() { // Overrides the toString method to represent the product in string format
            return id + "; " + name + "; $ " + price;
        }
	}
	
	 /**
	 * Generates a product information file.
	 * 
	 * <p>This method generates a text file containing the products information,
	 * including ID, name and price of each product.</p>
	 *
	 * @param productCounts The number of products you want to generate.
	 * @throws IOException If an error occurs while writing the file.
	 */	
	public void createProductsFile(int productCounts) {
		
		
		 Product[] products = new Product[productCounts];// Create an array of Product objects
		 
		 Random random = new Random();
		
        for (int i = 0; i < productCounts; i++) { // Initialize each Product object in the array with the corresponding names, IDs and prices
        	int randomIndex = random.nextInt(nameProducts.length);
            products[i] = new Product(nameProducts[randomIndex], idProducts[randomIndex], priceProducts[randomIndex]);
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inputFiles/productsInfo.txt"))) { // Try to open a BufferedWriter to write the information to a file
           
        	writer.write("ID del producto; "+ "Nombre del producto; "+"Precio del producto"+"\n"); // Write the header to the file (Product ID, Product Name, Product Price)
        	for (Product product : products) { // Go through the array of products and write the information of each one to the file
                writer.write(product.toString()); // Convert the Product object to a string and write to the file
                writer.newLine(); // Add a new line after each product
            }
            System.out.println("Product file generated successfully.");
        } catch (IOException e) {// Handle possible exceptions that may occur when writing the file
            System.out.println("An error occurred while generating the file: " + e.getMessage()); // Print an error message in case an input/output exception occurs
        }
	}
//Literal completion B requested in the project	
	
//Literal C requested in the project
	
	/**
     * Generates a seller information file.
     * 
     * <p>This method generates a text file with random seller information,
     * including document type, document number, first and last name.</p>
     *
     * @param salesmanCount The number of sellers you want to generate.
     * @throws IOException If an error occurs while writing the file.
     */
	public void createSalesManInfoFile (int salesmanCount) throws IOException{
	
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("inputFiles/salesManInfo.txt"))) { // Try to open a BufferedWriter to write the information to a file
	        Random random = new Random();
	    
	        for (int i = 0; i < salesmanCount; i++) { // Loop to generate the number of salespeople specified by salesmanCount
	            String documentType = "CC"; 
	            long numberDocument = Math.abs(random.nextLong() % 100000000);  // Random document number
	            String nameLocal = name[random.nextInt(name.length)];  // Random name
	            String lastNameLocal = lastName[random.nextInt(lastName.length)];  // Random last name
	                
	            writer.write(documentType + "; " + numberDocument + "; " + nameLocal + "; " + lastNameLocal);// Write the line to the file
	            writer.newLine();  // To add a new line
	        }
	     
	        System.out.println("Successfully generated seller file."); // confirmation message
	    } catch (IOException e) {
	        System.out.println("An error occurred while generating the file: " + e.getMessage());
	    }
	}
//C literal completion requested in the project
	
}	