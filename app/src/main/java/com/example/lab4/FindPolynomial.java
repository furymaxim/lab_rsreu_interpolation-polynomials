package com.example.lab4;

import net.objecthunter.exp4j.Expression;

public class FindPolynomial {

    public double[] PointX;
    public Values[] SetOfValues;
    private int n;
    private Expression Func;


    public FindPolynomial (int Order, double min, double max, Expression y){
        double ch;
        Func = y;
        n = Order;
        PointX = new double[n];
        if (min>max){
            ch = max;
            max = min;
            min = ch;
        }
        double h = (max-min)/(n-1);
        h=(double) Math.round(h * 100) / 100;
        PointX[0]=min;
        PointX[n-1]=max;
        for (int i=1;i<n-1;i++){
            min=min+h;
            PointX[i]=min;
        }
    }

    public String Calculation() { //Метод, создающий полином Ньютона с использованием разделенных разностей

        String result = "Simplify(";// Строка, в которой формируется полином

        String sign;

        SetOfValues = new Values[n];// Создание массива объектов Values для хранения разделенных разностей различных порядков

        SetOfValues[0]=new Values(n,PointX,Func);

        for (int i=1;i<n;i++){
            SetOfValues[i]=new Values(n-i,n,PointX,SetOfValues[i-1]);
        }

        result = result + Double.toString(SetOfValues[0].FunctionValue[0]); //Начало формирования полинома в строке, использую значения разделенных
                                                                            //из массива объектов Values
        for (int i=1;i<n;i++){

            sign = (SetOfValues[i].FunctionValue[0] >= 0) ? "+" : "";

            result = result + sign + Double.toString(SetOfValues[i].FunctionValue[0]);
            for (int j=0;j<i;j++){
                sign = (PointX[j] >= 0) ? "+" : "";
                result = result + "*(x" + sign + Double.toString(PointX[j])+")";
            }
        }

        result = result + ")";//Завершение формирования полинома, полученных полином затем упрощается с помощью метода Simplify
                              //класса InEqualityExample
        return result;

    }
}

