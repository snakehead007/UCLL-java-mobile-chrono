package be.ucll.prog4.ucllchrono;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "UCLL Chrono";

    // Widgets
    private TextView lblTime;
    private Button btnStart;
    private Button btnStop;

    private Worker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblTime = this.findViewById(R.id.lblTime);
        btnStart = this.findViewById(R.id.btnStart);
        btnStop = this.findViewById(R.id.btnStop);

        btnStop.setEnabled(false);
    }

    public void handleClickBtnStart(View view) {
        worker = new Worker();
        worker.execute(lblTime);

        btnStop.setEnabled(true);
        btnStart.setEnabled(false);
    }

    public void handleClickBtnStop(View view) {
        worker.cancel(true);

        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }


}
