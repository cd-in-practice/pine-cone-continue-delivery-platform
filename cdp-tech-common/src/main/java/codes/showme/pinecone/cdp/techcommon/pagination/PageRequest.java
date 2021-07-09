package codes.showme.pinecone.cdp.techcommon.pagination;

import java.io.Serializable;

public class PageRequest implements Serializable {

    private static final long serialVersionUID = 2471575503431110558L;

    /**
     * 当前页码
     */
    private int pageIndex;
    /**
     * 每页数量
     */
    private int pageSize;

    private PageRequest(){

    }

    public PageRequest(int pageIndex, int pageSize) {
        this.pageIndex = Math.max(pageIndex, 1);
        this.pageSize = Math.max(pageSize, 0);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
