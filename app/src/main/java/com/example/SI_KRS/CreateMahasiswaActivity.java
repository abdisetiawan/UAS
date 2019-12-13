package com.example.SI_KRS;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateMahasiswaActivity extends AppCompatActivity {
    EditText txtNmMhs, txtNIM, txtAlMhs, txtEmlMhs;
    String Nama, NIM, Alamat, Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_mahasiswa);
        this.setTitle("SI KRS - Hai Admin");
        EditText editNama, editNIM, editAlamat, editEmail;

        Button btnSimpanMhs = (Button)findViewById(R.id.btnSmMhs);
        btnSimpanMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiData();
            }
        });
    }

    private void validasiData() {
        Nama = txtNmMhs.getText().toString();
        NIM  = txtNIM.getText().toString();
        Alamat = txtAlMhs.getText().toString();
        Email = txtEmlMhs.getText().toString();

        if(TextUtils.isEmpty(Nama)){
            txtNmMhs.setError("Nama tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(NIM)){
            txtNIM.setError("NIDN tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(Alamat)){
            txtAlMhs.setError("Alamat tidak boleh Kosong");
        }

        if(TextUtils.isEmpty(Email)){
            txtEmlMhs.setError("Email tidak boleh Kosong");
        }
    }
}