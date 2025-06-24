package ch.hack4rail.traintipgenerator.services;

public interface CsvMapper<T, K> {
    T mapTo(K k);
    //This method is used to create java Object from a Csv Representation .
    K unmapFrom(T t);
//This method is used to create a Csv Representation from java Object .
}