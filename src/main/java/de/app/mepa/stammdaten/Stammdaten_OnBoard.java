package de.app.mepa.stammdaten;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

import de.app.mepa.GlobaleDaten;
import de.app.mepa.OnBoarding;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.mepa.R;

/**
 * Created by vivienstumpe on 16.05.16.
 */
public class Stammdaten_OnBoard extends AppCompatActivity implements View.OnClickListener {


    /*von Vivien Stumpe, 14.04.16
    Toolbar anlegen
    */
    Toolbar toolbar;

    /* von Vivien Stumpe, 01.5.16
    Attribute festlegen
     */
    protected String kreisverband;
    protected String ort;
    protected String ortsverein;
    protected String veranstaltung;

    // Hilfsvariablen
    private EditText etxt_kreisverband;
    private EditText etxt_ort;
    private EditText etxt_ortsverein;
    private EditText etxt_veranstaltung;
    private LinearLayout buttons;
    private Button speichern;
    private Button verwerfen;
    private TextWatcher tw;
    private CheckBox cck_mosan;
    private CheckBox cck_hilfs;
    private CheckBox cck_sanw;

    /* von Vivien Stumpe, 14.05.16
    Variablen für den Kalender zur Auswahl des Datums
 */
    private EditText etxt_date;
    private DatePickerDialog datepicker;
    private Calendar calendar=Calendar.getInstance();

    //---
    private GlobaleDaten mfall;
    private ImageView imgv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stammdaten_onboard);

        /*von Vivien Stumpe, 14.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* von Vivien Stumpe, 01.05.16
        Edittexte in der Activity finden und ansprechen
         */
        buttons=(LinearLayout)findViewById(R.id.lnl_stammdaten_buttons_onb);
        buttons.setVisibility(buttons.GONE);
        etxt_kreisverband=(EditText)findViewById(R.id.etxt_kreisverband_onb);
        etxt_ort=(EditText)findViewById(R.id.etxt_ort_onb);
        etxt_ortsverein=(EditText)findViewById(R.id.etxt_ortsverein_onb);
        etxt_veranstaltung=(EditText)findViewById(R.id.etxt_veranstaltung_onb);
        /* von Vivien Stumpe, 01.05.16
        TextWatcher "beobachtet" den User bei der Eingabe in ein EditText
        Damit entsprechend auf Eingaben reagiert werden kann
         */
        tw=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Buttons speichern & verwerfen sind sichtbar
                buttons.setVisibility(buttons.VISIBLE);
            }
        };
        /* von Vivien Stumpe, 01.05.16
        TextChangedListener muss auf die Eingabefelder gesetzt werden
        damit auch registriert wird, wenn eine Eingabe getätigt wurde
        und entsprechend reagiert werden kann -> siehe TextWatcher
         */
        etxt_kreisverband.addTextChangedListener(tw);
        etxt_ort.addTextChangedListener(tw);
        etxt_ortsverein.addTextChangedListener(tw);
        etxt_veranstaltung.addTextChangedListener(tw);

        speichern=(Button)findViewById(R.id.btn_speichern_stammd_onb);
        verwerfen=(Button)findViewById(R.id.btn_verwerfen_stammd_onb);
        cck_hilfs=(CheckBox)findViewById(R.id.cck_hilfsstelle_onb);
        cck_mosan=(CheckBox)findViewById(R.id.cck_mosan_onb);
        cck_sanw=(CheckBox)findViewById(R.id.cck_sanw_onb);
        speichern.setOnClickListener(this);
        verwerfen.setOnClickListener(this);
        cck_hilfs.setOnClickListener(this);
        cck_mosan.setOnClickListener(this);
        cck_sanw.setOnClickListener(this);
        etxt_date=(EditText)findViewById(R.id.etxt_veranstaltung_datum_onb);
        etxt_date.setOnClickListener(Stammdaten_OnBoard.this);
        //---
        setWerte();
        imgv_back=(ImageView)findViewById(R.id.imgv_before_stammd_onb);
        imgv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button belegen
        //von Vivien Stumpe, 01.04.16
        int ce = v.getId();
        //von Vivien Stumpe, 01.05.16
        //wenn eine der Checkboxen geklickt wurde erscheinen die Buttons
        if(ce == R.id.cck_hilfsstelle_onb){
            buttons.setVisibility(buttons.VISIBLE);
        }
        if(ce == R.id.cck_mosan_onb){
            buttons.setVisibility(buttons.VISIBLE);
        }
        if(ce == R.id.cck_sanw_onb){
            buttons.setVisibility(buttons.VISIBLE);
        }
        if(ce==R.id.imgv_before_stammd_onb){
            Intent onboard=new Intent(Stammdaten_OnBoard.this, OnBoarding.class);
            startActivity(onboard);
        }
        if(ce == R.id.btn_speichern_stammd_onb){
            speichereEingaben();
            if(mfall.getFall_angelegt()){
                Intent fallein=new Intent(Stammdaten_OnBoard.this, Falleingabe.class);
                startActivity(fallein);
            }
            //Buttons wieder ausblenden
            buttons.setVisibility(buttons.GONE);
        }
        /* von Vivien Stumpe, 14.05.2016
        DatePickerDialog zum Anzeigen des Kalenders und auswählen des Datums
        Kalender öffnet sich, wenn auf das Eingabefeld "Datum" geklickt wird
        Datum wird nach der Auswahl im Eingabefeld angezeigt
         */
        if(ce==R.id.etxt_veranstaltung_datum_onb){

            datepicker=new DatePickerDialog(Stammdaten_OnBoard.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar dateCalendar=Calendar.getInstance();
                    dateCalendar.set(Calendar.YEAR, year);
                    dateCalendar.set(Calendar.MONTH, monthOfYear);
                    dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String dateString= DateUtils.formatDateTime(Stammdaten_OnBoard.this, dateCalendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE);
                    etxt_date.setText(dateString);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datepicker.show();
        }
        if(ce==R.id.btn_verwerfen_stammd_onb){
            mfall.loescheVer();
            mfall.loescheVerb();
            setWerte();
            //Buttons werden ausgeblendet
            buttons.setVisibility(buttons.GONE);
            //Inhalt des Textfeldes löschen?
        }
    }

    /* von Vivien Stumpe, 15.05.16
    Prozedur, die die eingegebenen Daten in den Variablen speichert
    */
    public void speichereEingaben(){
        mfall=(GlobaleDaten)getApplication();
        mfall.setVer_name(etxt_veranstaltung.getText().toString());
        Log.d("Fall", "Name gespeichert");

        mfall.setVer_ort(etxt_ort.getText().toString());
        Log.d("Fall", "Ort gespeichert");

        mfall.setVer_date(etxt_date.getText().toString());
        Log.d("Fall", "Datum gespeichert");
        mfall.setVer_vorh();

        mfall.setVerb_kreisv(etxt_kreisverband.getText().toString());
        Log.d("Fall", "Kreisverband gespeichert");

        mfall.setVerb_ortsv(etxt_ortsverein.getText().toString());
        Log.d("Fall", "Ortsverein gespeichert");

        if(cck_hilfs.isChecked()) {
            mfall.setEin_hilfs(1);
        }
        else
            mfall.setEin_hilfs(0);

        if(cck_mosan.isChecked()) {
            mfall.setEin_mosan(1);
        }
        else
            mfall.setEin_mosan(0);

        if(cck_sanw.isChecked()) {
            mfall.setEin_sanw(1);
        }
        else
            mfall.setEin_sanw(0);

        mfall.setVerb_vorh();
        mfall.setFall_angelegt();
    }

    /* von Vivien Stumpe, 15.05.16
    belegt die Eingabefelder etc. mit den lokal gespeicherten Werten, sofern vorhanden
     */
    public void setWerte(){
        mfall=(GlobaleDaten)getApplication();

        if((mfall.getVer_name()!=null)){
            etxt_veranstaltung.setText(mfall.getVer_name());
        }
        if((mfall.getVer_ort()!=null)){
            etxt_ort.setText(mfall.getVer_ort());
        }
        if((mfall.getVer_date()!=null)){
            etxt_date.setText(mfall.getVer_date());
        }
        if((mfall.getVerb_ortsv()!=null)){
            etxt_ortsverein.setText(mfall.getVerb_ortsv());
        }
        if((mfall.getVerb_kreisv()!=null)){
            etxt_kreisverband.setText(mfall.getVerb_kreisv());
        }
        if(mfall.getEin_hilfs()!=null) {
            if (mfall.getEin_hilfs() == 1) {
                cck_hilfs.setChecked(true);
            }
        }
        if(mfall.getEin_mosan()!=null) {
            if (mfall.getEin_mosan() == 1) {
                cck_mosan.setChecked(true);
            }
        }
        if(mfall.getEin_sanw()!=null) {
            if (mfall.getEin_sanw() == 1) {
                cck_sanw.setChecked(true);
            }
        }
    }
}