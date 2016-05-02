package de.app.mepa.erstbefund;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;
import de.app.mepa.MyAdapter;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.ersthelfermassnahmen.Ersthelfermassnahmen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.massnahmen.Massnahmen;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;

public class Erstbefund extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener{
        private ImageView imgv_before;
        private ImageView imgv_menü;
        private Spinner spin_bewusstseinslage;
        private Spinner spin_kreislauf;
        private Spinner spin_pupillenfunktion_rechts;
        private Spinner spin_pupillenfunktion_links;
        private Spinner spin_ekg;
        private Spinner spin_schmerzen;
        private Spinner spin_atmung;
        
        private String[]bewusstseinslage={"-----","orientiert","getrübt","bewusstlos"};
        private String[]kreislauf={"-----","schock","kreislaufstillstand","puls regelmäßig","puls unregelmäßig"};
        private String[]pupillenfunktion_rechts={"rechts","eng","mittel","weit","entrundet","lichtreaktion"};
        private String[]pupillenfunktion_links={"links","eng","mittel","weit","entrundet","lichtreaktion"};
        private String[]ekg={"-----","sinusrhythmus","rhythmusstörung","kammerflimmern","asystolie"};
        private String[]schmerzen={"-----","leicht","mittel","stark"};
        private String[]atmung={"-----","spontan/frei","atemnot","hyperventilation","atemstillstand"};

        private DrawerLayout drawerlayout_erstbefund;
        private ListView listview_erstbefund;
        private MyAdapter myadapter_erstbefund;
         private int[] drawer_icons_erstbefund={R.drawable.falleingabe,
                 R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

        Toolbar toolbar;


    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;


@Override
protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstbefund);

        ArrayAdapter<String>adapter_bewusstseinslage=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,bewusstseinslage);

        adapter_bewusstseinslage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_kreislauf=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,kreislauf);

        adapter_kreislauf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_pupillenfunktion_rechts=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,pupillenfunktion_rechts);

        adapter_pupillenfunktion_rechts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_pupillenfunktion_links=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,pupillenfunktion_links);

        adapter_pupillenfunktion_links.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_ekg=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,ekg);

        adapter_ekg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_schmerzen=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,schmerzen);

        adapter_schmerzen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_atmung=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,atmung);

        adapter_atmung.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spin_bewusstseinslage=(Spinner)findViewById(R.id.spin_bewusstseinslage);
        spin_bewusstseinslage.setAdapter(adapter_bewusstseinslage);
        spin_bewusstseinslage.setOnItemSelectedListener(this);

        spin_kreislauf=(Spinner)findViewById(R.id.spin_kreislauf);
        spin_kreislauf.setAdapter(adapter_kreislauf);
        spin_kreislauf.setOnItemSelectedListener(this);

        spin_pupillenfunktion_rechts=(Spinner)findViewById(R.id.spin_pupillenfunktion_rechts);
        spin_pupillenfunktion_rechts.setAdapter(adapter_pupillenfunktion_rechts);
        spin_pupillenfunktion_rechts.setOnItemSelectedListener(this);

        spin_pupillenfunktion_links=(Spinner)findViewById(R.id.spin_pupillenfunktion_links);
        spin_pupillenfunktion_links.setAdapter(adapter_pupillenfunktion_links);
        spin_pupillenfunktion_links.setOnItemSelectedListener(this);

        spin_ekg=(Spinner)findViewById(R.id.spin_ekg);
        spin_ekg.setAdapter(adapter_ekg);
        spin_ekg.setOnItemSelectedListener(this);

        spin_schmerzen=(Spinner)findViewById(R.id.spin_schmerzen);
        spin_schmerzen.setAdapter(adapter_schmerzen);
        spin_schmerzen.setOnItemSelectedListener(this);

        spin_atmung=(Spinner)findViewById(R.id.spin_atmung);
        spin_atmung.setAdapter(adapter_atmung);
        spin_atmung.setOnItemSelectedListener(this);


        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_erstbefund=(DrawerLayout)findViewById(R.id.drawerLayout_Erstbefund);
        listview_erstbefund=(ListView)findViewById(R.id.listview_erstbefund);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_erstbefund=new MyAdapter(this,this.getResources().getStringArray(R.array.drawer_nav_neu),drawer_icons_erstbefund);
        listview_erstbefund.setAdapter(myadapter_erstbefund);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_erstbefund.setOnItemClickListener(this);
        
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


                 // von Vivien Stumpe, 11.04.16
                 //Hauptelement der Activity finden und der Variable zuweisen
                 //Wechseln der Aktivität mittels Swipe
                 //Darauf den OnTouchListener setzen, damit auf Berührungen reagiert wird
                 //wenn nach links gewischt wird, wird die nächste Seite mittels Intent geöffnet
        view=(View) findViewById(R.id.scrV_erstbefund);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Erstbefund.this, Ersthelfermassnahmen.class);
                startActivity(intent);
            }
            public void onSwipeRight() {
                drawerlayout_erstbefund.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Intent intent = new Intent(Erstbefund.this, Massnahmen.class);
                startActivity(intent);
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        imgv_before = (ImageView)findViewById(R.id.imgv_before_erstbefund);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        drawerlayout_erstbefund.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

@Override
public void onItemSelected(AdapterView<?>parent,View view,int position,long id){
        switch(position){
        case 0:
        // Was soll passieren, wenn das erste Element gewählt wurde?
        break;
        case 1:
        // Was soll passieren, wenn das zweite Element gewählt wurde?
        break;
        case 2:
        // Was soll passieren, wenn das dritte Element gewählt wurde?
        break;
        case 3:
        // Was soll passieren, wenn das vierte Element gewählt wurde?
        break;
        }
        }
@Override
public void onNothingSelected(AdapterView<?>parent){
        }


@Override
public void onItemClick(AdapterView<?>parent,View view,int position,long id){
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
        }

private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
                Intent intent = new Intent(Erstbefund.this, Falleingabe.class);
                startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
                Intent intent = new Intent(Erstbefund.this, Falluebersicht.class);
                startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
                Intent intent = new Intent(Erstbefund.this, Upload.class);
                startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
                Intent intent = new Intent(Erstbefund.this, Einstellungen.class);
                startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
                Intent intent = new Intent(Erstbefund.this, Impressum.class);
                startActivity(intent);
        }
        drawerlayout_erstbefund.closeDrawers();
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
            if (ce == R.id.imgv_before_erstbefund) {
                        Intent intent = new Intent(Erstbefund.this, Falleingabe.class);
                        startActivity(intent);
            }
            if(ce == R.id.imgv_menu){
                drawerlayout_erstbefund.openDrawer(GravityCompat.START);
            }
        }
}
