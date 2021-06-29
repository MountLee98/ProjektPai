package biuro.podrozy.projekt.controller;

import java.time.LocalDateTime;
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
import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.service.HotelService;
import biuro.podrozy.projekt.service.KontynentService;
import biuro.podrozy.projekt.service.KrajService;
import biuro.podrozy.projekt.service.LotniskoService;
import biuro.podrozy.projekt.service.MiastoService;
import biuro.podrozy.projekt.service.UczestnikService;
import biuro.podrozy.projekt.service.WycieczkaService;
import biuro.podrozy.projekt.service.ZakupWycieczkiService;

@RequestMapping("/rest/kontynent")
@RestController
public class KontynentController {

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
	List<Kontynent> getAllContinent() {
		List<Kontynent> list = kontynentService.getAll();
		return list;
	}
	
	@PostMapping("/addkontynent")
	void addContinent(@RequestParam("name")String name) {		
		kontynentService.addKontynent(new Kontynent(name));
	}
	
	@GetMapping("/getbyid")
	Kontynent getById(@RequestParam("continentId")Long id) {
		Kontynent kont = kontynentService.getById(id);
		return kont;
	}
	
	@GetMapping("/getbyname")
	Kontynent getByName(@RequestParam("name")String name) {
		Kontynent kont = kontynentService.getByName(name);
		return kont;
	}
	
	@PutMapping("/setname")
	void setName(@RequestParam("continentId")Long id, @RequestParam("newName")String newName) {
		kontynentService.setName(id, newName);
	}
	
	@DeleteMapping("/deletekontynent")
	ResponseEntity<String> deleteContinent(@RequestParam("continentId")Long id) {
		Optional<Wycieczka> w = wycService.getAll().stream().filter(x -> x.getHotel().getCity().getCountry().getContinent() == kontynentService.getById(id)).findFirst();
		if(w.isPresent()) {
			return new ResponseEntity<>("Nie można usunąć kontynentu kiedy odbywaja się w nim wycieczki. Usuń najpierw wycieczki i wszystkie powiązane z nimi dane (zakupy i uczestnicy)", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(kontynentService.deleteById(id), HttpStatus.OK);
	}
}
