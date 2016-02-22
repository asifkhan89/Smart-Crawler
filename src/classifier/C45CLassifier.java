package classifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class C45CLassifier {

    StringBuilder sb = new StringBuilder();

    public StringBuilder getSb() {
        return sb;
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    public C45CLassifier() {

        try {
//            String options = "U";
//            String[] options = {"-C", "0.5"};
            BufferedReader br1 = new BufferedReader(new FileReader("data_svm" + File.separator + "train.arff"));
            Instances train_data = new Instances(br1);
            System.out.println("====================================");
            System.out.println("TRAIN DATA:");
            System.out.println("====================================");
            System.out.println(train_data.toSummaryString());

            BufferedReader br2 = new BufferedReader(new FileReader("data_svm" + File.separator + "test.arff"));
            Instances test_data = new Instances(br2);
            System.out.println("====================================");
            System.out.println("TEST DATA:");
            System.out.println("====================================");
            System.out.println(test_data.toSummaryString());
            // run c4.5

            train_data.setClassIndex(train_data.numAttributes() - 1);
            test_data.setClassIndex(test_data.numAttributes() - 1);
            J48 tree = new J48();
//            tree.setOptions((options));

//            tree.setOptions(weka.core.Utils.splitOptions(options));
            tree.buildClassifier(train_data);
            System.out.println(tree);
            sb.append(tree.toString());
            Evaluation train_eval = new Evaluation(train_data);
            train_eval.evaluateModel(tree, train_data);
            System.out.println(train_eval.toSummaryString("\nTrain Results\n===============\n", false));
            Evaluation test_eval = new Evaluation(test_data);
            test_eval.evaluateModel(tree, test_data);
            System.out.println(test_eval.toSummaryString("\nTest Results\n================\n", false));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new C45CLassifier();
    }
}
