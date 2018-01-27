package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.entity.Region;
import ua.com.repository.RegionRepository;
import ua.com.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	RegionRepository repository;
	
	public Region save(Region region) {
		return repository.save(region);
	}

	public List<Region> findAll() {
		return repository.findAll();
	}

	public Region findOne(Integer id) {
		return repository.findOne(id);
	}

	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
	}

	
}
