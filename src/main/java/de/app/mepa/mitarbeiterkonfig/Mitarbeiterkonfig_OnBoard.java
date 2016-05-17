/**
 * Created by vstumpe on 16.05.2016.
 */
// Zuletzt bearbeitet von Vivien Stumpe, 16.05.16
package de.app.mepa.mitarbeiterkonfig;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import de.app.mepa.GlobaleDaten;
import de.app.mepa.OnBoarding;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.mepa.R;

//OnClickListener implementieren, um zu reagieren wenn eine View geklickt wurde

public class Mitarbeiterkonfig_OnBoard extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;

    //von Nathalie Horn 02.05.16
    //Attribute festlegen
    protected String vorname;
    protected String name;

    //Buttonvariablen für die Buttons Speichern und Verwerfen
    private LinearLayout lnl_buttons;
    private Button btn_speichern;
    private Button btn_verwerfen;
    private EditText etxt_mitarbeiter_name;
    private EditText etxt_mitarbeiter_vorname;
    private TextWatcher textWatcher;

    private GlobaleDaten mfall;
    private ImageView imgv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitarbeiterkonfig_onboard);
        /*
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);;

        /* von Nathalie Horn, 02.05.16
        Edittexte und Buttons in der Activity finden und ansprechen
         */
        lnl_buttons=(LinearLayout)findViewById(R.id.lnl_buttons_mitarbeiter_onb);
        lnl_buttons.setVisibility(lnl_buttons.GONE);
        etxt_mitarbeiter_name=(EditText)findViewById(R.id.etxt_mitarbeiter_name_onb);
        etxt_mitarbeiter_vorname=(EditText)findViewById(R.id.etxt_mitarbeiter_vorname_onb);

        /*
        Zuweisen der Buttonvariablen zu den Buttons in der Activity
        Setzen des OnClickListeners, damit auf Klicks reagiert wird
         */
        btn_verwerfen = (Button)findViewById(R.id.btn_verwerfen_mitarbeiter_konfig_onb);
        btn_speichern = (Button)findViewById(R.id.btn_speichern_mitarbeiter_konfig_onb);
        btn_verwerfen.setOnClickListener(this);
        btn_speichern.setOnClickListener(this);
        imgv_back=(ImageView)findViewById(R.id.imgv_before_mitarbeiter_onb);
        imgv_back.setOnClickListener(this);

        textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Buttons speichern & verwerfen sind sichtbar
                lnl_buttons.setVisibility(lnl_buttons.VISIBLE);
            }
        };

        /* von Vivien Stumpe, 01.05.16
        TextChangedListener muss auf die Eingabefelder gesetzt werden
        damit auch registriert wird, wenn eine Eingabe getätigt wurde
        und entsprechend reagiert werden kann -> siehe TextWatcher
         */
        etxt_mitarbeiter_name.addTextChangedListener(textWatcher);
        etxt_mitarbeiter_vorname.addTextChangedListener(textWatcher);

        /* von Vivien Stumpe, 16.05.16
        Falls Werte vorhanden sind, werden diese im Screen geladen
         */
        setWerte();
    }

    @Override
    public void onClick(View v) {
        //von Nathalie Horn, 02.05.16
        //clicked element mit dem geklickten Button belegen
        int ce = v.getId();
        if(ce==R.id.imgv_before_mitarbeiter_onb){
            Intent onboard=new Intent(Mitarbeiterkonfig_OnBoard.this, OnBoarding.class);
            startActivity(onboard);
        }
        if(ce == R.id.btn_speichern_mitarbeiter_konfig_onb) {
            //wenn das Eingabefeld nicht leer ist, werden die Buttons eingeblendet
            //muss noch angepasst werden -> Daten werden ja auch gelöscht?
            // & Vergleich mit bestehenden Daten fehlt
            speichereEingaben();
            if(mfall.getFall_angelegt()){
                Intent fallein=new Intent(Mitarbeiterkonfig_OnBoard.this, Falleingabe.class);
                startActivity(fallein);
            }

            //Buttons wieder ausblenden
            lnl_buttons.setVisibility(lnl_buttons.GONE);
        }

        if(ce==R.id.btn_verwerfen_mitarbeiter_konfig_onb){
            Toast.makeText(this, "Mitarbeiter verworfen", Toast.LENGTH_LONG).show();
            mfall.loescheSan();
            setWerte();
            //Buttons werden ausgeblendet
            lnl_buttons.setVisibility(lnl_buttons.GONE);
            //Inhalt des Textfeldes löschen?
        }
    }

    /* von Vivien Stumpe, 16.05.16
    Prozedur, die die eingegebenen Daten in den Variablen speichert
     */
    public void speichereEingaben(){
        mfall=(GlobaleDaten)getApplication();
        mfall.setSan_name(etxt_mitarbeiter_name.getText().toString());
        Log.d("Fall", "Name gespeichert");

        mfall.setSan_vorname(etxt_mitarbeiter_vorname.getText().toString());
        Log.d("Fall", "Vorname gespeichert");
        mfall.setSan_vorh();
        mfall.setFall_angelegt();
    }

    /* von Vivien Stumpe, 16.05.16
    belegt die Eingabefelder etc. mit den lokal gespeicherten Werten, sofern vorhanden
     */
    public void setWerte(){
        mfall=(GlobaleDaten)getApplication();

        if((mfall.getSan_name()!=null)){
            etxt_mitarbeiter_name.setText(mfall.getSan_name());
        }
        if((mfall.getSan_vorname()!=null)){
            etxt_mitarbeiter_vorname.setText(mfall.getSan_vorname());
        }
    }
}
