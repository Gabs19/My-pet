package app.gabriel.mypet;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    private ImageButton btnFodd;
    private ImageButton btnSpleep;
    private ImageButton btnGame;

    private TextView age;
    private TextView happyness;
    private TextView weight;

    private TextView name;

    Pet pet = new Pet();

    NumberFormat formatD = new DecimalFormat ( "0.2");

    Timer time_sad = new Timer();
    Timer time_hungry = new Timer();

    @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate ( savedInstanceState );
            setContentView ( R.layout.activity_main );

            name = (TextView ) findViewById ( R.id.name );
            name.setText ("Nome do seu pet:" + pet.getName ());

            age = ( TextView ) findViewById ( R.id.age );
            age.setText ( String.valueOf ( "Idade do Pet : " + pet.getAge () ) );

            weight = ( TextView ) findViewById ( R.id.weight );
            weight.setText ( String.valueOf ( "Seu pet Pesa : " + formatD.format ( pet.getWeight () ) + "/kg" ) );

            happyness = ( TextView ) findViewById ( R.id.feeling );
            happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + pet.getHapyness () ) );

    /*PS - dormir, brincar e comer aumentam a felicidade*/

    /* botão que aumenta o peso de acordo com quantidade de comida*/
    btnFodd = ( ImageButton ) findViewById ( R.id.btn_food );

        btnFodd.setOnClickListener ( new View.OnClickListener () {
        @Override
        public void onClick (View v) {
            pet.setWeight ( pet.getWeight () + 5 );
            pet.setHapyness ( pet.getHapyness () + 1 );
            happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + pet.getHapyness () ) );
            weight.setText ( String.valueOf ( "Seu pet Pesa : " + formatD.format ( pet.getWeight () ) + "/kg" ) );

            Toast.makeText ( MainActivity.this,pet.getName (),Toast.LENGTH_SHORT ).show ();
        }
    } );

    /*botão que controla o nivel de sono e felicidade */
    btnSpleep = ( ImageButton ) findViewById ( R.id.btn_sleep );

        btnSpleep.setOnClickListener ( new View.OnClickListener () {
        @Override
        public void onClick (View v) {
            Toast.makeText ( MainActivity.this , "Sem evento" , Toast.LENGTH_SHORT ).show ();
        }
    } );

    /* botão que controla a felicidade dependendo das brincadeiras */

    btnGame = ( ImageButton ) findViewById ( R.id.btn_play );

        btnGame.setOnClickListener ( new View.OnClickListener () {
        @Override
        public void onClick (View v) {
            double half = 1.5;
            pet.setHapyness ( pet.getHapyness () + 1 );
            happyness.setText ( String.valueOf ( "Nivel de Felicidade : " + pet.getHapyness () ) );
            if ( pet.getWeight () > 0 ) {
                pet.setWeight ( pet.getWeight () / half );
                weight.setText ( String.valueOf ( String.format ( "Seu pet Pesa : " + formatD.format ( pet.getWeight () ) + "/kg" ) ) );

                }
            }
        } );

        time_hungry.schedule ( new TimerTask () {
            @Override
            public void run () {
                eat ();
            }
        },0,5000 );

        time_sad.schedule ( new TimerTask () {
            @Override
            public void run () {
                play ();
            }
        } , 0 , 5000);

        }

    public void eat () {
        if ( pet.getWeight () < 30 ) {
            NotificationCompat.Builder messagem = new NotificationCompat.Builder ( this )
                    .setSmallIcon ( R.mipmap.ic_stat_8314___pikachu_512 )
                    .setContentTitle ( "Estou com fome! :(" )
                    .setContentText ( "Alimente seu pet" );

            Intent intent = new Intent ( this , MainActivity.class );
            TaskStackBuilder stackBuilder = TaskStackBuilder.create ( this );
            stackBuilder.addParentStack ( MainActivity.class );
            stackBuilder.addNextIntent ( intent );


            PendingIntent pendingIntent = stackBuilder.getPendingIntent ( 0 , PendingIntent.FLAG_UPDATE_CURRENT );
            messagem.setContentIntent ( pendingIntent );
            NotificationManager manager = ( NotificationManager ) getSystemService ( Context.NOTIFICATION_SERVICE );
            Notification vibration = messagem.build ();
            vibration.vibrate = new long[]{150 , 300 , 150 , 600};
            manager.notify ( 2 , messagem.build () );

            try {

                Uri ringtone = RingtoneManager.getDefaultUri ( RingtoneManager.TYPE_NOTIFICATION );
                Ringtone jingle = RingtoneManager.getRingtone ( this , ringtone );
                jingle.play ();
            } catch (Exception e) {
            }

        }
    }

    public void play() {
        if ( pet.getHapyness () <= 20 ) {
            NotificationCompat.Builder message = new NotificationCompat.Builder ( this )
                    .setSmallIcon ( R.mipmap.ic_stat_8314___pikachu_512 )
                    .setContentTitle ( "Estou infeliz! :(" )
                    .setContentText ( "Brinque um pouco com seu pet" );

            Intent intent = new Intent ( this , MainActivity.class );
            TaskStackBuilder stackBuilder = TaskStackBuilder.create ( this );
            stackBuilder.addParentStack ( MainActivity.class );
            stackBuilder.addNextIntent ( intent );


            PendingIntent pendingIntent = stackBuilder.getPendingIntent ( 0 , PendingIntent.FLAG_UPDATE_CURRENT );
            message.setContentIntent ( pendingIntent );
            NotificationManager manager = ( NotificationManager ) getSystemService ( Context.NOTIFICATION_SERVICE );
            Notification vibration = message.build ();
            vibration.vibrate = new long[]{150 , 300 , 150 , 600};
            manager.notify ( 1 , message.build () );

            try {

                Uri ringtone = RingtoneManager.getDefaultUri ( RingtoneManager.TYPE_NOTIFICATION );
                Ringtone jingle = RingtoneManager.getRingtone ( this , ringtone );
                jingle.play ();
            } catch (Exception e) {
            }
        }
    }

}

