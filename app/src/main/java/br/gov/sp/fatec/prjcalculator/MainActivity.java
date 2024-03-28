package br.gov.sp.fatec.prjcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.gov.sp.fatec.prjcalculator.databinding.ActivityMainBinding;
import br.gov.sp.fatec.prjcalculator.databinding.LayoutButtonsBinding;
import br.gov.sp.fatec.prjcalculator.databinding.LayoutInOutDisplayBinding;

public class MainActivity extends AppCompatActivity {
    final String TAG_SCREEN = "TAG-MainActivity";

    private ActivityMainBinding binding;
    private LayoutInOutDisplayBinding bindingDisplay;
    private LayoutButtonsBinding bindingButtons;
    private String input, output, newOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG_SCREEN, "Entering onCreate");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        bindingDisplay = LayoutInOutDisplayBinding.bind(binding.inOutDisplay.getRoot());
        bindingButtons = LayoutButtonsBinding.bind(binding.buttons.getRoot());
        setContentView(binding.getRoot());
    }

    public void onButtonClicked(View view) {
        Log.i(TAG_SCREEN, "Clicked onButtonClicked");
        Button btnClicked = (Button) view;
        String dataBtn = btnClicked.getText().toString();

        Log.i(TAG_SCREEN, "Value Button Clicked = " + dataBtn);

        switch (dataBtn) {
            case "C":
                input = null;
                output=null;
                newOutput=null;
                bindingDisplay.tvOutput.setText("0");
                break;

            case "^":
                resolution();
                input += "^";
                break;
            case "*":
                resolution();
                input += "*";
                break;

            case "=":
                resolution();
                break;

            case "%":
                input += "%";
                double d = Double.parseDouble(bindingDisplay.tvInput.getText().toString()) / 100;
                bindingDisplay.tvOutput.setText(String.valueOf(d));
                break;

            default:
                if (input == null) {
                    input = "";
                }
                if (dataBtn.equals("+") || dataBtn.equals("/") || dataBtn.equals("-")) {
                    resolution();
                }
                input += dataBtn;
        }
        bindingDisplay.tvInput.setText(input);

    }

    private void resolution() {
        if (input.split("\\+").length == 2) {
            String numbers[] = input.split("\\+");
            try {
                double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = removeDecimal(output);
                bindingDisplay.tvOutput.setText(newOutput);
                input = d +"";
            }catch (Exception e) {
                bindingDisplay.tvOutput.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\*").length == 2) {
            String numbers[] = input.split("\\*");
            try {
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = removeDecimal(output);
                bindingDisplay.tvOutput.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                bindingDisplay.tvOutput.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\/").length == 2) {
            String numbers[] = input.split("\\/");
            try {
                double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = removeDecimal(output);
                bindingDisplay.tvOutput.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                bindingDisplay.tvOutput.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\^").length == 2) {
            String numbers[] = input.split("\\^");
            try {
                double d = Math.pow(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
                output = Double.toString(d);
                newOutput = removeDecimal(output);
                bindingDisplay.tvOutput.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                bindingDisplay.tvOutput.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])){
                    double d = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = removeDecimal(output);
                    bindingDisplay.tvOutput.setText("-" + newOutput);
                    input = d +"";
                }
                else {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = removeDecimal(output);
                    bindingDisplay.tvOutput.setText(newOutput);
                    input = d + "";
                }
            }catch (Exception e){
                bindingDisplay.tvOutput.setText(e.getMessage().toString());
            }
        }
    }
    private String removeDecimal(String number){
        String n [] = number.split("\\.");
        if (n.length >1){
            if (n[1].equals("0")){
                number = n[0];
            }
        }
        return number;
    }
}