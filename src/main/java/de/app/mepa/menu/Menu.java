package de.app.mepa.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    private TextView txtv_start;
    private TextView txtv_einst;
    private TextView txtv_falleing;
    private TextView txtv_falluebersicht;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        txtv_start=(TextView)findViewById(R.id.txtv_start_menu);
        txtv_start.setOnClickListener(this);

        txtv_einst=(TextView)findViewById(R.id.txtv_einstellungen_menu);
        txtv_einst.setOnClickListener(this);

        txtv_falleing=(TextView)findViewById(R.id.txtv_falleingabe_menu);
        txtv_falleing.setOnClickListener(this);

        txtv_falluebersicht=(TextView)findViewById(R.id.txtv_falluebersicht_menu);
        txtv_falluebersicht.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int ce = v.getId();
        if (ce == R.id.txtv_start_menu){
            Intent intent = new Intent(Menu.this, MainActivity.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_einstellungen_menu){
            Intent intent = new Intent(Menu.this, Einstellungen.class);
            startActivity(intent);
        }
        //Hier fehlen noch die anderen Activities
    }
}
