package com.dmtryii.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        // Timer for counting the program execution time from the start
        final long startTime = System.currentTimeMillis();

        // Add config file
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream("src/main/resources/application.properties");
        properties.load(in);

        Engine engine;
        engine = new Engine(new File(properties.getProperty("DIR")));
        System.out.println(engine.search(properties.getProperty("SEARCH-WORD")));
        System.out.println("Number of matches: " + engine.getIdx().size());

        // Timer for counting the end of the program execution
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (double) (endTime - startTime) / 1000  + " seconds");
    }
}
