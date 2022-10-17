package com.example.a04ejinmobilaria;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;


import com.example.a04ejinmobilaria.Configuraciones.Constantes;
import com.example.a04ejinmobilaria.Modelos.Piso;
import com.example.a04ejinmobilaria.databinding.ActivityMainBinding;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<Piso> pisosList;
    private ActivityResultLauncher<Intent> launcherCrearPiso;
    private ActivityResultLauncher<Intent> launcherModificarOBorrarPiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        pisosList = new ArrayList<>();
        inicializarLaunchers();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             launcherCrearPiso.launch(new Intent(MainActivity.this, AddPisoActivity.class));
            }
        });
    }

    private void inicializarLaunchers() {
        launcherCrearPiso  = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null && result.getData().getExtras() != null){
                        Piso piso = (Piso) result.getData().getExtras().getSerializable(Constantes.PISO);
                        pisosList.add(piso);
                        pintarElementos();
                    }else {
                        Toast.makeText(MainActivity.this, "NO HAY INTENT O BUNDLE", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "VENTANA CANCELADA", Toast.LENGTH_SHORT).show();
                }
            }
        });

        launcherModificarOBorrarPiso  = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null && result.getData().getExtras() != null){
                        Piso piso = (Piso) result.getData().getExtras().getSerializable(Constantes.PISO);

                        if (result.getData().getExtras().getBoolean(Constantes.BORRAR)){
                            pisosList.remove();//TODO: Poner un indice al remove
                        }else{
                            //TODO: Modificar con los datos nuevos
                        }
                        pintarElementos();

                    }else {
                        Toast.makeText(MainActivity.this, "NO HAY INTENT O BUNDLE", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "VENTANA CANCELADA", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

     private void pintarElementos() {
        binding.content.contenedor.removeAllViews();

        for (int i = 0; i < pisosList.size(); i++) {
            Piso piso = pisosList.get(i);

            View pisoView = LayoutInflater.from(MainActivity.this).inflate(R.layout.piso_model_view, null);

            TextView lblDireccion = pisoView.findViewById(R.id.lblDireccionModel);
            TextView lblNumero = pisoView.findViewById(R.id.lblNumeroModel);
            TextView lblProvincia = pisoView.findViewById(R.id.lblProvinciModel);
            RatingBar rtbVolarcion = pisoView.findViewById(R.id.rtbValModel);

            lblDireccion.setText(piso.getDireccion());
            lblNumero.setText(String.valueOf(piso.getNumero()));
            lblProvincia.setText(piso.getProvincia());
            rtbVolarcion.setRating(piso.getVolaracion());

            binding.content.contenedor.addView(pisoView);
         }
     }
}