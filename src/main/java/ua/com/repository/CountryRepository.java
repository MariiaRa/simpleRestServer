package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {


}
