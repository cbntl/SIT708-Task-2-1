package com.example.sit708_task_2_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//Student Name : Claire Bantilan
//Student ID : 221039047
//Task : Task 2.1
public class MainActivity extends Activity {

    Spinner convertFromSpinner, convertToSpinner;
    EditText inputEditValue;
    TextView convertResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialising the UI components
        convertFromSpinner = (Spinner) findViewById(R.id.convert_from);
        convertToSpinner = (Spinner) findViewById(R.id.convert_to);
        inputEditValue = (EditText) findViewById(R.id.convert_value);
        convertResult = (TextView) findViewById(R.id.result_text);
        Button convertButton = (Button) findViewById(R.id.convert_button);

        // set the spinners according to ConversionUnits.xml
        ArrayAdapter<CharSequence> convertFromAdapter = ArrayAdapter.createFromResource(this, R.array.conversionUnits, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> convertToAdapter = ArrayAdapter.createFromResource(this, R.array.conversionUnits, android.R.layout.simple_spinner_item);
        convertFromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertFromSpinner.setAdapter(convertFromAdapter);
        convertToSpinner.setAdapter(convertToAdapter);
        convertFromSpinner.setSelection(0);
        convertToSpinner.setSelection(0);

        // Set the listeners for the button click
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = 0;
                String convertFromUnit = convertFromSpinner.getSelectedItem().toString();
                String convertToUnit = convertToSpinner.getSelectedItem().toString();
                String valueString = inputEditValue.getText().toString();

                if (valueString.isEmpty() || Double.parseDouble(valueString)==0) {
                    Toast.makeText(getApplicationContext(), "Input Invalid: Must input a valid number.", Toast.LENGTH_SHORT).show();
                    result = 0;
                    return;
                }

                if (convertFromUnit.equals(convertToUnit)) {
                    Toast.makeText(getApplicationContext(), "Input Invalid: Source and Destination Unit must not be the same", Toast.LENGTH_SHORT).show();
                    result = 0;
                    return;
                }


                double value = 0;
                try {
                    value = Double.parseDouble(valueString);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Kindly input an appropriate number for conversion", Toast.LENGTH_SHORT).show();
                    return;
                }



                //Conversion Logic
                if (convertFromUnit.equals("Centimeter")) {
                    if (convertToUnit.equals("Centimeter")) {
                        result = value;
                    } else if (convertToUnit.equals("Yard")) {
                        result = value / 91.44;
                    } else if (convertToUnit.equals("Foot")) {
                        result = value / 30.48;
                    } else if (convertToUnit.equals("Inch")) {
                        result = value / 2.54;
                    }

                } else if (convertFromUnit.equals("Yard")) {
                    if (convertToUnit.equals("Centimeter")) {
                        result = value * 91.44;
                    } else if (convertToUnit.equals("Yard")) {
                        result = value;
                    } else if (convertToUnit.equals("Foot")) {
                        result = value * 3;
                    } else if (convertToUnit.equals("Inch")) {
                        result = value * 36;
                    }
                } else if (convertFromUnit.equals("Foot")) {
                    if (convertToUnit.equals("Centimeter")) {
                        result = value * 30.48;
                    } else if (convertToUnit.equals("Yard")) {
                        result = value / 3;
                    } else if (convertToUnit.equals("Foot")) {
                        result = value;
                    } else if (convertToUnit.equals("Inch")) {
                        result = value * 12;
                    }
                } else if (convertFromUnit.equals("Inch")) {
                    if (convertToUnit.equals("Centimeter")) {
                        result = value * 2.54;
                    } else if (convertToUnit.equals("Yard")) {
                        result = value / 2.54;
                    } else if (convertToUnit.equals("Foot")) {
                        result = value * 12;
                    } else if (convertToUnit.equals("Inch")) {
                        result = value;
                    }
                }

                //if there is no result after calculation, display an error message
                if (result == 0) {
                    convertResult.setText("Input Invalid");
                    Toast.makeText(getApplicationContext(), "Input Invalid: Don't convert one type of units to another type of units.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Display the result
                convertResult.setText(String.valueOf(result)+" "+convertToUnit.toString());
                Toast.makeText(getApplicationContext(), "Successfully converted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}