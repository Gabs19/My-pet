package app.gabriel.mypet.Logica;

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

import app.gabriel.mypet.Pet;
import app.gabriel.mypet.PetDie;
import app.gabriel.mypet.R;

public class Game extends AppCompatActivity {

    private static final String PREF_NAME = "PetPreferences";

    private FloatingActionButton btnFodd;
    private FloatingActionButton btnSpleep;
    private FloatingActionButton btnGame;

    private TextView age;
    private TextView happyness;
    private TextView weight;
    private TextView name;
    private TextView type;

    Pet pet = new Pet ();

    NumberFormat formatD = new DecimalFormat ( "0.2" );

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_game );

        final SharedPreferences game = getSharedPreferences ( PREF_NAME , MODE_PRIVATE );
        final SharedPreferences.Editor animals = game.edit ();

        if ( game.getString ( "type" , pet.getType () ) == ( "dog" ) ) {
            pet.setHapyness ( 50 );
            pet.setWeight ( 20 );
        }
        else if(game.getString ( "type",pet.getType () ) == ( "cat" )){
            pet.setWeight ( 10 );
            pet.setHapyness ( 25 );
        }

        animals.putInt ( "hapyness",pet.getHapyness () );
        animals.putFloat ( "weight", pet.getWeight () );


        animals.apply ();


        name = ( TextView ) findViewById ( R.id.name );
        type = (TextView) findViewById(R.id.type);
        age = ( TextView ) findViewById ( R.id.age );
        weight = ( TextView ) findViewById ( R.id.weight );
        happyness = ( TextView ) findViewById ( R.id.feeling );

        btnFodd = ( FloatingActionButton ) findViewById ( R.id.btn_food );
        btnSpleep = ( FloatingActionButton ) findViewById ( R.id.btn_sleep );
        btnGame = ( FloatingActionButton ) findViewById ( R.id.btn_play );

        name.setText ( game.getString ( "name" , pet.getName () ) );

        type.setText(game.getString("type", pet.getType()));

        age.setText ( String.valueOf ( "Idade do seu pet : " + game.getInt ( "age" , pet.getAge () ) ) );

        weight.setText ( String.valueOf ( "Seu pet Pesa : " + formatD.format ( game.getFloat ( "weight" , pet.getWeight () ) ) + "/kg" ) );

        happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + game.getInt ( "hapyness" , pet.getHapyness () ) ) );


        /*PS - dormir, brincar e comer aumentam a felicidade*/

        /* botão que aumenta o peso de acordo com quantidade de comida*/
        btnFodd.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {

                SharedPreferences food = getSharedPreferences ( PREF_NAME , MODE_PRIVATE );
                SharedPreferences.Editor food_editor = food.edit ();

                pet.setWeight ( pet.getWeight () + 5 );
                food_editor.putFloat ( "weight" , pet.getWeight () );

                pet.setHapyness ( pet.getHapyness () + 1 );
                food_editor.putInt ( "hapyness" , pet.getHapyness () );

                food_editor.commit ();

                happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + food.getInt ( "hapyness" , pet.getHapyness () ) ) );
                weight.setText ( String.valueOf ( "Seu pet Pesa : " + formatD.format ( food.getFloat ( "weight" , pet.getWeight () ) ) + "/kg" ) );

            }
        } );

        /*botão que controla o nivel de sono e felicidade */

        btnSpleep.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Toast.makeText ( Game.this , "Sem evento" , Toast.LENGTH_SHORT ).show ();
            }
        } );

        /* botão que controla a felicidade dependendo das brincadeiras */


        btnGame.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                pet.setHapyness ( pet.getHapyness () + 1 );

                float half = ( float ) 1.5;

                SharedPreferences happy = getSharedPreferences ( PREF_NAME , MODE_PRIVATE );
                SharedPreferences.Editor happy_Editor = happy.edit ();

                happy_Editor.putInt ( "hapyness" , pet.getHapyness () );


                if ( happy.getFloat ( "weight" , pet.getWeight () ) > 0 ) {
                    pet.setWeight ( pet.getWeight () / half );
                    happy_Editor.putFloat ( "weight" , pet.getWeight () );

                    happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + happy.getInt ( "hapyness" , pet.getHapyness () ) ) );
                    weight.setText ( String.valueOf ( "Seu pet Pesa : " + formatD.format ( happy.getFloat ( "weight" , pet.getWeight () ) ) + "/kg" ) );

                }

                happy_Editor.apply ();
            }
        } );



        if( game.getFloat("weight", pet.getWeight()) <= 15){
            animals.clear().apply();
            Intent pet_die = new Intent(Game.this,PetDie.class);
            startActivity(pet_die);
            finish();


        }
    }
}
