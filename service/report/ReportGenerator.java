package com.metaphorce.inventorymanager.service.report;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.service.inventory.InventoryServiceImpl;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportGenerator {
    
    private InventoryServiceImpl inventoryService;
    
    /**
     * Constructs a ReportGenerator with the provided InventoryServiceImpl instance.
     *
     * @param inventoryService The InventoryServiceImpl instance to be used for data
     *                         retrieval.
     */
    public ReportGenerator(InventoryServiceImpl inventoryService){
        this.inventoryService = inventoryService;
    }
    
    /**
     * Generates a report in CSV format and writes it to a file.
     * The report contains data on ingredients and their quantities in the
     * inventory.
     */
    public void generateReport(){
        ArrayList data = getData();
        String path = "src\\main\\java\\com\\metaphorce\\inventorymanager\\service\\report\\report.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            writeRow(writer);
            for (int i = 0; i < data.size(); i++) {
                Ingredient row = (Ingredient)data.get(i);
                writeRow(writer, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Writes a row of ingredient data to the CSV report file.
     *
     * @param writer The BufferedWriter to write to the file.
     * @param row    The Ingredient object representing a row of data.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeRow(BufferedWriter writer, Ingredient row) throws IOException {
        writer.write(row.getIngredientName() + "," + row.getIngredientQuantity());
        writer.newLine(); 
    }
    
    /**
     * Writes the header row to the CSV report file.
     *
     * @param writer The BufferedWriter to write to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeRow(BufferedWriter writer) throws IOException {
        writer.write("Gorditas S.A Report");
        writer.newLine(); 
    }
    
    /**
     * Retrieves all ingredient data from the inventory.
     *
     * @return An ArrayList of all ingredients in the inventory.
     */
    public ArrayList getData(){
        ArrayList data = this.inventoryService.getAllIngredients();
        return data;
    }
}
