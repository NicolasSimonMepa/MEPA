package de.app.mepa.erkrankung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.R;

public class Erkrankung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erkrankung);
    }

    public void selectItem (View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox2:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox3:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox4:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox5:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox6:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox7:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox8:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox9:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox10:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox11:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            case R.id.checkbox12:
                if (checked)
                // ausgewählt
                else
                // nicht ausgewählt
                break;
            // TODO: Erkrankung festlegen
        }
    }
}
