package com.temteindustries.weddingsite.CSV;

import com.temteindustries.weddingsite.Cache.GuestCache;
import com.temteindustries.weddingsite.model.GuestObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CSVGenerator {

    public static String GenerateCSVText(){

        HashMap<Integer, GuestObject> cache = GuestCache.getInstance();
        String CSVText = "ID,FirstName,LastName,Ceremony,Reception\n";

        for (int i = 1; i <= cache.size(); i ++){
            GuestObject guest = cache.get(i);
            CSVText = CSVText + i +','+guest.getfName()+','+guest.getlName()+','+guest.getCeremony()+','+guest.getReception()+"\n";
        }

        return CSVText;
    }

    public static void GenerateFile(String CSVString) throws IOException {
        File file = new File("target/classes/static/GuestList.csv");
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(CSVString);
        output.close();
    }
}
