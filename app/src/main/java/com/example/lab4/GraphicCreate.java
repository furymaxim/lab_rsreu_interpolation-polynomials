package com.example.lab4;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class GraphicCreate {

    private double Xmin;
    private double Xmax;
    private double Ymin;
    private double Ymax;
    private double h;
    private LineGraphSeries<DataPoint> SeriesOfFunc;


    public  GraphicCreate (String Xmin_str, String Xmax_str, String Func_str ) {
        double x,y,Lenth;
        SeriesOfFunc = new LineGraphSeries<DataPoint>();
        Expression exp = new ExpressionBuilder(Func_str).variables("x").build();
        Xmin = Double.parseDouble(Xmin_str);
        Xmax = Double.parseDouble(Xmax_str);
        if (Xmin>Xmax){Lenth=Xmax;Xmax=Xmin;Xmin=Lenth;}
        Lenth = Xmax - Xmin;
        Xmin = Xmin - Lenth-0.5;
        Xmax = Xmax + Lenth+0.5;
        h = (Xmax - Xmin)/100;
        x = Xmin;
        Ymin = exp.setVariable("x",x).evaluate();
        Ymax = Ymin;
        for (int i = 0; i<100; i++ ) {
            x = x + h;
            y = exp.setVariable("x", x).evaluate();
            if (y<Ymin){Ymin=y-2;}
            if (y>Ymax){Ymax=y+2;}
            SeriesOfFunc.appendData(new DataPoint(x,y),false,100);
        }
    }

    public LineGraphSeries<DataPoint> GetSeries (String polynomial) {

        double x,y;

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();

        Expression exp = new ExpressionBuilder(polynomial).variables("x").build();

        x = Xmin;

        for (int i = 0; i<100; i++ ) {
            x = x + h;
            y = exp.setVariable("x", x).evaluate();
            series.appendData(new DataPoint(x,y),false,100);
        }
        return series;
    }

    public double GetXmin (){
        return Xmin;
    }
    public double GetXmax (){
        return Xmax;
    }
    public double GetYmin (){
        return Ymin;
    }
    public double GetYmax (){
        return Ymax;
    }
    public LineGraphSeries<DataPoint> GetSeriesOfFunc(){
        return SeriesOfFunc;
    }
}
