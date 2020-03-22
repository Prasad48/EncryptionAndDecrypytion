package com.bhavaniprasad.encryptionanddecryption;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncryptionDecryption extends AppCompatActivity {
    private static final String Tag = "EncryptionDecryption";
    private TextView title,resulttextview,backnavigation;
    EditText textodo;
    Button submittext;
    String process,result,decryptedresult;
    int count,store;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption_decryption);
        submittext=findViewById(R.id.submit);
        resulttextview=findViewById(R.id.resulttext);
        textodo=findViewById(R.id.edittext);
        Intent intent = getIntent();
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.lightgray));



        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E8E8E8")));
            getSupportActionBar().setCustomView(R.layout.action_bar);
            getSupportActionBar().setElevation(0);
            process=intent.getStringExtra("titlekey");
            title=findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
            imageView=findViewById(getResources().getIdentifier("backnavigationimage", "id", getPackageName()));
            imageView.setVisibility(View.VISIBLE);
            backnavigation=findViewById(getResources().getIdentifier("backnavigation","id",getPackageName()));
            backnavigation.setVisibility(View.VISIBLE);
            backnavigation.setText("Crypto String");
            title.setText(process);
            title.measure(0,0);

            backnavigation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

        }

        submittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredtext=textodo.getText().toString();
                if(process.equals("Encryption")){
                    result="";
                    for(int i=0;i<enteredtext.length();i++){
                        count=1;
                        while((i+1)<enteredtext.length() && (enteredtext.charAt(i)==enteredtext.charAt(i+1))){
                            i++;
                            count++;
                        }
                        result+=enteredtext.charAt(i)+""+count;
                    }
                    Log.d(Tag,"result:"+result);
                    resulttextview.setText(result);
                }
                else{
                    String Regex="[[a-zA-Z]{1}[1-9]+\\s?[1-9]?]*";
                    Pattern pattern =Pattern.compile(Regex);
                    Matcher matcher = pattern.matcher(enteredtext);

                    boolean matches=matcher.matches();
                    if(matches && !Character.isDigit(enteredtext.charAt(0))){
                        decryptedresult="";
                        for(int i=0;i<enteredtext.length();i++){
                            if( Character.isDigit(enteredtext.charAt(i)) ){
                                store=i;
                                int index=checkadjacentdigits(i,enteredtext);
                                String digitsinstring = enteredtext.substring(store,index+1);
                                int digits=Integer.parseInt(digitsinstring);
                                for(int count=0;count<digits;count++){
                                    System.out.print(enteredtext.charAt(store-1));
                                    decryptedresult+=enteredtext.charAt(store-1);
                                }
                                i=index;
                            }
                        }
                        resulttextview.setText(decryptedresult);
                    }
                    else{
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(EncryptionDecryption.this);
                        builder1.setMessage("Please enter valid string");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert = builder1.create();
                        alert.show();

                    }
                }
            }
        });

        textodo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    submittext.setEnabled(false);
                    resulttextview.setText("");
                }
                else {
                    submittext.setEnabled(true);
                }
            }
        });


    }

    public int checkadjacentdigits(int i,String st){
        if((i+1)<st.length() && Character.isDigit(st.charAt(i+1))){
            i+=1;
            return checkadjacentdigits(i,st);
        }
        else{
            return i;
        }
    }
}
