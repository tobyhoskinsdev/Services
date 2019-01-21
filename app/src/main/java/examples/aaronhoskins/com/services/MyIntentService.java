package examples.aaronhoskins.com.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import static examples.aaronhoskins.com.services.IntentActionConstants.ACTION_ONE;
import static examples.aaronhoskins.com.services.IntentActionConstants.ACTION_TWO;

public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("");
    }

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        switch (intent.getAction()) {
            case ACTION_ONE:
                for(int i = 0 ; i < 10 ; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("TAG", "onHandleIntent: ACTION ONE = " + i);
                }
                break;
            case ACTION_TWO:
                for(int i = 0 ; i < 10 ; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("TAG", "onHandleIntent: ACTION TWO = " + i);
                }
                break;
        }
        stopSelf();
    }
}
