package biuro.podrozy.projekt.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biuro.podrozy.projekt.model.Hotel;
import biuro.podrozy.projekt.model.Kraj;
import biuro.podrozy.projekt.model.Lotnisko;
import biuro.podrozy.projekt.model.Miasto;
import biuro.podrozy.projekt.repository.HotelRepo;
import biuro.podrozy.projekt.repository.KontynentRepo;
import biuro.podrozy.projekt.repository.KrajRepo;
import biuro.podrozy.projekt.repository.LotniskoRepo;
import biuro.podrozy.projekt.repository.MiastoRepo;

@Service
public class MiastoServiceImpl implements MiastoService{

	@Autowired
	KrajRepo krajRepo;
	
	@Autowired
	KontynentRepo kontynentRepo;
	
	@Autowired
	MiastoRepo miastoRepo;
	
	@Autowired
	HotelRepo hotelRepo;
	
	@Autowired
	LotniskoRepo lotniskoRepo;
	
	@Override
	public List<Miasto> getAll() {
		return miastoRepo.findAll();
	}

	@Override
	public void addMiasto(Long id, Miasto miasto) {
		Optional<Kraj> k = krajRepo.findById(id);
		if(k.isPresent()) {
			miasto.setCountry(k.get());
//			k.get().getCities().add(miasto);
			miastoRepo.save(miasto);
//			krajRepo.save(k.get());
		}
	}

	@Override
	public void addMiasto(Miasto miasto) {
		miastoRepo.save(miasto);
	}

	@Override
	public Miasto getById(Long id) {
		Optional<Miasto> m = miastoRepo.findById(id);
		if(m.isPresent()) {
			return m.get();
		}
		return null;
	}

	@Override
	public Miasto getByName(String name) {
		Optional<Miasto> m = getAll().stream().filter(x -> x.getName() == name).findFirst();
		if(m.isPresent()) {
			return m.get();
		}
		return null;
	}

	@Override
	public void setName(Long id, String name) {
		Optional<Miasto> m = miastoRepo.findById(id);
		if(m.isPresent()) {
			m.get().setName(name);
			miastoRepo.save(m.get());
		}
	}

	@Override
	@Transactional
	public String deleteById(Long id) {
		Optional<Miasto> m = miastoRepo.findById(id);
		if(m.isPresent()) {
//			if(m.get().getHotels().isEmpty() && m.get().getAirports().isEmpty()) {
//				miastoRepo.delete(m.get());
//			} else {
//				if(m.get().getHotels().isEmpty()) {
//					for(Lotnisko airport : m.get().getAirports()) {
//						lotniskoRepo.delete(airport);
//					}
//					miastoRepo.delete(m.get());
//				} else {
//					if(m.get().getAirports().isEmpty()) {
//						for(Hotel hotel : m.get().getHotels()) {
//							hotelRepo.delete(hotel);
//						}
//						miastoRepo.delete(m.get());
//					} else {
//						for(Hotel hotel : m.get().getHotels()) {
//							hotelRepo.delete(hotel);
//						}
//						for(Lotnisko airport : m.get().getAirports()) {
//							lotniskoRepo.delete(airport);
//						}
//						miastoRepo.delete(m.get());
//					} 
//				}
//			}
			miastoRepo.delete(m.get());
			return "true";
		}	
		return "false";
	}

}
