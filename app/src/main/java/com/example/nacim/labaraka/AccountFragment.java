package com.example.nacim.labaraka;

import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by nacim on 17/04/17.
 */

public class AccountFragment extends Fragment {

    EditText emailEditText = null;
    EditText passwordEditText = null;
    Button connectionButton = null;

    public AccountFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.account_fragment, container, false);

        emailEditText = (EditText) root.findViewById(R.id.account_fragment_connection_email_edittext);
        passwordEditText = (EditText) root.findViewById(R.id.account_fragment_connection_password_edittext);
        connectionButton = (Button) root.findViewById(R.id.account_fragment_connection_button);

        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_correct_password())
                    Log.d("CONNECTION", "correct structure");
                else
                    Log.d("CONNECTION", "wrong structure");
            }
        });
        return root;
    }

    private boolean is_correct_input()
    {
        return is_correct_email() && is_correct_password();
    }

    private boolean is_correct_email()
    {
        return true;
    }

    private boolean is_correct_password()
    {
        return passwordEditText.getText().toString().length() >= 5;
    }
}
