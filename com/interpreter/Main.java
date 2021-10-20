/**
 * The interpreter class is the main class for the interpreter.
 * 
 * @author Toby Godfrey
 * @version 1.0
 */

package com.interpreter;

import java.awt.FileDialog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.xml.catalog.Catalog;

public class Main {
    /**
     * The main method is the entry point for the application.
     * @param args These are the command line arguments.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        FileDialog fileDialog = new FileDialog(frame, "Choose a BareBones file", FileDialog.LOAD);
        fileDialog.setDirectory("./");
        fileDialog.setFile("*.bb");
        fileDialog.setVisible(true);
        String filename = fileDialog.getFile();
        String location = fileDialog.getDirectory();
        if (filename == null){
            System.out.println("You cancelled the choice");
            System.exit(0);
        }
        String path = location + filename;
        System.out.println(path);
        Interpreter interpreter = new Interpreter();
        ArrayList<ArrayList<String>> words = interpreter.preprocessFile(path);
        HashMap<String, Variable> variables = new HashMap<>();
        for(int i = 0; i < words.size(); i++){
            System.out.println(words.get(i));
            String operation = (words.get(i)).get(0);
            if(operation.equals("clear") || operation.equals("incr") || operation.equals("decr")){
                String operand = (words.get(i)).get(1);
                Variable var = variables.get(operand);
                if(operation.equals("clear")){
                    if(var == null){
                        var = new Variable(0);
                        variables.put(operand, var);
                    } else {
                        var.setValue(0);
                        variables.put(operand, var);
                    }
                    System.out.println(operand + var.getValue());
                }
                else if(operation.equals("incr")){
                    if(var == null){
                        var = new Variable(0);
                        var.increment();
                        variables.put(operand, var);
                    } else {
                        var.increment();
                        variables.put(operand, var);
                    }
                    System.out.println(operand + var.getValue());
                }
                else if(operation.equals("decr")){
                    if(var == null){
                        var = new Variable(0);
                        var.decrement();
                        variables.put(operand, var);
                    } else {
                        var.decrement();
                        variables.put(operand, var);
                    }
                    System.out.println(operand + var.getValue());
                }  
                System.out.println(variables);
            }
        }
    }
    
}
