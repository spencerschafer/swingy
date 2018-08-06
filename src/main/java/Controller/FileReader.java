package Controller;

import java.io.File;

public class FileReader {

    File file = null;

    public FileReader() {
        this.file = file;
    }

    //reads a file to access already saved information
    public void readFile() {

    }

    //saves current data to text file
    public void saveFile() {

    }
}
/*
public class Main {
    public static void main(String[] args) {

        boolean loadFromClasspath = true;
        String fileName = System.getProperty("user.dir") + "/src/main/resources/saves/save.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));

            String line = null;
            while ((line = reader.readLine()) != null) {
                // do something with the line here
                System.out.println(line);
            }
        } catch (IOException e) {
            return;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    return;
                }
            }
        }
    }
}
*/
