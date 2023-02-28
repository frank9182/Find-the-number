package com.myapplication.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber;
    private Button button_compare;
    private TextView txt_result;
    private ProgressBar progressBar;
    private TextView txt_history;
    private int score ;
    private int searchvalue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        button_compare = (Button) findViewById(R.id.button_compare);
        txt_result = (TextView) findViewById(R.id.txt_result);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txt_history = (TextView) findViewById(R.id.txt_history);

        button_compare.setOnClickListener(btncomparelistener);
        init();

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double price = 1_000_000.01;

        Log.i("Debug", formatter.format(price));

        DateFormat dataFormatter = DateFormat.getDateTimeInstance();
        Log.i("Debug", dataFormatter.format(new Date()));
    }

    public void init() {

        score = 0;
        searchvalue = 1 + (int) (Math.random() * 100);
        Log.i("Debug", "Searched value :" + searchvalue);

        txt_result.setText("");
        txt_history.setText("");
        progressBar.setProgress(score);
        editTextNumber.setText("");
        editTextNumber.requestFocus();
    }

        private void    congratulations(){

        txt_result.setText(R.string.strCongrat);

            AlertDialog retryalert = new AlertDialog.Builder(this).create();
            retryalert.setTitle(R.string.app_name);
            retryalert.setMessage(getString(R.string.strMessage, score));

            retryalert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {init();

                }
            });
            retryalert.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.strNo), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) { finish();

                }
            });

            retryalert.show();
        }

    private View.OnClickListener btncomparelistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Log.i("Debug", "Clicked button");



            String streditTextNumber = editTextNumber.getText().toString();
            if(streditTextNumber.equals("")) return;

            txt_history.append(streditTextNumber + "\r\n");
            progressBar.incrementProgressBy(1);
            score++;

            int enterredValue = Integer.parseInt(streditTextNumber);
            if(enterredValue == searchvalue){
                congratulations();

            }else if(enterredValue > searchvalue){
                txt_result.setText(R.string.strSmaller);

            }else if(enterredValue < searchvalue){
                txt_result.setText(R.string.strGreater);
            }

            editTextNumber.setText("");
            editTextNumber.requestFocus();
        }
    };
}
