package biuro.podrozy.projekt.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biuro.podrozy.projekt.model.Kontynent;
import biuro.podrozy.projekt.model.Kraj;
import biuro.podrozy.projekt.model.Miasto;
import biuro.podrozy.projekt.repository.KontynentRepo;
import biuro.podrozy.projekt.repository.KrajRepo;
import biuro.podrozy.projekt.repository.MiastoRepo;

@Service
public class KrajServiceImpl implements KrajService{

	@Autowired
	KrajRepo krajRepo;
	
	@Autowired
	KontynentRepo kontynentRepo;
	
	@Autowired
	MiastoRepo miastoRepo;
	
	@Override
	public List<Kraj> getAll() {
		return krajRepo.findAll();
	}

	@Override
	public void addKraj(Long id, Kraj kraj) {
		Optional<Kontynent> kont = kontynentRepo.findById(id);
		if(kont.isPresent()) {
			kraj.setContinent(kont.get());
			kraj = krajRepo.save(kraj);
//			kont.get().getCountries().add(kraj);
			krajRepo.save(kraj);
//			kontynentRepo.save(kont.get());
		}
	}

	@Override
	public void addKraj(Kraj kraj) {
			krajRepo.save(kraj);
	}

	@Override
	public Kraj getById(Long id) {
		Optional<Kraj> k = krajRepo.findById(id);
		if(k.isPresent()) {
			return k.get();
		}
		return null;
	}

	@Override
	public Kraj getByName(String name) {
		Optional<Kraj> k = getAll().stream().filter(x -> x.getName() == name).findFirst();
		if(k.isPresent()) {
			return k.get();
		}
		return null;
	}

	@Override
	public void setName(Long id, String name) {
		Optional<Kraj> k = krajRepo.findById(id);
		if(k.isPresent()) {
			k.get().setName(name);
			krajRepo.save(k.get());
		}	
	}

	@Override
	@Transactional
	public String deleteById(Long id) {
		Optional<Kraj> k = krajRepo.findById(id);
		if(k.isPresent()) {
//			if(k.get().getCities().isEmpty()) {
//				krajRepo.delete(k.get());
//			} else {
//				for(Miasto city : k.get().getCities()) {
//					miastoRepo.delete(city);
//				}
//				krajRepo.delete(k.get());
//			}
			krajRepo.delete(k.get());
			return "true";
		}	
		return "false";
	}

}
