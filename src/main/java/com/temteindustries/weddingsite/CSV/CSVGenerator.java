package com.temteindustries.weddingsite.CSV;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVGenerator {

    public static void GenerateFile(String CSVString) throws IOException {
        File file = new File("target/classes/static/GuestList.csv");
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(CSVString);
        output.close();
    }
}
