package org.sid.demo;

import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.filters.ApplyStateFilter;
import org.jobrunr.jobs.states.JobState;
import org.springframework.stereotype.Component;

@Component
public class RecurringJobFilter implements ApplyStateFilter {
    @Override
    public void onStateApplied(Job job, JobState oldState, JobState newState) {
        ServiceTest service = ApplicationContextProvider.getBean(ServiceTest.class);
         service.getServiceName();
    }
}
