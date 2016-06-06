//Zuletzt geändert von Vivien Stumpe, 06.06.16
//Zuletzt geändert von Nathalie Horn, 31.05.16
package de.app.mepa.upload;

import android.os.Build;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.app.mepa.FalleingabeDataSource;
import de.app.mepa.GlobaleDaten;
import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.R;

public class Upload extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //von Nathalie Horn, 25.04.16, geändert am 02.05.16
    //Private Textvariablen, die die Möglichkeiten des Uploads anzeigen.
    private TextView txtv_email;
    private TextView txtv_bluetooth;
    private TextView txtv_usb;
    private TextView txtv_upload;

    //Drawer Layout: Listview, Adapter und Array der Icons im Drawer Menü
    private DrawerLayout drawerlayout_upload;
    private ListView listview_upload;
    private MyAdapter myadapter_upload;
    private int[] drawer_icons_upload = {R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    /*von Nathalie Horn, 13.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    private GlobaleDaten mfall;

    //von Nathalie Horn, 31.05.16
    //Variablen für Fallauswahl
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    //von Nathalie Horn, 30.05.16
    ListView falluebersichtListView;
    FalleingabeDataSource dataSource;
    String[] patList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //Verbindung zwischen Variable und TextView in der Activity herstellen
        //von Nathalie Horn, 25.04.16
        //geändert am 02.05.16
        txtv_email = (TextView)findViewById(R.id.txtv_upload_email);
        txtv_bluetooth = (TextView)findViewById(R.id.txtv_upload_bluetooth);
        txtv_usb = (TextView)findViewById(R.id.txtv_upload_usb);
        txtv_upload = (TextView)findViewById(R.id.txtv_upload_upload);

        //von Nathalie Horn, 31.05.16
        relativeLayout = (RelativeLayout)findViewById(R.id.relative);

        //Click Event abfangen und den OnClickListener für die aktuelle View aufrufen
        //von Nathalie Horn, 25.04.16
        //geändert am 02.05.16
        txtv_email.setOnClickListener(this);
        txtv_bluetooth.setOnClickListener(this);
        txtv_usb.setOnClickListener(this);
        txtv_upload.setOnClickListener(this);

        // für das Drawer Layout, Drawer und Listview zuweisen
        drawerlayout_upload = (DrawerLayout) findViewById(R.id.drawerLayout_Upload);
        listview_upload = (ListView) findViewById(R.id.listview_upload);
        //Adapter für Listview erzeugen
        myadapter_upload = new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_upload);
        listview_upload.setAdapter(myadapter_upload);
        listview_upload.setOnItemClickListener(this);

        /*von , 12.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_upload, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_upload.addDrawerListener(actionbardrawertoggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }


    public void showAllListEntriesNeu () {
        patList = dataSource.getAllPat();
        ArrayAdapter<String> faelleAdapterNeu=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                patList);
         falluebersichtListView.setAdapter(faelleAdapterNeu);
    }

    @Override
    public void onClick(View v) {
        //Deklaration und Initialisierung der Hilfsvariablen ce, die die ID des geklickten Elements enthält
        int ce = v.getId();

        //Upload durch E-Mail
        //von Nathalie Horn am 27.04.16
        if(ce == R.id.txtv_upload_email){

            //Dies ist der erste Weg für das Pop-Up, eine neue Activity. Es erscheint allerdings nichts.
            //startActivity(new Intent(Upload.this, Upload_Falluebersicht.class));
            //Intent popup = new Intent(Upload.this,Upload_Falluebersicht.class);
            //startActivity(popup);

            //Dies ist der zweite Weg, das Popup-Widget.
            //von Nathalie Horn, 31.05.16
            //Erzeugen des PopUpWindows zur Fallauswahl
            layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.activity_upload_falluebersicht, null);

            popupWindow = new PopupWindow(container, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT,true);
            popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
            popupWindow.update(450, 680);

            container.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    popupWindow.dismiss();
                    return true;
                }
            });

            dataSource = new FalleingabeDataSource(this);
            dataSource.open();
            
            //Hier soll der Inhalt der Falluebersicht angezeigt werden. Ich bin mir nicht sicher ob das der richtige Weg ist.
            popupWindow.setContentView(falluebersichtListView);
            falluebersichtListView = (ListView)container.findViewById(R.id.list_upload_falluebersicht);

            showAllListEntriesNeu();
            falluebersichtListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   
                    // von Vivien Stumpe, 23.05.16
                    //Teilt den String bei einem Leerzeichen
                    String string = patList[position];
                    String[] parts = string.split("\\s+");
                    String prot_id_string = parts[0];

                    Log.d("Fall", prot_id_string + " Übergebene Fall-ID aus der Listview");
                    String name = parts[1];
                    Log.d("Fall", name + " Übergebener Nachname aus der Listview");
                    //prot_id enthält die ID des ausgewählten Falls
                    mfall=(GlobaleDaten)getApplication();
                    /*  verändert von Vivien Stumpe, 05.06.16
                         wenn es ein Foto gibt, wird die Mail mit Foto und XML verschickt
                         sonst nur die XML-Datei
                         und wenn gar nichts zu der Fall-ID verfügbar ist, kommt eine Fehlermeldung
                     */
                    File root = Environment.getExternalStorageDirectory();
                    //Fall-ID muss hier eingesetzt werden!!!
                    File foto = new File(root.getAbsolutePath() + File.separator + "mepa", prot_id_string + ".jpeg");
                    File file = new File(root.getAbsolutePath() + File.separator + "MEPA_Dateiordner", prot_id_string + ".xml");
                    String adresse = "patient@krankenhaus.de";
                    String adressarray[] = {adresse};
                    //Fall-ID muss hier eingesetzt werden!!!
                    String subject="Informationen zu Fall: "+ prot_id_string;
                    //Fall-ID muss hier eingesetzt werden!!!
                    String nachricht = "Anbei die Daten zu Fall "+prot_id_string+" vom " + mfall.getVer_date()
                            + ".\n\nViele Grüße\n" +
                            mfall.getSan_vorname() + " " + mfall.getSan_name();

                    //wenn es die XML-Datei und ein Foto gibt, wird beides versandt
                    if(file.exists()&&foto.exists()) {
                        emailMultipleAttachments(file, foto, adressarray, nachricht, subject);
                    }
                    else{
                        //wenn es die XML-Datei gibt, wird diese versandt
                        if(file.exists()){
                            email(file, adressarray, nachricht, subject);
                        }
                        else{
                            //Wenn nicht wird eine Fehlermeldung ausgegebeben
                            Toast.makeText(getApplicationContext(), "Keine Dateien verfügbar", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            dataSource.close();
        }

        //von Nathalie Horn am 02.05.16
        if(ce == R.id.txtv_upload_bluetooth){
            File root = Environment.getExternalStorageDirectory();
            //Fall-ID muss hier eingesetzt werden!!!
            File foto = new File(root.getAbsolutePath() + File.separator + "mepa", 229670116 + ".jpeg");
            File file = new File(root.getAbsolutePath() + File.separator + "MEPA_Dateiordner", 1360885741 + ".xml");

            //wenn es die XML-Datei und ein Foto gibt, wird beides versandt
            if(file.exists()&&foto.exists()) {
                bluetoothMultipleAttachments(file, foto);
            }
            else{
                //wenn es die XML-Datei gibt, wird diese versandt
                if(file.exists()){
                    bluetooth(file);
                }
                else{
                    //Wenn nicht wird eine Fehlermeldung ausgegebeben
                    Toast.makeText(getApplicationContext(), "Keine Dateien verfügbar", Toast.LENGTH_LONG).show();
                }
            }
        }

        if(ce == R.id.txtv_upload_usb){
            Toast.makeText(this, "Funktion noch nicht verfügbar", Toast.LENGTH_LONG).show();
        }

        if(ce == R.id.txtv_upload_upload){
            Toast.makeText(this, "Funktion noch nicht verfügbar", Toast.LENGTH_LONG).show();
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt (Drawer/Hamburger Menü)
        selectItemFromDrawer(position);
        finish();
    }

    // geändert von Nathalie Horn, 25.04.16
    private void selectItemFromDrawer(int position) {

        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if (position == 0) {
            Intent intent = new Intent(getApplicationContext(), Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if (position == 1) {
            Intent intent = new Intent(getApplicationContext(), Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if (position == 2) {
            Intent intent = new Intent(getApplicationContext(), Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if (position == 3) {
            Intent intent = new Intent(getApplicationContext(), Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if (position == 4) {
            Intent intent = new Intent(getApplicationContext(), Impressum.class);
            startActivity(intent);
        }
    }

    /*  von Vivien Stumpe, 05.06.16
        zurück zur Falleingabe beim Drücken des Zurückpfeils des Smartphones
    */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Falleingabe.class);
        startActivity(intent);
        finish();
    }
    //Upload durch E-Mail
    //von Nathalie Horn am 27.04.16
    private void email(File file, String[] adressarray, String nachricht, String subject) {
        // Intent anlegen der die Funktion "Action_Send" aufruft.
        Intent emailversand = new Intent(android.content.Intent.ACTION_SEND);

        // Fügt der E-Mail Eigenschaften und unseren Text hinzu
        emailversand.putExtra(android.content.Intent.EXTRA_EMAIL, adressarray);
        emailversand.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailversand.putExtra(android.content.Intent.EXTRA_TEXT, nachricht);

        //Durch EXTRA_STREAM wird eine Datei versendet.
        emailversand.setType("*/*");
        emailversand.putExtra(android.content.Intent.EXTRA_STREAM, Uri.parse("file://" + file.getAbsolutePath()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            emailversand.setType(null); // If we're using a selector, then clear the type to null. I don't know why this is needed, but it doesn't work without it.

            final Intent restrictIntent = new Intent(Intent.ACTION_SENDTO);
            Uri data = Uri.parse("mailto:");
            restrictIntent.setData(data);
            emailversand.setSelector(restrictIntent);
        }
            //startet den Intent
            startActivity(Intent.createChooser(emailversand, "E-Mail verschicken"));

    }
    /*  von Vivien Stumpe, 05.06.16
        Email mit Foto und XML als Anhang
     */
    private void emailMultipleAttachments(File file, File foto, String[] adressarray, String nachricht, String subject) {
        mfall = (GlobaleDaten) getApplication();

        // Intent anlegen der die Funktion "Action_Send_Multiple" aufruft, um mehrere Datein zu verschicken
        Intent emailversandm = new Intent(Intent.ACTION_SEND_MULTIPLE);

        // Fügt der E-Mail Eigenschaften und unseren Text hinzu
        emailversandm.putExtra(android.content.Intent.EXTRA_EMAIL, adressarray);
        emailversandm.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailversandm.putExtra(android.content.Intent.EXTRA_TEXT, nachricht);
        
        emailversandm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Fügt der E-Mail Eigenschaften und unseren Text hinzu
        emailversandm.putExtra(android.content.Intent.EXTRA_EMAIL, adressarray);
        emailversandm.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        emailversandm.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailversandm.putExtra(android.content.Intent.EXTRA_TEXT, nachricht);

        emailversandm.setType("message/rfc822");

        //Arraylist für die Uris der Anhänge
        ArrayList<Uri> uris = new ArrayList<>();
        //Uri der XML-Datei
        uris.add(Uri.parse("file://" + file.getAbsolutePath()));
        //Uri des Fotos
        uris.add(Uri.parse("file://" + foto.getAbsolutePath()));
        //Intent die Anhänge anfügen
        emailversandm.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            emailversandm.setType(null); // If we're using a selector, then clear the type to null. I don't know why this is needed, but it doesn't work without it.

            final Intent restrictIntent = new Intent(Intent.ACTION_SENDTO);
            Uri data = Uri.parse("mailto:");
            restrictIntent.setData(data);
            emailversandm.setSelector(restrictIntent);
        }
        //Intent sarten
        startActivity(Intent.createChooser(emailversandm, "E-Mail verschicken"));

    }
    //von Nathalie Horn am 02.05.16
    //angepasst von VS, 06.06.16
    private void bluetooth(File file){
        //von Nathalie Horn, 17.05.16
        //Variablen, die nötig sind um Apps zu filtern
        boolean found;
        found = false;

        //Zugriff auf, bzw Erstellung einer Datei im Verzeichnis MEPA_Dateiordner, das über den Dateimanager zu finden ist
        File ordner;
        ordner = new File(Environment.getExternalStorageDirectory(), "MEPA_Dateiordner");
        if (!ordner.exists()) {
            ordner.mkdirs();
        }
            /*  von Vivien Stumpe, 30.05.16
                XML-Datei zu einer Fall-ID wird als Anhang versandt
                !! muss noch durch einen Parameter ersetzt werden !!
             */

        // Intent anlegen der die Funktion "Action_Send" aufruft um eine Datei zu versenden
        Intent bluetoothversand = new Intent(android.content.Intent.ACTION_SEND);
        //Durch EXTRA_STREAM wird eine Datei versendet.
        bluetoothversand.setType("*/*");
        //bluetoothversand.putExtra(android.content.Intent.EXTRA_STREAM, u);
        bluetoothversand.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + file.getAbsolutePath()));

        //startActivity(Intent.createChooser(bluetoothversand, "Protokoll senden"));

        //Von Nathalie Horn, 17.05.16
        // Erhält die Liste an Intents, die E-Mails verschicken können.
        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(bluetoothversand, 0);
        if (!resInfo.isEmpty()) {
            for (ResolveInfo info : resInfo) {
                if (info.activityInfo.packageName.toLowerCase().contains("bluetooth") ||
                        info.activityInfo.name.toLowerCase().contains("bluetooth")) {
                    bluetoothversand.setPackage(info.activityInfo.packageName);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return;
            }
            //startet den Intent
            startActivity(Intent.createChooser(bluetoothversand, "Select"));
        }
    }
    /*  von Vivien Stumpe, 06.06.16
        Bluetoothweiterleitung mit Foto und XML als Anhang
     */
    private void bluetoothMultipleAttachments(File file, File foto) {
        mfall = (GlobaleDaten) getApplication();
        //von Nathalie Horn, 17.05.16
        //Variablen, die nötig sind um Apps zu filtern
        boolean found;
        found = false;
        // Intent anlegen der die Funktion "Action_Send_Multiple" aufruft, um mehrere Datein zu verschicken
        Intent bluetoothversandm = new Intent(Intent.ACTION_SEND_MULTIPLE);

        bluetoothversandm.setType("*/*");
        //Arraylist für die Uris der Anhänge
        ArrayList<Uri> uris = new ArrayList<>();
        //Uri der XML-Datei
        uris.add(Uri.parse("file://" + file.getAbsolutePath()));
        //Uri des Fotos
        uris.add(Uri.parse("file://" + foto.getAbsolutePath()));
        //Intent die Anhänge anfügen
        bluetoothversandm.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        //Von Nathalie Horn, 17.05.16
        // Erhält die Liste an Intents, die E-Mails verschicken können.
        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(bluetoothversandm, 0);
        if (!resInfo.isEmpty()) {
            for (ResolveInfo info : resInfo) {
                if (info.activityInfo.packageName.toLowerCase().contains("bluetooth") ||
                        info.activityInfo.name.toLowerCase().contains("bluetooth")) {
                    bluetoothversandm.setPackage(info.activityInfo.packageName);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return;
            }
            //startet den Intent
            startActivity(Intent.createChooser(bluetoothversandm, "Select"));
        }
    }
}
