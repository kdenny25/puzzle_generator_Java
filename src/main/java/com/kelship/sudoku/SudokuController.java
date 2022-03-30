/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelship.sudoku;

import java.io.*;
import java.util.*;
import java.text.*;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 *
 * @author kdenn
 */
@Controller
public class SudokuController {
    
@RequestMapping("/")
    public String display(HttpServletRequest req, Model m)
    {
        /*
        *   Difficulty set as follows
        *   Easy = 31 - 44
        *   Moderate = 45 - 49
        *   Hard = 49 - 54
        *   Very Hard = 55 - 61
        */

        //read the provided form data
        String difficulty = req.getParameter("difficulty");
        String dimension = req.getParameter("dimension");
        
        int dif = Integer.parseInt(difficulty);
        
        Generator generator = new Generator();       
        Grid grid = generator.generate(dif);
        
        int size = grid.getSize();
            
        // create a list containing Int array
        String[] rawGrid = new String[size];

        for(int row = 0; row < size; row++){
            // add 1 to size for outline
            for(int column = 0; column < size; column++){
                //Cell cell = grid.getCell(row, column);
                int cell = grid.getCell(row, column).getValue();
                if(cell != 0){
                    rawGrid[column] = String.valueOf(cell);
                    //System.out.println(String.valueOf(cell));
                }else{
                    rawGrid[column] = "";
                }
            }
            m.addAttribute("grid", rawGrid);
        }
        return "index";
    }
    public String getCellVal(int row, int col){
        return "index";
    }
    
    
    
}
