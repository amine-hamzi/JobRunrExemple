package org.sid.demo;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class SampleController {

    private final JobScheduler jobScheduler;

    private final SampleService sampleJobService;

    public SampleController(JobScheduler jobScheduler, SampleService sampleJobService) {
        this.jobScheduler = jobScheduler;
        this.sampleJobService = sampleJobService;
    }

    @GetMapping("/run-job")
    public String runJob(@RequestParam(value = "name", defaultValue = "Hello World") String name) {

        jobScheduler.enqueue(() -> sampleJobService.execute(name));
        return "Job is enqueued.";

    }

    @GetMapping("/schedule-job")
    public String scheduleJob(
            @RequestParam(value = "name", defaultValue = "Hello World") String name,
            @RequestParam(value = "when", defaultValue = "30") long when) {

        jobScheduler.schedule(
                Instant.now().plusSeconds(when),
                () -> sampleJobService.execute(name)
        );

        return "Job is scheduled.";
    }
}
