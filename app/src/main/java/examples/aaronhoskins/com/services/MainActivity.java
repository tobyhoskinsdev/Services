package examples.aaronhoskins.com.services;

import android.app.Service;
import android.content.Intent;
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
    TextView tvStartedServiceDisplay;

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
        }
    }

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
