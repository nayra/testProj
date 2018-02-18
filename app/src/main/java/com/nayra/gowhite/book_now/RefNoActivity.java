package com.nayra.gowhite.book_now;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nayra.gowhite.R;
import com.nayra.gowhite.view_model.AddAppointmentWithVendorViewModel;

public class RefNoActivity extends AppCompatActivity {

    private TextView txtCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ref_no);

        txtCode = findViewById(R.id.tvCode);

        txtCode.setText(AddAppointmentWithVendorViewModel.getInstance().getAppointmentCode());

        final Button doneButton = findViewById(R.id.btDone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefNoActivity.this.finish();
            }
        });

    }
}
