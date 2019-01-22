package examples.aaronhoskins.com.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class MyBoundService extends Service {
    ArrayList<Integer> integerArrayList;
    IBinder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
        integerArrayList = new ArrayList<>();
        Log.d(TAG, "onCreate: SERVICE HAS BEEN CREATED");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: SERVICE HAS BEEN BOUND");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: SERVICE IS UNBOUND");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: SERVICE IS DESTROYED");
    }

    public void initData(int howMany, int maxValue) {
        Log.d(TAG, "initData: GENERATING RANDOM NUMBERS FOR LIST");
        for(int currentNumber = 0 ; currentNumber < howMany; currentNumber++) {
            Random random = new Random();
            addData(random.nextInt(maxValue));
        }
    }

    public void addData(int numberToAdd) {
        Log.d(TAG, "addData: ADDING NUMBER TO LIST");
        if(integerArrayList != null) {
            integerArrayList.add(numberToAdd);
        }
    }

    public ArrayList<Integer> getData() {
        Log.d(TAG, "getData: SENDING LIST TO CALLER");
        return integerArrayList;
    }


    public class MyBinder extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }
}
