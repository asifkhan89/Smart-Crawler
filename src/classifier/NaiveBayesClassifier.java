package classifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class NaiveBayesClassifier {
//weka.classifiers.bayes.net.search.SearchAlgorithm

    public NaiveBayesClassifier() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data_naiveBayes" + File.separator + "InputFile.arff"));
            Instances inst = new Instances(br);
            inst.setClassIndex(inst.numAttributes() - 1);

            BufferedReader br1 = new BufferedReader(new FileReader("data_naiveBayes" + File.separator + "test.arff"));
            Instances inst1 = new Instances(br1);
            inst1.setClassIndex(inst1.numAttributes() - 1);
            NaiveBayes b = new NaiveBayes();
//            String[] options={"-Q","weka.classifiers.bayes.net.search.local.TAN"};
//            String[] options=new String[]{};
//            b.setOptions(options);
            b.buildClassifier(inst);
//            System.out.println(b);

            Instances labeled = new Instances(inst1);
            for (int cnt = 0; cnt < inst1.numInstances(); cnt++) {
                //      System.out.println("iiiii----"+test.numInstances());
                //      System.out.println(i+" inst i----"+test.instance(i));
                double clslabel = b.classifyInstance(inst1.instance(cnt));
//                   System.out.println(cnt+" Label found----"+clslabel);
                labeled.instance(cnt).setClassValue(clslabel);
            }
//            String s = "'goal','goal_attempt','card','corner','foul','offside','non_highlight','no_event'";
//            System.out.println(labeled);

            for (int i = 0; i < inst1.numInstances(); i++) {
                String s = labeled.instance(i).toString();
//                String[] e = s.split(",");
//                String event = e[e.length - 1];
//                System.out.println(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
