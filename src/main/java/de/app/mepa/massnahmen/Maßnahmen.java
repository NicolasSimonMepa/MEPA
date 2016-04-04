package de.app.mepa.massnahmen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.R;

public class Maßnahmen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massnahmen);
    }

    public void selectItem (View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox13:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox14:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox15:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox16:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox17:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox18:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox19:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox20:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox21:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox22:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox23:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox24:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox25:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox26:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox27:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox28:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox29:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;

            // TODO: Maßnahmen festlegen
        }
    }
}
