package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ua.com.entity.Author;
import ua.com.entity.Publisher;
import ua.com.repository.PublisherRepository;
import ua.com.request.MyPageRequest;
import ua.com.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{
@Autowired
	PublisherRepository repository;
	
	public Publisher save(Publisher publisher) {
		return repository.save(publisher);
	}

	public List<Publisher> findAll() {
		return repository.findAll();
	}

	public Publisher findOne(Integer id) {
		return repository.findOne(id);
	}

	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
	}

	public Publisher findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Page<Publisher> findAll(MyPageRequest myPageRequest) {
		PageRequest pageRequest = new PageRequest(myPageRequest.getNumberPage(),myPageRequest.getSizePage());
		Page<Publisher> pagePublishers =  repository.findAll(pageRequest);
			return pagePublishers;
	}

}
