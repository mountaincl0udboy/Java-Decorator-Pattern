package kz.narxoz.patterns.decorator;

import kz.narxoz.patterns.decorator.examples.CryptoException;

import java.io.File;
import java.io.IOException;

public class DataSourceDecorator implements DataSource{

    private DataSource wrapper;

    DataSourceDecorator(DataSource dataSource){

        wrapper = dataSource;
    }

    @Override
    public void writeData(File file) throws IOException, CryptoException {
        wrapper.writeData(file);
    }

    @Override
    public File readData() {

        return wrapper.readData();
    }
}
