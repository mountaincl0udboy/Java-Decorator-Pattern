package kz.narxoz.decorator;

import kz.narxoz.decorator.examples.CryptoException;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class CompressionDecorator extends kz.narxoz.decorator.DataSourceDecorator {
    CompressionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(File inputFileName) throws IOException, CryptoException {

        FileInputStream fis=new FileInputStream(inputFileName);
        File outputFile = new File(
                "src/main/java/kz/narxoz/decorator/tmp/tmp_comp_file");
        FileOutputStream fos=new FileOutputStream(outputFile);

        DeflaterOutputStream dos=new DeflaterOutputStream(fos);

        int data;
        while ((data=fis.read())!=-1)
        {
            dos.write(data);
        }

        //close the file
        fis.close();
        dos.close();

        super.writeData(outputFile);
    }

    @Override
    public File readData() throws CryptoException, IOException {
        File file = super.readData();
        FileInputStream fis=new FileInputStream(file);



        File outputFile = new File(
                "src/main/java/kz/narxoz/decorator/tmp/tmp_decomp_file");

        FileOutputStream fos=new FileOutputStream(outputFile);
        InflaterInputStream iis=new InflaterInputStream(fis);
        int data;
        while((data=iis.read())!=-1)
        {
            fos.write(data);
        }

        //close the files
        fos.close();
        iis.close();


        return outputFile;
    }
}