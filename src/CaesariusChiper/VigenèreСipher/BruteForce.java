package CaesariusChiper.VigenèreСipher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class BruteForce implements CryptoAnalyzer {


    @Override
    public void encryption(Path inputPpath, Path outputPath)  {
        try {
            Random random = new Random();
            int lowerBound = 1;
            int upperBound = 30;
            int key = random.nextInt(upperBound - lowerBound) + lowerBound;


            List<Character> charList = new ArrayList<>();
            List<String> lines = Files.readAllLines(inputPpath, StandardCharsets.UTF_8);
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
        } catch ( Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void decryption(Path inputPath, Path outputPath)  {
        try {
            int key = 0;
            int indicator = 0;
            boolean keyIsFound = (indicator > 2) ? true : false;

            do {
                indicator = 0;

                List<Character> charList = new ArrayList<>();
                List<String> lines = Files.readAllLines(inputPath, StandardCharsets.UTF_8);
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

                boolean condition1 = hasMissingSpaceAfterComma(charArray);
                boolean condition2 = hasMissingSpaces(charArray);
                boolean condition3 = hasCorrectDots(charArray);
                boolean condition4 = hasCapitalFirstLetter(charArray);

                if (condition1)
                    indicator += 2;
                if (condition2)
                    indicator++;
                if (condition3)
                    indicator += 2;
                if (condition4)
                    indicator++;

                key++;

                if (indicator > 3) {
                    Files.write(outputPath, new String(charArray).getBytes(StandardCharsets.UTF_8));
                    System.out.println("Decrypted message has been written to the file.");
                    break;
                }
            } while (!keyIsFound);

        } catch (IOException ioException){
            ioException.printStackTrace();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private static boolean hasMissingSpaceAfterComma(char[] charArray) {
        for (int i = 0; i < charArray.length - 1; i++) {
            if (charArray[i] == ',' && charArray[i + 1] == ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean hasMissingSpaces(char[] charArray) {
        for (int i = 0; i < charArray.length - 1; i++) {
            if (Character.isLetter(charArray[i]) && charArray[i + 1] == ' ' && charArray[i + 2] != ' '){
                return true;
            }
        }
        return false;
    }

    private static boolean hasCapitalFirstLetter(char[] charArray) {
        boolean isCapital = true;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {
                if (isCapital && !Character.isUpperCase(charArray[i])) {
                    return false;
                }
                isCapital = false;
            } else if (charArray[i] == '.') {
                isCapital = true;
            }
        }
        return true;
    }

    private static boolean hasCorrectDots(char[] charArray) {
        int length = charArray.length;
            for (int i = 0; i < charArray.length - 1; i++) {
       if (length > 0 && i >= 1 && Character.isLetter(charArray[i - 1]) && charArray[i] == '.') {
        return true;
    } else if (length > 1 && i >= 2 && Character.isLetter(charArray[i - 2]) && charArray[i - 1] == '.' && charArray[i] == ' ') {
        return true;
    }
}

    return false;
}
    }
