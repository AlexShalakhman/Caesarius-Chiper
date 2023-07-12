package finalProject.UI;

import finalProject.FileCheck.MyFileReader;
import finalProject.VigenèreСipher.BruteForce;
import finalProject.VigenèreСipher.CaesarChiper;


import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    public static CaesarChiper caesarChiper = new CaesarChiper();
    public static BruteForce bruteForce = new BruteForce();

    public static void start(){

        try(Scanner scanner = new Scanner(System.in)){
            boolean keepGoing = true;
            System.out.println("          Hello, user!");
            System.out.println("    What do you want to do today?");
            System.out.println();
        do {
            try {

                System.out.println("Here are the following options - ");
                System.out.println("1. Encrypt text.");
                System.out.println("2. Decrypt text.");
                System.out.println("3. End this session.");
                System.out.print("Please write corresponding number of the option to proceed here - ");
                int options = scanner.nextInt();
                scanner.nextLine();

                switch (options) {
                    case 1:
                        System.out.println("Alright! You have chosen to encrypt text");
                        System.out.print("Please input absolute path to your initial file with text - ");
                        String encryptionFilePath1 = scanner.nextLine();
                        Path method1Path1 = MyFileReader.fileChecker(encryptionFilePath1);
                        System.out.print("Please, input absolute path to intended encrypted file location - ");
                        String encryptionFilePath2 = scanner.nextLine();
                        Path method1Path2 = MyFileReader.fileChecker(encryptionFilePath2);
                        System.out.println("Do you want to put custom key or do you want to use random key?");
                        System.out.println("Please type - \"Custom\" for custom key or type - \"Random\" for random");
                        String answer = scanner.nextLine();
                        switch (answer.toLowerCase()){
                            case "custom" :
                                caesarChiper.encryption(method1Path1, method1Path2);
                                break;
                            case "random":
                                bruteForce.encryption(method1Path1, method1Path2);
                                break;
                            }

                        break;

                    case 2:
                        System.out.println("Gotcha! You want to decrypt you text");
                        System.out.print("Please, input absolute path to your file with text - ");
                        String decryptionFilePath1 = scanner.nextLine();
                        Path method2Path1 = MyFileReader.fileChecker(decryptionFilePath1);
                        System.out.print("Please, input absolute path to intended decrypted file location - ");
                        String decryptionFilePath2 = scanner.nextLine();
                        Path method2Path2 = MyFileReader.fileChecker(decryptionFilePath2);
                        System.out.println("Do you want to put custom key or do you want to use random key?");
                        System.out.println("Please type - \"Custom\" for custom key or tupe - \"Random\" for random");
                        String answerForCase2 = scanner.nextLine();
                        switch (answerForCase2.toLowerCase()){
                            case "custom" :
                                caesarChiper.decryption(method2Path1, method2Path2);
                                break;
                            case "random":
                                bruteForce.decryption(method2Path1, method2Path2);
                                break;
                        }
                        break;

                    case 3:
                        System.out.println("You have ended this session.");
                        keepGoing = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                System.out.println();
            } catch (InputMismatchException im) {
                System.out.println("Only numbers permitted.");
                scanner.nextLine();
            }
            }while(keepGoing);

        }catch (Exception e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
