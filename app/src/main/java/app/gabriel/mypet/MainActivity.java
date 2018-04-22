package app.gabriel.mypet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    private ImageButton btnFodd;
    private ImageButton btnSpleep;
    private ImageButton btnGame;

    private TextView age;
    private TextView happyness;
    private TextView weight;

    Pet pet = new Pet();

    NumberFormat formatD = new DecimalFormat ( ".2");


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        age = (TextView ) findViewById ( R.id.age );
        age.setText ( String.valueOf ("Idade do Pet : " + pet.getAge () ) );

        weight = (TextView ) findViewById ( R.id.weight );
        weight.setText ( String.valueOf ( "Seu pet Pesa : " + pet.getWeight () + "/kg") );

        happyness = (TextView ) findViewById ( R.id.feeling );
        happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + pet.getHapyness () ) );

        /*PS - dormir, brincar e comer aumentam a felicidade*/

        /* botão que aumenta o peso de acordo com quantidade de comida*/
        btnFodd = (ImageButton) findViewById ( R.id.btn_food );

        btnFodd.setOnClickListener ( new OnClickListener () {
            @Override
            public void onClick (View v) {
                pet.setWeight( pet.getWeight () + 5);
                pet.setHapyness ( pet.getHapyness () + 1 );
                happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + pet.getHapyness () ) );
                weight.setText ( String.valueOf ( "Seu pet Pesa : " + formatD.format ( pet.getWeight () )  + "/kg" ));

            }
        } );

        /*botão que controla o nivel de sono e felicidade */
        btnSpleep = (ImageButton) findViewById ( R.id.btn_sleep );

        btnSpleep.setOnClickListener ( new OnClickListener () {
            @Override
            public void onClick (View v) {
                Toast.makeText ( MainActivity.this,"Sem evento",Toast.LENGTH_SHORT ).show ();

            }
        } );

        /* botão que controla a felicidade dependendo das brincadeiras */

        btnGame =(ImageButton) findViewById ( R.id.btn_play );

        btnGame.setOnClickListener ( new OnClickListener () {
            @Override
            public void onClick (View v) {
                double half = 1.5;
                pet.setHapyness (pet.getHapyness () + 1);
                happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + pet.getHapyness () ) );
                if(pet.getWeight () > 0){
                    pet.setWeight( pet.getWeight () / half );
                    weight.setText ( String.valueOf (String.format ("Seu pet Pesa : " + formatD.format ( pet.getWeight () )  + "/kg" )));

                }

            }
        } );

    }

}
