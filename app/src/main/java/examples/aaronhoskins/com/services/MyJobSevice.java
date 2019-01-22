package examples.aaronhoskins.com.services;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobSevice extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("TAG", "onStartJob: ");
        Intent intent = new Intent(getApplicationContext(), StartedService.class);
        getApplicationContext().startService(intent);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
