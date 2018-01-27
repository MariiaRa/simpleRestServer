package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.entity.Country;
import ua.com.repository.CountryRepository;
import ua.com.service.CountryService;
@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	CountryRepository repository;
	
	
	@Override
	public Country save(Country country) {
		
		return repository.save(country);
	}

	@Override
	public List<Country> findAll() {
		
		return repository.findAll();
	}

	@Override
	public Country findOne(Integer id) {
		
		return repository.findOne(id);
	}

	@Override
	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
	}

}
