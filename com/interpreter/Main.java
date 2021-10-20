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
import javax.swing.JFrame;

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
        interpreter.interpret(words);
    }
    
}
