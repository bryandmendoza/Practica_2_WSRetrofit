package com.example.practica2_wsretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practica2_wsretrofit.Retrofit.CovidData;
import com.example.practica2_wsretrofit.Retrofit.Covid19Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText txtCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getCovidData(View view) {
        txtCountry = findViewById(R.id.txtCountry);
        if(txtCountry.getText().toString().length()==0)
        {
            Toast.makeText(this, "No ha ingresado ningún país." , Toast.LENGTH_LONG).show();
        }
        else
        {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://covid-19-data.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
            Covid19Service service = retrofit.create(Covid19Service.class);
            Call<List<CovidData>> result = service.getCovidDataByCountry(txtCountry.getText().toString());
            result.enqueue(new Callback<List<CovidData>>() {
                @Override
                public void onResponse(Call<List<CovidData>> call, Response<List<CovidData>> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Error interno. \n Detalle del error: "+response.code()+" => "+response.message() , Toast.LENGTH_LONG).show();
                        return;
                    }
                    else {
                        List<CovidData> covidDataGroup = response.body();
                        if (covidDataGroup.size() == 0) {
                            Toast.makeText(MainActivity.this, "No se ha encontrado resultados.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else {
                            CovidData covidData = covidDataGroup.get(0);
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            Bundle b = new Bundle();
                            b.putString("country_selected", "País elegido: "+covidData.getCountry()+"\n");
                            b.putString("last_update", "Última actualización: "+covidData.getLastUpdate()+"\n");
                            b.putString("confirmed", "Casos confirmados: "+covidData.getConfirmed()+"\n");
                            b.putString("recovered", "Recuperados: "+covidData.getRecovered()+"\n");
                            b.putString("deaths", "Muertos: "+covidData.getDeaths()+"\n");
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<CovidData>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Sucedió un error en la consulta. Intente nuevamente. \n Detalle del error: "+t.getMessage() , Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}