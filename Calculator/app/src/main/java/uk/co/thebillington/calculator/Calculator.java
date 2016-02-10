package uk.co.thebillington.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    Button zero;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;

    Button add;
    Button subtract;
    Button multiply;
    Button divide;
    Button equals;
    Button square;

    TextView output;

    String outText;

    int firstNum;
    int secondNum;
    boolean numAdded;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //Create buttons that are linked to the buttons in the
        //activity_calculator XML file
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);

        add = (Button) findViewById(R.id.plus);
        subtract = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.times);
        divide = (Button) findViewById(R.id.divide);
        equals = (Button) findViewById(R.id.equals);
        square = (Button) findViewById(R.id.squared);

        output = (TextView) findViewById(R.id.output);
        outText = "";
        output.setText(outText);

        numAdded = false;
        firstNum = 0;
        secondNum = 0;
        operation = "";
    }

    public void numberClick(View v) {
        int n = Integer.parseInt(((Button) v).getText().toString());
        outText += n;
        output.setText(outText);

        if (numAdded) {
            secondNum = secondNum * 10 + n;
        } else {
            firstNum = firstNum * 10 + n;
        }
    }

    public void buttonClick(View v) {
        String n = ((Button) v).getText().toString();
        if (n.equals("=")) {
            if (numAdded) {
                switch (operation) {
                    case "+":
                        outText += " = " + add() + " ";
                        break;
                    case "-":
                        outText += " = " + subtract() + " ";
                        break;
                }
                numAdded = false;
                output.setText(outText);
                outText += "\n";
                firstNum = 0;
                secondNum = 0;
            }
            else {
                Toast.makeText(this, "Please select an operation", Toast.LENGTH_LONG).show();
            }
        } else {
            operation = n;
            outText += " " + n + " ";
            numAdded = true;
        }

        output.setText(outText);
    }

    private int add() {
        return firstNum + secondNum;
    }

    private int subtract() {
        return firstNum - secondNum;
    }

}
