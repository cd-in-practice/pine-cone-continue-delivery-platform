package codes.showme.pinecone.cdp.web.controller;

import codes.showme.pinecone.cdp.domain.app.App;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;
import codes.showme.pinecone.cdp.web.config.CdpConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller()
@RequestMapping(value = "/")
public class IndexController {

    @Resource
    private CdpConfig cdpConfig;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap map, @RequestParam(name = "p", defaultValue = "1") int pageIndex,
                        @RequestParam(name = "namespace", required = false) String namespace) {
        Pagination<App> pagination = App.listByNamespace(namespace, new PageRequest(pageIndex, cdpConfig.getPageSize()));
        map.put("pagination", pagination);
        return "index";
    }

    public void setCdpConfig(CdpConfig cdpConfig) {
        this.cdpConfig = cdpConfig;
    }
}
