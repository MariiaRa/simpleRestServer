package ua.com.request;

public class SearchingRequest {

	private String searchTerm;

	public SearchingRequest() {}

	public SearchingRequest(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	@Override
	public String toString() {
		return "SearchingRequest [searchTerm=" + searchTerm + "]";
	}


	
}
