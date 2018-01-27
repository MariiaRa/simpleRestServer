package ua.com.request;

public class MyPageRequest {

	private int numberPage;
	
	private int sizePage;

	public MyPageRequest() {}

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

	@Override
	public String toString() {
		return "MyPageRequest [numberPage=" + numberPage + ", sizePage=" + sizePage + "]";
	}
	
	
	
	
	
}
