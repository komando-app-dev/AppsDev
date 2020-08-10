package tomerbu.edu.lec3calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

//Controller:
public class MainActivity extends AppCompatActivity {
    private TextView tvDisplay;
    private boolean userStartedTyping = false;
    //Model instance
    private CalculatorBrain brain = new CalculatorBrain();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
    }

    // A number was pressed (1, 2, 3, 4, 5, 6, 7, 9, 0)

    //34234234234234.4554324234 -> tvDisplay
    public void digitTapped(View view) {
        CharSequence btnText = getButtonText(view);
        //handle 0 at the beginning:
        //append it to the display
        if (userStartedTyping) {
            tvDisplay.append(btnText);
        } else {
            if (btnText.equals("0")) {
                return;
            }
            //first time: 0
            tvDisplay.setText(btnText);
            userStartedTyping = true;
        }
    }

    //get the text from the view that was tapped
    private String getButtonText(View view) {
        //cast the view to a button
        Button tappedButton = (Button) view; //if it's not a button -> crash my app
        //return the text on the button:
        return tappedButton.getText().toString();
    }
    //user<->View
    //view<->controller
    //controller<->Model


    //* + - pressed after there was text in the display
    public void performOperation(View view) { //+=-*π
        if (userStartedTyping) {
            //get the displayValue
            Double displayValue = getDisplayValue();
            //brain -> setOperand
            brain.setOperand(displayValue);
            //next time the user starts typing the text will be replaced.
            userStartedTyping = false;
        }
        String mathSymbol = getButtonText(view);//* - +
        brain.performOperation(mathSymbol);

        Double result = brain.getResult();
        if (result != null) {
            setDisplayValue(result);
        }
    }

    private Double getDisplayValue() {
        //get the text from the text view
        String displayText = tvDisplay.getText().toString();
        //convert it to a double
        try {
            return Double.parseDouble(displayText);
        } catch (NumberFormatException e) {
            return null;//return null instead of crashing.
        }
    }

    //change the display text using a double.
    private void setDisplayValue(Double value) {
        int val = value.intValue();

        //if there is residue:
        if (value - val > 0.001) {
            String formattedString = String.format(Locale.getDefault(), "%.3f", value);
            tvDisplay.setText(formattedString);
        } else {
            //no formatter as int:
            tvDisplay.setText(String.valueOf(val));
        }
    }


    //a decimal point .
    //3.
    public void decimalTapped(View view) {
        if (!userStartedTyping){
            tvDisplay.setText("0.");
            userStartedTyping = true;
        }else {
            String displayText = getDisplayText();
            if (!displayText.contains(".")){
                tvDisplay.append(".");
            }
        }
    }

    private String getDisplayText(){
        return tvDisplay.getText().toString();
    }
    //<-delete a single char
    public void backSpace(View view) {
        if (!userStartedTyping){return;}

        String text = getDisplayText();//subString
        if (text.length() == 1){
            setDisplayValue(0.0);
            userStartedTyping = false;
        }else {
            //remove one char:
            text = text.substring(0, text.length() - 1);
            tvDisplay.setText(text);
        }
    }



    //clear everything (AC = All Clear)
    public void clear(View view) {
        setDisplayValue(0.0);
        userStartedTyping = false;
        brain = new CalculatorBrain();
        //brain.clear()
        //pb0 = null, accumulator = null
    }


}