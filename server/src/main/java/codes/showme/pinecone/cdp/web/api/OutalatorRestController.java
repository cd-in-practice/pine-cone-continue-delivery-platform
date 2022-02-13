package codes.showme.pinecone.cdp.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/outalator")
public class OutalatorRestController {

    @PostMapping("/prometheus")
    @Async
    public ResponseEntity<String> prometheusAlert(@RequestBody(required = true) String body){



        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
