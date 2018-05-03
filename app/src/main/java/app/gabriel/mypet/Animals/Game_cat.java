package app.gabriel.mypet.Animals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import app.gabriel.mypet.PetDie;
import app.gabriel.mypet.Pets.Cat;
import app.gabriel.mypet.R;

public class Game_cat extends AppCompatActivity {
    private static final String PREF_NAME = "PetPreferences";

    private FloatingActionButton btnFodd;
    private FloatingActionButton btnSpleep;
    private FloatingActionButton btnGame;

    private TextView age;
    private TextView happyness;
    private TextView weight;
    private TextView name;

    Cat pet = new Cat ();

    NumberFormat formatD = new DecimalFormat ( "0.2" );

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_game_cat );

        final SharedPreferences cat = getSharedPreferences ( PREF_NAME , MODE_PRIVATE );
        final SharedPreferences.Editor cat_editor = cat.edit ();

        name = ( TextView ) findViewById ( R.id.name );
        age = ( TextView ) findViewById ( R.id.age );
        weight = ( TextView ) findViewById ( R.id.weight );
        happyness = ( TextView ) findViewById ( R.id.feeling );

        btnFodd = ( FloatingActionButton ) findViewById ( R.id.btn_food );
        btnSpleep = ( FloatingActionButton ) findViewById ( R.id.btn_sleep );
        btnGame = ( FloatingActionButton ) findViewById ( R.id.btn_play );

        name.setText ( cat.getString ( "name" , pet.getName () ) );

        age.setText ( String.valueOf ( "Idade do seu pet : " + cat.getInt ( "age" , pet.getAge () ) ) );

        weight.setText ( String.valueOf ( "Seu pet Pesa : " + formatD.format ( cat.getFloat ( "weight" , pet.getWeight () ) ) + "/kg" ) );

        happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + cat.getInt ( "hapyness" , pet.getHapyness () ) ) );


        /*PS - dormir, brincar e comer aumentam a felicidade, porém como gatos gostam mais de dormir. A ação de dormir o deixará mais feliz*/

        /* botão que diminui a fome e aumenta o peso de acordo com quantidade de comida*/

        btnFodd.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {

                pet.setWeight ( pet.getWeight () + 1 );
                cat_editor.putFloat ( "weight" , pet.getWeight () );

                pet.setHapyness ( pet.getHapyness () + 1 );
                cat_editor.putInt ( "hapyness" , pet.getHapyness () );

                pet.setHungry ( pet.getHungry () - 1 );
                cat_editor.putInt ( "hungry" , pet.getHungry () );

                cat_editor.apply ();

                happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + cat.getInt ( "hapyness" , pet.getHapyness () ) ) );
                weight.setText ( String.valueOf ( "Seu pet Pesa : " + formatD.format ( cat.getFloat ( "weight" , pet.getWeight () ) ) + "/kg" ) );

            }
        } );

        /*botão que controla o nivel de sono e felicidade */

        btnSpleep.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Toast.makeText ( Game_cat.this , "Sem evento" , Toast.LENGTH_SHORT ).show ();
            }
        } );

        /* botão que controla a felicidade dependendo das brincadeiras */

        btnGame.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {

                pet.setHapyness ( pet.getHapyness () + 1 );
                cat_editor.putInt ( "hapyness" , pet.getHapyness () );

                float half = ( float ) 1.5;

                if ( cat.getFloat ( "weight" , pet.getWeight () ) > 0 ) {
                    pet.setWeight ( pet.getWeight () / half );
                    cat_editor.putFloat ( "weight" , pet.getWeight () );

                    pet.setHungry ( pet.getHungry () + 1 );
                    cat_editor.putInt ( "hungry" , pet.getHungry () );

                    happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + cat.getInt ( "hapyness" , pet.getHapyness () ) ) );
                    weight.setText ( String.valueOf ( "Seu pet Pesa : " + formatD.format ( cat.getFloat ( "weight" , pet.getWeight () ) ) + "/kg" ) );
                }

                cat_editor.apply ();
            }
        } );

        if ( cat.getInt ( "hapyness" , pet.getHapyness () ) <= 0 || cat.getInt ( "hungry" , pet.getHungry () ) >= 50 ) {

            cat_editor.clear().apply ();

            Intent cat_die  = new Intent ( Game_cat.this, PetDie.class );
            startActivity ( cat_die );
            finish ();

        }
    }
}
