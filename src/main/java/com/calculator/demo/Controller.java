package com.calculator.demo;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller implements Initializable{

    public Label result;
    public Label prevnumber;
    private boolean check = true;
    private String operator = "";
    private String prevOperator = "";
    private boolean num1Entered = false;
    private double total = 0.0;
    private double num1 = 0.0;
    private double num2 = 0.0;
    public void number(String number)
    {
        result.setText(result.getText() + number);
    }

    public void prevNumber(String prevNumber)
    {
        prevnumber.setText(prevnumber.getText() + prevNumber);
    }

    public void prevOperator(String prevOperator){
        prevnumber.setText(prevnumber.getText() + " " + prevOperator + " ");
    }

    public double computeResult(double num1, double num2, String operator)
    {
        switch (operator)
        {
            case "+":
                return num1+num2;
            case "-":
                return num1-num2;
            case "×":
                return num1*num2;
            case "÷":
                if(num2 == 0)
                    return 0.0;
                else
                    return num1/num2;
            case "%":
                return (num1/num2)*100;
            default:
                return 0.0;
        }
    }
    public void computeProcess(ActionEvent event)
    {
        Button button = (Button) event.getSource();
        if(check)
        {
            result.setText("");
            prevnumber.setText("");
            check = false;
        }
        String value = button.getText();
        if(prevOperator.equals("="))
        {
            prevnumber.setText("");
            num1 = 0;
            num2 = 0;
            total = 0;
            num1Entered = false;
            operator = "";
            prevOperator = "";
            number(value);
            prevNumber(value);
        }
        else{
            number(value);
            prevNumber(value);
        }
    }

    public void operatorProcess(ActionEvent event)
    {
        if(!prevOperator.equals("=") && result.getText().equals(""))
          return;
        Button button = (Button) event.getSource();
        operator = button.getText();
        if(prevOperator.equals("=")){
            prevOperator = operator;
            prevOperator(operator);
            return;
        }
        if(!num1Entered) {
            num1 = Integer.parseInt(result.getText());
            prevOperator(operator);
            prevOperator = operator;
            result.setText("");
            num1Entered = true;
            return;
        }
        else {
            num2 = Integer.parseInt(result.getText());
        }
        total = computeResult(num1, num2, prevOperator);
        num1 = total;
        num2 = 0;
        int remfloat = (int)total;
        if(total-remfloat==0)
            prevnumber.setText(remfloat + " " + operator + " ");
        else
            prevnumber.setText(total + " " + operator + " ");
        result.setText("");
        prevOperator = operator;
    }

    public void operatorEquals(ActionEvent event){
        if(result.getText().equals(""))
            return;
        Button button = (Button) event.getSource();
        operator = button.getText();
        if(prevOperator.isEmpty())
            return;
        num2 = Integer.parseInt(result.getText());
        total = computeResult(num1, num2, prevOperator);
        num1 = total;
        num2 = 0;
        int remfloat = (int)total;
        if(total-remfloat==0)
            prevnumber.setText(String.valueOf(remfloat));
        else
            prevnumber.setText(String.valueOf(total));
        result.setText("");
        prevOperator = operator;
    }

    public void computeUnary(){

    }

    public void operatorUnary(){

    }

    public void exit(ActionEvent event)
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        System.exit(1);
    }

    public void clear(ActionEvent event)
    {
        Button button = (Button) event.getSource();
        String value = button.getText();
        if(value.equals("C"))
        {
            result.setText("");
            prevnumber.setText("");
        }
        num1 = 0;
        num2 = 0;
        num1Entered = false;
        operator = "";
    }

    public void backSpace(ActionEvent event)
    {
        int length = result.getText().length();
        if(length>0 && !operator.equals("="))
        {
            result.setText(result.getText().substring(0, length-1));
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }


}