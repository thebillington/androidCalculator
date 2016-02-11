package uk.co.thebillington.calculator;

/**
 * Created by thebillington on 10/02/16.
 */
public class Sum {

    //This is an empty class to represent a sum. Further along in the tutorials we are
    //going to make use of this class, to store detailed information about each individual
    //sum that we calculate. This will allow the user to view a detailed calculation history,
    //as well as amending sums if needed.

    private String operation;
    private int firstNum;
    private int secondNum;

    public Sum(String op, int f, int l) {
        operation = op;
        firstNum = f;
        secondNum = l;
    }

}
