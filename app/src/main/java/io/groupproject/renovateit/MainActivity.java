package io.groupproject.renovateit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

        TextView logo  = (TextView)findViewById(R.id.logo);
        TextView slogan = (TextView)findViewById(R.id.slogan);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.TFF");
        logo.setTypeface(face);
        slogan.setTypeface(face);

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent sign_up = new Intent(MainActivity.this,SignUp.class);
                startActivity(sign_up);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign_in = new Intent(MainActivity.this,signin.class);
                startActivity(sign_in);

            }
        });






    }


}