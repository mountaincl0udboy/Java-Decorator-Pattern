package kz.narxoz.patterns.decorator.examples;

import java.io.File;

public class RunEncryption {
    public static void main(String[] args) throws CryptoException {
        String intputFile = "src/main/java/kz/narxoz/patterns/decorator/myFile";
        String encFile = "src/main/java/kz/narxoz/patterns/decorator/myFile_encrypted";
        String decFile = "src/main/java/kz/narxoz/patterns/decorator/myFile_decrypted";

        //Encryption.encrypt("Mary has one cat",new File(intputFile),new File(encFile));

        Encryption.decrypt("Mary has one cat", new File(encFile), new File(decFile));
    }
}
