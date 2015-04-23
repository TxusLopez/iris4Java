/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irisdriver;

import java.io.InputStream;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 *
 * @author 106811
 */
public class Iris {
    
    private Classifier classModel;
    private Instances dataModel;
    //private String classModelFile = "D:\\tree(J48).model";
    
    //private String pathModelFile = "D:\\tree(J48).model";

    /**
     *  Class constructor.
     */
    public Iris(String pathModel) throws Exception {
            //InputStream classModelStream;

            //  Create a stream object for the model file embedded
            //  within the JAR file.
            //classModelStream = getClass().getResourceAsStream(classModelFile);
        
            //classModel = (Classifier)SerializationHelper.read(pathModelFile);
            classModel = (Classifier)SerializationHelper.read(pathModel);
    }

    /**
     *  Close the instance by setting both the model file string and
     *  the model object itself to null.  When the garbage collector
     *  runs, this should make clean up simpler.  However, the garbage
     *  collector is not called synchronously since that should be
     *  managed by the larger execution environment.
     */
    public void close() {
            classModel = null;
            //classModelFile = null;
    }

    /**
     * Evaluate the model on the data provided by @param measures.
     * This returns a string with the species name.
     *
     * @param measures object with petal and sepal measurements
     * @return string with the species name
     * @throws Exception
     */
    public String classifySpecies(Hashtable<String, String> measures) throws Exception {
            FastVector dataClasses = new FastVector();
            FastVector dataAttribs = new FastVector();
            Attribute species;
            double values[] = new double[measures.size() + 1];
            int i = 0, maxIndex = 0;

            //  Assemble the potential species options.
            dataClasses.addElement("setosa");
            dataClasses.addElement("versicolor");
            dataClasses.addElement("virginica");
            species = new Attribute("species", dataClasses);

            //  Create the object to classify on.
            for (Enumeration<String> keys = measures.keys(); keys.hasMoreElements(); ) {
                    String key = keys.nextElement();
                    double val = Double.parseDouble(measures.get(key));			
                    dataAttribs.addElement(new Attribute(key));
                    values[i++] = val;
            }
            dataAttribs.addElement(species);
            dataModel = new Instances("classify", dataAttribs, 0);
            dataModel.setClass(species);
            dataModel.add(new DenseInstance(1, values));
            dataModel.instance(0).setClassMissing();

            //  Find the class with the highest estimated likelihood

            double cl[] = classModel.distributionForInstance(dataModel.instance(0));
            for(i = 0; i < cl.length; i++)
                    if(cl[i] > cl[maxIndex])
                            maxIndex = i;

            return dataModel.classAttribute().value(maxIndex);
    }
    
    
}
