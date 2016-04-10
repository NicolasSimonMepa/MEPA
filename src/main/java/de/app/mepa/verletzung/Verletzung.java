//Zuletzt bearbeitet von Vivien Stumpe, 04.04.16
package de.app.mepa.verletzung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import de.app.mepa.mepa.R;

public class Verletzung extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Variablen für die Spinner in der Activity erstellen
    //von Vivien Stumpe, 04.04.16
    private Spinner spin_schaedel_art;
    private Spinner spin_gesicht_art;
    private Spinner spin_hws_art;
    private Spinner spin_brustkorb_art;
    private Spinner spin_bauch_art;
    private Spinner spin_bws_art;
    private Spinner spin_becken_art;
    private Spinner spin_arme_art;
    private Spinner spin_beine_art;
    private Spinner spin_weichteile_art;

    private Spinner spin_schaedel_grad;
    private Spinner spin_gesicht_grad;
    private Spinner spin_hws_grad;
    private Spinner spin_brustkorb_grad;
    private Spinner spin_bauch_grad;
    private Spinner spin_bws_grad;
    private Spinner spin_becken_grad;
    private Spinner spin_arme_grad;
    private Spinner spin_beine_grad;
    private Spinner spin_weichteile_grad;

    //String Array erstellen mit den Elementen, die im Dropdown-Menü des Spinners in der Activity ausgewählt werden können
    //von Vivien Stumpe, 04.04.16
    private String[]art = {" ", "offen", "geschl"};
    private String[]grad = {" ", "leicht", "mittel", "schwer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verletzung);

        //ArrayAdapter erzeugen
        //Der ArrayAdapter generiert die Listenelemente des Spinners
        //Parameter sind die aktuelle Activity, Systemressource, Array mit den Listenelementen
        //von Vivien Stumpe, 04.04.16
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Verletzung.this,
                android.R.layout.simple_spinner_item,art);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter_grad = new ArrayAdapter<String>(Verletzung.this,
                android.R.layout.simple_spinner_item,grad);

        adapter_grad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Verknüpfung der Spinnervariable zum Spinner in der Activity herstellen
        //SpinnerAdapter dem Spinner zuweisen damit die Elemente ind er Activity angezeigt werden
        //setOnItemSelectedListener implementieren, damit mit dem ausgewählten Eintrag auch gearbeitet werden kann
        //von Vivien Stumpe, 04.04.16
        spin_schaedel_art = (Spinner)findViewById(R.id.spin_schaedel_art);
        spin_schaedel_art.setAdapter(adapter);
        spin_schaedel_art.setOnItemSelectedListener(this);

        spin_gesicht_art = (Spinner)findViewById(R.id.spin_gesicht_art);
        spin_gesicht_art.setAdapter(adapter);
        spin_gesicht_art.setOnItemSelectedListener(this);

        spin_hws_art = (Spinner)findViewById(R.id.spin_hws_art);
        spin_hws_art.setAdapter(adapter);
        spin_hws_art.setOnItemSelectedListener(this);

        spin_brustkorb_art = (Spinner)findViewById(R.id.spin_brustkorb_art);
        spin_brustkorb_art.setAdapter(adapter);
        spin_brustkorb_art.setOnItemSelectedListener(this);

        spin_bauch_art = (Spinner)findViewById(R.id.spin_bauch_art);
        spin_bauch_art.setAdapter(adapter);
        spin_bauch_art.setOnItemSelectedListener(this);

        spin_bws_art = (Spinner)findViewById(R.id.spin_bws_art);
        spin_bws_art.setAdapter(adapter);
        spin_bws_art.setOnItemSelectedListener(this);

        spin_becken_art = (Spinner)findViewById(R.id.spin_becken_art);
        spin_becken_art.setAdapter(adapter);
        spin_becken_art.setOnItemSelectedListener(this);

        spin_arme_art = (Spinner)findViewById(R.id.spin_arme_art);
        spin_arme_art.setAdapter(adapter);
        spin_arme_art.setOnItemSelectedListener(this);

        spin_beine_art = (Spinner)findViewById(R.id.spin_beine_art);
        spin_beine_art.setAdapter(adapter);
        spin_beine_art.setOnItemSelectedListener(this);

        spin_weichteile_art = (Spinner)findViewById(R.id.spin_weichteile_art);
        spin_weichteile_art.setAdapter(adapter);
        spin_weichteile_art.setOnItemSelectedListener(this);

        spin_schaedel_grad = (Spinner)findViewById(R.id.spin_schaedel_grad);
        spin_schaedel_grad.setAdapter(adapter_grad);
        spin_schaedel_grad.setOnItemSelectedListener(this);

        spin_gesicht_grad = (Spinner)findViewById(R.id.spin_gesicht_grad);
        spin_gesicht_grad.setAdapter(adapter_grad);
        spin_gesicht_grad.setOnItemSelectedListener(this);

        spin_hws_grad = (Spinner)findViewById(R.id.spin_hws_grad);
        spin_hws_grad.setAdapter(adapter);
        spin_hws_grad.setOnItemSelectedListener(this);

        spin_brustkorb_grad = (Spinner)findViewById(R.id.spin_brustkorb_grad);
        spin_brustkorb_grad.setAdapter(adapter);
        spin_brustkorb_grad.setOnItemSelectedListener(this);

        spin_bauch_grad = (Spinner)findViewById(R.id.spin_bauch_grad);
        spin_bauch_grad.setAdapter(adapter);
        spin_bauch_grad.setOnItemSelectedListener(this);

        spin_bws_grad = (Spinner)findViewById(R.id.spin_bws_grad);
        spin_bws_grad.setAdapter(adapter);
        spin_bws_grad.setOnItemSelectedListener(this);

        spin_becken_grad = (Spinner)findViewById(R.id.spin_becken_grad);
        spin_becken_grad.setAdapter(adapter);
        spin_becken_grad.setOnItemSelectedListener(this);

        spin_arme_grad = (Spinner)findViewById(R.id.spin_arme_grad);
        spin_arme_grad.setAdapter(adapter);
        spin_arme_grad.setOnItemSelectedListener(this);

        spin_beine_grad = (Spinner)findViewById(R.id.spin_beine_grad);
        spin_beine_grad.setAdapter(adapter);
        spin_beine_grad.setOnItemSelectedListener(this);

        spin_weichteile_grad = (Spinner)findViewById(R.id.spin_weichteile_grad);
        spin_weichteile_grad.setAdapter(adapter);
        spin_weichteile_grad.setOnItemSelectedListener(this);
    }

    //Prozedur, die aufgerufen wird wenn ein Listenelement im Spinner ausgewählt wurde
    //von Vivien Stumpe, 04.04.16
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Was soll passieren, wenn das erste Element gewählt wurde?
                break;
            case 1:
                // Was soll passieren, wenn das zweite Element gewählt wurde?
                break;
            case 2:
                // Was soll passieren, wenn das dritte Element gewählt wurde?
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
