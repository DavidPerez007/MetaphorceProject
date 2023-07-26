package com.metaphorce.inventorymanager.service.report;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.service.inventory.InventoryServiceImpl;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportGenerator {
    
    private InventoryServiceImpl inventoryService;
    
    public ReportGenerator(InventoryServiceImpl inventoryService){
        this.inventoryService = inventoryService;
    }
    
    
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
    public void writeRow(BufferedWriter writer, Ingredient row) throws IOException {
        writer.write(row.getIngredientName() + "," + row.getIngredientQuantity());
        writer.newLine(); 
    }
    
    public void writeRow(BufferedWriter writer) throws IOException {
        writer.write("Gorditas S.A Report");
        writer.newLine(); 
    }
    public ArrayList getData(){
        ArrayList data = this.inventoryService.getAllIngredients();
        return data;
    }
}
