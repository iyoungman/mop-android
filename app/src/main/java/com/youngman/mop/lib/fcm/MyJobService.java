package com.youngman.mop.lib.fcm;

import android.app.job.JobParameters;
import android.app.job.JobService;

/**
 * Created by YoungMan on 2019-11-10.
 */

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
