package com.activitats.silvia.paisesficherosarrays;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {
    Spinner sp_pais, sp_ciudad;
    TextView tvpais, tvciudad, listas;
    final String TAG="Silvia";
    ArrayAdapter<String> adap_paises, adap_ciudades;
    ArrayList<String> ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listas= (TextView) findViewById(R.id.tvListasDinamicas);
        tvpais= (TextView) findViewById(R.id.tvPais);
        tvciudad= (TextView) findViewById(R.id.tvCiudad);
        sp_pais= (Spinner) findViewById(R.id.spPais);

        //rellenamos el spinner: creamos array de strings de paises y recogemos la info del archivo arrays
        String array_paises[] = getResources().getStringArray(R.array.Países);

        //creamos arraylist de paises, con el metodo aslist le pasamos el array estático que ya teniamos
        ArrayList<String> ap= new ArrayList<String>(Arrays.asList(array_paises));

        //creamos adaptador de strings y le pasamos el array de paises
        adap_paises= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ap);

        //pasamos al spinner el adaptador que hemos creado
        sp_pais.setAdapter(adap_paises);


        sp_ciudad= (Spinner) findViewById(R.id.spCiudad);

        //creamos arraylist de ciudaddes
         ac= new ArrayList<String>();

        //creamos adaptador para el arraylist de ciudades
        adap_ciudades= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ac);

        //añadimos listener al spiner de país para que cambie la info del spinner de ciudad
        // (metodo aslist para recuperar info del arrays.xml) según el país seleccionado
        sp_pais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //España
                        ac = new ArrayList<String>( Arrays.asList(getResources().getStringArray(R.array.Ciudades_España)));
                        break;
                    case 1: //Alemania
                        ac = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Ciudades_Alemania)));
                        break;
                    case 2: //Francia
                        ac = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Ciudades_Francia)));
                        break;
                    case 3: //Italia
                        ac = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Ciudades_Italia)));
                        break;
                }
                adap_ciudades.clear();
                adap_ciudades.addAll(ac);
                sp_ciudad.setAdapter(adap_ciudades);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //al tener un arraylist podemos ampliar paises
        //adap_paises.add("Portugal");

        //notificamos a android que han cambiado los datos y vuelva a repintar la info
        adap_paises.notifyDataSetChanged();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
