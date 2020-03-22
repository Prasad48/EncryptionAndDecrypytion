package com.bhavaniprasad.encryptionanddecryption;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView title;
    Button encrypt,decrypt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encrypt=findViewById(R.id.encrypt);
        decrypt=findViewById(R.id.decrypt);

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

            title=findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
            title.setText("Crypto String");
            title.measure(0,0);

        }

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent encryptintent = new Intent(MainActivity.this, EncryptionDecryption.class);
                encryptintent.putExtra("titlekey","Encryption");
                startActivity(encryptintent);
            }
        });
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent decryptintent = new Intent(MainActivity.this, EncryptionDecryption.class);
                decryptintent.putExtra("titlekey","Decryption");
                startActivity(decryptintent);
            }
        });
    }

}
