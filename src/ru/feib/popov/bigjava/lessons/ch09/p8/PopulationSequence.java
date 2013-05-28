package ru.feib.popov.bigjava.lessons.ch09.p8;


import java.util.ArrayList;
import java.io.*;
/**
 * Reads data from file and puts it into ArrayList.
 * @author popov
 */
public class PopulationSequence implements Sequence {
    private static final String FILE_PATH = 
            "\\\\feib\\dfs\\HomeDir\\Popov\\Documents\\Учебник\\" + 
            "Big Java Excersises\\ch09\\sequence\\CountryPopulation.TXT";
                    
    
    private ArrayList<Integer> populations;
    private int position;
    
    /**
     * Create object, filling ArrayList by values from TXT file
     * @throws Exception when can't read file 
     */
    public PopulationSequence() throws Exception {
        position = 0;
        populations = new ArrayList<>();
        BufferedReader br;
        FileInputStream fstream;
        DataInputStream in;
        
        try {
            fstream = new FileInputStream(FILE_PATH);
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
        } catch (Exception e) {
            throw e;
        }
                
        String strLine;
        while((strLine=br.readLine()) != null) {    
            populations.add(Integer.parseInt(strLine));
        }
        
        br.close();
        in.close();
        fstream.close();
    }
    
    /**
     * Get next population value
     * @return population value as integer
     */
    @Override
    public int next () {
        position++;
        return populations.get(position);
    }
    
    /**
     * Check if next value exists
     * @return true - if exists, otherwise - false
     */
    @Override
    public boolean hasNext() {
        return (position < populations.size() - 1);
    }
}
