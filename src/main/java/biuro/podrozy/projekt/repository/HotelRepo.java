package biuro.podrozy.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biuro.podrozy.projekt.model.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Long>{

}
