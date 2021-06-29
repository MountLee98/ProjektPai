package biuro.podrozy.projekt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.model.ZakupWycieczki;
import biuro.podrozy.projekt.service.HotelService;
import biuro.podrozy.projekt.service.KontynentService;
import biuro.podrozy.projekt.service.KrajService;
import biuro.podrozy.projekt.service.LotniskoService;
import biuro.podrozy.projekt.service.MiastoService;
import biuro.podrozy.projekt.service.UczestnikService;
import biuro.podrozy.projekt.service.WycieczkaService;
import biuro.podrozy.projekt.service.ZakupWycieczkiService;

@RequestMapping("/rest/zakup")
@RestController
public class ZakupWycieczkiController {

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
	List<ZakupWycieczki> getAll() {
		return zakupService.getAll();
	}
	
	@PostMapping("/addzakup")
	void addZakupWycieczki(@RequestParam("tripPurchaseId")Long id) {
		zakupService.addZakupWycieczki(id, new ZakupWycieczki());
	}
	
	@GetMapping("/getbytrip")
	List<ZakupWycieczki> getByTrip(Wycieczka wyc) {
		return zakupService.getByTrip(wyc);
	}
	
	@GetMapping("/getbyid")
	ZakupWycieczki getById(Long id) {
		return zakupService.getById(id);
	}
	
	@DeleteMapping("/deletezakup")
	boolean deleteById(Long id) {
		return zakupService.deleteById(id);
	}
	
}
