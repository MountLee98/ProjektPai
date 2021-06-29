package biuro.podrozy.projekt.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biuro.podrozy.projekt.model.Lotnisko;
import biuro.podrozy.projekt.model.Miasto;
import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.repository.KontynentRepo;
import biuro.podrozy.projekt.repository.KrajRepo;
import biuro.podrozy.projekt.repository.LotniskoRepo;
import biuro.podrozy.projekt.repository.MiastoRepo;
import biuro.podrozy.projekt.repository.WycieczkaRepo;

@Service
public class LotniskoServiceImpl implements LotniskoService{

	@Autowired
	KrajRepo krajRepo;
	
	@Autowired
	KontynentRepo kontynentRepo;
	
	@Autowired
	MiastoRepo miastoRepo;
	
	@Autowired
	LotniskoRepo lotniskoRepo;
	
	@Autowired
	WycieczkaRepo wycRepo;
	
	@Override
	public List<Lotnisko> getAll() {
		return lotniskoRepo.findAll();
	}

	@Override
	public void addLotnisko(Long id, Lotnisko lotnisko) {
		Optional<Miasto> m = miastoRepo.findById(id);
		if(m.isPresent()) {
			lotnisko.setCity(m.get());
//			m.get().getAirports().add(lotnisko);
			lotniskoRepo.save(lotnisko);
//			miastoRepo.save(m.get());
		}
	}

	@Override
	public void addLotnisko(Lotnisko lotnisko) {
		lotniskoRepo.save(lotnisko);
	}

	@Override
	public Lotnisko getById(Long id) {
		Optional<Lotnisko> l = lotniskoRepo.findById(id);
		if(l.isPresent()) {
			return l.get();
		}
		return null;
	}

	@Override
	public Lotnisko getByName(String name) {
		Optional<Lotnisko> l = getAll().stream().filter(x -> x.getName() == name).findFirst();
		if(l.isPresent()) {
			return l.get();
		}
		return null;
	}

	@Override
	public void setName(Long id, String name) {
		Optional<Lotnisko> l = lotniskoRepo.findById(id);
		if(l.isPresent()) {
			l.get().setName(name);
			lotniskoRepo.save(l.get());
		}
	}

	@Override
	@Transactional
	public String deleteById(Long id) {
		Optional<Lotnisko> l = lotniskoRepo.findById(id);
		if(l.isPresent()) {
//			if(l.get().getTripsFrom().isEmpty() && l.get().getTripsTo().isEmpty()) {
//				lotniskoRepo.delete(l.get());
//			} else {
//				if(l.get().getTripsFrom().isEmpty()) {
//					for(Wycieczka wyc : l.get().getTripsTo()) {
//						wycRepo.delete(wyc);
//					}
//					lotniskoRepo.delete(l.get());
//				} else {
//					if(l.get().getTripsTo().isEmpty()) {
//						for(Wycieczka wyc : l.get().getTripsFrom()) {
//							wycRepo.delete(wyc);
//							lotniskoRepo.delete(l.get());
//						}
//					} else {
//						for(Wycieczka wyc : l.get().getTripsFrom()) {
//							wycRepo.delete(wyc);
//						}
//						for(Wycieczka wyc : l.get().getTripsTo()) {
//							wycRepo.delete(wyc);
//						}
//						lotniskoRepo.delete(l.get());
//					}
//				}
//			}
			lotniskoRepo.delete(l.get());
			return "true";
		}	
		return "false";
	}

}
