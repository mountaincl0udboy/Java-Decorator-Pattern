package kz.narxoz.patterns.decorator;

import kz.narxoz.patterns.decorator.examples.CryptoException;

import java.io.File;
import java.io.IOException;


public interface DataSource {

    public void writeData(File file) throws IOException, CryptoException;

    public File readData();
}
