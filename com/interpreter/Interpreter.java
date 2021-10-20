/**
 * The interpreter class is the interpreter functions.
 * 
 * @author Toby Godfrey
 * @version 1.0
 */

package com.interpreter;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Interpreter {
    /**
     * The preprocessFile method removes any empty lines from the file and splits the file at semicolons.
     * @param path This is the path to the file
     * @return data A string array is returned
     * @throws IOException
     */
    public String[] preprocessFile(String path) throws IOException {
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
        String[] data;
        data = (dataString.replaceAll("[\\\r\\\n]+","")).split(";");
        return data;
    }
}