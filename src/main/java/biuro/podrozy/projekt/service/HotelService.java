package biuro.podrozy.projekt.service;

import java.util.List;

import biuro.podrozy.projekt.model.Gwiazdki;
import biuro.podrozy.projekt.model.Hotel;

public interface HotelService {

	List<Hotel> getAll();
	void addHotel(Long id, Hotel hotel);
	void addHotel(Hotel hotel);
	Hotel getById(Long id);
	Hotel getByName(String name);
	List<Hotel> getByStandard(Gwiazdki gwiazdki);
	void setStandard(Long id, Gwiazdki gwiazdki);
	void setName(Long id, String name);
	void setDescription(Long id, String description);
	String deleteById(Long id);
}
