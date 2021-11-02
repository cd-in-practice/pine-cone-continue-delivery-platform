package codes.showme.pinecone.cdp.web.api;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/code")
public class CodeApiController {

    @PostMapping("/sync-task")
    @Async
    public void createSyncTask(@RequestBody CreateSyncTaskReq req) {

    }
}
