package app.gabriel.mypet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import app.gabriel.mypet.Logica.Game;

public class SplashIntro extends AppCompatActivity {

    private static final String PREF_NAME = "PetPreferences";

    private ProgressBar rodinha;

    Pet pet = new Pet ();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash_intro );

        rodinha = ( ProgressBar ) findViewById ( R.id.rodinha );
        rodinha.getIndeterminateDrawable ().setColorFilter ( Color.parseColor ( "#FF4C35" ) , PorterDuff.Mode.MULTIPLY );

        new Handler ().postDelayed ( new Runnable () {


            @Override
            public void run () {
                SharedPreferences splash = getSharedPreferences ( PREF_NAME , MODE_PRIVATE );

                if ( splash.getString ( "name" , pet.getName () ) == null && splash.getString ( "type" , pet.getType () ) == null ) {
                    Intent Intro = new Intent ( SplashIntro.this , Intro.class );
                    startActivity ( Intro );
                    finish ();
                }

                else if ( splash.getString ( "type" , pet.getType () ) == "dog" ) {
                    Intent Game = new Intent ( SplashIntro.this , app.gabriel.mypet.Animals.Game_dog.class );
                    startActivity ( Game );
                    finish ();
                }

                else {
                    Intent Game = new Intent ( SplashIntro.this , app.gabriel.mypet.Animals.Game_cat.class );
                    startActivity ( Game );
                    finish ();
                }

//                else if(splash.getString ( "type",pet.getType () ) ==  "cat"  && splash.getString ( "name",pet.getName () ) != null){
//                    Intent Game = new Intent ( SplashIntro.this , app.gabriel.mypet.Animals.Game_cat.class );
//                    startActivity ( Game );
//                    finish ();
//                }

            }

        } , 1500 );

    }
}
