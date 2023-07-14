package CaesariusChiper.VigenèreСipher;


import java.nio.file.Path;

public interface CryptoAnalyzer {

    public  void encryption(Path inputPpath, Path outputPath);

    public  void decryption(Path inputPath, Path outputPath);

}
