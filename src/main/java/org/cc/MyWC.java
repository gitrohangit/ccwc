package org.cc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyWC {
    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("-c")) {
            System.out.println("Usage: ccwc -c <filename>");
            return;
        }

        String filename = args[1];
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }

        long byteCount = countBytes(file);

        System.out.println(byteCount + " " + filename);
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
}
