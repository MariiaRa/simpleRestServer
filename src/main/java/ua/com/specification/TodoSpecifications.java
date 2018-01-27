package ua.com.specification;

import org.springframework.data.jpa.domain.Specification;

import ua.com.entity.Book;

public final class TodoSpecifications {
	private TodoSpecifications() {}
	 
    public static Specification<Book> inStock(int stock) {
        return (root, query, cb) -> {
			return cb.lessThanOrEqualTo(root.get("stock"), stock);
        };
    }
    
    public static Specification<Book> titleOrBriefDescriptionContainsIgnoreCase(String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = getContainsLikePattern(searchTerm);
            return cb.or(
                    cb.like(cb.lower(root.<String>get("title")), containsLikePattern),
                    cb.like(cb.lower(root.<String>get("bookBriefDescription")), containsLikePattern)
            );
        };
    }
    
    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        }
        else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }
       
}
