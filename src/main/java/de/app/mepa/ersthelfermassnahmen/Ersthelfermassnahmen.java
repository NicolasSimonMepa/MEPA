// zuletzt geändert von Indra Marcheel, 18.05.16
package de.app.mepa.ersthelfermassnahmen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import de.app.mepa.MyAdapter;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.erstbefund.Erstbefund;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.R;
import de.app.mepa.notfallsituation.notfallsituation;
import de.app.mepa.upload.Upload;


public class Ersthelfermassnahmen extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener{
    Button button;
    static final int CAM_REQUEST = 1;
    private ImageView imgv_before;
    private ImageView imgv_menü;
    private Spinner spin_ersthelfermassnahmen;
    private Spinner spin_zustand;
    private Spinner spin_transport;
    private Spinner spin_notarzt;
    private Spinner spin_entlassung;
    private DrawerLayout drawerlayout_ersthelfermassnahmen;
    private ListView listview_ersthelfermassnahmen;
    private MyAdapter myadapter_ersthelfermassnahmen;
    private int[] drawer_icons_ersthelfermassnahmen={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    Toolbar toolbar;

    private String[]ersthelfermassnahmen={"-----","keine","suffizient","insuffizient","AED"};
    private String[]zustand={"-----", "unverändert", "verbessert", "verschlechtert"};
    private String[]transport={"-----", "nicht erforderlich", "Pat. lehnt transport ab"};
    private String[]notarzt={"-----", "nachgefordert", "abbestellt"};
    private String[]entlassung={"-----", "KTW", "RTW", "RTH", "Polizei", "Taxi/PKW", "ÖPNV", "eigenständig", "Angehörige", "zurück zur Veranstaltung"};

    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe

    //Indra Marcheel, 18.05.2016
    private EditText edtxt_entlassung_zeuge;

    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ersthelfermassnahmen);

        ArrayAdapter<String> adapter_ersthelfermassnahmen=new ArrayAdapter<String>(Ersthelfermassnahmen.this,
                R.layout.spinner_layout,ersthelfermassnahmen);

        adapter_ersthelfermassnahmen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_ersthelfermassnahmen=(Spinner)findViewById(R.id.spin_ersthelfermassnahmen);
        spin_ersthelfermassnahmen.setAdapter(adapter_ersthelfermassnahmen);
        spin_ersthelfermassnahmen.setOnItemSelectedListener(this);
        
        ArrayAdapter<String> adapter_zustand=new ArrayAdapter<String>(Ersthelfermassnahmen.this,
                R.layout.spinner_layout,zustand);

        adapter_zustand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_zustand=(Spinner)findViewById(R.id.spin_zustand);
        spin_zustand.setAdapter(adapter_zustand);
        spin_zustand.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter_transport=new ArrayAdapter<String>(Ersthelfermassnahmen.this,
                R.layout.spinner_layout,transport);

        adapter_transport.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_transport=(Spinner)findViewById(R.id.spin_transport);
        spin_transport.setAdapter(adapter_transport);
        spin_transport.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter_notarzt=new ArrayAdapter<String>(Ersthelfermassnahmen.this,
                R.layout.spinner_layout,notarzt);

        adapter_notarzt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_notarzt=(Spinner)findViewById(R.id.spin_notarzt);
        spin_notarzt.setAdapter(adapter_notarzt);
        spin_notarzt.setOnItemSelectedListener(this);
        
        ArrayAdapter<String> adapter_entlassung=new ArrayAdapter<String>(Ersthelfermassnahmen.this,
                R.layout.spinner_layout,entlassung);

        adapter_entlassung.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_entlassung=(Spinner)findViewById(R.id.spin_entlassung);
        spin_entlassung.setAdapter(adapter_entlassung);
        spin_entlassung.setOnItemSelectedListener(this);

        //von Nathalie Horn, 01.05.16
        //Systemzeit anzeigen, Deklaration und Initialisierung der Variablen
        final TextView uebergabe_zeit;
        Button aktualisieren;
        final String[] uhrzeit = new String[1];

        uebergabe_zeit = (TextView) findViewById(R.id.edtxt_uebergabe_zeit);
        aktualisieren = (Button) findViewById(R.id.aktualisieren);

        aktualisieren.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMANY);
                uebergabe_zeit.setText(dateFormat.format(new java.util.Date()));

            }
        });


        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_ersthelfermassnahmen=(DrawerLayout)findViewById(R.id.drawerLayout_Ersthelfermassnahmen);
        listview_ersthelfermassnahmen=(ListView)findViewById(R.id.listview_ersthelfermassnahmen);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_ersthelfermassnahmen=new MyAdapter(this,this.getResources().getStringArray(R.array.drawer_nav_neu),drawer_icons_ersthelfermassnahmen);
        listview_ersthelfermassnahmen.setAdapter(myadapter_ersthelfermassnahmen);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_ersthelfermassnahmen.setOnItemClickListener(this);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


                 // von Vivien Stumpe, 19.04.16
                //Wechseln der Aktivität mittels Swipe
                 //Hauptelement der Activity finden und der Variable zuweisen
                 //Darauf den OnTouchListener setzen, damit auf Berührungen reagiert wird
                 //wenn nach links gewischt wird, wird die nächste Seite mittels Intent geöffnet

        view=(View) findViewById(R.id.scrV_ersthelfermassnahmen);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Ersthelfermassnahmen.this, notfallsituation.class);
                startActivity(intent);
            }
            public void onSwipeRight() {
                drawerlayout_ersthelfermassnahmen.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Intent intent = new Intent(Ersthelfermassnahmen.this, Erstbefund.class);
                startActivity(intent);
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        imgv_before = (ImageView)findViewById(R.id.imgv_before_uebergabe);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        drawerlayout_ersthelfermassnahmen.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        button = (Button) findViewById(R.id.btn_foto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent,CAM_REQUEST);
            }
        });

        edtxt_entlassung_zeuge=(EditText) findViewById(R.id.edtxt_entlassung_zeuge);
         /* von Indra Marcheel, 18.05.2016
        es können nur character eingegeben werden
         */
        edtxt_entlassung_zeuge.setFilters(new InputFilter[] {
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start,
                                               int end, Spanned dst, int dstart, int dend) {
                        if(src.equals("")){ // for backspace
                            return src;
                        }
                        if(src.toString().matches("[a-zA-Z ]+")){
                            return src;
                        }
                        return "";
                    }
                }
        });
    }
    
    private File getFile() {
        File folder = new File("sdcard/mepa");

        if (!folder.exists())
        {
            folder.mkdir();
        }

        File image_file = new File(folder,"entlassungsrevers.jpg");
        return image_file;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Ersthelfermassnahmen der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    private void selectItemFromDrawer(int position) {
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if (position == 0) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if (position == 1) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if (position == 2) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if (position == 3) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if (position == 4) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Impressum.class);
            startActivity(intent);
        }
        drawerlayout_ersthelfermassnahmen.closeDrawers();
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
        if (ce == R.id.imgv_before_uebergabe) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Falleingabe.class);
            startActivity(intent);
        }
        if(ce == R.id.imgv_menu){
            drawerlayout_ersthelfermassnahmen.openDrawer(GravityCompat.START);
        }
    }
}
