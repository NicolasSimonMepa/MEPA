//Zuletzt geändert von Vivien Stumpe am 08.04.2016
package de.app.mepa.falleingabe;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.notfallsituation.notfallsituation;
import de.app.mepa.pers_daten.Pers_daten;
import de.app.mepa.verletzung.Verletzung;
import de.app.mepa.massnahmen.Massnahmen;
import de.app.mepa.erkrankung.Erkrankung;

//OnClickListener implementieren, um auf einen Klick des Benutzers zu reagieren
//von Vivien Stumpe, 03.04.16
//OnItemClickListener implementieren, um auf einen Klick im Drawer zu reagieren
//von Vivien Stumpe, 08.04.16
public class Falleingabe extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //private Buttonvariable, die auf den Button in der Activity zeigen soll
    //von Vivien Stumpe, 03.04.16
    private Button btn_fallein;

    //private Textviewvariable, die auf Persönliche Daten in der Activity zeigen soll
    //von Vivien Stumpe, 04.04.16
    private TextView txtv_pers_daten;
    private TextView txtv_notfall;
    private TextView txtv_verletzung;
    private TextView txtv_erkrankung;
    private TextView txtv_massnahmen;
    

    //von Vivien Stumpe, 08.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    private DrawerLayout drawerlayout_falleingabe;
    private ListView listview_falleingabe;
    private MyAdapter myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falleingabe);

        //Verbindung zwischen Buttonvariable und Button in der Activity herstellen
        //von Vivien Stumpe, 03.04.16
        btn_fallein = (Button)findViewById(R.id.btn_menu_fallein);

        //Click Event abfangen und den OnClickListener für die aktuelle View aufrufen
        //von Vivien Stumpe, 03.04.16
        btn_fallein.setOnClickListener(this);

        //Verbindung zwischen Variable und TextView in der Activity herstellen
        //von Vivien Stumpe, 04.04.16
        txtv_pers_daten = (TextView)findViewById(R.id.txtv_pers_daten);
        txtv_notfall = (TextView)findViewById(R.id.txtv_notfallsit);
        txtv_verletzung = (TextView)findViewById(R.id.txtv_verletzung);
        txtv_erkrankung = (TextView)findViewById(R.id.txtv_erkrankung);
        txtv_massnahmen = (TextView)findViewById(R.id.txtv_maßnahmen);

        //Click Event abfangen und den OnClickListener für die aktuelle View aufrufen
        //von Vivien Stumpe, 04.04.16
        txtv_pers_daten.setOnClickListener(this);
        txtv_notfall.setOnClickListener(this);
        txtv_verletzung.setOnClickListener(this);
        txtv_erkrankung.setOnClickListener(this);
        txtv_massnahmen.setOnClickListener(this);

        //von Vivien Stumpe, 08.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_falleingabe=(DrawerLayout) findViewById(R.id.drawerLayout_Falleingabe);
        listview_falleingabe=(ListView) findViewById(R.id.listview_falleingabe);
        //Adapter setzen, um die Einträge der ListView darzustellen
        myadapter=new MyAdapter(this);
        listview_falleingabe.setAdapter(myadapter);
        listview_falleingabe.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Deklaration und Initialisierung einer Hilfsvariablen (clicked element), die die ID des geklickten Buttons erhält
        //von Vivien Stumpe, 03.04.16
        int ce = v.getId();

        //Ein Intent erzeugen, wenn der bestimmte Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her
        //Aufrufen der Activity mittels Intent
        //von Vivien Stumpe, 03.04.16
        if(ce == R.id.btn_menu_fallein){
            Intent intent = new Intent(Falleingabe.this, Menu.class);
            startActivity(intent);
        }
        //Ein Intent erzeugen, wenn der bestimmte Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her
        //Aufrufen der Activity mittels Intent
        //von Vivien Stumpe, 04.04.16
        if(ce == R.id.txtv_pers_daten){
            Intent intent = new Intent(Falleingabe.this, Pers_daten.class);
            startActivity(intent);
        }
        if(ce == R.id.txtv_notfallsit){
            Intent intent = new Intent(Falleingabe.this, notfallsituation.class);
            startActivity(intent);
        }
        if(ce == R.id.txtv_verletzung){
            Intent intent = new Intent(Falleingabe.this, Verletzung.class);
            startActivity(intent);
        }
        if(ce == R.id.txtv_erkrankung){
            Intent intent = new Intent(Falleingabe.this, Erkrankung.class);
            startActivity(intent);
        }
        if(ce == R.id.txtv_maßnahmen){
            Intent intent = new Intent(Falleingabe.this, Massnahmen.class);
            startActivity(intent);
        }
    }

        //von Vivien Stumpe, 08.04.16
    @Override
    //Wird aufgerufen, wenn ein Element im Drawer geklickt wurde
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItemFromDrawer(position);
    }

        private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
            if(position==0) {
                Intent intent = new Intent(Falleingabe.this, MainActivity.class);
                startActivity(intent);
            }
            //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
            if(position==1) {
                Intent intent = new Intent(Falleingabe.this, Einstellungen.class);
                startActivity(intent);
            }
            //Wenn das dritte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
            if(position==2) {
                Intent intent = new Intent(Falleingabe.this, Falluebersicht.class);
                startActivity(intent);
            }

        }

}
    //von Vivien Stumpe, 08.04.16
    //Klasse für einen eigenen Adapter implementieren
class MyAdapter extends BaseAdapter{
    private Context context;
        //Array für die Texte, die im Menü dargestellt werden sollen
    private String[] drawer_falleingabe;
        //Array für die Icons/Piktogramme, die im Menü dargestellt werden sollen
    private int[] icons_falleingabe={R.drawable.mepa_icon, R.drawable.mepa_icon, R.drawable.mepa_icon};

        //Konstruktor überladen
    public MyAdapter(Context context){
        this.context=context;

        //Einträge des Menüs festlegen - sie finden sich in strings.xml im string Array drawer_nav_falleingabe
        drawer_falleingabe=context.getResources().getStringArray(R.array.drawer_nav_falleingabe);
    }
        //erforderlicher Dienst
    @Override
    public int getCount() {
        return drawer_falleingabe.length;
    }
    //erforderlicher Dienst
    @Override
    public Object getItem(int position) {
        return drawer_falleingabe[position];
    }
    //erforderlicher Dienst
    @Override
    public long getItemId(int position) {
        return position;
    }
    //erforderlicher Dienst
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=null;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Layout für die Elemente der ListView zuweisen - Wie sollen die Einträge im Menü dargestellt werden?
            row=inflater.inflate(R.layout.drawer_listview_item, parent, false);
        }
        else
        {
            row=convertView;
        }
        //Verbindung zu den Elementen des Drawers herstellen
        TextView txtv_title_drawer=(TextView) row.findViewById(R.id.txtv_drawer_listview);
        ImageView imgv_title_drawer=(ImageView) row.findViewById(R.id.imgv_drawer_listview);
        //Einträge der ListView im Menü darstellen
        txtv_title_drawer.setText(drawer_falleingabe[position]);
        //Icons/Piktogramme im Menü darstellen
        imgv_title_drawer.setImageResource(icons_falleingabe[position]);
        return row;
    }
}
