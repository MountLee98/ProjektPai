package biuro.podrozy.projekt.controller;

import biuro.podrozy.projekt.model.*;
import biuro.podrozy.projekt.storage.StorageService;
import biuro.podrozy.projekt.service.*;
import biuro.podrozy.projekt.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/")
@Controller
public class SiteController {

//    private final StorageService storageService;
//
//    @Autowired
//    public SiteController(StorageService storageService) {
//        this.storageService = storageService;
//    }

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

    @GetMapping("/mainAdmin")
    String hello(){
        return "mainAdmin";
    }

    @GetMapping("/addSuccess")
    String addSuccess(){
        return "addSuccess";
    }

    @GetMapping("/addContinent")
    String addContinent(Model model){
        Kontynent kontynent = new Kontynent();
        model.addAttribute("kontynent", kontynent);
        return "dodajKontynent";
    }

    @PostMapping("/addContinent")
    String addContinent(@ModelAttribute("kontynent")Kontynent kontynent) {
        kontynentService.addKontynent(kontynent);
        return "addSuccess";
    }

    @GetMapping("/addCountry")
    String addCountry(Model model){
        Kraj kraj = new Kraj();
        model.addAttribute("kraj", kraj);
        model.addAttribute("kontynenty", kontynentService.getAll());
        return "dodajKraj";
    }

    @PostMapping("/addCountry")
    String addCountry(@ModelAttribute("kraj")Kraj kraj) {
        krajService.addKraj(kraj);
        return "addSuccess";
    }

    @GetMapping("/addTrip")
    String addTrip(Model model){
        Wycieczka wycieczka = new Wycieczka();
        model.addAttribute("wycieczka", wycieczka);
        model.addAttribute("lotniska", lotniskoService.getAll());
        model.addAttribute("hotele", hotelService.getAll());
/*        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));*/
        return "dodajWycieczke";
    }

    @PostMapping("/addTrip")
    String addTrip(@ModelAttribute("wycieczka")Wycieczka wycieczka) {
        wycService.addWycieczka(wycieczka);
        return "addSuccess";
    }

    @GetMapping("/addHotel")
    String addHotel(Model model){
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        model.addAttribute("miasta", miastoService.getAll());
        return "dodajHotel";
    }

    @PostMapping("/addHotel")
    String addCHotel(@ModelAttribute("hotel")Hotel hotel) {
        hotelService.addHotel(hotel);
        return "addSuccess";
    }

    @GetMapping("/addAirport")
    String addAirport(Model model){
        Lotnisko lotnisko = new Lotnisko();
        model.addAttribute("lotnisko", lotnisko);
        model.addAttribute("miasta", miastoService.getAll());
        return "dodajLotnisko";
    }

    @PostMapping("/addAirport")
    String addAirport(@ModelAttribute("lotnisko")Lotnisko lotnisko) {
        lotniskoService.addLotnisko(lotnisko);
        return "addSuccess";
    }

    @GetMapping("/addCity")
    String addCity(Model model){
        Miasto miasto = new Miasto();
        model.addAttribute("miasto", miasto);
        model.addAttribute("kraje", krajService.getAll());
        return "dodajMiasto";
    }

    @PostMapping("/addCity")
    String addCity(@ModelAttribute("miasto")Miasto miasto) {
        miastoService.addMiasto(miasto);
        return "addSuccess";
    }

    @GetMapping("/addPurchase")
    String addPurchase(Model model){
        ZakupWycieczki zakup = new ZakupWycieczki();
        model.addAttribute("zakup", zakup);
        model.addAttribute("wycieczki", wycService.getAll());
        return "dodajZakup";
    }

    @PostMapping("/addPurchase")
    String addPurchase(@ModelAttribute("zakup")ZakupWycieczki zakup) {
        zakupService.addZakupWycieczki(zakup);
        return "addSuccess";
    }

    @GetMapping("/addParticipant")
    String addParticipant(Model model){
        Uczestnik uczestnik = new Uczestnik();
        model.addAttribute("uczestnik", uczestnik);
        model.addAttribute("zakupy", zakupService.getAll());
        return "dodajUczestnika";
    }

    @PostMapping("/addParticipant")
    String addParticipant(@ModelAttribute("uczestnik")Uczestnik uczestnik) {
        uczestnikService.addUczestnik(uczestnik);
        return "addSuccess";
    }

    @GetMapping("/showTrips")
    String showTrips(Model model){
        model.addAttribute("wycieczki", wycService.getAll());
        return "pokazWycieczki";
    }

    @GetMapping("/")
    String main(){
        return "main";
    }

    @GetMapping("/searchAirport")
    String searchAirport(Model model){
        //model.addAttribute("lotniska", lotniskoService.getAll());
        Lotnisko lotnisko = new Lotnisko();
        model.addAttribute("lotnisko", lotnisko);
        model.addAttribute("lotniska", lotniskoService.getAll());
        return "wyszukajLotnisko";
    }

    @PostMapping("/searchAirport")
    String searchAirport(@ModelAttribute("lotnisko")Lotnisko lotnisko, Model model) {
        model.addAttribute("wycieczki", wycService.getByFromName(lotnisko.getName()));
        return "wyszukajWynik";
    }

    @GetMapping("/searchHotel")
    String searchHotel(Model model){
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        model.addAttribute("hotele", hotelService.getAll());
        return "wyszukajHotel";
    }

    @PostMapping("/searchHotel")
    String searchHotel(@ModelAttribute("hotel")Hotel hotel, Model model) {
        model.addAttribute("wycieczki", wycService.getByHotelName(hotel.getName()));
        return "wyszukajWynik";
    }

    @GetMapping("/searchDeparture")
    String searchDeparture(Model model){
        Wycieczka wycieczka = new Wycieczka();
        model.addAttribute("wycieczka", wycieczka);
        return "wyszukajWyjazd";
    }

    @PostMapping("/searchDeparture")
    String searchDeparture(@ModelAttribute("wycieczka")Wycieczka wycieczka, Model model) {
        model.addAttribute("wycieczki", wycService.getByDepartureDate(wycieczka.getDepartureDate()));
        return "wyszukajWynik";
    }

    @GetMapping("/searchReturn")
    String searchReturn(Model model){
        Wycieczka wycieczka = new Wycieczka();
        model.addAttribute("wycieczka", wycieczka);
        return "wyszukajPowrot";
    }

    @PostMapping("/searchReturn")
    String searchReturn(@ModelAttribute("wycieczka")Wycieczka wycieczka, Model model) {
        model.addAttribute("wycieczki", wycService.getByReturnDate(wycieczka.getReturnDate()));
        return "wyszukajWynik";
    }

    @GetMapping("/searchType")
    String searchType(Model model){
        Wycieczka wycieczka = new Wycieczka();
        model.addAttribute("wycieczka", wycieczka);
        return "wyszukajTyp";
    }

    @PostMapping("/searchType")
    String searchType(@ModelAttribute("wycieczka")Wycieczka wycieczka, Model model) {
        model.addAttribute("wycieczki", wycService.getByType(wycieczka.getType()));
        return "wyszukajWynik";
    }

    @GetMapping("/searchStars")
    String searchStars(Model model){
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        model.addAttribute("hotele", hotelService.getAll());
        return "wyszukajGwiazdki";
    }

    @PostMapping("/searchStars")
    String searchStars(@ModelAttribute("hotel")Hotel hotel, Model model) {
        model.addAttribute("wycieczki", wycService.getByHotelStars(hotel.getStars()));
        return "wyszukajWynik";
    }

    @GetMapping("/searchDuration")
    String searchDuration(Model model){
        Wycieczka wycieczka = new Wycieczka();
        model.addAttribute("wycieczka", wycieczka);
        return "wyszukajDlugosc";
    }

    @PostMapping("/searchDuration")
    String searchDuration(@ModelAttribute("wycieczka")Wycieczka wycieczka, Model model) {
        model.addAttribute("wycieczki", wycService.getByDuration(wycieczka.getDaysDuration()));
        return "wyszukajWynik";
    }

    @GetMapping("/deleteTrip/tripId={tripId}")
    public String deleteTrip(@PathVariable Long tripId) {
        wycService.deleteById(tripId);
        return "redirect:/showTrips";
    }

    @GetMapping("/editTrip/tripId={tripId}")
    public String editDelegation(@PathVariable Long tripId, Model model){
        model.addAttribute("wycieczka", wycService.getById(tripId));
        model.addAttribute("lotniska", lotniskoService.getAll());
        model.addAttribute("hotele", hotelService.getAll());
        return "edytujWycieczke";
    }

    @PostMapping("/editTrip")
    public String editDelegationPost(@ModelAttribute Wycieczka wycieczka){
        wycService.addWycieczka(wycieczka);
        return "redirect:/showTrips";
    }
}
