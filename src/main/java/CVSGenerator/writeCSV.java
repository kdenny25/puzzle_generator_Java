/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CVSGenerator;

import java.io.*;
import java.util.*;
import com.opencsv.CSVWriter;
/**
 *
 * @author kdenn
 */
public class writeCSV {
    private static final String CSV_FILE_PATH
        = "./result.csv";
    public static void main(String[] args)
    {
        writeDataAtOnce(CSV_FILE_PATH);
    }
    
    
    public static void writeDataAtOnce(String filePath)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "Name", "Class", "Marks" });
            data.add(new String[] { "Aman", "10", "620" });
            data.add(new String[] { "Suraj", "10", "630" });
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void addDataToCSV(String output)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(output);
        Scanner sc = new Scanner(System.in);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
  
            // create CSVWriter with ';' as separator
            CSVWriter writer = new CSVWriter(outputfile, ';',
                                             CSVWriter.NO_QUOTE_CHARACTER,
                                             CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                             CSVWriter.DEFAULT_LINE_END);
  
            // create a List which contains Data
            List<String[]> data = new ArrayList<String[]>();
  
            System.out.println("Enter no of rows");
            int noOfRow = Integer.parseInt(sc.nextLine());
            System.out.println("Enter Data");
            for (int i = 0; i < noOfRow; i++) {
                String row = sc.nextLine();
                String[] rowdata = row.split(" ");
                data.add(rowdata);
            }
  
            writer.writeAll(data);
  
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
