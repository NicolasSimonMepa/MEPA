package de.app.mepa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.app.mepa.mepa.R;

/**
 * Created by vivienstumpe on 09.04.16.
 */
//von Vivien Stumpe, 08.04.16
//Klasse für einen eigenen Adapter implementieren
public class MyAdapter extends BaseAdapter {

    //mithilfe des Context Objekts werden Informationen über das Projekt zur Verfügung gestellt
    //brauchen wir, um z.B. das String Array zugreifen zu können oder das Layout anzupassen
    private Context context;
    //Array für die Texte, die im Menü dargestellt werden sollen
    private String[] drawer_menu;
    //Array für die Icons/Piktogramme, die im Menü dargestellt werden sollen
    private int[] icons_menu;

    //Konstruktor überladen
    public MyAdapter(Context context, String[] menupunkte, int[] icons){
        this.context=context;

        //Einträge des Menüs festlegen - sie finden sich in strings.xml im string Array das bei der Objekterzeugung übergeben wird
        drawer_menu=menupunkte;
        //Icons des Menüs festlegen - sie wurden bei der Objekterzeugung in der jeweiligen java Klasse übergeben
        icons_menu=icons;
    }
    //erforderlicher Dienst
    @Override
    public int getCount() {
        return drawer_menu.length;
    }
    //erforderlicher Dienst
    @Override
    public Object getItem(int position) {
        return drawer_menu[position];
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
        txtv_title_drawer.setText(drawer_menu[position]);
        //Icons/Piktogramme im Menü darstellen
        imgv_title_drawer.setImageResource(icons_menu[position]);
        return row;
    }
}


