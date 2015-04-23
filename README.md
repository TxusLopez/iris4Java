# iris4Java
Java code for iris dataset for learning purposes

Here you can find a basic example about how to use the WEKA library in Java. The code uses a decision tree algorithm (C4.5-->J48 in WEKA) to classify instances of the iris dataset.

A model is already generated, called "othertree(J48_cv).model", which can be easily generated with WEKA. The "IrisDriver.java" provides a simple interface to import the model and the test set "iris-test.arff". The application writes on a file called "output_file.txt" the results of the classification. The "Iris.java" uses the model and classify the new instances provided by "iris-test.arff".

You can find also two JUnit files: "IrisTest.java" and "IrisTest2.java".
Finally, the traiing instances are also provided if you can generate your own model with WEKA, using other techniques
apart from J48.
