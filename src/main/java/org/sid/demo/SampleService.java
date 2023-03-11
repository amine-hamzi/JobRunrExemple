package org.sid.demo;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Job(name = "The sample job without variable")
    public void execute() {
        execute("Hello world!");
    }

    @Job(name = "The sample job with variable %0")
    public void execute(String input) {
        logger.info("The sample job has begun. The variable you passed is {}", input);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            logger.info("Sample job has finished...");
        }
    }


    @Recurring(cron = "0 0 * * *")
    @Job(name = "RECURRING JOB", retries = 2, jobFilters = {RecurringJobFilter.class})
    public void doTask() throws Exception {
        logger.info("***************************************************************");
        logger.info("Recurring job is processing");
        throw new Exception();
    }
}
