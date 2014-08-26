package cn.bvin.app.gamersky.models;

public class PageCursor {

	private String pageIndex;
    private String pageCount;
    
	public PageCursor(String pageIndex, String pageCount) {
		super();
		this.pageIndex = pageIndex;
		this.pageCount = pageCount;
	}
	
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
    
    
}
