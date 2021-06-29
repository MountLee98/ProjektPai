package biuro.podrozy.projekt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biuro.podrozy.projekt.model.Miasto;
import biuro.podrozy.projekt.model.Uczestnik;
import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.model.ZakupWycieczki;
import biuro.podrozy.projekt.repository.HotelRepo;
import biuro.podrozy.projekt.repository.KontynentRepo;
import biuro.podrozy.projekt.repository.KrajRepo;
import biuro.podrozy.projekt.repository.LotniskoRepo;
import biuro.podrozy.projekt.repository.MiastoRepo;
import biuro.podrozy.projekt.repository.UczestnikRepo;
import biuro.podrozy.projekt.repository.WycieczkaRepo;
import biuro.podrozy.projekt.repository.ZakupWycieczkiRepo;

@Service
public class ZakupWycieczkiServiceImpl implements ZakupWycieczkiService{

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
	
	@Autowired
	UczestnikRepo uczestnikRepo;
	
	@Override
	public List<ZakupWycieczki> getAll() {
		return zakupRepo.findAll();
	}

	@Override
	public void addZakupWycieczki(Long id, ZakupWycieczki zakup) {
		Optional<Wycieczka> w = wycRepo.findById(id);
		if(w.isPresent()) {
			zakup.setTrip(w.get());
//			w.get().getTripsPurchases().add(zakup);
			zakupRepo.save(zakup);
//			wycRepo.save(w.get());
		}
	}

	@Override
	public void addZakupWycieczki(ZakupWycieczki zakup) {
		zakupRepo.save(zakup);
	}
	
	@Override
	public List<ZakupWycieczki> getByTrip(Wycieczka wyc) {
//		Optional<Wycieczka> w = wycRepo.findById(wyc.getTripId());
//		if(w.isPresent()) {
//			List<ZakupWycieczki> z = w.get().getTripsPurchases();
			List<ZakupWycieczki> z = getAll().stream().filter(x -> x.getTrip() == wyc).collect(Collectors.toList());
			if(z.isEmpty() == false) {
				return z;
			}
//		}	
		return null;
	}

	@Override
	public ZakupWycieczki getById(Long id) {
		Optional<ZakupWycieczki> z = zakupRepo.findById(id);
		if(z.isPresent()) {
			return z.get();
		}
		return null;
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		Optional<ZakupWycieczki> z = zakupRepo.findById(id);
		if(z.isPresent()) {
//			if(z.get().getClientList().isEmpty()) {
//				zakupRepo.delete(z.get());
//			} else {
//				for(Uczestnik ucz : z.get().getClientList()) {
//					uczestnikRepo.delete(ucz);
//				}
//				zakupRepo.delete(z.get());
//			}
			zakupRepo.delete(z.get());
			return true;
		}	
		return false;
	}

}
