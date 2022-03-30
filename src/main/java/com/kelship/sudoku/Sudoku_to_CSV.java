/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelship.sudoku;

import java.io.*;
import java.util.*;
import com.opencsv.CSVWriter;
import java.util.Random;

/**
 *
 * @author kdenn
 */
public class Sudoku_to_CSV {
    
    private static final String CSV_FILE_PATH  = "./Sudoku_Puzzle.csv";
    
    public static void main(String[] args){
        
        //sudokuToCsv(43, 2);
        
        //String[][] puzzles = getMultPuzzles(2, 42,9);
        
        addToCSV(genMultPuzzles(12, difficulty("Moderate"),9));
        
        //generate an array of sudoku puzzle
    }
    
    public static void addToCSV(String[][] a){
        // create file object for file placed at filepath
        File file = new File(CSV_FILE_PATH);
        
        int size = a.length;
        
        try{
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            
            // create CSVWriter object with filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
                    
            for (int row = 0; row < size; row++) {
                writer.writeNext(a[row]);
            }
            writer.close();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static String[][] genMultPuzzles(int numPuz, int difficulty, int size){
        
        int puzSize = (size+1)*numPuz;
        int puzWidth = (size*2)+1;
        
        String[][] puzzles = new String[puzSize][puzWidth];
        
        for(int puzzle = 0; puzzle < numPuz; puzzle++){
            String[][] tempPuz = sudokuMatrix(difficulty);
            
            for(int row=0; row < size+1; row++){
                for(int column = 0; column < puzWidth; column++){
                    puzzles[row+((size+1)*puzzle)][column] = tempPuz[row][column];
                }
            }
        }
        
        
        return puzzles;
    }
    
    public static String[][] sudokuMatrix(int difficulty){
        
        // generate sudoku puzzle
        Generator generator = new Generator();
            
        int size = 9;

        // create a list containing Int array
        String[][] rawGrid = new String[size+1][size*2+1];

        Grid grid = generator.generate(difficulty);
        // this for loop generates the puzzle
        for(int row = 0; row < size; row++){
            // add 1 to size for outline
            for(int column = 0; column < size; column++){
                //Cell cell = grid.getCell(row, column);
                int cell = grid.getCell(row, column).getValue();
                if(cell != 0){
                    rawGrid[row][column] = String.valueOf(cell);
                    //System.out.println(String.valueOf(cell));
                }else{
                    rawGrid[row][column] = "";
                }
            }
        }
            
        // Generate solved grid
        Solver solver = new Solver();
        solver.solve(grid);            

        // This for loop generates the solved grid
        for(int srow = 0; srow < size; srow++){
            rawGrid[srow][size] = "H";
            // add 1 to size for outline
            for(int column = 0; column < size; column++){
                //Cell cell = grid.getCell(row, column);
                int cell = grid.getCell(srow, column).getValue();
                if(cell != 0){
                    rawGrid[srow][column+size+1] = String.valueOf(cell);
                    //System.out.println(String.valueOf(cell));
                }else{
                    rawGrid[srow][column+size+1] = "";
                }
            }
        }

        for (int column = 0; column < size*2+1; column++) {
            rawGrid[size][column] = "H";
        }
        
        return rawGrid;
    }
    
       
    // converts the string variable of difficulty to a numeric value that indicates
    // the number of missing values in the puzzle.
    private static int difficulty(String difficulty){
         /*
        *   Difficulty set as follows
        *   Easy = 31 - 44
        *   Moderate = 45 - 49
        *   Hard = 49 - 54
        *   Very Hard = 55 - 61
        */
        
        switch (difficulty) {
            case "Easy":  
                return (int)(Math.random() * (44 - 31 + 1) + 31);
            case "Moderate":  
                return (int)(Math.random() * (49 - 45 + 1) + 45);
            case "Hard":  
                return (int)(Math.random() * (54 - 49 + 1) + 49);
            case "Very Hard":  
                return (int)(Math.random() * (61 - 55 + 1) + 55);
            default: 
                return 31;
        }
    }
}
