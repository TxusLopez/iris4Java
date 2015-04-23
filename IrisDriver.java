/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irisdriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author TxusLópez
 */
public class IrisDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    //As an example of arguments: sepallength=5.1 sepalwidth=3.5 petallength=1.4 petalwidth=0.2    
        try {
            Hashtable<String, String> values = new Hashtable<String, String>();
            /*Iris irisModel = new Iris();
            
            for(int i = 0; i < args.length; i++) {
                String[] tokens = args[i].split("=");

                values.put(tokens[0], tokens[1]);
            }
            
            System.out.println("Classification: " + irisModel.classifySpecies(values));*/
            
            //Loading the model
            String pathModel="";
            String pathTestSet="";
            JFileChooser chooserModel = new JFileChooser();
            chooserModel.setCurrentDirectory(new java.io.File("."));
            chooserModel.setDialogTitle("Choose the model");
            chooserModel.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooserModel.setAcceptAllFileFilterUsed(true);

            if (chooserModel.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File filePathModel=chooserModel.getSelectedFile();
                pathModel=filePathModel.getPath();
                
                Iris irisModel = new Iris(pathModel);
                
                //Loading the model
                JFileChooser chooserTestSet = new JFileChooser();
                chooserTestSet.setDialogTitle("Choose TEST SET");
                chooserTestSet.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooserTestSet.setAcceptAllFileFilterUsed(true);

                //Loading the testing dataset
                if (chooserTestSet.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File filePathTestSet=chooserTestSet.getSelectedFile();
                    pathTestSet=filePathTestSet.getPath();

                    //WRITTING THE OUTPUT:
                    BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\output_file.txt"));
                    
                    //Transforming the data set into pairs attribute-value
                    ConverterUtils.DataSource unlabeledSource = new ConverterUtils.DataSource(pathTestSet);
                    Instances unlabeledData = unlabeledSource.getDataSet();
                    if (unlabeledData.classIndex() == -1){
                        unlabeledData.setClassIndex(unlabeledData.numAttributes() - 1);
                    }
                    
                    for (int i = 0; i < unlabeledData.numInstances(); i++) {
                        Instance ins=unlabeledData.instance(i);
                        
                        //ins.numAttributes()-1 --> not to include the label
                        for (int j = 0; j < ins.numAttributes()-1; j++) {
                        
                            String attrib=ins.attribute(j).name();
                            double val=ins.value(ins.attribute(j));

                            values.put(attrib,String.valueOf(val));
                                                    
                        }
                        
                        String predictedLabel=irisModel.classifySpecies(values);
                        System.out.println("Classification: " + predictedLabel);
                        values.clear();
                        
                        //Writting the results in a txt
                        writer.write("The label is: " + predictedLabel);

                        //writer.newLine();

                        //writers.write("The error rate of the prediction is : " + eval.errorRate());

                        //writer.newLine();
                        
                    }
                    
                    writer.flush();
                    writer.close();

                }
                
            }

            
        } catch (Exception ex) {
            Logger.getLogger(IrisDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
}
