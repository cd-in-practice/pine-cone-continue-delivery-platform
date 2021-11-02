package codes.showme.pinecone.cdp.web.controller;

import codes.showme.pinecone.cdp.domain.app.App;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequestMapping(value = "/code")
public class CodeController {

    @RequestMapping(method = RequestMethod.POST, value = "/syncs")
    public String index(ModelMap map, @RequestParam(name = "p", defaultValue = "1") int pageIndex,
                        @RequestParam(name = "namespace", required = false) String namespace) {

        return "codes";
    }
}
