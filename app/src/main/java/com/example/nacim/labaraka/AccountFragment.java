package com.example.nacim.labaraka;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.nacim.labaraka.API.CategoryAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                    //prestashop();
            }
        });

        return root;
    }

    private boolean check_input_format() {
        return check_email_format() && check_password_format();
    }

    private boolean check_email_format() {
        String pattern = "^[a-z0-9!#$%&\\'*+\\/=?^`{}|~_-]+[.a-z0-9!#$%&\\'*+\\/=?^`{}|~_-]*@[a-z0-9]+[._a-z0-9-]*\\.[a-z0-9]+$";
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(emailEditText.getText().toString());
        return m.find();
    }

    private boolean check_password_format() {
        String pattern = "^[.a-zA-Z_0-9-!@#$%\\^&*()]{5,32}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(passwordEditText.getText().toString());
        return m.find();
    }
}
