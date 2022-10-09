package kz.narxoz.patterns.decorator;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDataSource implements DataSource {

    private String fileName = null;

    FileDataSource(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void writeData(File file) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file);

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        int data;
        while ((data=fileInputStream.read())!=-1)
        {
            fileOutputStream.write(data);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    @Override
    public File readData() {

        return new File(fileName);

    }
}
