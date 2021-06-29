package biuro.podrozy.projekt.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biuro.podrozy.projekt.model.Kontynent;
import biuro.podrozy.projekt.model.Kraj;
import biuro.podrozy.projekt.repository.KontynentRepo;
import biuro.podrozy.projekt.repository.KrajRepo;
import biuro.podrozy.projekt.repository.WycieczkaRepo;

@Service
public class KontynentServiceImpl implements KontynentService{

	@Autowired 
	KontynentRepo kontynentRepo;
	
	@Autowired 
	KrajRepo krajRepo;
	
	
	
	@Override
	public List<Kontynent> getAll() {		
		return kontynentRepo.findAll();
	}
	
	@Override
	public void addKontynent(Kontynent kont) {
		kontynentRepo.save(kont);
	}

	@Override
	public Kontynent getById(Long id) {
		Optional<Kontynent> k = kontynentRepo.findById(id);
		if(k.isPresent()) {
			return k.get();
		}
		return null;
	}

	@Override
	public Kontynent getByName(String name) {
		Optional<Kontynent> k = getAll().stream().filter(x -> x.getName() == name).findFirst();
		if(k.isPresent()) {
			return k.get();
		}
		return null;
	}

	@Override
	public void setName(Long id, String name) {
		Optional<Kontynent> k = kontynentRepo.findById(id);
		if(k.isPresent()) {
			k.get().setName(name);
			kontynentRepo.save(k.get());
		}		
	}

	@Override
	@Transactional
	public String deleteById(Long id) {
		Optional<Kontynent> k = kontynentRepo.findById(id);
		if(k.isPresent()) {
//			if(k.get().getCountries().isEmpty()) {
//				kontynentRepo.delete(k.get());
//			} else {
//				for(Kraj country : k.get().getCountries()) {
//					krajRepo.delete(country);
//				}
//				kontynentRepo.delete(k.get());
//			
//			}
			kontynentRepo.delete(k.get());
			return "true";
		}	
		return "false";
	}

}
