/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irisdriver;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TxusLópez
 */
public class IrisTest2 {
    
    Iris test;
    
    public IrisTest2() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            test = new Iris("D:\\tree(J48_cv).model");
        } catch (Exception e){
            fail("Iris class creation failed: " + e.getMessage());
        }
        
    }
    
    @After
    public void tearDown() {
        test.close();

    }

    /**
     * Test of close method, of class Iris.
     */
    @Test
    public void testClose() {
        try {
            System.out.println("close");
            //Iris instance = new Iris();
            //instance.close();
            // TODO review the generated test code and remove the default call to fail.
            //fail("The test case is a prototype.");
        } catch (Exception ex) {
            Logger.getLogger(IrisTest2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of classifySpecies method, of class Iris.
     */
    @Test
    public void testClassifySpecies() throws Exception {
        System.out.println("classifySpecies");
        
        Hashtable<String, String> testValues = new Hashtable<String, String>();

        testValues.put("Sepal.Length", "4.92929274813475");
        testValues.put("Sepal.Width", "3.02115558560619"); 
        testValues.put("Petal.Length", "1.66111228600889");
        testValues.put("Petal.Width", "0.494913257610669");
        if(test.classifySpecies(testValues) != "setosa")
                fail("setosa Test A failed.");
        testValues.clear();

        testValues.put("Sepal.Length", "5.01546360901192"); 
        testValues.put("Sepal.Width", "2.88881892726493"); 
        testValues.put("Petal.Length", "1.21569048438709"); 
        testValues.put("Petal.Width", "-0.0217150655097085");
        if(test.classifySpecies(testValues) != "setosa")
                fail("setosa Test B failed.");
        testValues.clear();

        testValues.put("Sepal.Length", "5.89395864793917"); 
        testValues.put("Sepal.Width", "3.29989993511548"); 
        testValues.put("Petal.Length", "3.83039227615041"); 
        testValues.put("Petal.Width", "1.37570550945425");
        if(test.classifySpecies(testValues) != "versicolor")
                fail("versicolor Test A failed.");
        testValues.clear();

        testValues.put("Sepal.Length", "5.6267275495847");
        testValues.put("Sepal.Width", "2.89811069873405"); 
        testValues.put("Petal.Length", "5.24972276380846"); 
        testValues.put("Petal.Width", "1.37410752993031");
        if(test.classifySpecies(testValues) != "versicolor")
                fail("versicolor Test B failed.");
        testValues.clear();

        testValues.put("Sepal.Length", "6.34320263841694"); 
        testValues.put("Sepal.Width", "2.66808487957697"); 
        testValues.put("Petal.Length", "5.85453759164497"); 
        testValues.put("Petal.Width", "2.37517354329087"); 
        if(test.classifySpecies(testValues) != "virginica")
                fail("virginica Test A failed.");
        testValues.clear();

        testValues.put("Sepal.Length", "6.85295094185716"); 
        testValues.put("Sepal.Width", "2.51646353971598"); 
        testValues.put("Petal.Length", "4.21432509213448"); 
        testValues.put("Petal.Width", "1.74441756575054"); 
        if(test.classifySpecies(testValues) != "virginica")
                fail("virginica Test B failed.");
        testValues.clear();
    }
    
}
