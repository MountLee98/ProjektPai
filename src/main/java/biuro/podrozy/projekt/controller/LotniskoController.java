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

import biuro.podrozy.projekt.model.Lotnisko;
import biuro.podrozy.projekt.model.Miasto;
import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.service.HotelService;
import biuro.podrozy.projekt.service.KontynentService;
import biuro.podrozy.projekt.service.KrajService;
import biuro.podrozy.projekt.service.LotniskoService;
import biuro.podrozy.projekt.service.MiastoService;
import biuro.podrozy.projekt.service.UczestnikService;
import biuro.podrozy.projekt.service.WycieczkaService;
import biuro.podrozy.projekt.service.ZakupWycieczkiService;

@RequestMapping("/rest/lotnisko")
@RestController
public class LotniskoController {

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
	List<Lotnisko> getAllAirport() {
		List<Lotnisko> list = lotniskoService.getAll();
		return list;
	}
	
	@PostMapping("/addlotnisko")
	void addAirport(@RequestParam("cityId")Long id, @RequestParam("name")String name) {		
		lotniskoService.addLotnisko(id, new Lotnisko(name));
	}
	
	@GetMapping("/getbyid")
	Lotnisko getById(@RequestParam("airportId")Long id) {
		Lotnisko lotnisko = lotniskoService.getById(id);
		return lotnisko;
	}
	
	@GetMapping("/getbyname")
	Lotnisko getByName(@RequestParam("name")String name) {
		Lotnisko lotnisko = lotniskoService.getByName(name);
		return lotnisko;
	}
	
	@PutMapping("/setname")
	void setName(@RequestParam("airportId")Long id, @RequestParam("newName")String newName) {
		lotniskoService.setName(id, newName);
	}
	
	@DeleteMapping("/deletelotnisko")
	ResponseEntity<String> deleteAirport(@RequestParam("airportId")Long id) {
		Optional<Wycieczka> wFrom = wycService.getAll().stream().filter(x -> x.getFrom() == lotniskoService.getById(id)).findFirst();
		if(wFrom.isPresent()) {
			return new ResponseEntity<>("Nie można usunąć lotniska kiedy odbywaja się w nim wycieczki. Usuń najpierw wycieczki i wszystkie powiązane z nimi dane (zakupy i uczestnicy)", HttpStatus.NOT_FOUND);
		} else {
			Optional<Wycieczka> wTo = wycService.getAll().stream().filter(x -> x.getTo() == lotniskoService.getById(id)).findFirst();
			if(wTo.isPresent()) {
				return new ResponseEntity<>("Nie można usunąć lotniska kiedy odbywaja się w nim wycieczki. Usuń najpierw wycieczki i wszystkie powiązane z nimi dane (zakupy i uczestnicy)", HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(lotniskoService.deleteById(id), HttpStatus.OK);
	}
}
