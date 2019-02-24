package com.example.lab4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class MainActivity extends AppCompatActivity {

    private Button mFind;
    private LineGraphSeries<DataPoint> SeriesOfPolyn;
    private LineGraphSeries<DataPoint> SeriesOfFunc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GraphView graph = (GraphView)findViewById(R.id.graph);
        final EditText Func = (EditText)findViewById(R.id.Func);
        final EditText Xmin = (EditText)findViewById(R.id.interval_1);
        final EditText Xmax = (EditText)findViewById(R.id.interval_2);
        final EditText Order = (EditText)findViewById(R.id.OrderNumber);
        final TextView AnswerText = (TextView)findViewById(R.id.Answer);

        mFind = (Button)findViewById(R.id.Find);

        mFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result;
                String Func_str = Func.getText().toString();
                String Xmin_str = Xmin.getText().toString();
                String Xmax_str = Xmax.getText().toString();
                String Order_str = Order.getText().toString();

                try {
                    Start StartProgram = new Start(Func_str,Xmin_str,Xmax_str,Order_str);
                    GraphicCreate graphics = new GraphicCreate(Xmin_str,Xmax_str,Func_str);
                    result = StartProgram.SolveTask();
                    SeriesOfPolyn = graphics.GetSeries(result);
                    SeriesOfFunc = graphics.GetSeriesOfFunc();

                    graph.removeAllSeries();
                    graph.getViewport().setScrollable(true);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.getViewport().setMinX(graphics.GetXmin());
                    graph.getViewport().setMaxX(graphics.GetXmax());

                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getViewport().setMinY(graphics.GetYmin());
                    graph.getViewport().setMaxY(graphics.GetYmax());

                    SeriesOfFunc.setColor(Color.GREEN);
                    graph.addSeries(SeriesOfFunc);

                    SeriesOfPolyn.setColor(Color.RED);
                    graph.addSeries(SeriesOfPolyn);

                    SeriesOfFunc.setTitle("Аппрокс. функция");
                    SeriesOfPolyn.setTitle("Интерп. многочлен");
                    graph.getLegendRenderer().setVisible(true);
                    graph.getLegendRenderer().setTextSize(15);
                    graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);
                    graph.getGridLabelRenderer().setNumVerticalLabels(6);
                    graph.getGridLabelRenderer().setNumHorizontalLabels(6);

                    AnswerText.setText(result.replaceAll("-","-\n-").replaceAll("\\+","+\n+"));
                } catch (Exception mistake) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Проверьте корректность введенных данных", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
}

