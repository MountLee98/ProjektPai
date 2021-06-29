package biuro.podrozy.projekt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biuro.podrozy.projekt.model.Uczestnik;
import biuro.podrozy.projekt.service.HotelService;
import biuro.podrozy.projekt.service.KontynentService;
import biuro.podrozy.projekt.service.KrajService;
import biuro.podrozy.projekt.service.LotniskoService;
import biuro.podrozy.projekt.service.MiastoService;
import biuro.podrozy.projekt.service.UczestnikService;
import biuro.podrozy.projekt.service.WycieczkaService;
import biuro.podrozy.projekt.service.ZakupWycieczkiService;

@RequestMapping("/rest/uczestnik")
@RestController
public class UczestnikController {

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
	List<Uczestnik> getAll() {
		return uczestnikService.getAll();
	}
	
	@PostMapping("/adduczestnik")
	void addUczestnik() {
		uczestnikService.addUczestnik(new Uczestnik());
	}
	
	@GetMapping("/getbyid")
	Uczestnik getById(
			@RequestParam("clientId")Long id) {
		return uczestnikService.getById(id);
	}
	
	@GetMapping("/getbyischild")
	List<Uczestnik> getByIsChild(
			@RequestParam("isChild")boolean isChild) {
		return uczestnikService.getByIsChild(isChild);
	}
	
	void setName(
			@RequestParam("clientId")Long id, 
			@RequestParam("name")String name) {
		uczestnikService.setName(id, name);
	}
	
	void setSurname(
			@RequestParam("clientId")Long id, 
			@RequestParam("surname")String surname) {
		uczestnikService.setSurname(id, surname);
	}
	
	void setIsChild(
			@RequestParam("clientId")Long id, 
			@RequestParam("isChild")boolean isChild) {
		uczestnikService.setIsChild(id, isChild);
	}
	
	boolean deleteById(
			@RequestParam("clientId")Long id) {
		return uczestnikService.deleteById(id);
	}
	
}
