package com.example.acalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.editTextId);
        display.setShowSoftInputOnFocus(false);
        display.requestFocus();
        display.setText(getString(R.string.display));
        display.setOnClickListener(this);
    }
    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);

        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }
        display.setSelection(cursorPos + 1);

    }

    public void zeroBtn(View view) {
        updateText("0");
    }

    public void oneBtn(View view) {
        updateText("1");
    }

    public void twoBtn(View view) {
        updateText("2");

    }

    public void threeBtn(View view) {
        updateText("3");

    }

    public void fourBtn(View view) {
        updateText("4");
    }

    public void fiveBtn(View view) {
        updateText("5");

    }

    public void sixBtn(View view) {
        updateText("6");

    }

    public void sevenBtn(View view) {
        updateText("7");

    }

    public void eightBtn(View view) {
        updateText("8");
    }

    public void nineBtn(View view) {
        updateText("9");
    }

    public void acBtn(View view) {
        display.setText("");
    }

    public void parenthesisBtn(View view) {
        int cursorPos=display.getSelectionStart();
        int openPar=0;
        int closePar=0;
        int textLen=display.getText().length();
        for(int i=0;i<cursorPos;i++){
            if(display.getText().toString().charAt(i) == '('){
                openPar++;
            }
            if(display.getText().toString().charAt(i) == ')'){
                closePar++;
            }
        }
        if(openPar==closePar|| display.getText().toString().charAt(textLen - 1) == '('){
            updateText("(");
        }else if(openPar>closePar&& display.getText().toString().charAt(textLen - 1) != '('){
            updateText(")");
        }
        display.setSelection(cursorPos+1);
    }

    public void exponentBtn(View view) {
        updateText("^");
    }

    public void divideBtn(View view) {
        updateText("÷");
    }

    public void multiplyBtn(View view) {
        updateText("×");
    }

    public void subtractBtn(View view) {
        updateText("-");
    }

    public void addBtn(View view) {
        updateText("+");
    }

    public void plusMinusBtn(View view) {
        updateText("√");
    }

    public void pointBtn(View view) {
        updateText(".");
    }

    public void equalBtn(View view) {
        String userExp=display.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("×","*");
        userExp=userExp.replaceAll("√","sqrt(");

        if(userExp.contains("sqrt(")){
            int index=0;
            for(int i=0;i<userExp.length();i++){
                if(userExp.charAt(i)=='s'&&userExp.charAt(i+1)=='q'&&userExp.charAt(i+2)=='r'&&userExp.charAt(i+3)=='t'&&userExp.charAt(i+4)=='('){
                    index=i+4;
                    break;
                }
            }
            for(int i=index+1;i<userExp.length();i++){
                if((userExp.charAt(i)>='0'&&userExp.charAt(i)<='9')||userExp.charAt(i)=='.'){
                    index++;
                }else{
                    break;
                }
            }
            String leftStr=userExp.substring(0,index+1);
            String rightStr=userExp.substring(index+1);
            userExp=leftStr+")"+rightStr;
        }
        Expression exp=new Expression(userExp);
        double ans=exp.calculate();
        int x=(int) ans;
        String result;
        if(x-ans==0){
             result=String.valueOf(x);
        }else{
            result=String.valueOf(ans);
        }
//        Log.d("ans ",String.valueOf(ans));
//
        display.setText(result);
        display.setSelection(result.length());
    }

    public void backSpaceBtn(View view) {
        int cursorPos=display.getSelectionStart();
        int textLen=display.getText().length();

        if(cursorPos != 0 && textLen!= 0){
            SpannableStringBuilder selection= (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }

    @Override
    public void onClick(View v) {
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText("");
        }
    }
}