//Zuletzt geändert von Vivien Stumpe, 10.04.16
//Zuletzt geändert von Nathalie Horn, 17.05.16
package de.app.mepa.upload;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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


    @Override
    public void onClick(View v) {
        //Deklaration und Initialisierung der Hilfsvariablen ce, die die ID des geklickten Elements enthält
        int ce = v.getId();

        //Upload durch E-Mail
        //von Nathalie Horn am 27.04.16
        if(ce == R.id.txtv_upload_email){

            //von Nathalie Horn, 17.05.16
            //Variablen, die nötig sind um Apps zu filtern
            boolean found;
            found = false;

            mfall=(GlobaleDaten)getApplication();

            String adresse = "patient@krankenhaus.de";
            String adressarray[] = { adresse };
            String nachricht = "Anbei die Daten zu Fall [Fall-ID] vom "+mfall.getVer_date()
                    +".\n\nViele Grüße\n" +
                    mfall.getSan_vorname()+" "+mfall.getSan_name();

            //Zugriff auf, bzw Erstellung einer Datei im Verzeichnis MEPA_Dateiordner, das über den Dateimanager zu finden ist
            File ordner;
            ordner = new File(Environment.getExternalStorageDirectory(), "MEPA_Dateiordner");
            if (!ordner.exists()) {
                ordner.mkdirs();
            }
            //Der Dateiname besteht aus 'Text' und der Zeit in Millisekunden
            //Erstellung einer URI zur Datei
            /* File f = new File( ordner, "Text" + System.currentTimeMillis() + ".txt" );
            Uri u = Uri.fromFile(f);
            */

            /*  von Vivien Stumpe, 30.05.16
                XML-Datei zu einer Fall-ID wird als Anhang versandt
                !! muss noch durch einen Parameter ersetzt werden !!
             */
            File root = Environment.getExternalStorageDirectory();
            File file = new File( root.getAbsolutePath() + File.separator + "MEPA_Dateiordner", 1225716742+".xml" );

            File foto = new File( root.getAbsolutePath() + File.separator + "mepa", 373788354+".jpeg" );


            /*
            //von Nathalie Horn, 02.05.16
            //Schreibt etwas in die Textdatei, sodass sie versendet werden kann
            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter writer = new OutputStreamWriter(fileOut);
            try {
                writer.write(nachricht);
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            // Intent anlegen der die Funktion "Action_Send" aufruft.
            Intent emailversand = new Intent(android.content.Intent.ACTION_SEND);

            // Fügt der E-Mail Eigenschaften und unseren Text hinzu
            emailversand.putExtra(android.content.Intent.EXTRA_EMAIL, adressarray);
            emailversand.putExtra(android.content.Intent.EXTRA_SUBJECT, "Informationen zu Fall: [Fall-ID]");
            emailversand.putExtra(android.content.Intent.EXTRA_TEXT, nachricht);

            //Durch EXTRA_STREAM wird eine Datei versendet.
            emailversand.setType("*/*");
            //emailversand.putExtra(android.content.Intent.EXTRA_STREAM, Uri.parse("file://" + file.getAbsolutePath()));
            //emailversand.putExtra(android.content.Intent.EXTRA_STREAM, Uri.parse("file://" + foto.getAbsolutePath()));


            //Von Nathalie Horn, 17.05.16
            // Erhält die Liste an Intents, die E-Mails verschicken können.
            List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(emailversand, 0);
            if (!resInfo.isEmpty()) {
                for (ResolveInfo info : resInfo) {
                    if (info.activityInfo.packageName.toLowerCase().contains("mail") ||
                            info.activityInfo.name.toLowerCase().contains("mail")) {
                        emailversand.setPackage(info.activityInfo.packageName);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return;
                }
                //startet den Intent
                startActivity(Intent.createChooser(emailversand, "Select"));
            }
        }

        //von Nathalie Horn am 02.05.16
        if(ce == R.id.txtv_upload_bluetooth){

            //von Nathalie Horn, 17.05.16
            //Variablen, die nötig sind um Apps zu filtern
            boolean found;
            found = false;

            String nachricht = "Anbei die Daten zu Fall [Fall-ID].'\n'Viele Grüße'\n" +
                    "[Sani Vorname und Nachname]";

            //Zugriff auf, bzw Erstellung einer Datei im Verzeichnis MEPA_Dateiordner, das über den Dateimanager zu finden ist
            File ordner;
            ordner = new File(Environment.getExternalStorageDirectory(), "MEPA_Dateiordner");
            if (!ordner.exists()) {
                ordner.mkdirs();
            }
            //Der Dateiname besteht aus 'Text' und der Zeit in Millisekunden
            //Erstellung einer URI zur Datei
            File f = new File( ordner, "Text" + System.currentTimeMillis() + ".txt" );
            Uri u = Uri.fromFile(f);
            /*  von Vivien Stumpe, 30.05.16
                XML-Datei zu einer Fall-ID wird als Anhang versandt
                !! muss noch durch einen Parameter ersetzt werden !!
             */
            File root = Environment.getExternalStorageDirectory();
            File file = new File( root.getAbsolutePath() + File.separator + "MEPA_Dateiordner", 1225716742+".xml" );

            //von Nathalie Horn, 02.05.16
            //Schreibt etwas in die Textdatei, sodass sie versendet werden kann
            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter writer = new OutputStreamWriter(fileOut);
            try {
                writer.write(nachricht);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Intent anlegen der die Funktion "Action_Send" aufruft.
            Intent bluetoothversand = new Intent(android.content.Intent.ACTION_SEND);
            //Durch EXTRA_STREAM wird eine Datei versendet.
            bluetoothversand.setType("*/*");
            //bluetoothversand.putExtra(android.content.Intent.EXTRA_STREAM, u);
            //bluetoothversand.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + file.getAbsolutePath()));

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
    }

    // geändert von Nathalie Horn, 25.04.16
    private void selectItemFromDrawer(int position){

        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if(position==0) {
            Intent intent = new Intent(Upload.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(Upload.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(Upload.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(Upload.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Upload.this, Impressum.class);
            startActivity(intent);
        }
    }
}
