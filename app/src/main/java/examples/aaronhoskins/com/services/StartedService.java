package examples.aaronhoskins.com.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class StartedService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "onCreate: CREATING SERVICE");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "onStartCommand: RUNNING SERVICE");
        int counter = 0;

        while (counter != 10) {
            try {
                Thread.sleep(1000);
                Log.d("TAG", "onStartCommand: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            counter++;
        }
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: DESTROYING SERVICE");
    }
}
