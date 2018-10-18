package br.com.spingboot.workshop.workshopspringboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spingboot.workshop.workshopspringboot.domain.Car;
import br.com.spingboot.workshop.workshopspringboot.enumarated.BrandType;
import br.com.spingboot.workshop.workshopspringboot.enumarated.CarType;
import br.com.spingboot.workshop.workshopspringboot.exception.ErrorDTO;
import br.com.spingboot.workshop.workshopspringboot.repository.CarRepository;
import br.com.spingboot.workshop.workshopspringboot.service.CarService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkshopSpringbootApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarControllerTest {
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private CarService carService;
    
    @Autowired
    private TestRestTemplate restTemplate;

    public CarControllerTest() {
    }
    
    private Car buildCar() {

        final Car car = new Car();
        
        car.setName(RandomStringUtils.random(25));
        car.setBrand(BrandType.CHEVROLET);
        car.setPricePerDay(100d);
        car.setType(CarType.HATCH);
        car.setYear(2018);
        
        return car;
    }

    /*@Test
    public void postCreated() throws Exception {
        
        final int entityCount = this.carRepository.findAll().size();
        
        final Car entityToCreate = this.buildCar();
        
        final ResponseEntity<Car> response = this.restTemplate.exchange("/cars", HttpMethod.POST, new HttpEntity<Car>(entityToCreate), Car.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(entityToCreate.getName());
        assertThat(response.getBody().getBrand()).isEqualTo(entityToCreate.getBrand());
        assertThat(response.getBody().getPricePerDay()).isEqualTo(entityToCreate.getPricePerDay());
        assertThat(response.getBody().getType()).isEqualTo(entityToCreate.getType());
        assertThat(response.getBody().getYear()).isEqualTo(entityToCreate.getYear());
        
        assertThat(response.getBody().getCreatedAt()).isNotNull();
        assertThat(response.getBody().getCreatedAt()).isEqualTo(response.getBody().getUpdatedAt());
        
        final List<Car> cars = this.carRepository.findAll();
        assertThat(cars).hasSize(entityCount + 1);
    }
    
    @Before
    public void before(){
        this.carRepository.deleteAll();
    }*/
    @Test
    public void postBadRequestByNameNull() throws Exception {
        
        final int entityCount = this.carRepository.findAll().size();
        
        final Car entityToCreate = this.buildCar();
        entityToCreate.setName(null);
        
        final ResponseEntity<ErrorDTO> response = this.restTemplate.exchange("/cars", HttpMethod.POST, new HttpEntity<Car>(entityToCreate), ErrorDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getDetails()).isNotEmpty();
        assertThat(response.getBody().getDetails()).hasSize(1);
        assertThat(response.getBody().getDetails().get(0).getItem()).isEqualTo("name");
        assertThat(response.getBody().getDetails().get(0).getDescription()).isEqualTo("Não pode estar em branco");
        
        final List<Car> cars = this.carRepository.findAll();
        assertThat(cars).hasSize(entityCount);
    }

}
