package ua.com.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.entity.User;
import ua.com.request.SearchingRequest;

public class SearchingUser implements Specification<User> {

private SearchingRequest searchingRequest;
	
	public SearchingUser(SearchingRequest searchingRequest) {
		this.searchingRequest = searchingRequest;
	}

	@Override
	public Predicate toPredicate(Root<User> rootBook, CriteriaQuery<?> crq, CriteriaBuilder crb){
		CriteriaBuilder cb = crb;
		CriteriaQuery<?> cq =crq;
		Root<User> root=  rootBook;
		Predicate predicate1 = cb.like(cb.lower(root.get("firstName")),"%"+searchingRequest.getSearchTerm()+"%");
		Predicate predicate2 = cb.like(cb.lower(root.get("lastName")),"%"+searchingRequest.getSearchTerm()+"%");
		Predicate predicate3 = cb.like(cb.lower(root.get("email")),"%"+searchingRequest.getSearchTerm()+"%");
		Predicate predicate4 = cb.like(cb.lower(root.get("phone")),"%"+searchingRequest.getSearchTerm()+"%");
		System.out.println(searchingRequest.getSearchTerm());
		return cb.or(predicate1, predicate2, predicate3, predicate4);
	}
	
	
}
