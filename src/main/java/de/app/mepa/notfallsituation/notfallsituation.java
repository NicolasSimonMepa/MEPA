//Zuletzt bearbeitet von Emile Yoncaova, 17.05.16
package de.app.mepa.notfallsituation;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import de.app.mepa.MyAdapter;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.bemerkung.Bemerkung;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.ersthelfermassnahmen.Ersthelfermassnahmen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.GlobaleDaten;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;

public class notfallsituation extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText etxt_notfallsituation;
    private GlobaleDaten mfall;
    //von Vivien Stumpe, 10.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_notfall;
    private ListView listview_notfall;
    private MyAdapter myadapter_notfall;
    private int[] drawer_icons_notfall={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    /*von Vivien Stumpe, 12.04.16
    Toolbar-variable anlegen, um die Toolbar in den Screen einzubinden
    */
    Toolbar toolbar;

    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;

    /* von Vivien Stumpe, 25.04.16
    ImageViews für den Zurück Pfeil in der Aktivität
    ergänzt am 01.05.16 (imgv_menü)
     */
    private ImageView imgv_before;
    private ImageView imgv_menü;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallsituation);
        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_notfall=(DrawerLayout) findViewById(R.id.drawerLayout_Notfallsituation);
        listview_notfall=(ListView) findViewById(R.id.listview_Notfallsituation);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_notfall=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_notfall);
        listview_notfall.setAdapter(myadapter_notfall);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_notfall.setOnItemClickListener(this);

        /*von Vivien Stumpe, 12.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etxt_notfallsituation=(EditText) findViewById(R.id.etxt_notfallsituation);

        setWerte();
        /* von Vivien Stumpe, 19.04.16
        Wechseln der Aktivität mittels Swipe
        Hauptelement der Activity finden und der Variable zuweisen
        Darauf den OnTouchListener setzen, damit auf Berührungen reagiert wird
        wenn nach links gewischt wird, wird die nächste Seite mittels Intent geöffnet
        */
        view=(View) findViewById(R.id.rl_notfall);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(notfallsituation.this, Bemerkung.class);
                startActivity(intent);
            }

            /* von Vivien Stumpe, 26.04.16
            Bei einem Swipe nach rechts wird die vorherige Aktivität geöffnet
            Außerdem bleibt der Drawer (Hamburger Menü) geschlossen
            */
            public void onSwipeRight() {
                drawerlayout_notfall.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Intent intent = new Intent(notfallsituation.this, Ersthelfermassnahmen.class);
                startActivity(intent);
            }
        });
        /* von Vivien Stumpe, 22.02.16
            Tastatur wird nicht automatisch beim Öffnen der Aktivität eingeblendet
            sondern erst, wenn ins Eingabefeld geklickt wird
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        /* von Vivien Stumpe, 25.04.16
        Views in der Akitivität finden und den Variablen zuweisen
        OnClickListener darauf setzen, damit auf Klicks reagiert wird
        ergänzt von Vivien Stumpe, 01.05.16 -> imgv_menü
         */
        imgv_before = (ImageView)findViewById(R.id.imgv_before_notfall);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        /*von Vivien Stumpe, 01.05.16
        Der Drawer (Hamburger Menü) ist gesperrt und kann nicht geöffnet werden
        */
        drawerlayout_notfall.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    // von Vivien Stumpe, 01.05.16 aktualisiert
    private void selectItemFromDrawer(int position){

        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if(position==0) {
            Intent intent = new Intent(notfallsituation.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(notfallsituation.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(notfallsituation.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(notfallsituation.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(notfallsituation.this, Impressum.class);
            startActivity(intent);
        }
        //Menü schließen
        drawerlayout_notfall.closeDrawers();
    }

    @Override
    public void onClick(View v) {
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
        if(ce == R.id.imgv_before_notfall){
            Intent intent = new Intent(notfallsituation.this, Falleingabe.class);
            startActivity(intent);
        }
        /* von Vivien Stumpe, 01.05.16
        Das Menü wird geöffnet in der Startposition (bei uns links)
         */
        if(ce == R.id.imgv_menu){
            drawerlayout_notfall.openDrawer(GravityCompat.START);
        }
    }
    public void onPause(){
        super.onPause();
        //Eingaben werden lokal gespeichert
        speichereEingaben();
    }
    public void speichereEingaben(){
        mfall=(GlobaleDaten)getApplication();
        mfall.setNotfallsituation(etxt_notfallsituation.getText().toString());
    }
    public void setWerte() {
        mfall = (GlobaleDaten) getApplication();
        if ((mfall.getNotfallsituation() != null)) {
            etxt_notfallsituation.setText(mfall.getNotfallsituation());
        }
    }
}
