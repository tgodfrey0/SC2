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
import java.util.Iterator;

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
            /*for(int j = 0; j < words.get(i).size(); j++){
                String word = words.get(i).get(j);
                word = word.replaceAll(" ", "");
                if(word.length() == 0) (words.get(i)).remove(new String(""));
            } // IT DOESNT FUCKING WORK FIX IT*/

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
 
}