package com.company;
import java.io.*;

public class Serializer implements Serializable{

    static public boolean serialize(String filePath, Object data) {
        try {
            var file = new FileOutputStream(filePath);
            var out = new ObjectOutputStream(file);
            out.writeObject(data);
            out.close();
            file.close();
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    static public Object deserialize(String filePath) {
        try {
            var file = new FileInputStream(filePath);
            var in = new ObjectInputStream(file);
            var data = in.readObject();
            in.close();
            file.close();
            return data;
        } catch (Exception error) {
            System.out.println(error);
            return false;
        }
    }

}

