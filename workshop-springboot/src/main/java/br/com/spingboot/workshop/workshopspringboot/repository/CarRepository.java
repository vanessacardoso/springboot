package br.com.spingboot.workshop.workshopspringboot.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.spingboot.workshop.workshopspringboot.domain.Car;
@Repository
public interface CarRepository  extends MongoRepository<Car, String> {
	
	public Car findOneByNameIgnoreCase(String name);
	
	public List<Car> findAllByName(String name);


}
