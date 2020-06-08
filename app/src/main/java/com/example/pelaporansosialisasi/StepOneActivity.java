package com.example.pelaporansosialisasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;
import java.util.List;

public class StepOneActivity extends FormBaseActivity {
    EditText etPelapor, etPenyelenggara, etJmlPeserta, etKetPeserta;
    MultipleSelectionSpinner spinner;
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one);
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
        etPelapor = findViewById(R.id.etPelapor);
        etPenyelenggara = findViewById(R.id.etPenyelenggara);
        etJmlPeserta = findViewById(R.id.etJmlPeserta);
        etKetPeserta = findViewById(R.id.etKeterangan);
        spinner = findViewById(R.id.pendamping1);

        list.add("Aan Suprobo, S. Kom.");
        list.add("Bimbingan Teknis Aplikasi MonS");
        list.add("Candra Aji, S. Kom.");
        list.add("Dianita, A. Md.");
        list.add("Firdaus Huda Maulana, A. Md.");
        list.add("Joko Marwiyanto, S. Kom, M. En");
        list.add("Listyaningsih, A.Md. Kom");
        list.add("Tri Pujianti, Amd.");
        list.add("Wahyudi, S. Kom.");
        spinner.setItems(list);
    }

    @Override
    public void onClick(View v) {
        String spelapor = etPelapor.getText().toString();
        String spenyelenggara = etPenyelenggara.getText().toString();
        String sjmlpeserta = etJmlPeserta.getText().toString();
        String sketpeserta = etKetPeserta.getText().toString();
        String sspinner1 = spinner.getSelectedItemsAsString();
        Intent intent = new Intent(getApplicationContext(), StepTwoActivity.class);
        intent.putExtra("pelapor", spelapor);
        intent.putExtra("penyelenggara", spenyelenggara);
        intent.putExtra("jmlpeserta", sjmlpeserta);
        intent.putExtra("ketpeserta", sketpeserta);
        intent.putExtra("pendamping1", sspinner1);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

