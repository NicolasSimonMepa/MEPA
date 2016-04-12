//Zuletzt bearbeitet von Vivien Stumpe, 10.04.16
package de.app.mepa.notfallsituation;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;

public class notfallsituation extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //von Vivien Stumpe, 10.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_notfall;
    private ListView listview_notfall;
    private MyAdapter myadapter_notfall;
    private int[] drawer_icons_notfall={R.drawable.mepa_icon, R.drawable.mepa_icon,
            R.drawable.falleingabe, R.drawable.mepa_icon, R.drawable.upload, R.drawable.impressum, R.drawable.mepa_icon,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallsituation);
        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_notfall=(DrawerLayout) findViewById(R.id.drawerLayout_Notfallsituation);
        listview_notfall=(ListView) findViewById(R.id.listview_Notfallsituation);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_notfall=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_erfassung), drawer_icons_notfall);
        listview_notfall.setAdapter(myadapter_notfall);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_notfall.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(notfallsituation.this, MainActivity.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(notfallsituation.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(notfallsituation.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(notfallsituation.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(notfallsituation.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das sechste Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==5) {
            Intent intent = new Intent(notfallsituation.this, Impressum.class);
            startActivity(intent);
        }
        //Wenn das siebte Element im Menü geklickt wurde, werden die Stammdaten geöffnet
        if(position==6) {
            Intent intent = new Intent(notfallsituation.this, Stammdaten.class);
            startActivity(intent);
        }
    }
}