package ua.com.service;

import java.util.List;

import ua.com.entity.Country;

public interface CountryService {
	Country save(Country country);
	List<Country> findAll();
	Country findOne(Integer id);
	boolean delete(Integer id);
}
