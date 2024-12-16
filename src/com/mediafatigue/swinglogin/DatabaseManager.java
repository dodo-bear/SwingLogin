package com.mediafatigue.swinglogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class DatabaseManager {
	
	private static final String DELIMITER = ",";

    public static void writeToFile(String[][] data, String fileName) throws IOException {
    	
    	PrintWriter clearer = new PrintWriter(new File(fileName));
    	clearer.print("");
    	clearer.close();
    	
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String[] row : data) {
                if (row.length != 2) {
                    throw new IOException("Invalid column count for entry: " + row.length);
                }
                writer.write(String.join(DELIMITER, row));
                writer.newLine();
            }
        }
    }
	
    public static String[][] readFromFile(String fileName) throws IOException {
        BootlegArrayList rows = new BootlegArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(DELIMITER);
                if (row.length != 2) {
                    throw new IOException("Invalid column count found: " + row.length);
                }
                rows.add(row);
            }
        }
        return BootlegArrayList.castToStringArray(rows.toArray());
    }
    
    public static int generateStackHash(String input) {
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            hash += input.charAt(i) * (i + 1);
        }
        return hash;
    }
}
