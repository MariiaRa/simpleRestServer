package ua.com.service;

import java.util.List;

import ua.com.entity.Region;

public interface RegionService {
	Region save(Region region);
	List<Region> findAll();
	Region findOne(Integer id);
	boolean delete(Integer id);
}
