package ua.com.request;

public class CategoryUpdateRequest {

	private Integer id;
	private String name;
	public CategoryUpdateRequest(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CategoryUpdate [id=" + id + ", name=" + name + "]";
	}
	public CategoryUpdateRequest() {
		
	}
	
	
	
	
}
