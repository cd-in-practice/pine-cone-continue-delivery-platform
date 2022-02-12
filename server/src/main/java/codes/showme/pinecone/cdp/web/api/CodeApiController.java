package codes.showme.pinecone.cdp.web.api;

import codes.showme.pinecone.cdp.code.analysis.domain.SyncGitLabRepoTask;
import codes.showme.pinecone.cdp.code.analysis.service.SyncGitLabService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/code")
public class CodeApiController {

    @Resource
    private SyncGitLabService syncGitLabService;

    @PostMapping("/sync-task")
    @Async
    public void createSyncTask(@RequestBody CreateSyncTaskReq req) {
        SyncGitLabRepoTask.save(convert(req));
    }

    private SyncGitLabRepoTask convert(CreateSyncTaskReq createSyncTaskReq) {
        return null;
    }

    @GetMapping("/sync-task")
    public List<SyncGitLabRepoTask> listSyncTasks(@RequestParam(name = "p", defaultValue = "1") int pageIndex,
                                                  @RequestParam(name = "namespace", required = false) String namespace) {


        return new ArrayList<>();
    }

    @PostMapping("/sync-gitlab")
    @Async
    public void syncGitLabRepo(@RequestBody SyncGitLabService.SyncGitLabRepoReq req, @RequestParam(name = "namespace", required = false) String namespace) {
        syncGitLabService.sync(req, namespace);
    }


}
