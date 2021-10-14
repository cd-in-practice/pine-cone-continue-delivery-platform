package codes.showme.pinecone.cdp.alert.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/aliyun")
public class AliyunAlertController {

    @RequestMapping("/receive")
    public String receive(@RequestParam(required = false) Map<String, String> allParams,
                          @RequestHeader(required = false) Map<String, String> allHeaders, @RequestBody(required = false) String body) {
        return "ok";
    }


}
