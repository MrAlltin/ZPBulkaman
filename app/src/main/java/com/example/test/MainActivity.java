package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    double ZP=0, cost=0;
    boolean fullday=false;

    TextView textView2, ZPT, costt;
    Switch aSwitch;
    Button button, writebtn;
    EditText el;

    private static Socket s;
    private static PrintWriter printWriter;

    String message = "hello";
    private static String ip = "192.168.0.104";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button2);
        writebtn=(Button)findViewById(R.id.button);
        aSwitch=(Switch)findViewById(R.id.switch1);
        costt=(TextView)findViewById(R.id.editText);
        ZPT=(TextView)findViewById(R.id.textView4);
        textView2=(TextView)findViewById(R.id.textView2);

        costt.setText("");
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fullday = true;
                } else {
                    fullday = false;
                }
            }
        });
    }

    public void calculate(View v) {
        if (costt.getText().length() != 0) {
            cost = Double.parseDouble(costt.getText().toString());
            if (fullday) {
                ZP = cost * 0.005 + 1300;

            } else ZP = cost * 0.005 + 650;
            ZPT.setText(Double.toString(ZP));
        } else Toast.makeText(getApplicationContext(), "Введите сумму!", Toast.LENGTH_SHORT).show();
    }
    public void send_text(View v){
        message=MakeMessage(MakeMessage());

        myTask mt = new myTask();

        mt.execute();

        Toast.makeText(getApplicationContext(),"Данные отправлены", Toast.LENGTH_LONG).show();


    }

    class myTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                s = new Socket(ip, 5000);
                printWriter = new PrintWriter( new BufferedWriter( new OutputStreamWriter(s.getOutputStream())), true);
                printWriter.println(message);
                printWriter.flush();
                printWriter.close();
                s.close();


            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
