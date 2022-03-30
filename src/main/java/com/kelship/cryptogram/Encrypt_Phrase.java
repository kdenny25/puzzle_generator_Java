/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelship.cryptogram;

import java.util.Random;
import java.util.List;
import java.io.*;
import java.lang.StringBuilder;
import javax.swing.*;
import com.opencsv.*;
/**
 *
 * @author kdenn
 * All classes are intended to manage one phrase at a time. The main class will increment
 * through each phrase and manage the list of phrases.
 * 
 * 
 * NOTE: Include handling for punctuation
 */
public class Encrypt_Phrase {
    

    
    public static void main(String args[]){
        
        char alphabet[]  = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
        'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
        'W', 'X', 'Y', 'Z' };
        
        String toEncrypt = "This is a string \"to\" encrypt by Kelsea Denny.";
        toEncrypt = toEncrypt.toUpperCase();
        
        // creates an object that can be iterated thorugh
        StringBuilder encryptedStr = new StringBuilder(toEncrypt);
        
        // Generates an encryption of the alphabet
        char[] encAlpha = encryptAlpha(12, alphabet);
        
        String CSV_FILE_PATH  = "./1950_News.csv";
        String SAVE_TO = "./1950_News2.csv";
        
        String[][] testList = readData(CSV_FILE_PATH);
        
        JProgressBar progressBar = new JProgressBar(0, testList.length * 2);
        
        genCryptograms(testList, 2, progressBar, SAVE_TO);
//        // prints out the encrypted alphabet
//        for(int i = 0; i < encAlpha.length; i++){
//            System.out.print(encAlpha[i] + " ");
//        }
//        
//        // print line
//        System.out.println("");
//        
//        // prints out  the alphabet
//        for(int i = 0; i < encAlpha.length; i++){
//            System.out.print(alphabet[i] + " ");
//        }
//        
//        System.out.println(" ");
//        
//        
//        for(int j = 0; j < toEncrypt.length(); j++){
//            boolean letterFound = false;
//            System.out.print(toEncrypt.charAt(j));
//            
//            
//            // if the character is not blank then check character and add encrypted
//            // character
//            // NOTE: It may use fewer resources to find a letter in the phrase and replace
//            //       all of them rather than iterate through each letter.
//            // NOTE: Add option to detect special characters          
//            if(toEncrypt.charAt(j) != ' ' && !isSpecialChar(toEncrypt.charAt(j))){
//                int l = 0;
//                while(!letterFound){
//                    if(alphabet[l] == toEncrypt.charAt(j)){
//                        letterFound = true;
//                        encryptedStr.setCharAt(j, encAlpha[l]);
//                    }
//                    l++;
//                }
//            }
//            
//        }    
//        System.out.println(" ");
//        System.out.println(encryptedStr);
//        
//        testMostLetters(toEncrypt, alphabet, encAlpha);
    }
    
    
    /*
     * Takes an matrix of Strings and encrypts them
     * Develope other instances of the class to accept multiple column encryption
    */
    public static  void genCryptograms(String [][] toEncrypt, int hints, JProgressBar progressBar, String filePath){
        
        char alphabet[]  = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
        'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
        'W', 'X', 'Y', 'Z' };
        
        String tempPhrase;
        
        int progress = progressBar.getValue()+1;
        
        //Declare new matrix with all columns
        String [][] newList = new String[toEncrypt.length][toEncrypt[0].length + hints + 1];
        
        // Add original matrix to the new one.
        for(int x = 0; x < toEncrypt.length; x++){
            for(int y = 0; y < toEncrypt[0].length; y++){
                newList[x][y] = toEncrypt[x][y];
                System.out.println(toEncrypt[x][y]);
            }
        }
        
        // creates an object that can be iterated through
        StringBuilder encryptedStr;
        
        for(int i = 0; i < toEncrypt.length; i++){
            //Set phrase to uppercase
            tempPhrase = toEncrypt[i][0].toUpperCase();
            
            // instantiate String that can be iterated through
            encryptedStr = new StringBuilder(tempPhrase);
            
            //Generate encrypted alphabet
            char[] encAlpha = encryptAlpha(12, alphabet);
            
            for(int j = 0; j < tempPhrase.length(); j++){
                boolean letterFound = false;
                System.out.print(tempPhrase.charAt(j));


                // if the character is not blank then check character and add encrypted
                // character
                // NOTE: It may use fewer resources to find a letter in the phrase and replace
                //       all of them rather than iterate through each letter.
                // NOTE: Add option to detect special characters          
                if(tempPhrase.charAt(j) != ' ' && !isSpecialChar(tempPhrase.charAt(j))){
                    int l = 0;
                    while(!letterFound){
                        if(alphabet[l] == tempPhrase.charAt(j)){
                            letterFound = true;
                            encryptedStr.setCharAt(j, encAlpha[l]);
                        }
                        l++;
                    }
                }                
            }
            System.out.println();
            // add encrypted string to list
            newList[i][toEncrypt[0].length] = encryptedStr.toString();
            
            int[] loc = returnMostLetter(tempPhrase, alphabet);
        
            for(int k = 0; k < hints; k++){
                newList[i][toEncrypt[0].length + 1 + k] = (encAlpha[loc[k]] + " -> " + alphabet[loc[k]]);
            }
            progressBar.setValue(progress++);
        }
        addToCSV(newList, filePath, progressBar);

    }
    /*
     * Generates an array containing the array location of the most used characters
     * can be used to generate hints by pulling the letters from the encrypted list
     * and the non-encrypted list
    */
    
    // Returns an array with the array placement of most used characters
    public static int[] returnMostLetter (String s, char[] alphabet) {
        // Create int array to count letters. Same placement per letter
        int[] letterCount = new int[alphabet.length];
        int countUnique = 0;
       
        //Iterate through Char in string and generates list of letter counts
        for(int i = 0; i < s.length(); i++){
            // find Char placement in alphabet and add to counter
            for(int placement = 0; placement < alphabet.length; placement++){
                if(alphabet[placement] == s.charAt(i)){
                    letterCount[placement]++;
                } 
            }
        }
        
        // Setting up a size value for alphaLoc
        for(int i = 0; i < alphabet.length; i++){
            if (letterCount[i] > 0){
                countUnique++;
            }
        }
        
        // Array with size equal to number of Unique Characters in string
        // will hold the array location of most used letters
        int[] alphaLoc = new int[countUnique];
        
        int adjustLoc = 0;
                
        // Stuffs alphaLoc with all array address of all unique characters
        for(int k = adjustLoc; k < alphabet.length; k++){
            if(letterCount[k] != 0){
                alphaLoc[adjustLoc] = k;
                adjustLoc++;
            }
        }
        
        // Sort alphaLoc from most used to least used characters in string
        for(int j = 0; j < alphaLoc.length; j++){
            for( int k = 0; k < alphaLoc.length; k++){
                if (letterCount[alphaLoc[j]] > letterCount[alphaLoc[k]]){
                    int tempLoc = alphaLoc[j];
                    alphaLoc[j] = alphaLoc[k];
                    alphaLoc[k] = tempLoc;            
                }
            }
        }
        return alphaLoc;
    }
    
    private static void testMostLetters (String s, char[] alphabet, char[] encAlpha){
        int[] loc = returnMostLetter(s, alphabet);
        
        for(int i = 0; i < loc.length; i++){
            System.out.println(encAlpha[loc[i]] + " -> " + alphabet[loc[i]]);
        }
    }
 
    /*
     * Generate Encrypted Alphabet
    */
    private static char[] encryptAlpha (int phraseLength, char[] alphabet){
             
        //track numbers already used
        int[] intArray = new int[26];
        
        char[] encAlph = new char[26];
        
        //instance of random class
        Random rand = new Random();
        int upperbound = 26;
        int int_random = 0;
             
        // assigns random numbers in an array. This is the place of the new encrypted
        // alphabet
        for(int j = 0; j < 26; j++){
            
            boolean isUsed = true;
            
            // checks if generated digit had already been used.
            while(isUsed){
                // generate a random number for length of phrase
                for(int i = 0; i < phraseLength; i++){
                    int_random = rand.nextInt(upperbound);
                }
                
                isUsed = false;
                
                // checks if any numbers in the array are equal to random number
                for(int k = 0; k < j; k++){
                    if(intArray[k] == int_random){
                        isUsed = true;
                    }
                    
                    if(j == int_random){
                        isUsed = true;
                    }
                }
                // if the generated number is the same as the placed number keep going
            }
            intArray[j] = int_random;            
        }    
        
        //generate encrypted alphabet
        for(int l = 0; l < encAlph.length; l++){
            encAlph[l] = alphabet[intArray[l]];
        }
        return encAlph;
        
    }

    
    /*
     * check for special characters
    */
    public static boolean isSpecialChar (char charToCheck){
        String specialChar = "!@#$%&*()'+,-./:;<=>?\"[]^_`{|}1234567890?";
        
        return specialChar.contains(Character.toString(charToCheck));
    }
    /*
     * Get Phrase list
    */
    public static String[][] readData(String file){
        
        try{
            // Create an object of filereader
            // class with CSV file as parameter
            FileReader filereader = new FileReader(file);
            
            // create csvReader object passing
            // file reader as a paremeter
            CSVReader csvReader = new CSVReader(filereader);
            List<String[]> allData = csvReader.readAll();
            String[] nextRecord;
            String[][] returnData = new String[allData.size()][];
            
            returnData = allData.toArray(returnData);
            
            return returnData;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void addToCSV(String[][] a, String CSV_FILE_PATH, JProgressBar progressBar){
        // create file object for file placed at filepath
        File file = new File(CSV_FILE_PATH);
        int progress = progressBar.getValue()+1;
        
        int size = a.length;
        
        try{
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            
            // create CSVWriter object with filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
                    
            for (int row = 0; row < size; row++) {
                writer.writeNext(a[row]);
                progressBar.setValue(progress++);
            }
            writer.close();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}

