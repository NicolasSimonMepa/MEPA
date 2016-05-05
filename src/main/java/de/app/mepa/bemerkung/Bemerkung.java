package de.app.mepa.bemerkung;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.notfallsituation.notfallsituation;
import de.app.mepa.upload.Upload;
import de.app.mepa.mepa.R;

public class Bemerkung extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener{
    private Button safe, laden, loeschen;
    private EditText eingabe;
    private SharedPreferences speicher;
    private Editor editor;
    private ImageView imgv_before;
    private ImageView imgv_menü;
    private DrawerLayout drawerlayout_bemerkung;
    private ListView listview_bemerkung;
    private MyAdapter myadapter_bemerkung;
    private int[] drawer_icons_bemerkung={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};


    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bemerkung);

        drawerlayout_bemerkung=(DrawerLayout) findViewById(R.id.drawerLayout_Bemerkung);
        listview_bemerkung=(ListView) findViewById(R.id.listview_Bemerkung);

        myadapter_bemerkung=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_bemerkung);
        listview_bemerkung.setAdapter(myadapter_bemerkung);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_bemerkung.setOnItemClickListener(this);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        safe = (Button)findViewById(R.id.btn_bemerkung_speichern);
        laden = (Button)findViewById(R.id.btn_bemerkung_laden);
        eingabe = (EditText)findViewById(R.id.edtxt_bemerkung);
        loeschen = (Button)findViewById(R.id.btn_bemerkung_loeschen);

        safe.setOnClickListener(this);
        laden.setOnClickListener(this);
        loeschen.setOnClickListener(this);

        speicher = getApplicationContext().getSharedPreferences("Bemerkung",0);
        editor = speicher.edit();
        /* von Vivien Stumpe, 11.04.16
        Wechseln der Aktivität mittels Swipe
        Hauptelement der Activity finden und der Variable zuweisen
        Darauf den OnTouchListener setzen, damit auf Berührungen reagiert wird
        wenn nach links gewischt wird, wird die Falleingabe mittels Intent geöffnet
        */

        view=(View) findViewById(R.id.rl_bemerkung);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Bemerkung.this, Falleingabe.class);
                startActivity(intent);
            }

            public void onSwipeRight() {
                drawerlayout_bemerkung.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Intent intent = new Intent(Bemerkung.this, notfallsituation.class);
                startActivity(intent);
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        imgv_before = (ImageView)findViewById(R.id.imgv_before_bemerkung);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        drawerlayout_bemerkung.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Ersthelfermassnahmen der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Bemerkung.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Bemerkung.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Bemerkung.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Bemerkung.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Bemerkung.this, Impressum.class);
            startActivity(intent);
        }
        drawerlayout_bemerkung.closeDrawers();
    }
    @Override
    public void onClick(View v) {
        if (v == safe){
            TextSpeichern(eingabe.getText().toString());
        }
        if (v == laden){
            TextLaden();
        }
        if (v == loeschen){
            TextLoeschen();
        }
        /* von Vivien Stumpe, 25.04.16
        Deklaration und Initialisierung einer Hilfsvariablen (clicked element),
        die die ID der geklickten View erhält
        */
        int ce = v.getId();

        /* von Vivien Stumpe, 25.04.16
        Ein Intent erzeugen, wenn die bestimmte ImageView geklickt wurde
        Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her
        Aufrufen der Activity mittels Intent
        */
        if (ce == R.id.imgv_before_bemerkung) {
            Intent intent = new Intent(Bemerkung.this, Falleingabe.class);
            startActivity(intent);
        }
        if(ce == R.id.imgv_menu){
            drawerlayout_bemerkung.openDrawer(GravityCompat.START);
        }
    }
    private void TextLaden(){
        if (speicher.getString("Bemerkung1", null)!= null){
            eingabe.setText(speicher.getString("Bemerkung1", null));
        }
        else {
            Toast.makeText(this, "keine Daten im Speicher", Toast.LENGTH_LONG).show();
        }
    }
    private void TextSpeichern(String inhalt){
        if (inhalt != null){
            editor.putString("Bemerkung1", inhalt);
            editor.commit();
            Toast.makeText(this, "Daten gespeichert", Toast.LENGTH_LONG).show();
        }
    }
    private void TextLoeschen(){
        if (speicher.getString("Bemerkung1", null)!= null){
            editor.clear();
            editor.commit();
            Toast.makeText(this, "Daten gelöscht", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "keine Daten im Speicher", Toast.LENGTH_LONG).show();
        }
    }
}
