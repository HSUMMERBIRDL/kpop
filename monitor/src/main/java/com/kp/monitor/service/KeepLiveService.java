package com.kp.monitor.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by ${Stephen} on 2017-05-12.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class KeepLiveService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
