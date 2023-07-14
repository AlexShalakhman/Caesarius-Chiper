package CaesariusChiper.FileCheck;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFileReader {

    public static Path fileChecker(String path){
        try {
            Path path1 = Paths.get(path);
            if (path1.isAbsolute() && Files.isRegularFile(path1)) {
                System.out.println();
                System.out.println("Awesome! Path is correct for further processing.");
                System.out.println();
                return path1;
            } else if (!path1.isAbsolute()) {
                System.out.println("Invalid path. Please provide an absolute path to a file.");
            }
        } catch (InvalidPathException ia) {
            System.out.println("Invalid path. Please provide an absolute path to a file.");
            ia.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
