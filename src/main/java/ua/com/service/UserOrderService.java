package ua.com.service;

import java.util.List;
import org.springframework.data.domain.Page;
import ua.com.entity.UserOrder;
import ua.com.request.MyPageRequest;
import ua.com.request.SearchingRequest;


public interface UserOrderService {
	UserOrder save(UserOrder userOrder);
	List<UserOrder> findAll();
	UserOrder findOne(Integer id);
	boolean delete(Integer id);
	Page<UserOrder> findAll(MyPageRequest page);
	List<UserOrder> findAll(SearchingRequest searchingRequest);
}
