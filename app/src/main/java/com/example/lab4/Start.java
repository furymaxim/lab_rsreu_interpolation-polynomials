package com.example.lab4;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Start {

    private Expression Func;
    private double Xmin;
    private double Xmax;
    private int Order;

    public Start (String Func_str,String Xmin_str,String Xmax_str,String Order_str){
        Func = new ExpressionBuilder(Func_str).variables("x").build();
        Xmin = Double.parseDouble(Xmin_str);
        Xmax = Double.parseDouble(Xmax_str);
        Order = Integer.parseInt(Order_str);
    }

    public String SolveTask() {
        FindPolynomial GetPolynomial = new FindPolynomial(Order,Xmin,Xmax,Func);
        InEqualityExample Simplification = new InEqualityExample();
        return Simplification.Symplify(GetPolynomial.Calculation());
    }


}
