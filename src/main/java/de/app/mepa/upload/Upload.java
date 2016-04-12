//Zuletzt geändert von Vivien Stumpe, 10.04.16
//Zuletzt geändert von Nathalie Horn, 11.04.16
package de.app.mepa.upload;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;

public class Upload extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //Drawer Layout: Listview, Adapter und Array der Icons im Drawer Menü
    private DrawerLayout drawerlayout_upload;
    private ListView listview_upload;
    private MyAdapter myadapter_upload;
    private int[] drawer_icons_upload = {R.drawable.mepa_icon, R.drawable.mepa_icon,
            R.drawable.mepa_icon, R.drawable.mepa_icon, R.drawable.impressum, R.drawable.mepa_icon,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // für das Drawer Layout, Drawer und Listview zuweisen
        drawerlayout_upload = (DrawerLayout) findViewById(R.id.drawerLayout_Upload);
        listview_upload = (ListView) findViewById(R.id.listview_upload);
        //Adapter für Listview erzeugen
        myadapter_upload = new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_upload), drawer_icons_upload);
        listview_upload.setAdapter(myadapter_upload);
        listview_upload.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt (Drawer/Hamburger Menü)
        selectItemFromDrawer(position);
    }

        private void selectItemFromDrawer(int position) {

            //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
            if (position == 0) {
                Intent intent = new Intent(Upload.this, MainActivity.class);
                startActivity(intent);
            }

            //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
            if (position == 1) {
                Intent intent = new Intent(Upload.this, Einstellungen.class);
                startActivity(intent);
            }

            //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe geöffnet
            if (position == 2) {
                Intent intent = new Intent(Upload.this, Falleingabe.class);
                startActivity(intent);
            }

            //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
            if (position == 3) {
                Intent intent = new Intent(Upload.this, Falluebersicht.class);
                startActivity(intent);
            }

            //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
            if (position == 4) {
                Intent intent = new Intent(Upload.this, Impressum.class);
                startActivity(intent);
            }

            //Wenn das sechste Element im Menü geklickt wurde, werden die Stammdaten geöffnet
            if (position == 5) {
                Intent intent = new Intent(Upload.this, Stammdaten.class);
                startActivity(intent);
            }
        }
}


