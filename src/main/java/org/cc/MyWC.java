package org.cc;

import java.io.*;

public class MyWC {
    public static void main(String[] args) {
        if (args.length < 3 || !args[0].startsWith("ccwc") || !args[1].startsWith("-")) {
            System.out.println("Usage: ccwc [-c | -l] <filename>");
            return;
        }

        String option = args[1];
        String filename = args[2];

        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }

        long count;
        switch (option) {
            case "-c":
                count = countBytes(file);
                System.out.println(count + " " + filename);
                break;
            case "-l":
                count = countLines(file);
                System.out.println(count + " " + filename);
                break;
            default:
                System.out.println("Invalid option: " + option);
        }
    }

    private static long countBytes(File file) {
        long byteCount = 0;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteCount += bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return byteCount;
    }

    private static long countLines(File file) {
        long lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }
}
