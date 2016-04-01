package de.app.mepa.mepa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import de.app.mepa.menu.Menu;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Button für den Button Menü in der Main Activity
    private Button btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Buttonverweis der Schaltfläche zur Variable
            btn_menu = (Button)findViewById(R.id.btn_menu);
        // Event abfangen
            btn_menu.setOnClickListener(this);

    }

//Muss zwingend implementiert werden für den OnClickListener
    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button
        int ce = v.getId();
            //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        if (ce == R.id.btn_menu){
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
        }
    }
}
