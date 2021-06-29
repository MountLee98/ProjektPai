package biuro.podrozy.projekt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biuro.podrozy.projekt.model.Kontynent;
import biuro.podrozy.projekt.model.Kraj;
import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.service.HotelService;
import biuro.podrozy.projekt.service.KontynentService;
import biuro.podrozy.projekt.service.KrajService;
import biuro.podrozy.projekt.service.LotniskoService;
import biuro.podrozy.projekt.service.MiastoService;
import biuro.podrozy.projekt.service.UczestnikService;
import biuro.podrozy.projekt.service.WycieczkaService;
import biuro.podrozy.projekt.service.ZakupWycieczkiService;

@RequestMapping("/rest/kraj")
@RestController
public class KrajController {

	@Autowired
	KrajService krajService;
	
	@Autowired
	KontynentService kontynentService;
	
	@Autowired
	MiastoService miastoService;
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	WycieczkaService wycService;
	
	@Autowired
	LotniskoService lotniskoService;
	
	@Autowired
	ZakupWycieczkiService zakupService;
	
	@Autowired
	UczestnikService uczestnikService;
	
	@GetMapping("/getall")
	List<Kraj> getAllCountry() {
		List<Kraj> list = krajService.getAll();
		return list;
	}
	
	@PostMapping("/addkraj")
	void addCountry(@RequestParam("continentId")Long id, @RequestParam("name")String name) {		
		krajService.addKraj(id, new Kraj(name));
	}
	
	@GetMapping("/getbyid")
	Kraj getById(@RequestParam("countryId")Long id) {
		Kraj kraj = krajService.getById(id);
		return kraj;
	}
	
	@GetMapping("/getbyname")
	Kraj getByName(@RequestParam("name")String name) {
		Kraj kraj = krajService.getByName(name);
		return kraj;
	}
	
	@PutMapping("/setname")
	void setName(@RequestParam("countryId")Long id, @RequestParam("newName")String newName) {
		krajService.setName(id, newName);
	}
	
	@DeleteMapping("/deletekraj")
	ResponseEntity<String> deleteCountry(@RequestParam("countryId")Long id) {
		Optional<Wycieczka> w = wycService.getAll().stream().filter(x -> x.getHotel().getCity().getCountry() == krajService.getById(id)).findFirst();
		if(w.isPresent()) {
			return new ResponseEntity<>("Nie można usunąć kraju kiedy odbywaja się w nim wycieczki. Usuń najpierw wycieczki i wszystkie powiązane z nimi dane (zakupy i uczestnicy)", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(krajService.deleteById(id), HttpStatus.OK);
	}
}
