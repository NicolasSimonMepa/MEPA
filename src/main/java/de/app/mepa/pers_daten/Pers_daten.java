//Zuletzt bearbeitet von Vivien Stumpe, 16.05.16

package de.app.mepa.pers_daten;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Calendar;

import de.app.mepa.MyAdapter;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;
import de.app.mepa.verletzung.Verletzung;
import de.app.mepa.GlobaleDaten;


public class Pers_daten extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
//von Vivien Stumpe, 10.04.16
//DrawerLayout für das Hamburger Menü
//ListView, die die Einträge des Menüs enthält
//Adapter, der die Einträge der ListView darstellt
//Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_pers_daten;
    private ListView listview_pers_daten;
    private MyAdapter myadapter_pers_daten;
  private int[] drawer_icons_pers_daten={R.drawable.falleingabe,
          R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};
    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;

    /*von Vivien Stumpe, 12.04.16
    Toolbar-variable anlegen, um die Toolbar in den Screen einzubinden
    */
    Toolbar toolbar;

    /* von Vivien Stumpe, 18.04.16
    Variablen für die Spinner in der Activity erstellen

    */
    private Spinner spin_zugef;

    /* von Vivien Stumpe, 18.04.16
    String Array erstellen mit den Elementen, die im Dropdown-Menü des Spinners in der Activity ausgewählt werden können
    */
    private String[]zugef = {"----", "Polizei", "RTW/KTW", "San-Team", "Security",
            "Angehörige", "Selbst", "Passanten", "Sonstiges"};

    /* von Vivien Stumpe, 18.04.16
    Edittext erzeugen
     */
    private EditText etxt_sonstiges;

    /* von Indra Marcheel, 09.05.2016
    Edittext erzeugen
     */
    private EditText etxt_name_pers_daten;
    private EditText etxt_vorname_pers_daten;
    private EditText etxt_str_pers_daten;

    /* von Vivien Stumpe, 25.04.16
    ImageViews für den Zurück Pfeil in der Aktivität
    ergänzt am 29.04.16 (imgv_menü)
     */
    private ImageView imgv_before;
    private ImageView imgv_menü;

    /* von Vivien Stumpe, 14.05.16
    Variablen für den Kalender zur Auswahl des Geburtsdatums
     */
    private EditText etxt_gebdat;
    private DatePickerDialog datepicker;
    private Calendar calendar=Calendar.getInstance();

    //-------
    private GlobaleDaten mfall;
    private EditText etxt_plz_pers_daten, etxt_ort_pers_daten, etxt_land_pers_daten, etxt_tel_pers_daten,
    etxt_krankenkasse_pers_daten, etxt_versnr_pers_daten, etxt_versichertennr_pers_daten, etxt_fundort_pers_daten;
    private RadioButton rbtn_weibl, rbtn_maennl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pers_daten);
        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_pers_daten=(DrawerLayout) findViewById(R.id.drawerLayout_pers_daten);
        listview_pers_daten=(ListView) findViewById(R.id.listview_pers_daten);

        myadapter_pers_daten=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_pers_daten);
        listview_pers_daten.setAdapter(myadapter_pers_daten);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_pers_daten.setOnItemClickListener(this);


        //von Vivien Stumpe, 11.04.16
        //Verbindung der View zur Scrollview in der Aktivität
        view=(View) findViewById(R.id.scrV_pers_daten);
        //OnTouchListener auf die View setzen
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            //Bei einem Swipe nach links wird die nächste Aktivität geöffnet
            public void onSwipeLeft() {
            Intent intent = new Intent(Pers_daten.this, Verletzung.class);
               startActivity(intent);
            }
            /* von Vivien Stumpe, 26.04.16
            Bei einem Swipe nach rechts wird die vorherige Aktivität geöffnet
             */
            public void onSwipeRight() {
                Intent intent = new Intent(Pers_daten.this, Falleingabe.class);
                startActivity(intent);
            }
        });

        /*von Vivien Stumpe, 12.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /* von Vivien Stumpe, 18.04.16
        ArrayAdapter erzeugen
        Der ArrayAdapter generiert die Listenelemente des Spinners
        Parameter sind die aktuelle Activity, das Layout für den Spinnerinhalt, Array mit den Listenelementen
        Layout für den Spinner geändert am 25.04.16 (VS)
        */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Pers_daten.this,
                R.layout.spinner_layout,zugef);

        adapter.setDropDownViewResource(R.layout.spinner_layout);

        /* von Vivien Stumpe, 18.04.16
        Verknüpfung der Spinnervariable zum Spinner in der Activity herstellen
        SpinnerAdapter dem Spinner zuweisen damit die Elemente in der Activity angezeigt werden
        setOnItemSelectedListener implementieren, damit mit dem ausgewählten Eintrag auch gearbeitet werden kann
        */
        spin_zugef = (Spinner)findViewById(R.id.spin_zugefuehrt_pers_daten);
        spin_zugef.setAdapter(adapter);
        spin_zugef.setOnItemSelectedListener(this);

        /* von Vivien Stumpe, 18.04.16
        TableRow Variable der View zuordnen
        TableRow in der Activity ausblenden --> soll erst eingeblendet werden, wenn Sonstiges im Spinner gewählt wurde
         */
        etxt_sonstiges=(EditText) findViewById(R.id.etxt_sonstiges_pers_daten);
        etxt_sonstiges.setVisibility(View.GONE);
        //View aktualisieren
        etxt_sonstiges.invalidate();

        etxt_name_pers_daten=(EditText) findViewById(R.id.etxt_name_pers_daten);
        /* von Indra Marcheel, 09.05.2016
        es können nur character eingegeben werden
         */
        etxt_name_pers_daten.setFilters(new InputFilter[] {
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

        etxt_vorname_pers_daten=(EditText) findViewById(R.id.etxt_vorname_pers_daten);
         /* von Indra Marcheel, 09.05.2016
        es können nur character eingegeben werden
         */
        etxt_vorname_pers_daten.setFilters(new InputFilter[] {
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
         /* von Indra Marcheel, 09.05.2016
        es können nur character, - , und zahlen eingegebne werden 
         */
        etxt_str_pers_daten=(EditText) findViewById(R.id.etxt_str_pers_daten);
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if ( !Character.isLetterOrDigit(source.charAt(i)) & !Character.toString(source.charAt(i)) .equals(" ") & !Character.toString(source.charAt(i)) .equals("-")) {
                        return "";
                    }
                }
                return null;
            }
        };

        etxt_str_pers_daten.setFilters(new InputFilter[]{filter});


        /* von Vivien Stumpe, 22.04.16
            Tastatur wird nicht automatisch beim Öffnen der Aktivität eingeblendet
            sondern erst, wenn ins Eingabefeld geklickt wird
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /* von Indra Marcheel, 09.05.2016
        maximale eingabelänge wird auf 50 zeichen beschränkt
         */
        etxt_sonstiges.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});

        /* von Vivien Stumpe, 25.04.16
        Views in der Akitivität finden und den Variablen zuweisen
        OnClickListener darauf setzen, damit auf Klicks reagiert wird
        ergänzt von Vivien Stumpe, 29.04.16 --> imgv_menü
         */
        imgv_before = (ImageView)findViewById(R.id.imgv_before_pers);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        /*von Vivien Stumpe, 29.04.16
        Der Drawer (Hamburger Menü) ist gesperrt und kann nicht geöffnet werden
        */
        drawerlayout_pers_daten.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        // Variablen für das Geburtsdatum
        etxt_gebdat=(EditText)findViewById(R.id.etxt_geb_pers_daten);
        etxt_gebdat.setOnClickListener(this);
        /* von Vivien Stumpe, 15.05.16
        Views im Screen den Variablen zuordnen
         */
        etxt_plz_pers_daten=(EditText)findViewById(R.id.etxt_plz_pers_daten);
        etxt_ort_pers_daten=(EditText)findViewById(R.id.etxt_ort_pers_daten);
        etxt_land_pers_daten=(EditText)findViewById(R.id.etxt_land_pers_daten);
        etxt_tel_pers_daten=(EditText)findViewById(R.id.etxt_telefon_pers_daten);
        etxt_krankenkasse_pers_daten=(EditText)findViewById(R.id.etxt_krankenkasse_pers_daten);
        etxt_versnr_pers_daten=(EditText)findViewById(R.id.etxt_versicherungsnr_pers_daten);
        etxt_versichertennr_pers_daten=(EditText)findViewById(R.id.etxt_versichertennr_pers_daten);
        etxt_fundort_pers_daten=(EditText)findViewById(R.id.etxt_fundort_pers_daten);
        rbtn_maennl=(RadioButton)findViewById(R.id.rBtn_maennl);
        rbtn_weibl=(RadioButton)findViewById(R.id.rBtn_weibl);
        rbtn_maennl.setOnClickListener(this);
        rbtn_weibl.setOnClickListener(this);

        /* von Vivien Stumpe, 15.05.16
        Falls Werte vorhanden sind, werden diese im Screen geladen
         */
        setWerte();
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    // von Vivien Stumpe, 25.04.16 aktualisiert
    private void selectItemFromDrawer(int position){

        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if(position==0) {
            Intent intent = new Intent(Pers_daten.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(Pers_daten.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(Pers_daten.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(Pers_daten.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Pers_daten.this, Impressum.class);
            startActivity(intent);
        }
        //Menü schließen
        drawerlayout_pers_daten.closeDrawers();
    }


    /* von Vivien Stumpe, 18.04.16
    */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Was soll passieren, wenn das erste Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 1:
                // Was soll passieren, wenn das zweite Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 2:
                // Was soll passieren, wenn das dritte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 3:
                // Was soll passieren, wenn das vierte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 4:
                // Was soll passieren, wenn das fünfte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 5:
                // Was soll passieren, wenn das sechste Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet

                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 6:
                // Was soll passieren, wenn das siebte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 7:
                // Was soll passieren, wenn das achte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 8:
                // Wenn sonstiges ausgewählt wurde, wird das Eingabefeld dazu angezeigt
                etxt_sonstiges.setVisibility(View.VISIBLE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                //von Vivien Stumpe, 10.05.16 - Fokus auf das Sonstiges Feld legen
                etxt_sonstiges.requestFocus();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        if(ce == R.id.imgv_before_pers){
            Intent intent = new Intent(Pers_daten.this, Falleingabe.class);
            startActivity(intent);
        }

        /* von Vivien Stumpe, 29.04.16
        Das Menü wird geöffnet in der Startposition (bei uns links)
         */
        if(ce == R.id.imgv_menu){
            drawerlayout_pers_daten.openDrawer(GravityCompat.START);
        }

        /* von Vivien Stumpe, 14.05.2016
        DatePickerDialog zum Anzeigen des Kalenders und auswählen des Geburtsdatums
        Kalender öffnet sich, wenn auf das Eingabefeld "Geboren am" geklickt wird
        Datum wird nach der Auswahl im Eingabefeld angezeigt
         */
        if(ce==R.id.etxt_geb_pers_daten){

            datepicker=new DatePickerDialog(Pers_daten.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar dateCalendar=Calendar.getInstance();
                    dateCalendar.set(Calendar.YEAR, year);
                    dateCalendar.set(Calendar.MONTH, monthOfYear);
                    dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String dateString= DateUtils.formatDateTime(Pers_daten.this, dateCalendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE);
                    etxt_gebdat.setText(dateString);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datepicker.show();
        }

    }
    /* von Vivien Stumpe, 15.05.16
    Prozedur, die die eingegebenen Daten in den Variablen speichert
     */
    public void speichereEingaben(){
        mfall=(GlobaleDaten)getApplication();
        mfall.setPat_name(etxt_name_pers_daten.getText().toString());
        Log.d("Fall", "Name gespeichert");

        mfall.setPat_vorname(etxt_vorname_pers_daten.getText().toString());
        Log.d("Fall", "Vorname gespeichert");

        mfall.setPat_geb(etxt_gebdat.getText().toString());
        Log.d("Fall", "Geb gespeichert");

        mfall.setPat_str(etxt_str_pers_daten.getText().toString());
        Log.d("Fall", "Str gespeichert");

        mfall.setPat_plz(etxt_plz_pers_daten.getText().toString());
        Log.d("Fall", "PLZ gespeichert");

        mfall.setPat_ort(etxt_ort_pers_daten.getText().toString());
        Log.d("Fall", "Ort gespeichert");

        mfall.setPat_land(etxt_land_pers_daten.getText().toString());
        Log.d("Fall", "Land gespeichert");

        mfall.setPat_tel(etxt_tel_pers_daten.getText().toString());
        Log.d("Fall", "Telefon gespeichert");

        mfall.setPat_krankenkasse(etxt_krankenkasse_pers_daten.getText().toString());
        Log.d("Fall", "Krankenkasse gespeichert");

        mfall.setPat_versichertennr(etxt_versichertennr_pers_daten.getText().toString());
        Log.d("Fall", "Versichertennr gespeichert");

        mfall.setPat_versnr(etxt_versnr_pers_daten.getText().toString());
        Log.d("Fall", "Versicherung-Nummer gespeichert");

        mfall.setEin_fundort(etxt_fundort_pers_daten.getText().toString());
        Log.d("Fall", "Fundort gespeichert");

        if((spin_zugef.getSelectedItem().toString().equals("Polizei"))){
                mfall.setEin_zugef("Polizei");
            }
        if((spin_zugef.getSelectedItem().toString().equals("RTW/KTW"))){
            mfall.setEin_zugef("RTW/KTW");
        }
        if((spin_zugef.getSelectedItem().toString().equals("San-Team"))){
            mfall.setEin_zugef("San-Team");
        }
        if((spin_zugef.getSelectedItem().toString().equals("Security"))){
            mfall.setEin_zugef("Security");
        }
        if((spin_zugef.getSelectedItem().toString().equals("Angehörige"))){
            mfall.setEin_zugef("Angehörige");
        }
        if((spin_zugef.getSelectedItem().toString().equals("Selbst"))){
            mfall.setEin_zugef("Selbst");
        }
        if((spin_zugef.getSelectedItem().toString().equals("Passanten"))){
            mfall.setEin_zugef("Passanten");
        }
        if((spin_zugef.getSelectedItem().toString().equals("Sonstiges"))){
            mfall.setEin_zugef("Sonstiges");
        }

        if(rbtn_weibl.isChecked()) {
            mfall.setPat_sex("weiblich");
        }
        if(rbtn_maennl.isChecked()){
            mfall.setPat_sex("maennlich");
        }
    }
    /* von Vivien Stumpe, 15.05.16
    wird aufgerufen, wenn eine andere Aktivität in den Vordergrund gelangt
     */
    public void onPause(){
        super.onPause();
        //Eingaben werden lokal gespeichert
        speichereEingaben();
    }

    /* von Vivien Stumpe, 15.05.16
    belegt die Eingabefelder etc. mit den lokal gespeicherten Werten, sofern vorhanden
     */
    public void setWerte(){
        mfall=(GlobaleDaten)getApplication();

        if((mfall.getPat_name()!=null)){
            etxt_name_pers_daten.setText(mfall.getPat_name());
        }
        if((mfall.getPat_vorname()!=null)){
            etxt_vorname_pers_daten.setText(mfall.getPat_vorname());
        }
        if((mfall.getPat_str()!=null)){
            etxt_str_pers_daten.setText(mfall.getPat_str());
        }
        if((mfall.getPat_plz()!=null)){
            etxt_plz_pers_daten.setText(mfall.getPat_plz());
        }
        if((mfall.getPat_ort()!=null)){
            etxt_ort_pers_daten.setText(mfall.getPat_ort());
        }
        if((mfall.getPat_land()!=null)){
            etxt_land_pers_daten.setText(mfall.getPat_land());
        }
        if((mfall.getPat_tel()!=null)){
            etxt_tel_pers_daten.setText(mfall.getPat_tel());
        }
        if((mfall.getPat_geb()!=null)){
            etxt_gebdat.setText(mfall.getPat_geb());
        }
        if((mfall.getPat_krankenkasse()!=null)){
            etxt_krankenkasse_pers_daten.setText(mfall.getPat_krankenkasse());
        }
        if((mfall.getPat_versichertennr()!=null)){
            etxt_versichertennr_pers_daten.setText(mfall.getPat_versichertennr());
        }
        if((mfall.getPat_versnr()!=null)){
            etxt_versnr_pers_daten.setText(mfall.getPat_versnr());
        }
        if((mfall.getEin_fundort()!=null)) {
            etxt_fundort_pers_daten.setText(mfall.getEin_fundort());
        }
        if((mfall.getEin_zugef()!=null)){
            if(mfall.getEin_zugef().equals("Polizei")){
                spin_zugef.setSelection(1);
            }
            if(mfall.getEin_zugef().equals("RTW/KTW")){
                spin_zugef.setSelection(2);
            }
            if(mfall.getEin_zugef().equals("San-Team")){
                spin_zugef.setSelection(3);
            }
            if(mfall.getEin_zugef().equals("Security")){
                spin_zugef.setSelection(4);
            }
            if(mfall.getEin_zugef().equals("Angehörige")){
                spin_zugef.setSelection(5);
            }
            if(mfall.getEin_zugef().equals("Selbst")){
                spin_zugef.setSelection(6);
            }
            if(mfall.getEin_zugef().equals("Passanten")){
                spin_zugef.setSelection(7);
            }
            if(mfall.getEin_zugef().equals("Sonstiges")){
                spin_zugef.setSelection(8);
            }
        }
        if((mfall.getPat_sex()!=null)) {
            if ((mfall.getPat_sex().equals("weiblich"))) {
                rbtn_weibl.setChecked(true);
            }
            else if((mfall.getPat_sex().equals("maennlich")))
                rbtn_maennl.setChecked(true);
        }

    }

}
