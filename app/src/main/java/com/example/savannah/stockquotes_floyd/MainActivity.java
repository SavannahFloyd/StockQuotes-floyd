package com.example.savannah.stockquotes_floyd;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private Button getInfo;
    private EditText info;
    private TextView output1;
    private TextView output2;
    private TextView output3;
    private TextView output4;
    private TextView output5;
    private TextView output6;
    private String input;
    private String outputString1;
    private String outputString2;
    private String outputString3;
    private String outputString4;
    private String outputString5;
    private String outputString6;
    private Stock inputStock;
    private View mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getInfo = (Button)findViewById(R.id.getInfo);
        output1 = (TextView)findViewById(R.id.output1);
        output2 = (TextView)findViewById(R.id.output2);
        output3 = (TextView)findViewById(R.id.output3);
        output4 = (TextView)findViewById(R.id.output4);
        output5 = (TextView)findViewById(R.id.output5);
        output6 = (TextView)findViewById(R.id.output6);
        info = (EditText)findViewById(R.id.info);
        mainView = findViewById(R.id.activity_main);
        getInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(info.getText().toString() != null) {
                    input = info.getText().toString();
                }
                if(input != null && !input.contains(" ") && input.length() <= 4){
                    inputStock = new Stock(input);
                    new Thread() {
                        public void run() {
                            try {
                                inputStock.load();

                            } catch (Exception e) {
                            }
                            outputString1 = inputStock.getSymbol();
                            outputString2 = inputStock.getName();
                            outputString3 = inputStock.getLastTradePrice();
                            outputString4 = inputStock.getLastTradeTime();
                            outputString5 = inputStock.getChange();
                            outputString6 = inputStock.getRange();
                            mainView.post(new Runnable(){
                                public void run(){
                                    output1.setText(outputString1);
                                    output2.setText(outputString2);
                                    output3.setText(outputString3);
                                    output4.setText(outputString4);
                                    output5.setText(outputString5);
                                    output6.setText(outputString6);
                                }
                            });
                            if(input.length() == 0 || inputStock.getName().contains("/")){
                                outputString1 = "Symbol Not Found";
                                outputString2 = "N/A";
                                outputString3 = "N/A";
                                outputString4 = "N/A";
                                outputString5 = "N/A";
                                outputString6 = "N/A";
                            }
                        }
                    }.start();
                }
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("output1",output1.getText().toString());
        outState.putString("output2",output2.getText().toString());
        outState.putString("output3",output3.getText().toString());
        outState.putString("output4",output4.getText().toString());
        outState.putString("output5",output5.getText().toString());
        outState.putString("output6",output6.getText().toString());
        outState.putString("edit",info.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        output1.setText(savedInstanceState.getString("output1"));
        output2.setText(savedInstanceState.getString("output2"));
        output3.setText(savedInstanceState.getString("outputt3"));
        output4.setText(savedInstanceState.getString("output4"));
        output5.setText(savedInstanceState.getString("output5"));
        output6.setText(savedInstanceState.getString("output6"));
        info.setText(savedInstanceState.getString("edit"));
    }
}
