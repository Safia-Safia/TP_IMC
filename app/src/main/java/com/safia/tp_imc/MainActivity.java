package com.safia.tp_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mPoids, mTaille;
    private RadioButton mMetre, mCentimetre;
    private CheckBox mMegafonction;
    private Button mImc, mRaz;
    private TextView mResulttext;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMetre = findViewById(R.id.radio_mètre);
        mCentimetre = findViewById(R.id.radio_centimètre);

        mMegafonction = findViewById(R.id.checkbox_megafonction);
        group = findViewById(R.id.group);

        mImc = findViewById(R.id.btn_imc);
        mRaz = findViewById(R.id.btn_raz);

        mPoids = findViewById(R.id.edit_poids);
        mTaille = findViewById(R.id.edit_taille);

        mResulttext = findViewById(R.id.result_text);

        mRaz.setOnClickListener(this);
        mImc.setOnClickListener(this);
        mMegafonction.setOnClickListener(this);
        mMetre.setOnClickListener(this);
        mCentimetre.setOnClickListener(this);

        megafonctionBtn();
    }

    @Override
    public void onClick(View v) {
        razBtn();
        calculeImc();
    }

    public void razBtn (){
        mRaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTaille.getText().clear();
                mPoids.getText().clear();
                mResulttext.setText(R.string.text_result);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void calculeImc() {

        String t = mTaille.getText().toString();
        String p = mPoids.getText().toString();


        float tValue = Float.parseFloat(t);
        float pValue = Float.parseFloat(p);

        if (tValue == 0 || pValue == 0) {
            Toast.makeText(MainActivity.this, "Cesse donc de mentir.", Toast.LENGTH_LONG).show();
        }

        if (group.getCheckedRadioButtonId() == R.id.radio_mètre) {
            tValue = (float) Math.pow(tValue, 2);
            float imc = pValue / (tValue);
            mResulttext.setText(getString(R.string.imcString) + imc);
        } else {
            tValue = (float) Math.pow((tValue / 100), 2);
            float imc = pValue / tValue;
            mResulttext.setText(getString(R.string.imcString) + imc);


        }
    }

    public void megafonctionBtn() {
        mMegafonction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMegafonction.isChecked()) {
                    mResulttext.setText(R.string.message_gentil);
                } else {
                    if (!mMegafonction.isChecked()) {
                        mResulttext.setText(R.string.text_result);
                    }
                }
            }
        });
    }
}