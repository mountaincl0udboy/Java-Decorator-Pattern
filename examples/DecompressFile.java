package kz.narxoz.patterns.decorator.examples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

//Uncompressing a file using an InflaterInputStream
class DecompressFile
{
    public static void main(String[] args) throws IOException {
        String inputFileName = "src/main/java/kz/narxoz/patterns/decorator/myFile_compressed";
        //assign Input File : file2 to FileInputStream for reading data
        FileInputStream fis=new FileInputStream(inputFileName);

        //assign output file: file3 to FileOutputStream for reading the data
        FileOutputStream fos=new FileOutputStream("src/main/java/kz/narxoz/patterns/decorator/myFile_decompressed");

        //assign inflaterInputStream to FileInputStream for uncompressing the data
        InflaterInputStream iis=new InflaterInputStream(fis);

        //read data from inflaterInputStream and write it into FileOutputStream
        int data;
        while((data=iis.read())!=-1)
        {
            fos.write(data);
        }

        //close the files
        fos.close();
        iis.close();

    }
}
