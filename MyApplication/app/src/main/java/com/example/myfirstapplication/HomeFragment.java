package com.example.myfirstapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {
    TextView textView;
    EditText inputText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate((R.layout.fragment_home), container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize your TextView here
        textView = view.findViewById(R.id.textView);

        // You can set the text for your TextView here
        textView.setText("Salut!");

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

                // Show a Toast message
                Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}