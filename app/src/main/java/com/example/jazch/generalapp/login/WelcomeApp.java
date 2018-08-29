package com.example.jazch.generalapp.login;
;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jazch.generalapp.R;
import com.example.jazch.generalapp.menu.menuLateral;

public class WelcomeApp extends AppCompatActivity implements View.OnClickListener{

    private Button btnWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_app);
        initializateIU();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void initializateIU(){
        btnWelcome=(Button)findViewById(R.id.btnWelcome); 
        btnWelcome.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnWelcome:
                Intent intent = new Intent(this, menuLateral.class);
                startActivity(intent);

                break;
        }
    }

    private  void tomarFoto(){

    }
}
