//Zuletzt geändert von Emile Yoncaova, 17.05.16
package de.app.mepa.erkrankung;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import de.app.mepa.GlobaleDaten;
import de.app.mepa.MyAdapter;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.massnahmen.Massnahmen;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;
import de.app.mepa.verletzung.Verletzung;

public class Erkrankung extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener{
    private ImageView imgv_before;
    private ImageView imgv_menü;
    //von Vivien Stumpe, 10.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_erkrankung;
    private ListView listview_erkrankung;
    private MyAdapter myadapter_erkrankung;
    private int[] drawer_icons_erkrankung={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};
    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;

    Toolbar toolbar;

    private GlobaleDaten mfall;
    private CheckBox cck_erkrankung_keine;
    private CheckBox cck_erkrankung_alkoholisiert;
    private CheckBox cck_erkrankung_erbrechen;
    private CheckBox cck_erkrankung_schwindel;
    private CheckBox cck_erkrankung_herzkreislauf;
    private CheckBox cck_erkrankung_hitzeschlag;
    private CheckBox cck_erkrankung_hitzeerschoepfung;
    private CheckBox cck_erkrankung_vergiftung;
    private CheckBox cck_erkrankung_atmung;
    private CheckBox cck_erkrankung_unterkuehlung;
    private CheckBox cck_erkrankung_baucherkrankung;
    private CheckBox cck_erkrankung_stoffwechsel;
    private CheckBox cck_erkrankung_neurologie;
    private CheckBox cck_erkrankung_gynaekologie;
    private CheckBox cck_erkrankung_psychatrie;
    private CheckBox cck_erkrankung_kindernotfall;
    private CheckBox cck_erkrankung_geburtshilfe;
    private CheckBox cck_erkrankung_sonstiges;
    private EditText edtxt_erkrankung_sonstiges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erkrankung);

        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_erkrankung=(DrawerLayout) findViewById(R.id.drawerLayout_Erkrankung);
        listview_erkrankung=(ListView) findViewById(R.id.listview_Erkrankung);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_erkrankung=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_erkrankung);
        listview_erkrankung.setAdapter(myadapter_erkrankung);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_erkrankung.setOnItemClickListener(this);

        //von Vivien Stumpe, 11.04.16
        //Verbindung der View zur Scrollview in der Aktivität
        view=(View) findViewById(R.id.scrV_erkrankung);
        //OnTouchListener auf die View setzen
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            //Bei einem Swipe nach links wird die nächste Aktivität geöffnet
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Erkrankung.this, Massnahmen.class);
                startActivity(intent);
            }

            public void onSwipeRight() {
                drawerlayout_erkrankung.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Intent intent = new Intent(Erkrankung.this, Verletzung.class);
                startActivity(intent);
            }
        });
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        imgv_before = (ImageView)findViewById(R.id.imgv_before_erkrankung);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        drawerlayout_erkrankung.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        cck_erkrankung_keine=(CheckBox)findViewById(R.id.cck_erkrankung_keine);
        cck_erkrankung_alkoholisiert=(CheckBox)findViewById(R.id.cck_erkrankung_alkoholisiert);
        cck_erkrankung_erbrechen=(CheckBox)findViewById(R.id.cck_erkrankung_erbrechen);
        cck_erkrankung_schwindel=(CheckBox)findViewById(R.id.cck_erkrankung_schwindel);
        cck_erkrankung_herzkreislauf=(CheckBox)findViewById(R.id.cck_erkrankung_herzkreislauf);
        cck_erkrankung_hitzeschlag=(CheckBox)findViewById(R.id.cck_erkrankung_hitzeschlag);
        cck_erkrankung_hitzeerschoepfung=(CheckBox)findViewById(R.id.cck_erkrankung_hitzeerschoepfung);
        cck_erkrankung_vergiftung=(CheckBox)findViewById(R.id.cck_erkrankung_vergiftung);
        cck_erkrankung_atmung=(CheckBox)findViewById(R.id.cck_erkrankung_atmung);
        cck_erkrankung_unterkuehlung=(CheckBox)findViewById(R.id.cck_erkrankung_unterkuehlung);
        cck_erkrankung_baucherkrankung=(CheckBox)findViewById(R.id.cck_erkrankung_baucherkrankung);
        cck_erkrankung_stoffwechsel=(CheckBox)findViewById(R.id.cck_erkrankung_stoffwechsel);
        cck_erkrankung_neurologie=(CheckBox)findViewById(R.id.cck_erkrankung_neurologie);
        cck_erkrankung_psychatrie=(CheckBox)findViewById(R.id.cck_erkrankung_psychatrie);
        cck_erkrankung_gynaekologie=(CheckBox)findViewById(R.id.cck_erkrankung_gynaekologie);
        cck_erkrankung_kindernotfall=(CheckBox)findViewById(R.id.cck_erkrankung_kindernotfall);
        cck_erkrankung_geburtshilfe=(CheckBox)findViewById(R.id.cck_erkrankung_geburtshilfe);
        cck_erkrankung_sonstiges=(CheckBox)findViewById(R.id.cck_erkrankung_sonstiges);
        edtxt_erkrankung_sonstiges=(EditText)findViewById(R.id.edtxt_erkrankung_sonstiges);

        cck_erkrankung_keine.setOnClickListener(this);
        cck_erkrankung_alkoholisiert.setOnClickListener(this);
        cck_erkrankung_erbrechen.setOnClickListener(this);
        cck_erkrankung_schwindel.setOnClickListener(this);
        cck_erkrankung_herzkreislauf.setOnClickListener(this);
        cck_erkrankung_hitzeschlag.setOnClickListener(this);
        cck_erkrankung_hitzeerschoepfung.setOnClickListener(this);
        cck_erkrankung_vergiftung.setOnClickListener(this);
        cck_erkrankung_atmung.setOnClickListener(this);
        cck_erkrankung_unterkuehlung.setOnClickListener(this);
        cck_erkrankung_baucherkrankung.setOnClickListener(this);
        cck_erkrankung_stoffwechsel.setOnClickListener(this);
        cck_erkrankung_neurologie.setOnClickListener(this);
        cck_erkrankung_psychatrie.setOnClickListener(this);
        cck_erkrankung_gynaekologie.setOnClickListener(this);
        cck_erkrankung_kindernotfall.setOnClickListener(this);
        cck_erkrankung_geburtshilfe.setOnClickListener(this);
        cck_erkrankung_sonstiges.setOnClickListener(this);

        setWerte();
    }

    //von Vivien Stumpe, 10.04.16
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    //von Vivien Stumpe, 10.04.16
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Erkrankung.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Erkrankung.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Erkrankung.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Erkrankung.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Erkrankung.this, Impressum.class);
            startActivity(intent);
        }
        drawerlayout_erkrankung.closeDrawers();
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
        if (ce == R.id.imgv_before_erkrankung) {
            Intent intent = new Intent(Erkrankung.this, Falleingabe.class);
            startActivity(intent);
        }
        if(ce == R.id.imgv_menu){
            drawerlayout_erkrankung.openDrawer(GravityCompat.START);
        }
    }
    public void onPause(){
        super.onPause();
        //Eingaben werden lokal gespeichert
        speichereEingaben();
    }
    public void speichereEingaben(){
        mfall=(GlobaleDaten)getApplication();
        if (cck_erkrankung_keine.isChecked()){
            mfall.setErk_keine(1);
        }
        else
            mfall.setErk_keine(0);
        if (cck_erkrankung_alkoholisiert.isChecked()){
            mfall.setAlkoholisiert(1);
        }
        else
            mfall.setAlkoholisiert(0);
        if (cck_erkrankung_erbrechen.isChecked()){
            mfall.setUebelkeit_erbrechen(1);
        }
        else
            mfall.setUebelkeit_erbrechen(0);
        if (cck_erkrankung_schwindel.isChecked()){
            mfall.setSchwindel(1);
        }
        else
            mfall.setSchwindel(0);
        if (cck_erkrankung_herzkreislauf.isChecked()){
            mfall.setHerz_kreislauf(1);
        }
        else
            mfall.setHerz_kreislauf(0);
        if (cck_erkrankung_hitzeschlag.isChecked()){
            mfall.setHitzeschlag(1);
        }
        else
            mfall.setHitzeschlag(0);
        if (cck_erkrankung_hitzeerschoepfung.isChecked()){
            mfall.setHitzeerschoepfung(1);
        }
        else
            mfall.setHitzeerschoepfung(0);
        if (cck_erkrankung_vergiftung.isChecked()){
            mfall.setVergiftung(1);
        }
        else
            mfall.setVergiftung(0);
        if (cck_erkrankung_atmung.isChecked()){
            mfall.setAtmung(1);
        }
        else
            mfall.setAtmung(0);
        if (cck_erkrankung_unterkuehlung.isChecked()){
            mfall.setUnterkuehlung(1);
        }
        else
            mfall.setUnterkuehlung(0);
        if (cck_erkrankung_baucherkrankung.isChecked()){
            mfall.setBaucherkrankung(1);
        }
        else
            mfall.setBaucherkrankung(0);
        if (cck_erkrankung_stoffwechsel.isChecked()){
            mfall.setStoffwechsel(1);
        }
        else
            mfall.setStoffwechsel(0);
        if (cck_erkrankung_neurologie.isChecked()){
            mfall.setNeurologie(1);
        }
        else
            mfall.setNeurologie(0);
        if (cck_erkrankung_psychatrie.isChecked()){
            mfall.setPsychatrie(1);
        }
        else
            mfall.setPsychatrie(0);
        if (cck_erkrankung_gynaekologie.isChecked()){
            mfall.setGynaekologie(1);
        }
        else
            mfall.setGynaekologie(0);
        if (cck_erkrankung_kindernotfall.isChecked()){
            mfall.setKindernotfall(1);
        }
        else
            mfall.setKindernotfall(0);
        if (cck_erkrankung_geburtshilfe.isChecked()){
            mfall.setGeburtshilfe(1);
        }
        else
            mfall.setGeburtshilfe(0);
        if (cck_erkrankung_sonstiges.isChecked()){
            mfall.setErk_sonstiges(1);
        }
        else
            mfall.setErk_sonstiges(0);
        mfall.setErk_edtxt_sonstiges(edtxt_erkrankung_sonstiges.getText().toString());
    }

    public void setWerte() {
        mfall = (GlobaleDaten) getApplication();
        if(mfall.getErk_keine()!=null) {
            if (mfall.getErk_keine() == 1) {
                cck_erkrankung_keine.setChecked(true);
            }
        }
        if(mfall.getAlkoholisiert()!=null) {
            if (mfall.getAlkoholisiert() == 1) {
                cck_erkrankung_alkoholisiert.setChecked(true);
            }
        }
        if(mfall.getUebelkeit_erbrechen()!=null) {
            if (mfall.getUebelkeit_erbrechen() == 1) {
                cck_erkrankung_erbrechen.setChecked(true);
            }
        }
        if(mfall.getSchwindel()!=null) {
            if (mfall.getSchwindel() == 1) {
                cck_erkrankung_schwindel.setChecked(true);
            }
        }
        if(mfall.getHerz_kreislauf()!=null) {
            if (mfall.getHerz_kreislauf() == 1) {
                cck_erkrankung_herzkreislauf.setChecked(true);
            }
        }
        if(mfall.getHitzeschlag()!=null) {
            if (mfall.getHitzeschlag() == 1) {
                cck_erkrankung_hitzeschlag.setChecked(true);
            }
        }
        if(mfall.getHitzeerschoepfung()!=null) {
            if (mfall.getHitzeerschoepfung() == 1) {
                cck_erkrankung_hitzeerschoepfung.setChecked(true);
            }
        }
        if(mfall.getVergiftung()!=null) {
            if (mfall.getVergiftung() == 1) {
                cck_erkrankung_vergiftung.setChecked(true);
            }
        }
        if(mfall.getAtmung()!=null) {
            if (mfall.getAtmung() == 1) {
                cck_erkrankung_atmung.setChecked(true);
            }
        }
        if(mfall.getUnterkuehlung()!=null) {
            if (mfall.getUnterkuehlung() == 1) {
                cck_erkrankung_unterkuehlung.setChecked(true);
            }
        }
        if(mfall.getBaucherkrankung()!=null) {
            if (mfall.getBaucherkrankung() == 1) {
                cck_erkrankung_baucherkrankung.setChecked(true);
            }
        }
        if(mfall.getStoffwechsel()!=null) {
            if (mfall.getStoffwechsel() == 1) {
                cck_erkrankung_stoffwechsel.setChecked(true);
            }
        }
        if(mfall.getNeurologie()!=null) {
            if (mfall.getNeurologie() == 1) {
                cck_erkrankung_neurologie.setChecked(true);
            }
        }
        if(mfall.getPsychatrie()!=null) {
            if (mfall.getPsychatrie() == 1) {
                cck_erkrankung_psychatrie.setChecked(true);
            }
        }
        if(mfall.getGynaekologie()!=null) {
            if (mfall.getGynaekologie() == 1) {
                cck_erkrankung_gynaekologie.setChecked(true);
            }
        }
        if(mfall.getKindernotfall()!=null) {
            if (mfall.getKindernotfall() == 1) {
                cck_erkrankung_kindernotfall.setChecked(true);
            }
        }
        if(mfall.getGeburtshilfe()!=null) {
            if (mfall.getGeburtshilfe() == 1) {
                cck_erkrankung_geburtshilfe.setChecked(true);
            }
        }
        if(mfall.getErk_sonstiges()!=null) {
            if (mfall.getErk_sonstiges() == 1) {
                cck_erkrankung_sonstiges.setChecked(true);
            }
        }
        if((mfall.getErk_edtxt_sonstiges()!=null)){
            edtxt_erkrankung_sonstiges.setText(mfall.getErk_edtxt_sonstiges());
        }
    }
}
