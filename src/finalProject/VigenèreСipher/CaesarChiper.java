package finalProject.VigenèreСipher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaesarChiper implements CryptoAnalyzer {


    @Override
    public  void encryption(Path initialMessage, Path outputPath) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the key: ");
            int key = scanner.nextInt();
            scanner.nextLine();


            List<Character> charList = new ArrayList<>();
            List<String> lines = Files.readAllLines(initialMessage, StandardCharsets.UTF_8);
            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    char character = (char) (line.charAt(i) + key);
                    charList.add(character);
                }
            }

            char[] charArray = new char[charList.size()];
            for (int i = 0; i < charList.size(); i++) {
                charArray[i] = charList.get(i);
            }

            Files.write(outputPath, new String(charArray).getBytes(StandardCharsets.UTF_8));
            System.out.println("Encrypted message has been written to the file.");
        } catch (IOException ioException){
            ioException.printStackTrace();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public  void decryption(Path encryptedFilePath, Path decrptedFilePath)  {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the key: ");
            int key = scanner.nextInt();
            scanner.nextLine();


            List<Character> charList = new ArrayList<>();
            List<String> lines = Files.readAllLines(encryptedFilePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    char character = (char) (line.charAt(i) - key);
                    charList.add(character);
                }
            }

            char[] charArray = new char[charList.size()];
            for (int i = 0; i < charList.size(); i++) {
                charArray[i] = charList.get(i);
            }

            Files.write(decrptedFilePath, new String(charArray).getBytes(StandardCharsets.UTF_8));
            System.out.println("Decrypted message has been written to the file.");

        } catch (IOException ioException){
            ioException.printStackTrace();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }


}
