package com.calculator.demo;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller implements Initializable{
    
    @FXML
    private Label result;
    @FXML
    private Label prevnumber;
    private boolean check = true;
    private String operator = "";
    double total = 0.0;
    double num1 = 0.0;
    double num2 = 0.0;
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
                if(num1-(int)num1 > 0 || num2-(int)num2 > 0)
                    return num1+num2;
                else
                    return (int)(num1+num2);
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
        number(value);
        prevNumber(value);
    }

    public void operatorProcess(ActionEvent event)
    {
        Button button = (Button) event.getSource();
        String value = button.getText();
        if(!value.equals("=")) {
            if(!operator.isEmpty())
                return;
            operator = value;
            prevOperator(operator);
            num1 = Double.parseDouble(result.getText());
            result.setText("");
        }
        else{
            if(operator.isEmpty())
                return;
            double num2 = Double.parseDouble(result.getText());
            total = computeResult(num1, num2, operator);
            result.setText(String.valueOf(total));
        }
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
    }

    public void backSpace(ActionEvent event)
    {
        int length = result.getText().length();
        if(length>0 && !operator.equals("="))
        {
            result.setText(result.getText().substring(0, length-1));
        }
        else if(operator.equals("="))
            prevnumber.setText("");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }


}