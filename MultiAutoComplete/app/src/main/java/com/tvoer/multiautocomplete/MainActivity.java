package com.tvoer.multiautocomplete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

public class MainActivity extends AppCompatActivity {
    private FlexboxLayout flexBox;
    private EditText edtName;
    private Button btnAdd;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flexBox = findViewById(R.id.flexBox);
        edtName = findViewById(R.id.edtName);
        btnAdd = findViewById(R.id.add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewContact();
            }
        });


        btnDelete = findViewById(R.id.delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFlexBoxItem();
            }
        });

        edtName.addTextChangedListener(new TextWatcherExtended() {
            @Override
            public void afterTextChanged(Editable s, boolean backSpace) {
                // Here you are! You got missing "backSpace" flag
                if (backSpace) {
                    deleteFlexBoxItem();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Do something useful if you wish.
                // Or override it in TextWatcherExtended class if want to avoid it here
            }
        });
    }

    private void deleteFlexBoxItem() {
        int count = flexBox.getChildCount();
        if (count - 2 >= 0) {
            flexBox.removeViewAt(count - 2);
        }
    }

    private void addNewContact() {
        FlexboxLayout.LayoutParams params1 = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins(10, 10, 10, 10);
        TextView tv1 = new TextView(this);
        tv1.setText(edtName.getText());
        tv1.setLayoutParams(params1);
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextSize(20);
        int count = flexBox.getChildCount();
        flexBox.addView(tv1, count - 1);
        edtName.setText("");
        log();
    }

    private void log() {
        View view;
        for (int i = 0; i < flexBox.getChildCount(); i++) {
            view = flexBox.getChildAt(i);

            if (view instanceof EditText) {
                Log.e("Flex", "EditText");

            } else if (view instanceof TextView) {
                Log.e("Flex", ((TextView) view).getText().toString());

            }
        }
    }
}
