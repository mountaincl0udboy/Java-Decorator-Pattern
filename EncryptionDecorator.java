package kz.narxoz.decorator;

import kz.narxoz.decorator.examples.CryptoException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class EncryptionDecorator extends DataSourceDecorator{
    private static String KEY = "1234567890abcdef";
    EncryptionDecorator(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public void writeData(File intputFile) throws IOException, CryptoException {
        // ENCRYPT
        File outputFile = new File(
                "src/main/java/kz/narxoz/decorator/tmp/tmp_enc_file"
        );
        LocalEncryption.encrypt(KEY, intputFile, outputFile);

        super.writeData(outputFile);
    }

    @Override
    public File readData() throws CryptoException, IOException {
        File file = super.readData();

        File outputFile =new File(
                "src/main/java/kz/narxoz/decorator/tmp/tmp_dec_file"
        );
        LocalEncryption.decrypt(KEY,file,outputFile);

        return outputFile;
    }
}



class  LocalEncryption {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static void encrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public static void decrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile,
                                 File outputFile) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                 | InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }
}