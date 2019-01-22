package examples.aaronhoskins.com.services;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static examples.aaronhoskins.com.services.IntentActionConstants.ACTION_ONE;
import static examples.aaronhoskins.com.services.IntentActionConstants.ACTION_TWO;

public class MainActivity extends AppCompatActivity {
    MyBoundService myBoundService;
    TextView tvStartedServiceDisplay;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStartedServiceDisplay = findViewById(R.id.tvStartedServiceDisplay);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onClick(View view) {
        Intent boundServicesIntent = new Intent(this, MyBoundService.class);
        switch (view.getId()){
            case R.id.btnStartStartedService:
                Intent intent = new Intent(this, StartedService.class);
                startService(intent);

                break;
            case R.id.btnStartIntentOne:
                Intent intent1 = new Intent(this, MyIntentService.class);
                intent1.setAction(ACTION_ONE);
                startService(intent1);
                break;
            case R.id.btnStartIntentTwo:
                Intent intent2 = new Intent(this, MyIntentService.class);
                intent2.setAction(ACTION_TWO);
                startService(intent2);
                break;
            case R.id.btnBindService:
                bindService(boundServicesIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnInitData:
                if(isBound) {
                    myBoundService.initData(10,1000);
                }
                break;
            case R.id.btnGetData:
                if(isBound) {
                    for(Integer i : myBoundService.getData()) {
                        Log.d("TAG", "VALUE FROM LIST = " + i);
                    }
                }
                break;
            case R.id.btnAddToData:
                if(isBound) {
                    myBoundService.addData(666);
                }
                break;
            case R.id.btnUnbindService:
                Log.d("TAG", "UNBINDING ");
                if(isBound) {
                    unbindService(serviceConnection);
                    isBound = false;
                }
                break;
            case R.id.btnScheduleService:
                Log.d("TAG", "onScheduledServices: ");

                ComponentName componentName = new ComponentName(this, MyJobSevice.class);
                JobInfo jobInfo = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    jobInfo = new JobInfo.Builder(0, componentName)
                            .setPeriodic(1000)
                            .build();
                }

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    JobScheduler jobScheduler = getSystemService(JobScheduler.class);
                    jobScheduler.schedule(jobInfo);

                }
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("TAG", "onServiceConnected: CONNECTING TO SERVICE");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) service;
            myBoundService = myBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("TAG", "onServiceDisconnected: DISCONNECTING FROM SERVICE");
            isBound = false;
        }
    };

    @SuppressWarnings("unused")
    @Subscribe
    public void onServiceEvent(ServiceEvents serviceEvent) {
        if(serviceEvent != null) {
            Log.d("TAG", "onServiceEvent: " + serviceEvent.getServiceType());
            String displayText = serviceEvent.getServiceType()
                    + " = " + serviceEvent.getCountFromService();
            tvStartedServiceDisplay.setText(displayText);
        }
    }
}
