package com.example.camundabug;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;

@RestController
@RequestMapping("/api/v1/")
public class Controller {

  private final ZeebeClient zeebeClient;

  public Controller(ZeebeClient zeebeClient) {
    this.zeebeClient = zeebeClient;
  }

  @PostMapping("process")
  public long createProcessInstance() {
    return this.zeebeClient.newCreateInstanceCommand()
        .bpmnProcessId("Process_TestProcess")
        .latestVersion()
        .send()
        .join()
        .getProcessInstanceKey();
  }

  @PostMapping("tasks/{taskId}/complete")
  public void completeTask(@PathVariable long taskId) {
    this.zeebeClient.newUserTaskCompleteCommand(taskId)
        .variables(Map.of("status", "COMPLETED"))
        .send()
        .join();
  }
}
