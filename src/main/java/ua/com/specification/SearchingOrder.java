package ua.com.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import ua.com.entity.UserOrder;
import ua.com.request.SearchingRequest;

public class SearchingOrder implements Specification<UserOrder> {
private SearchingRequest searchingRequest;
	
	public SearchingOrder(SearchingRequest searchingRequest) {
		this.searchingRequest = searchingRequest;
	}
	@Override
	public Predicate toPredicate(Root<UserOrder> rootOrder, CriteriaQuery<?> crq, CriteriaBuilder crb) {
		CriteriaBuilder cb = crb;
		CriteriaQuery<?> cq =crq;
		Root<UserOrder> root=  rootOrder;
		Predicate predicate1 = cb.like(cb.lower(root.get("id").as(String.class)),searchingRequest.getSearchTerm());
		Predicate predicate2 = cb.like(cb.lower(root.get("orderDate").as(String.class)),searchingRequest.getSearchTerm());
				
		System.out.println(searchingRequest.getSearchTerm());
		 return cb.or(predicate1, predicate2);
	}

}
