package biuro.podrozy.projekt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biuro.podrozy.projekt.model.Kraj;
import biuro.podrozy.projekt.model.Lotnisko;
import biuro.podrozy.projekt.model.Miasto;
import biuro.podrozy.projekt.model.Uczestnik;
import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.repository.HotelRepo;
import biuro.podrozy.projekt.repository.KontynentRepo;
import biuro.podrozy.projekt.repository.KrajRepo;
import biuro.podrozy.projekt.repository.LotniskoRepo;
import biuro.podrozy.projekt.repository.MiastoRepo;
import biuro.podrozy.projekt.repository.UczestnikRepo;
import biuro.podrozy.projekt.repository.WycieczkaRepo;
import biuro.podrozy.projekt.repository.ZakupWycieczkiRepo;

@Service
public class UczestnikServiceImpl implements UczestnikService{

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
	public List<Uczestnik> getAll() {
		return uczestnikRepo.findAll();
	}

	@Override
	public void addUczestnik(Uczestnik ucz) {
		uczestnikRepo.save(ucz);
	}

	@Override
	public Uczestnik getById(Long id) {
		Optional<Uczestnik> u = uczestnikRepo.findById(id);
		if(u.isPresent()) {
			return u.get();
		}
		return null;
	}

	@Override
	public List<Uczestnik> getByIsChild(boolean isChild) {
		List<Uczestnik> u = getAll().stream().filter(x -> x.getIsChild() == isChild).collect(Collectors.toList());
		if(u.isEmpty() == false) {
			return u;
		}
		return null;
	}

	@Override
	public void setName(Long id, String name) {
		Optional<Uczestnik> u = uczestnikRepo.findById(id);
		if(u.isPresent()) {
			u.get().setName(name);
			uczestnikRepo.save(u.get());
		}
	}

	@Override
	public void setSurname(Long id, String surname) {
		Optional<Uczestnik> u = uczestnikRepo.findById(id);
		if(u.isPresent()) {
			u.get().setSurname(surname);
			uczestnikRepo.save(u.get());
		}
	}

	@Override
	public void setIsChild(Long id, boolean isChild) {
		Optional<Uczestnik> u = uczestnikRepo.findById(id);
		if(u.isPresent()) {
			u.get().setChild(isChild);
			uczestnikRepo.save(u.get());
		}
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		Optional<Uczestnik> u = uczestnikRepo.findById(id);
		if(u.isPresent()) {
			uczestnikRepo.delete(u.get());
			return true;
		}	
		return false;
	}

	
}
