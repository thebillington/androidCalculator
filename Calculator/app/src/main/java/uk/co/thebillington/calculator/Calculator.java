package uk.co.thebillington.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    //Firstly create a field that stores a TextView, output,
    //which we are going to point to the textView in our
    //activity_calculator.xml file.
    //Since this variable does not need to be accessed from
    //external classes we are making it private for now.
    //When our code evolves we may need to create getters and
    //setters for it in case we want to access it externally
    private TextView output;

    //Next create a String to hold our String representation
    //of calculations
    private String outText;

    //Create two ints, firstNum and secondNum, which are going
    //to store the values of the two numbers input by the user
    //for a basic calculation
    private int firstNum;
    private int secondNum;

    //Create a boolean to state whether the user has finished
    //inputting their first number. We know the user has finished
    //inputting their first number as soon as an operation is called
    //i.e if the User types in: "1, 2, 4, +, 5, 6, 7, =" then we will
    //set the numAdded boolean to true when the '+' sign is input, as
    //we know that the user has finished selecting their first number
    private boolean numAdded;

    //Create a String to hold the selected operation made by the user,
    //so that when the '=' sign is clicked we can do a calculation based
    //on the operation chosen, by calling the add() or subtract() method
    private String operation;

    //This is an overidden method from our base class, AppCompatActivity.
    //The onCreate() method allows us to instantiate all of the objects
    //we are going to use to make the calculator work.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Call the onCreate method of the base class
        //The 'super' notation just tells the compiler to look
        //at whatever object is the parent of our Calculator
        //class (Activity). In this case we are calling the
        //AppCompatActivity.onCreate() method
        super.onCreate(savedInstanceState);

        //Set the contentView of our activity to the one we
        //created for the calculator, by pointing it to the
        //activity_calcultor.xml file in R.layout
        setContentView(R.layout.activity_calculator);

        //Set out output TextView to the TextView object we
        //created in the activity_calculator.xml file
        //Now we can call things like output.setText("hello")
        //to change the state of the object
        //The findViewById() method returns the View that we
        //point to at R.id.output. Since a TextView is an
        //extension of the View class, we can cast our object
        //to a TextView
        output = (TextView) findViewById(R.id.output);

        //Set our initial output text equal to an empty String,
        //and then pass the value to our output TextView object
        outText = "";
        output.setText(outText);

        //Initialise numAdded equal to false, as we want to add
        //our first number before our second number. Set our first
        //and second numbers equal to 0, ready for user input.
        //Set our operation to an empty String. In our main code
        //we will set the operation equal to +, -, *, / or ^2
        numAdded = false;
        firstNum = 0;
        secondNum = 0;
        operation = "";

        //Ignore this until the first section of tutorial code has been completed.
        //It is calling the buttonExample() method, which is used to show you how
        //you cans et the onClickListener of a button inside the Java code, rather
        //than in your XML.
        buttonExample();

    }

    //Create a new public method, numberClick() that can be accessed
    //by the onClick tag in our xml file. The numberClick() takes a View
    //v as input, where v is the object that was clicked. Similar to how
    //we cast the object returned by findViewById() to a TextView, we can
    //cast the input View of our numberClick() method to a Button.
    //However we only need one piece of information from our button; it's
    //int value. This means we aren't going to store our Button as an object,
    // we are just going to take the piece of information we need, with one
    // line of code. We take our View v, and cast to a Button:
    //(Button) v;
    //We surround this cast operation with brackets, so that we can call
    //getText() on the Button object that is returned from the cast:
    //((Button) v).getText();
    //We then call the toString method on the returned text, so that the
    //characters are returned as a String:
    //((Button) v).getText().toString();
    //We know that the text in our button is an int, ranging from 0-9,
    //therefore we can now cast the String to an int, using the parseInt()
    //method in the Integer class:
    //Integer.parseInt(((Button) v).getText().toString());
    //And finally we store the value as an int, n:
    //int n = Integer.parseInt(((Button) v).getText().toString());
    public void numberClick(View v) {
        //Retrieve the value of the input View button
        int n = Integer.parseInt(((Button) v).getText().toString());

        //Now for the clever part. If the user types 1 and then 0, this
        //method is called twice. Since we initialised our outText
        //to an empty String, when the 1 is clicked and this method is called,
        //the '1' is concatenated to the outText String, using the +=
        //operator. This gives us:
        //outText = "" + "1" = "1"
        //The second time this method is called, outText is already equal
        //to "1", therefore when we concatenate the zero, our output text
        //becomes equal to "10":
        //outText = "1" + "0" = "10"
        outText += n;
        output.setText(outText);

        //We have a String representation of our number now that the user
        //can read, but we don't have any useful information to do a computation.
        //If we have an int 1, and an int 0, we need to be able to work out how many
        //'tens' each number represents.
        //We can do this easily by adding our latest number input, to our original
        //input multiplied by 10.
        //Firstly when this method is called we need to check if we are looking at our
        //firstNum or secondNum, by checking the numAdded boolean.
        //Next we set our value of firstNum or secondNum equal to itself, multiplied by
        //10, plus our new value. Let's see how it works:
        //If the user typed in 2, 3, 2 then we know we need to represent the number "232".
        //The first time this method is called, firstNum is initialised to 0, therefore we
        //calculate firstNum as:
        //firstNum = firstNum * 10 + n = 0 * 10 + 2 = 2
        //Where n is the value of the button that was clicked. Now when our 3 is clicked
        //firstNum is already equal to 2, so we calculate:
        //firstNum = firstNum * 10 + n = 2 * 10 + 3 = 23
        //NOTE that the precedence rules of java allowed me to omit the brackets in the
        //(firstNum * 10) + n, as a multiplication is always done before an addition.
        //When our last number is clicked, 2, we do the same thing again:
        //firstNum = firstNum * 10 + n = 23 * 10 + 2 = 232
        //We now have an integer representation of the number the user input, as well as
        //a String representation.
        if (numAdded) {
            secondNum = secondNum * 10 + n;
        } else {
            firstNum = firstNum * 10 + n;
        }
    }

    //Create another new method, buttonClick() which will be called when any
    //of our mathematical operation buttons are clicked. Similar to the
    //numberClicked() method, we are going to extract from the Button which
    //operation was selected. Instead of casting to an int however, we just need
    //the String:
    //String n = ((Button) v).getText().toString();
    //If the '+' button was clicked then this would return a String of n, equal to
    // "+"
    //We can then run a check on n, to see what it is equal to. Notice that we can't
    //call:
    //if(n == "+")
    //The reason for this is that the == operator is used to check whether two objects
    //share the same memory location. This works for basic types, as an int a = 1 will
    //always be equal to an int b = 1:
    //if(a == b)
    //However with two Strings, String c = "yes" and String d = "yes", we have no guarantee
    //that a condition:
    //if(c == d)
    //will return true. Therefore we use the built in String comparison method, equals:
    //if(c.equals(d))
    //which will always return true if c and d share the same letters, in the same order.
    public void buttonClick(View v) {

        //Obtain the operator button that was clicked
        String n = ((Button) v).getText().toString();

        //Check if the equals button was pressed. If it was, we have to run a check
        //that we have had two numbers input already as you can't run the equals operation
        //until we have had two numbers, and an operation chosen.
        if (n.equals("=")) {
            if (numAdded) {
                //If the equals key has been pressed, then check our operation variable
                //to see what operation was chosen by the user. Then we can select whether
                //to call our add() or subtract() methods, and concatenate it's output
                //to our text view. Don't worry that we haven't set the operation variable
                //yet, we will do that outside of the if(n.equals("=")) {} block
                switch (operation) {
                    case "+":
                        outText += " = " + add() + " ";
                        break;
                    case "-":
                        outText += " = " + subtract() + " ";
                        break;
                    //case "*":
                        //YOUR CODE
                        //break;
                }

                //Once we have run the operation, re-initialise our variables ready for
                //the next calculation to be run. Concatenate a new line "\n" onto our
                //output text, this way we can show the full history of calculations
                //made by the user.
                numAdded = false;
                output.setText(outText);
                outText += "\n";
                firstNum = 0;
                secondNum = 0;
            }
            //If the user presses the equals key, but hasn't added a second number, then
            //create a new Toast and show it, telling the user to select an operation.
            else {
                Toast.makeText(this, "Please select an operation", Toast.LENGTH_LONG).show();
            }
        }
        //If the code has reached this else block, then it means that the key pressed
        //wasn't the equals key. Therefore we want to set out operation String equal
        //to the operation selected. If the + key is pressed, then:
        //operation = "+"
        //The stored value of operation is then used in the previous block when the
        //equals key is pressed, to decide which calculation to do on our two numbers.
        //We also concatenate our operation onto the output string, so the user can
        //see which operation they chose. Also once the operation has been selected,
        //we set numAdded equal to true, which will allow the user to start typing
        //their next number.
        else {
            operation = n;
            outText += " " + n + " ";
            numAdded = true;
        }

        //Once everything has been calculated and concatenated, set the output equal to our
        //output string. This is run every time any operation or number is clicked.
        output.setText(outText);
    }

    //Create a private method add() which returns the value of our firstNum + secondNum.
    private int add() {
        return firstNum + secondNum;
    }

    //Also create a private method subtract() which returns the value of our firstNum - secondNum.
    private int subtract() {
        return firstNum - secondNum;
    }

    //Inside the equals block of code, I have created a new case for multiply, which
    //you should complete yourself. Also add in cases for / and ^2, and see if you can
    //write implementations for the below functions to implement the rest of our operations.

    //private int multiply() {YOUR CODE}

    //private int divide() {YOUR CODE}

    //private int square() {YOUR CODE}

    //This is called whenever you click the R button in the XML
    //Change the code inside to do anything you want when the R button is clicked
    //such as appending something onto the outText
    public void yourMethod(View v) {
        Toast.makeText(this, "YOU CLICKED ON YOUR BUTTON", Toast.LENGTH_LONG).show();
        //outText += "You can add something you want to say";
        //output.setText("Or change the text completely";
    }

    //This method is called to setup the example buttons
    private void buttonExample() {

        //Create a new button q, and set it's value equal to that of the
        //object returned when we call findViewById on our q button inside
        //the activity_calculator.xml file.
        //Q has to be final so that we can access it later on, when we provide
        //code to our OnClickListener interface. Since we always want q to point
        //to the same button, making it final doesn't matter, as we are never
        //going to want to change the value that q points to in memory.
        final Button q = (Button) findViewById(R.id.q);

        //Set the onClickListener of q
        //Notice how the object we gave to q had to be a new OnClickListener,
        //the reason for this is that OnClickListener is not a class we can
        //instantiate, it is an interface. This means it provides the basic
        //properties of an onClickListener, but we have to override the
        //onClick() method ourselves.
        //Both methods of creating an onClick method for a button are viable,
        //but unless you are creating one onClick method for multiple buttons
        //I recommend using this technique
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When the button is clicked show a simple toast
                //Notice that we have to call Calculator.this instead of this when providing
                //the Toast with a context. A context is used in Android to pass information
                //to activities and the OS about where requests have come from. We had to call
                //Calculator.this instead of just 'this' like we did before, because when you
                //are writing an interface you are inside of the Interface object, rather than
                //the object you are writing the interface in.
                //If I called 'this' instead of 'Calculator.this' then the View.OnClickListener
                //object would be passed as context, instead of our Activity.
                Toast.makeText(Calculator.this, "You clicked the " + q.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        //Here is a faster way of providing an onClickListener, however it stops us from using
        //our stored button to access the properties of the button that was clicked. This doesn't
        // matter, as we can just use our View v instead, and cast this to a button to extract
        // information from the button that was clicked
        findViewById(R.id.w).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Calculator.this, "You clicked the " + ((Button)v).getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        //Now time for you to have a go, here's an empty OnClickListener that you can set to do
        //anything when the E button is clicked
        findViewById(R.id.e).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Make your button do something
                //You can access the button by calling:
                //((Button)v).doSomething()
            }
        });

    }

}
