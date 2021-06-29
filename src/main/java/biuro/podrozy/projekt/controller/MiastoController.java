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

import biuro.podrozy.projekt.model.Kraj;
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

@RequestMapping("/rest/miasto")
@RestController
public class MiastoController {

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
	List<Miasto> getAllCity() {
		List<Miasto> list = miastoService.getAll();
		return list;
	}
	
	@PostMapping("/addmiasto")
	void addCity(@RequestParam("countryId")Long id, @RequestParam("name")String name) {		
		miastoService.addMiasto(id, new Miasto(name));
	}
	
	@GetMapping("/getbyid")
	Miasto getById(@RequestParam("cityId")Long id) {
		Miasto miasto = miastoService.getById(id);
		return miasto;
	}
	
	@GetMapping("/getbyname")
	Miasto getByName(@RequestParam("name")String name) {
		Miasto miasto = miastoService.getByName(name);
		return miasto;
	}
	
	@PutMapping("/setname")
	void setName(@RequestParam("cityId")Long id, @RequestParam("newName")String newName) {
		miastoService.setName(id, newName);
	}
	
	@DeleteMapping("/deletecity")
	ResponseEntity<String> deleteCity(@RequestParam("cityId")Long id) {
		Optional<Wycieczka> w = wycService.getAll().stream().filter(x -> x.getHotel().getCity() == miastoService.getById(id)).findFirst();
		if(w.isPresent()) {
			return new ResponseEntity<>("Nie można usunąć miasta kiedy odbywaja się w nim wycieczki. Usuń najpierw wycieczki i wszystkie powiązane z nimi dane (zakupy i uczestnicy)", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(miastoService.deleteById(id), HttpStatus.OK);
	}
}
