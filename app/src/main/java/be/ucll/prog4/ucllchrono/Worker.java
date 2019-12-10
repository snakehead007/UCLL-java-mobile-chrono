package be.ucll.prog4.ucllchrono;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

public class Worker extends AsyncTask<TextView, String, Boolean> {
    private String TAG;
    private TextView lblTime;

    @Override
    protected Boolean doInBackground(TextView... textViews) {
        TAG = getClass().getSimpleName();
        lblTime = textViews[0];
        long startTime = SystemClock.elapsedRealtime();
        Log.d(TAG, "Chrono started on " + formatDuration(startTime));

        // Create eternal loop
        boolean b = true;
        while (b) {
            try {
                long duration = SystemClock.elapsedRealtime() - startTime;
                publishProgress(formatDuration(duration));
                Thread.sleep(100); // update duration every 100 milliseconds.
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage());
            }
        }

        return true;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        lblTime.setText(values[0]);
    }

    private String formatDuration(long duration) {
        long seconds, minutes, hours;

        // Less then a minute
        if (duration < (60 * 1000)) {
            // seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            seconds = duration / 1000;
            return String.format("00:%02d", seconds);

            // less then an hour
        } else if (duration < (60 * 60 * 1000)) {
            minutes = duration / 1000 / 60;
            seconds = duration / 1000 % 60;
            return String.format("%02d:%02d", minutes, seconds);

            // More then an hour
        } else {
            hours = duration / 1000 / 60 / 60;
            minutes = duration / 1000 / 60 % 60;
            seconds = duration / 1000 % 60;
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
    }
}
