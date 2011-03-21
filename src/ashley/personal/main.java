package ashley.personal;
/*
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class main extends Activity {
    *//** Called when the activity is first created. *//*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        long time = 1303581600000L - System.currentTimeMillis();
        TextView text = (TextView)findViewById(R.id.text);
        text.setText(Long.toString(time));
    }
}
*/
//System.currentTimeMillis();
//1303581600    -- 11am April 23 2011

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class main extends Activity {
    TextView text, text2, text3;
    long starttime = 0;
    long deadline = 1303581600*1000;
    //this  posts a message to the main thread from our timertask
    //and updates the textfield
   final Handler h = new Handler(new Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            long millis = 1303581600000L - System.currentTimeMillis();
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours = seconds / 3600;
            int days = seconds / 86400;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hours = hours % 24;

            text.setText(String.format("%d days %02d hours %02d minutes and %02d seconds", days, hours, minutes, seconds) + " remain until our wedding!");
            return false;
        }
    });

   //tells handler to send a message
   class firstTask extends TimerTask {

        @Override
        public void run() {
            h.sendEmptyMessage(0);
        }
   };

   Timer timer = new Timer();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        text = (TextView)findViewById(R.id.text);
        timer = new Timer();
        timer.schedule(new firstTask(), 0,500);
    }
}
