package app.gabriel.mypet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import app.gabriel.mypet.Animals.Game_cat;
import app.gabriel.mypet.Animals.Game_dog;


public class Intro extends AppCompatActivity {
    private static final String PREF_NAME = "PetPreferences";

    private Button btnCreate;
    private TextInputLayout name;

    Pet pet = new Pet ();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_intro );

        btnCreate = ( Button ) findViewById ( R.id.btn_create );

        name = ( TextInputLayout ) findViewById ( R.id.input_name );

        final RadioButton dog = ( RadioButton ) findViewById ( R.id.dog );
        final RadioButton cat = ( RadioButton ) findViewById ( R.id.cat );

        btnCreate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                pet.setName ( name.getEditText ().getText ().toString ().trim () );

                if ( pet.getName () == null ) {
                    Toast.makeText ( Intro.this , "Mano da um nome ao seu pet > " , Toast.LENGTH_SHORT ).show ();

                }
                else {
                    SharedPreferences intro = getSharedPreferences ( PREF_NAME , MODE_PRIVATE );
                    SharedPreferences.Editor editor = intro.edit ();

                    editor.putString ( "name" , pet.getName () );


                    if ( dog.isChecked () ) {
                        pet.setType ( "dog" );
                        Intent gameDog = new Intent ( Intro.this , Game_dog.class );
                        startActivity ( gameDog );
                        finish ();
                    }
                    if ( cat.isChecked () ) {
                        pet.setType ("cat");
                        Intent gameCat = new Intent ( Intro.this , Game_cat.class );
                        startActivity ( gameCat );
                        finish ();
                    }

                    editor.putString ( "type" , pet.getType () );

                    editor.apply ();
                }
            }
        } );
    }
}
