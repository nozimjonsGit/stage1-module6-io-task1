package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String data = readFileData(file) ;
        String[] lines = data.split(System.lineSeparator());
        String name = null;
        int age = 0;
        String email = null;
        long phone = 0;

        for (String line : lines) {
            if (line.startsWith("Name: ")) {
                name = line.substring("Name: ".length());
            } else if (line.startsWith("Age: ")) {
                age = Integer.parseInt(line.substring("Age: ".length()));
            } else if (line.startsWith("Email: ")) {
                email = line.substring("Email: ".length());
            } else if (line.startsWith("Phone: ")) {
                phone = Long.parseLong(line.substring("Phone: ".length()));
            }
        }

        return new Profile(name, age, email, phone);
    }

    public String readFileData(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
