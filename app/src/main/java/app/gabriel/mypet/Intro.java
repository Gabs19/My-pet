package app.gabriel.mypet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Intro extends AppCompatActivity {
//    private static final String PREF_NAME = "PetPreferences";

    private Button btnCreate;
    private TextInputLayout name;

    Pet pet = new Pet();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_intro );



        btnCreate = (Button) findViewById ( R.id.btn_create );

        name = (TextInputLayout ) findViewById ( R.id.input_name );

        btnCreate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                    pet.setName (  name.getEditText ().getText ().toString ().trim ());

                    if(pet.getName () == null){
                        Toast.makeText ( Intro.this,"Mano da um nome ao seu pet > " + pet.getName (),Toast.LENGTH_SHORT ).show ();

                    }
                    else {

//                    Toast.makeText ( Intro.this,"Seu pet Não foi criado, adicione um nome",Toast.LENGTH_SHORT ).show ();
                        Intent game = new Intent ( Intro.this , MainActivity.class );
                        startActivity ( game );
                    }
                }
            } );
        }
    }
