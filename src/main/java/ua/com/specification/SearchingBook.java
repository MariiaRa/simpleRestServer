package ua.com.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.entity.Book;
import ua.com.entity.User;
import ua.com.request.SearchingRequest;

public class SearchingBook implements Specification<Book>{
	
	private SearchingRequest searchingRequest;
	
	public SearchingBook(SearchingRequest searchingRequest) {
		this.searchingRequest = searchingRequest;
	}

	@Override
	public Predicate toPredicate(Root<Book> rootBook, CriteriaQuery<?> crq, CriteriaBuilder crb){
		CriteriaBuilder cb = crb;
		CriteriaQuery<?> cq =crq;
		Root<Book> root=  rootBook;
		Predicate predicate1 = cb.like(cb.lower(root.get("title")),"%"+searchingRequest.getSearchTerm()+"%");//where email like %text%
		Predicate predicate2 = cb.like(cb.lower(root.get("isbn")),"%"+searchingRequest.getSearchTerm()+"%");//where email like %text%
		Predicate predicate3 = cb.like(cb.lower(root.get("feature")),"%"+searchingRequest.getSearchTerm()+"%");//where email like %text%
		Predicate predicate4 = cb.like(cb.lower(root.get("bookBriefDescription")),"%"+searchingRequest.getSearchTerm()+"%");//where email like %text%
		Predicate predicate5 = cb.like(cb.lower(root.get("bookFullDescription")),"%"+searchingRequest.getSearchTerm()+"%");//where email like %text%
		System.out.println("searchingRequest.getSearchTerm()");
		 return cb.or(predicate1, predicate2, predicate3, predicate4, predicate5);
	
		
//		return predicate1;
	}


	
	

}
