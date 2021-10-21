/**
 * The interpreter class is the interpreter functions.
 * 
 * @author Toby Godfrey
 * @version 1.0
 */

package com.interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Interpreter {
    /**
     * The preprocessFile method removes any empty lines from the file and splits the file at semicolons.
     * @param path This is the path to the file
     * @return data A string array is returned
     * @throws IOException
     */
    public ArrayList<ArrayList<String>> preprocessFile(String path) throws IOException {
        FileReader fileReader = new FileReader(new File(path));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line);
            stringBuffer.append("\n");
        }
        fileReader.close();
        String dataString = stringBuffer.toString();
        ArrayList<String> data;
        data = new ArrayList<String>(Arrays.asList((dataString.replaceAll("[\\\r\\\n\\\t]+","")).split(";")));
        ArrayList<ArrayList<String>> words = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            words.add(new ArrayList<String>(Arrays.asList((data.get(i)).split(" "))));
        }
        for(int i = 0; i < words.size(); i++){
            Iterator<String> iterator = (words.get(i)).iterator();
            while(iterator.hasNext()){
                String s = iterator.next();
                if(s.length() == 0){
                    iterator.remove();
                }
            }
        }
        return words;
    }

    /**
     * The interpret method contains all the functionality to actually parse the Bare Bones data.
     * @param words This is an 2D ArrayList of every word in the file.
     */
    public void interpret(ArrayList<ArrayList<String>> words){
        HashMap<String, Variable> variables = new HashMap<>();
        Stack<Integer> loopIndexes = new Stack<>();
        for(int i = 0; i < words.size(); i++){
            int lineNum = i+1;
            System.out.println("Line " + lineNum + ": " + words.get(i));
            String operation = (words.get(i)).get(0);
            /**
             * This if statement checks if the operation is one of those which edits the variable.
             */
            if(operation.equals("clear") || operation.equals("incr") || operation.equals("decr")){
                String operand = (words.get(i)).get(1);
                Variable var = variables.get(operand);
                if(operation.equals("clear")){
                    if(var == null){
                        var = new Variable(0);
                        variables.put(operand, var);
                    } else {
                        var.setValue(0);
                        //variables.put(operand, var);
                    }
                    System.out.println(operand + " = " + var.getValue());
                }
                else if(operation.equals("incr")){
                    if(var == null){
                        var = new Variable(0);
                        var.increment();
                        variables.put(operand, var);
                    } else {
                        var.increment();
                    }
                    System.out.println(operand + " = " + var.getValue());
                }
                else if(operation.equals("decr")){
                    if(var == null){
                        var = new Variable(0);
                        var.decrement();
                        variables.put(operand, var);
                    } else {
                        var.decrement();
                    }
                    System.out.println(operand + " = " + var.getValue());
                }  
            } 
            /**
             * If it is a while statement, The variable object is fetched/created, then if it already satisfies the while loop condition the ned end statement is found and the interpreter skips to this line. If the condition is not met, then the index of the while condition is pushed to a stack object.
             */
            else if(operation.equals("while")){
                String operand = (words.get(i)).get(1);
                Variable var = variables.get(operand);
                if(var == null){
                    var = new Variable(0);
                    variables.put(operand, var);
                }
                if(var.getValue() == 0){
                    for(int k = i; k < words.size(); k++){
                        if(words.get(k).get(0).equals("end")){
                            i = k;
                            System.out.println("End Index: " + i);
                            break;
                        }
                    }
                } else {
                    System.out.println("Set" + i);
                    if(loopIndexes.isEmpty()){
                        loopIndexes.push(Integer.valueOf(i));
                    } else if(!(loopIndexes.peek().equals(Integer.valueOf(i)))){
                        loopIndexes.push(Integer.valueOf(i));
                    }
                }
                System.out.println(operand + " = " + var.getValue());
            } 
            /**
             * If the operation is a end statement, the variable gets the location of the loop condition from the stack then either jumps there, or ends the loop if the condition is satisfied.
             */
            else if(operation.equals("end")){
                int loopIndex = loopIndexes.peek();
                Variable loopedVariable = variables.get(words.get(loopIndex).get(1));
                if(loopedVariable.getValue() != 0){
                    i = loopIndex-1;
                    continue;
                } else{
                    loopIndexes.pop();
                }
            }
        }
    }
 
}