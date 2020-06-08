package com.example.pelaporansosialisasi;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.kofigyan.stateprogressbar.StateProgressBar;

public abstract class FormBaseActivity extends Activity implements View.OnClickListener {
    Button nextBtn;
    Button backBtn;
    StateProgressBar stateprogressbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        injectCommonViews();
    }

    protected void injectCommonViews() {
        nextBtn = findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(this);

        stateprogressbar = findViewById(R.id.usage_stateprogressbar);
    }

    protected void injectBackView() {
        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(this);
    }

}
