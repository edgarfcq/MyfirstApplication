package com.example.myfirstapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HomeFragment extends Fragment {
    private TextView textView;
    private EditText inputText;
    private static final String PREFS_NAME = "MyPrefsFile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize your TextView here
        textView = view.findViewById(R.id.textView);

        // You can set the text for your TextView here
        SharedPreferences prefs = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedText = prefs.getString("text", "Salut!");
        textView.setText(savedText);

        // You can also set an onClickListener for your button
        Button submitButton = view.findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Accessing the text from the EditText
                inputText = view.findViewById(R.id.inputText);
                String input = inputText.getText().toString();

                // Setting the text to the TextView
                textView.setText("Vous êtes " + input);

                // Save the text to SharedPreferences
                saveTextToPreferences(input);

                // Show a Toast message
                Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveTextToPreferences(String input) {
        SharedPreferences prefs = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("text", "Vous êtes " + input);
        editor.apply();
    }
}
