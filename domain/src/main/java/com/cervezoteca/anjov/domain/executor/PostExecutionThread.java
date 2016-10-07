package com.cervezoteca.anjov.domain.executor;

import rx.Scheduler;

/**
 * Created by angelvazquez on 24/7/16.
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}
