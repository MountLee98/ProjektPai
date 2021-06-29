package biuro.podrozy.projekt.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biuro.podrozy.projekt.model.Hotel;
import biuro.podrozy.projekt.model.Miasto;
import biuro.podrozy.projekt.model.Typ;
import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.service.HotelService;
import biuro.podrozy.projekt.service.KontynentService;
import biuro.podrozy.projekt.service.KrajService;
import biuro.podrozy.projekt.service.LotniskoService;
import biuro.podrozy.projekt.service.MiastoService;
import biuro.podrozy.projekt.service.UczestnikService;
import biuro.podrozy.projekt.service.WycieczkaService;
import biuro.podrozy.projekt.service.ZakupWycieczkiService;

@RequestMapping("/rest/wycieczka")
@RestController
public class WycieczkaController {

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
	List<Wycieczka> getAll() {
		List<Wycieczka> list = wycService.getAll();
		return list;
	}
	
	@PostMapping("/addwycieczka")
	void addWycieczka(
			@RequestParam("airportFromId")Long idFrom, 
			@RequestParam("airportToId")Long idTo, 
			@RequestParam("hotelId")Long idHotel) {
		wycService.addWycieczka(idFrom, idTo, idHotel, new Wycieczka());
	}
	
	@GetMapping("/getbyhotel")
	List<Wycieczka> getByHotel(
			@RequestParam("hotelId")Long idHotel) {
		return wycService.getByHotel(hotelService.getById(idHotel));
	}
	
	@PutMapping("/sethotel")
	void addHotel(
			@RequestParam("tripId")Long idWyc, 
			@RequestParam("hotelId")Long idHotel) {
		wycService.addHotel(idWyc, idHotel);
	}
	
	@PutMapping("/setfrom")
	void addFrom(
			@RequestParam("tripId")Long idWyc, 
			@RequestParam("airportToId")Long idLotn) {
		wycService.addFrom(idWyc, idLotn);
	}
	
	@PutMapping("/setto")
	void addTo(
			@RequestParam("tripId")Long idWyc, 
			@RequestParam("airportToId")Long idLotn) {
		wycService.addTo(idWyc, idLotn);
	}
	
	@GetMapping("/getbydeparture")
	List<Wycieczka> getByDepartureDate(
			@RequestParam("departureDate")LocalDate date) {
		return wycService.getByDepartureDate(date);
	}
	
	@PutMapping("/setdeparture")
	void addDepartureDate(
			@RequestParam("tripId")Long id, 
			@RequestParam("departureDate")LocalDate date) {
		wycService.addDepartureDate(id, date);
	}
	
	@GetMapping("/getbyreturn")
	List<Wycieczka> getByReturnDate(
			@RequestParam("returnDate")LocalDate date) {
		return wycService.getByReturnDate(date);
	}
	
	@PutMapping("/setreturn")
	void addReturnDate(
			@RequestParam("tripId")Long id, 
			@RequestParam("returnDate")LocalDate date) {
		wycService.addReturnDate(id, date);
	}
	
	@PutMapping("/setduration")
	void setDuration(
			@RequestParam("tripId")Long id, 
			@RequestParam("departureDate")LocalDate departureDate, 
			@RequestParam("days")int days) {
		wycService.setDuration(id, departureDate, days);
	}
	
	@GetMapping("/getbytype")
	List<Wycieczka> getByType(
			@RequestParam("Type")Typ type) {
		return wycService.getByType(type);
	}
	
	@PutMapping("/settype")
	void addType(
			@RequestParam("tripId")Long id, 
			@RequestParam("type")Typ type) {
		wycService.addType(id, type);
	}
	
	@GetMapping("/getbyadultprice")
	List<Wycieczka> getByAdultPrice(
			@RequestParam("price")float price) {
		return wycService.getByAdultPrice(price);
	}
	
	@PutMapping("/setadultprice")
	void addAdultPrice(
			@RequestParam("tripId")Long id, 
			@RequestParam("price")float price) {
		wycService.addAdultPrice(id, price);
	}
	
	@GetMapping("/getbykidprice")
	List<Wycieczka> getByKidPrice(
			@RequestParam("price")float price) {
		return wycService.getByKidPrice(price);
	}
	
	@PutMapping("/setkidprice")
	void addKidPrice(
			@RequestParam("tripId")Long id, 
			@RequestParam("price")float price) {
		wycService.addKidPrice(id, price);
	}
	
	@GetMapping("/getbyadultseats")
	List<Wycieczka> getByAdultSeats(
			@RequestParam("seatsAmount")int seatsAmount) {
		return wycService.getByAdultSeats(seatsAmount);
	}
	
	@PutMapping("/setadultseats")
	void addAdultSeats(
			@RequestParam("tripId")Long id, 
			@RequestParam("seatsAmount")int seatsAmount) {
		wycService.addAdultSeats(id, seatsAmount);
	}
	
	@GetMapping("/getbykidseats")
	List<Wycieczka> getByKidSeats(
			@RequestParam("seatsAmount")int seatsAmount) {
		return wycService.getByKidSeats(seatsAmount);
	}
	
	@PutMapping("/setkidseats")
	void addKidSeats(
			@RequestParam("tripId")Long id, 
			@RequestParam("seatsAmount")int seatsAmount) {
		wycService.addKidSeats(id, seatsAmount);
	}
	
	@GetMapping("/getbyispromoted")
	List<Wycieczka> getByIsPromoted(
			@RequestParam("isPromoted")boolean isPromoted) {
		return wycService.getByIsPromoted(isPromoted);
	}
	
	@PutMapping("/setispromoted")
	void addIsPromoted(
			@RequestParam("tripId")Long id, 
			@RequestParam("isPromoted")boolean isPromoted) {
		wycService.addIsPromoted(id, isPromoted);
	}
	
	@PutMapping("/setpicture")
	void addPicture(
			@RequestParam("tripId")Long id, @RequestParam("picture")File picture) {
		wycService.addPicture(id, picture);
	}
	
	@GetMapping("/getbyid")
	Wycieczka getById(
			@RequestParam("tripId")Long id) {
		return wycService.getById(id);
	}
	
	@DeleteMapping("/deletewycieczka")
	boolean deleteById(
			@RequestParam("tripId")Long id) {
		return wycService.deleteById(id);
	}
}
