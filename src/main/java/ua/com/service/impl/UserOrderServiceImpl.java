package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ua.com.entity.UserOrder;
import ua.com.repository.UserOrderRepository;
import ua.com.request.MyPageRequest;
import ua.com.request.SearchingRequest;
import ua.com.service.UserOrderService;
import ua.com.specification.SearchingOrder;
@Service
public class UserOrderServiceImpl implements UserOrderService{
@Autowired
UserOrderRepository repository;
	
	
	@Override
	public UserOrder save(UserOrder userOrder) {
		return repository.save(userOrder);
	}

	@Override
	public List<UserOrder> findAll() {
		return repository.findAll();
	}

	@Override
	public UserOrder findOne(Integer id) {	
		return repository.findOne(id);
	}

	@Override
	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
	}

	@Override
	public Page<UserOrder> findAll(MyPageRequest page) {
		PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage());
		Page<UserOrder> pageUserOrders =  repository.findAll(pageRequest);
		return pageUserOrders;
	}
	
	public List<UserOrder> findAll(SearchingRequest searchingRequest) {
		SearchingOrder searchingOrder = new SearchingOrder(searchingRequest);
		return repository.findAll(searchingOrder);
			}
	
}
