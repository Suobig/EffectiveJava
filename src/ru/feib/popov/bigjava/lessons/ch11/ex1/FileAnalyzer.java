/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.bigjava.lessons.ch11.ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author popov
 */
public class FileAnalyzer {
    private int counterChars;
    private int counterWords;
    private int counterLines;
    
    public FileAnalyzer(File file) throws FileNotFoundException {
        try(Scanner charReader = new Scanner(file)) {
            charReader.useDelimiter("");

            while (charReader.hasNext()) {
                counterChars++;
                charReader.next();
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        
        try(Scanner wordReader = new Scanner(file)) {

            while (wordReader.hasNext()) {
                counterWords++;
                wordReader.next();
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        
        counterLines = 1;
        try(Scanner lineReader = new Scanner(file)) {

            while (lineReader.hasNextLine()) {
                counterLines++;
                lineReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
    }
    
    public int getChars() {
        return counterChars;
    }
    
    public int getWords() {
        return counterWords;
    }
    
    public int getLines() {
        return counterLines;
    }
}
