//Zuletzt bearbeitet von Vivien Stumpe, 11.04.16
package de.app.mepa.pers_daten;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.erkrankung.Erkrankung;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;
import de.app.mepa.verletzung.Verletzung;
import de.app.mepa.OnSwipeTouchListener;


public class Pers_daten extends AppCompatActivity implements AdapterView.OnItemClickListener {
//von Vivien Stumpe, 10.04.16
//DrawerLayout für das Hamburger Menü
//ListView, die die Einträge des Menüs enthält
//Adapter, der die Einträge der ListView darstellt
//Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_pers_daten;
    private ListView listview_pers_daten;
    private MyAdapter myadapter_pers_daten;
    private int[] drawer_icons_pers_daten={R.drawable.mepa_icon, R.drawable.mepa_icon,
            R.drawable.falleingabe, R.drawable.mepa_icon, R.drawable.upload, R.drawable.impressum, R.drawable.mepa_icon,};
    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pers_daten);
        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_pers_daten=(DrawerLayout) findViewById(R.id.drawerLayout_pers_daten);
        listview_pers_daten=(ListView) findViewById(R.id.listview_pers_daten);

        myadapter_pers_daten=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_erfassung), drawer_icons_pers_daten);
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
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Pers_daten.this, MainActivity.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Pers_daten.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Pers_daten.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Pers_daten.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Pers_daten.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das sechste Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==5) {
            Intent intent = new Intent(Pers_daten.this, Impressum.class);
            startActivity(intent);
        }
        //Wenn das siebte Element im Menü geklickt wurde, werden die Stammdaten geöffnet
        if(position==6) {
            Intent intent = new Intent(Pers_daten.this, Stammdaten.class);
            startActivity(intent);
        }
    }
}
