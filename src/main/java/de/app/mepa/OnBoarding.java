package de.app.mepa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.mepa.R;
import de.app.mepa.mitarbeiterkonfig.Mitarbeiterkonfig_OnBoard;
import de.app.mepa.stammdaten.Stammdaten_OnBoard;

/**
 * Created by vivienstumpe on 16.05.16.
 */
public class OnBoarding extends AppCompatActivity implements View.OnClickListener {

    private TextView stammd, mitarbeiter;
    private GlobaleDaten mfall;
    private Button skip;
    private int img_stammd, img_mit;
    public void setIconStammd(int s) {
        img_stammd = s;
        TextView txtv_stammd = (TextView) findViewById(R.id.txtv_onb_stammdaten);
        try {
            txtv_stammd.setCompoundDrawablesWithIntrinsicBounds(0, img_stammd, 0, 0);
        } catch (Exception e) {

        }
    }
    public void setIconMitarb(int m) {
        img_mit = m;
        TextView txtv_mit = (TextView) findViewById(R.id.txtv_onb_mitarbeiter);
        try {
            txtv_mit.setCompoundDrawablesWithIntrinsicBounds(0, img_mit, 0, 0);
        } catch (Exception e) {

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboardingscreen);
        stammd=(TextView)findViewById(R.id.txtv_onb_stammdaten);
        mitarbeiter=(TextView)findViewById(R.id.txtv_onb_mitarbeiter);
        stammd.setOnClickListener(this);
        mitarbeiter.setOnClickListener(this);
        skip=(Button)findViewById(R.id.btn_onb_skip);
        skip.setOnClickListener(this);
        mfall=(GlobaleDaten)getApplication();
        //IconsStart();
        IconsSave();
    }
    protected void IconsStart(){
        setIconStammd(R.drawable.stammdaten);
        setIconMitarb(R.drawable.mitarbeiter_konf);
    }
    /* von Vivien Stumpe, 15.05.16
    Prozedur, die prüft, ob Daten eingegeben wurden und dementsprechend die Icons für das Kachelmenü lädt
    */
    protected void IconsSave(){
        mfall = (GlobaleDaten) getApplication();
        //Vergleich, ob die Pflichtfelder eingegeben wurden -> muss im Screen noch sichergestellt sein!
        if ((mfall.getVerb_vorh())&&(mfall.getVer_vorh())){
            setIconStammd(R.drawable.stammdaten_save);
        }
        else{
            setIconStammd(R.drawable.stammdaten);
        }
        if (mfall.getSan_vorh()){
            setIconMitarb(R.drawable.mitarbeiter_konf_save);
        }
        else{
            setIconMitarb(R.drawable.mitarbeiter_konf);
        }
    }
    @Override
    public void onClick(View v) {
        //von Nathalie Horn, 02.05.16
        //clicked element mit dem geklickten Button belegen
        int ce = v.getId();

        if (ce == R.id.txtv_onb_mitarbeiter) {
            Intent intent = new Intent(OnBoarding.this, Mitarbeiterkonfig_OnBoard.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_onb_stammdaten) {
            Intent intent = new Intent(OnBoarding.this, Stammdaten_OnBoard.class);
            startActivity(intent);
        }
        if (ce==R.id.btn_onb_skip){
            mfall.setUebersprungen(true);
            finishOnboarding();
        }
    }

    private void finishOnboarding() {
        // Launch the main Activity, called Falleingabe
        Intent main = new Intent(this, Falleingabe.class);
        startActivity(main);

        // Close the OnboardingActivity
        finish();
    }
}
