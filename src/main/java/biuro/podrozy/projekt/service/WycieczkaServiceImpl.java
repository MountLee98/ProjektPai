package biuro.podrozy.projekt.service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import biuro.podrozy.projekt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biuro.podrozy.projekt.repository.HotelRepo;
import biuro.podrozy.projekt.repository.KontynentRepo;
import biuro.podrozy.projekt.repository.KrajRepo;
import biuro.podrozy.projekt.repository.LotniskoRepo;
import biuro.podrozy.projekt.repository.MiastoRepo;
import biuro.podrozy.projekt.repository.WycieczkaRepo;
import biuro.podrozy.projekt.repository.ZakupWycieczkiRepo;

@Service
public class WycieczkaServiceImpl implements WycieczkaService{

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
	
	@Autowired
	LotniskoRepo lotniskoRepo;
	
	@Autowired
	ZakupWycieczkiRepo zakupRepo;
	
	@Override
	public List<Wycieczka> getAll() {
		return wycRepo.findAll();
	}

	@Override
	public void addWycieczka(Long idFrom, Long idTo, Long idHotel, Wycieczka wyc) {
		Optional<Lotnisko> lFrom = lotniskoRepo.findById(idFrom);
		if(lFrom.isPresent()) {
			Optional<Lotnisko> lTo = lotniskoRepo.findById(idTo);
			if(lTo.isPresent()) {
				Optional<Hotel> h = hotelRepo.findById(idHotel);
				if(h.isPresent()) {
					wyc.setFrom(lFrom.get());
					wyc.setTo(lTo.get());
					wyc.setHotel(h.get());
//					lFrom.get().getTripsFrom().add(wyc);
//					lTo.get().getTripsTo().add(wyc);
//					h.get().getTrips().add(wyc);
					wycRepo.save(wyc);
					lotniskoRepo.save(lFrom.get());
					lotniskoRepo.save(lTo.get());
					hotelRepo.save(h.get());
				}
			}
		}
	}

	@Override
	public void addWycieczka(Wycieczka wyc) {
		wycRepo.save(wyc);
	}

	@Override
	public List<Wycieczka> getByHotel(Hotel hotel) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getHotel() == hotel).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public List<Wycieczka> getByHotelName(String name) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getHotel().getName().equals(name)).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public List<Wycieczka> getByHotelStars(Gwiazdki gwiazdki) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getHotel().getStars() == gwiazdki).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public List<Wycieczka> getByFrom(Lotnisko lotnisko) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getFrom() == lotnisko).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public List<Wycieczka> getByTo(Lotnisko lotnisko) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getTo() == lotnisko).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public List<Wycieczka> getByFromName(String name) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getFrom().getName().equals(name)).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public void addHotel(Long idWyc, Long idHotel) {
		Optional<Wycieczka> w = wycRepo.findById(idWyc);
		if(w.isPresent()) {
			Optional<Hotel> h = hotelRepo.findById(idHotel);
			if(w.isPresent()) {
				w.get().setHotel(h.get());
				wycRepo.save(w.get());
			}
		}
	}

	@Override
	public List<Wycieczka> getByDepartureDate(LocalDate date) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getDepartureDate().equals(date)).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public void addDepartureDate(Long id, LocalDate date) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			w.get().setDepartureDate(date);
			wycRepo.save(w.get());
		}
	}

	@Override
	public List<Wycieczka> getByReturnDate(LocalDate date) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getReturnDate().equals(date)).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public void addReturnDate(Long id, LocalDate date) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			w.get().setReturnDate(date);
			wycRepo.save(w.get());
		}
	}

	@Override
	public void setDuration(Long id, LocalDate departureDate, int days) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			w.get().setDepartureDate(departureDate);
			w.get().setReturnDate(departureDate.plusDays(days));
			wycRepo.save(w.get());
		}
	}

	@Override
	public List<Wycieczka> getByDuration(int days) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getDaysDuration() == days).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public List<Wycieczka> getByType(Typ type) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getType() == type).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public void addType(Long id, Typ type) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			w.get().setType(type);
			wycRepo.save(w.get());
		}
	}

	@Override
	public List<Wycieczka> getByAdultPrice(float price) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getAdultPrice() == price).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public void addAdultPrice(Long id, float price) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			w.get().setAdultPrice(price);
			wycRepo.save(w.get());
		}
	}

	@Override
	public List<Wycieczka> getByKidPrice(float price) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getKidsPrice() == price).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public void addKidPrice(Long id, float price) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			w.get().setKidsPrice(price);
			wycRepo.save(w.get());
		}
	}

	@Override
	public List<Wycieczka> getByAdultSeats(int seatsAmount) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getAdultNumberOfSeats() == seatsAmount).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public void addAdultSeats(Long id, int seatsAmount) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			w.get().setAdultNumberOfSeats(seatsAmount);
			wycRepo.save(w.get());
		}
	}

	@Override
	public List<Wycieczka> getByKidSeats(int seatsAmount) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.getKidsNumberOfSeats() == seatsAmount).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public void addKidSeats(Long id, int seatsAmount) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			w.get().setKidsNumberOfSeats(seatsAmount);
			wycRepo.save(w.get());
		}
	}

	@Override
	public List<Wycieczka> getByPromoted(boolean promoted) {
		List<Wycieczka> w = getAll().stream().filter(x -> x.isPromoted() == promoted).collect(Collectors.toList());
		if(w.isEmpty() == false) {
			return w;
		}
		return null;
	}

	@Override
	public void addPromoted(Long id, boolean promoted) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			w.get().setPromoted(promoted);
			wycRepo.save(w.get());
		}
	}

	@Override
	public void addPicture(Long id, File picture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Wycieczka getById(Long id) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			return w.get();
		}
		return null;
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
//			if(w.get().getTripsPurchases().isEmpty()) {
//				wycRepo.delete(w.get());
//			} else {
//				for(ZakupWycieczki zakup : w.get().getTripsPurchases()) {
//					zakupRepo.delete(zakup);
//				}
//				wycRepo.delete(w.get());
//			}
			wycRepo.delete(w.get());
			return true;
		}	
		return false;
	}

	@Override
	public void addFrom(Long idWyc, Long idLotn) {
		Optional<Wycieczka> w = wycRepo.findById(idWyc);
		if(w.isPresent()) {
			Optional<Lotnisko> l = lotniskoRepo.findById(idLotn);
			if(w.isPresent()) {
				w.get().setFrom(l.get());
				wycRepo.save(w.get());
			}
		}
	}

	@Override
	public void addTo(Long idWyc, Long idLotn) {
		Optional<Wycieczka> w = wycRepo.findById(idWyc);
		if(w.isPresent()) {
			Optional<Lotnisko> l = lotniskoRepo.findById(idLotn);
			if(w.isPresent()) {
				w.get().setTo(l.get());
				wycRepo.save(w.get());
			}
		}
	}

}
