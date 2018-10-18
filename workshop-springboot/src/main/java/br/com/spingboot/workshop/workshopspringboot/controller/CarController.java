package br.com.spingboot.workshop.workshopspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spingboot.workshop.workshopspringboot.domain.Car;
import br.com.spingboot.workshop.workshopspringboot.service.CarService;

@RestController // sinaliza ao spring-boot que essa classe irá prover serviços
@RequestMapping("/cars") // sinaliza ao spring-boot o caminho correspondente à essa
public class CarController {

	@Autowired
	private CarService carService;

	@PostMapping // declara que este método é POST (para criar um carro)
	public ResponseEntity<Car> create(@RequestBody @Validated final Car car) {
		car.setId(null);
		return new ResponseEntity<Car>(this.carService.create(car), HttpStatus.CREATED);
	}

	@GetMapping // declara que este método é GET (para buscar carros)
	public ResponseEntity<List<Car>> getAll() {
		return new ResponseEntity<List<Car>>(this.carService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{car_id}") // declara que este método é GET (para buscar um carro) quando passado o id pelo
								// path ex: /cars/5bbe6edff8a7e33394b154b5
	public ResponseEntity<Car> getOne(@PathVariable(name = "car_id", required = true) String id) {
		final Car car = this.carService.findOne(id);

		if (car == null)
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Car>(car, HttpStatus.OK);
	}

	@PutMapping("/{car_id}") // declara que este método é PUT (alterar um carro) quando passado o id pelo
								// path ex: /cars/5bbe6edff8a7e33394b154b5
	public ResponseEntity<Car> update(@PathVariable(name = "car_id", required = false) String id,
			@RequestBody final Car car) {
		return new ResponseEntity<Car>(this.carService.update(car), HttpStatus.OK);
	}

	@DeleteMapping("/{car_id}") // declara que este método é DELETE (deletar um carro) quando passado o id pelo
								// path ex: /cars/5bbe6edff8a7e33394b154b5
	public ResponseEntity<Void> delete(@PathVariable(name = "car_id", required = false) String id) {
		this.carService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
