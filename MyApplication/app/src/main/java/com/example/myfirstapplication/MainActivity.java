package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
        inputText=(EditText) findViewById(R.id.inputText);
    }

    public void updateText(View view) {
        textView.setText("Vous êtes" + inputText.getText());
        System.out.println("Button clicked");
    }
}