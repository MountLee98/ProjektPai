package biuro.podrozy.projekt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biuro.podrozy.projekt.model.Gwiazdki;
import biuro.podrozy.projekt.model.Hotel;
import biuro.podrozy.projekt.model.Miasto;
import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.repository.HotelRepo;
import biuro.podrozy.projekt.repository.KontynentRepo;
import biuro.podrozy.projekt.repository.KrajRepo;
import biuro.podrozy.projekt.repository.MiastoRepo;
import biuro.podrozy.projekt.repository.WycieczkaRepo;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	KrajRepo krajRepo;
	
	@Autowired
	KontynentRepo kontynentRepo;
	
	@Autowired
	MiastoRepo miastoRepo;
	
	@Autowired
	HotelRepo hotelRepo;
	
	@Autowired
	WycieczkaRepo wycRepo;
	
	@Override
	public List<Hotel> getAll() {
		return hotelRepo.findAll();
	}

	@Override
	public void addHotel(Long id, Hotel hotel) {
		Optional<Miasto> m = miastoRepo.findById(id);
		if(m.isPresent()) {
			hotel.setCity(m.get());
//			m.get().getHotels().add(hotel);
			hotelRepo.save(hotel);
//			miastoRepo.save(m.get());
		}
	}

	@Override
	public void addHotel(Hotel hotel) {
		hotelRepo.save(hotel);
	}

	@Override
	public Hotel getById(Long id) {
		Optional<Hotel> h = hotelRepo.findById(id);
		if(h.isPresent()) {
			return h.get();
		}
		return null;
	}

	@Override
	public Hotel getByName(String name) {
		Optional<Hotel> h = getAll().stream().filter(x -> x.getName() == name).findFirst();
		if(h.isPresent()) {
			return h.get();
		}
		return null;
	}

	@Override
	public List<Hotel> getByStandard(Gwiazdki gwiazdki) {
		List<Hotel> h = getAll().stream().filter(x -> x.getStars() == gwiazdki).collect(Collectors.toList());
		if(h.isEmpty() == false) {
			return h;
		}
		return null;
	}
	
	@Override
	public void setStandard(Long id, Gwiazdki gwiazdki) {
		Optional<Hotel> h = hotelRepo.findById(id);
		if(h.isPresent()) {
			h.get().setStars(gwiazdki);
			hotelRepo.save(h.get());
		}
	}

	@Override
	public void setName(Long id, String name) {
		Optional<Hotel> h = hotelRepo.findById(id);
		if(h.isPresent()) {
			h.get().setName(name);
			hotelRepo.save(h.get());
		}
	}

	@Override
	@Transactional
	public String deleteById(Long id) {
		Optional<Hotel> h = hotelRepo.findById(id);
		if(h.isPresent()) {
//			if(h.get().getTrips().isEmpty()) {
//				hotelRepo.delete(h.get());
//			} else {
//				for(Wycieczka wyc : h.get().getTrips()) {
//					wycRepo.delete(wyc);
//				}
//				hotelRepo.delete(h.get());
//			}
			hotelRepo.delete(h.get());
			return "true"; 
		}
		return "false";
	}

	@Override
	public void setDescription(Long id, String description) {
		Optional<Hotel> h = hotelRepo.findById(id);
		if(h.isPresent()) {
			h.get().setDescription(description);
			hotelRepo.save(h.get());
		}
		
	}

}
