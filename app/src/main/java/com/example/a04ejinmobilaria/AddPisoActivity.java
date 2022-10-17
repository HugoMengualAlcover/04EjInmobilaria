package com.example.a04ejinmobilaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a04ejinmobilaria.Modelos.Piso;
import com.example.a04ejinmobilaria.databinding.ActivityAddPisoBinding;

public class AddPisoActivity extends AppCompatActivity {

    private ActivityAddPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_piso);

        binding = ActivityAddPisoBinding.inflate(getLayoutInflater());

        //3.Asocia el binding al activity
        setContentView(binding.getRoot());


        binding.btnCancelarAddPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso piso = comprobarCosos();
                if (piso != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PISO",piso);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(AddPisoActivity.this, "Faltan Datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Piso comprobarCosos() {
        if(binding.txtDireccionAddPiso.getText().toString().isEmpty() || binding.txtNumeroAddPiso.getText().toString().isEmpty() ||
        binding.txtCpAddPiso.getText().toString().isEmpty() || binding.txtCpAddPiso.getText().toString().isEmpty())
            return null;
        if(binding.spCiudadAddPiso.getSelectedItemPosition() == 0)
            return null;


        String ciudad = (String) binding.spCiudadAddPiso.getSelectedItem();

        return new Piso(binding.txtDireccionAddPiso.getText().toString(), Integer.parseInt(binding.txtNumeroAddPiso.getText().toString()),
                binding.txtProvinciaAddPiso.getText().toString(), ciudad,binding.txtCpAddPiso.getText().toString(), binding.rtbValoracionAddPiso.getRating());

    }
}