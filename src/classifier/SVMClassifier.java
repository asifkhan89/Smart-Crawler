/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

//import static forex.Mnframe.svmoutarea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;

public class SVMClassifier {

    public static void main(String[] args) {
        String trainSVM = "data_svm" + File.separator + "train.arff";
        String testSVM = "data_svm" + File.separator + "test.arff";

        SVMClassifier svm = new SVMClassifier();
        svm.classify(trainSVM, testSVM);
    }

    public void classify(String trainn, String testt) {
        BufferedReader breader, breader1;
        try {
//            File f_train = new File(System.getProperty("user.dir") + File.separator + trainn);
//            File f_test = new File(System.getProperty("user.dir") + File.separator + testt);
//            System.out.println("f test : " + f_test);
//            System.out.println("f train : " + f_train);
            
            breader = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + trainn));
            Instances train = new Instances(breader);
            train.setClassIndex(train.numAttributes() - 1);

            breader1 = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + testt));
            Instances test = new Instances(breader1);
            test.setClassIndex(test.numAttributes() - 1);

            LibSVM clasifier = new LibSVM();
//            IBk clasifier = new IBk();
            clasifier.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(clasifier, train);

            Instances labeled = new Instances(test);

            //----- label instances ------
            for (int i = 0; i < test.numInstances(); i++) {
                //System.out.println("iiiii----"+test.numInstances());
                // System.out.println(i+" inst i----"+test.instance(i));
                double clslabel = clasifier.classifyInstance(test.instance(i));
//                System.out.println(i + " Label found----" + clslabel);
                labeled.instance(i).setClassValue(clslabel);
                if (clslabel == 0) {
//                    System.out.println("Class IS relevent\n\n");
//                    svmoutarea.append("Class IS Up\n\n");
                } else {
//                    System.out.println("Class Is not_relevent\n\n");
//                    svmoutarea.append("Class IS Down\n\n");
                }
            }
            String[] s = labeled.instance(0).toString().split(",");

//            System.out.println("nnnnnnnnnnnn         "+labeled.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
