package com.mediafatigue.swinglogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

/**
 * A storage class for file management methods and the included hash function.
 */
public class DatabaseManager {
	
	private static final String DELIMITER = "\u1242"; //If someone's username uses this, pray.

    /**
     * Turns a 2D grid stored as a <code>BootlegArrayList</code> into delimiter-separated rows in a text file.
     * @param data The grid to be converted.
     * @param fileName The path to write the file to.
     * @throws IOException If the file is not a text file.
     */
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
	
    /**
     * Takes a text file and organizes the contents into an n*2 grid based on line breaks and a specified delimiter character.
     * @param fileName The path to the file to be read.
     * @return The contents of the file, organized into a 2D array.
     * @throws IOException If the wrong number of datapoints are found in a column.
     */
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
    
    /**
     * Hashes a given string using a custom hash algorithm.
     * @param input The string to be hashed.
     * @return The output of the hash function as a single integer.
     */
    public static int generateStackHash(String input) {
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            hash += input.charAt(i) * (i + 1);
        }
        return hash;
    }
}
