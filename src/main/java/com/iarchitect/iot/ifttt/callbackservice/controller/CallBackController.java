package com.iarchitect.iot.ifttt.callbackservice.controller;

import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@RestController
//@EnableWebMvc
public class CallBackController extends BaseController {
    private final RestTemplate restTemplate;
    //    final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    final ScheduledExecutorService exitingScheduledExecutorService =
            MoreExecutors.getExitingScheduledExecutorService(scheduledThreadPoolExecutor, 15, TimeUnit.MINUTES);

    public CallBackController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping(path = "/event/{eventName}/key/{apiKey}", method = RequestMethod.GET)
    public String callNow(@PathVariable String eventName,
                          @PathVariable String apiKey) {
        String url = buildCallBackUrl(eventName, apiKey);
        return this.restTemplate.getForObject(url, String.class);
    }

    @RequestMapping(path = "/event/{eventName}/delay/{delay}/key/{apiKey}", method = RequestMethod.GET)
    public String requestCallBack(@PathVariable String eventName,
                                  @PathVariable long delay,
                                  @PathVariable String apiKey,
                                  @RequestParam boolean nightOnly) throws ExecutionException, InterruptedException {
        if (nightOnly) {
            if (isNightTime()) {
                return scheduleCallBackAsync(eventName, delay, apiKey);
            }
        } else {
            return scheduleCallBackAsync(eventName, delay, apiKey);
        }
        return "Nothing scheduled";
    }

    private String scheduleCallBack(String eventName, long delay, String apiKey) throws InterruptedException {
        Thread.sleep(delay * 60 * 1000);
        String url = buildCallBackUrl(eventName, apiKey);
        return this.restTemplate.getForObject(url, String.class);
    }

    private String scheduleCallBackAsync(String eventName, long delay, String apiKey) throws ExecutionException, InterruptedException {
//        executorService.schedule(invokeIftttWebHook(eventName, apiKey), delay, TimeUnit.MINUTES);
        // Executor service should stay up for subsequent requests
        // executorService.shutdown();

        ScheduledFuture<String> schedule = exitingScheduledExecutorService.schedule(invokeIftttWebHook(eventName, apiKey), delay, TimeUnit.MINUTES);
//        String response = schedule.get();
//        System.out.println(response);
        return "Call Back Requested";
    }

    private Callable<String> invokeIftttWebHook(String eventName, String apiKey) {
        String url = buildCallBackUrl(eventName, apiKey);
        return () -> {
            String response = this.restTemplate.getForObject(url, String.class);
            System.out.println("From the thread = " + response);
            return response;
        };
    }

    private String buildCallBackUrl(@PathVariable String eventName, @PathVariable String apiKey) {
        return "https://maker.ifttt.com/trigger/" + eventName + "/with/key/" + apiKey;
    }
}
