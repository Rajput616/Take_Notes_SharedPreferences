package com.example.sharedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    EditText editText;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = textView.getText().toString();
                String msg =  prevText + "\n"+ editText.getText().toString();
                editText.setText("", TextView.BufferType.EDITABLE);
                SharedPreferences shrd = getSharedPreferences("demo", MODE_MULTI_PROCESS);
                SharedPreferences.Editor editor = shrd.edit();
                editor.putString( "str", msg + prevText);
                editor.apply();
                textView.setText(msg);
            }
        });
        //Get the Value of Shared Preferences Back
        SharedPreferences getShared = getSharedPreferences("demo" , MODE_MULTI_PROCESS);
        String value = getShared.getString("str", "");
        textView.setText(value);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shrd = getApplicationContext().getSharedPreferences("demo", MODE_MULTI_PROCESS);
                SharedPreferences.Editor editor = shrd.edit();
                editor.remove("str");
                editor.apply();
                Toast t = Toast.makeText(MainActivity.this, "Notes Cleared", Toast.LENGTH_SHORT);
                t.show();

                textView.setText("");
                editText.setText("", TextView.BufferType.EDITABLE);
            }
        });
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}