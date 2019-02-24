package com.example.lab4;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;


public class InEqualityExample {
    public static String Symplify(String expression) {
        String polynomial;

            String equation = expression;

            ExprEvaluator util = new ExprEvaluator(false, 100);

            IExpr result = util.evaluate(equation);

            polynomial = result.toString();

        return polynomial;

    }
}
