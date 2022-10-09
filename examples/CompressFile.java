package kz.narxoz.patterns.decorator.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

class CompressFile
{
    public static void main(String[] args) throws IOException {
        String inputFileName = "src/main/java/kz/narxoz/patterns/decorator/myFile";

        //Assign the original file : file to
        //FileInputStream for reading data
        FileInputStream fis=new FileInputStream(inputFileName);

        //Assign compressed file:file2 to FileOutputStream
        FileOutputStream fos=new FileOutputStream(inputFileName+"_compressed");

        //Assign FileOutputStream to DeflaterOutputStream
        DeflaterOutputStream dos=new DeflaterOutputStream(fos);

        //read data from FileInputStream and write it into DeflaterOutputStream
        int data;
        while ((data=fis.read())!=-1)
        {
            dos.write(data);
        }

        //close the file
        fis.close();
        dos.close();
    }
}
