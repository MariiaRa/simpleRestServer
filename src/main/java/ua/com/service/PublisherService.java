package ua.com.service;


import java.util.List;

import org.springframework.data.domain.Page;
import ua.com.entity.Publisher;
import ua.com.request.MyPageRequest;

public interface PublisherService {
Publisher save(Publisher publisher);
List<Publisher> findAll();
Publisher findOne(Integer id);
boolean delete(Integer id);
Publisher findByName(String name);
Page<Publisher> findAll(MyPageRequest myPageRequest);
}
