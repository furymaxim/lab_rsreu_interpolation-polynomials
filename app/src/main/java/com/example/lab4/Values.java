package com.example.lab4;

import net.objecthunter.exp4j.Expression;

public class Values {

    public double[] FunctionValue;

    public Values (int n, double[] PointX, Expression Func){
        double y;
        FunctionValue = new double[n];
        for (int i=0; i<n; i++){
            y = Func.setVariable("x",PointX[i]).evaluate();
            FunctionValue[i] = (double) ((y * 1000) / 1000);
        }
    }

    public Values (int num, int n, double[] PointX, Values SetOfValues){
        int series=n-num;
        double StepRes;
        FunctionValue = new double[num];
        for (int i=0; i<num; i++){
            StepRes = (SetOfValues.FunctionValue[i+1]-SetOfValues.FunctionValue[i])/(PointX[series+i]-PointX[i]);
            FunctionValue[i] = (double) Math.round(StepRes * 1000) / 1000;
        }
    }
}

