package ua.com.request;

public class CategorySearchPageRequest {

private int numberPage;
	
	private int sizePage;
	
	private String searchTerm;

	public CategorySearchPageRequest(int numberPage, int sizePage, String searchTerm) {
		super();
		this.numberPage = numberPage;
		this.sizePage = sizePage;
		this.searchTerm = searchTerm;
	}

	public CategorySearchPageRequest() {
		super();
	}

	public int getNumberPage() {
		return numberPage;
	}

	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}

	public int getSizePage() {
		return sizePage;
	}

	public void setSizePage(int sizePage) {
		this.sizePage = sizePage;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	@Override
	public String toString() {
		return "CategorySearchPageRequest [numberPage=" + numberPage + ", sizePage=" + sizePage + ", searchTerm="
				+ searchTerm + "]";
	}
	
	
	
	
}
