package biuro.podrozy.projekt.controller;

import biuro.podrozy.projekt.model.*;
import biuro.podrozy.projekt.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
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
    private String uploadFolder = "";

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
    String addTrip(@ModelAttribute("wycieczka")Wycieczka wycieczka, @RequestParam("photo") MultipartFile file, HttpServletRequest request) {
        try {
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            try {
                File dir = new File(uploadDirectory);
                String fileName = file.getOriginalFilename();
                String filePath = Paths.get(uploadDirectory, fileName).toString();
                System.out.println(dir+" "+fileName+" "+filePath);
                if (fileName == null || fileName.contains("..")) {
                    wycService.addWycieczka(wycieczka);
                    return "addSuccess";
                }
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            System.out.println(imageData);
            wycieczka.setImage(imageData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wycService.addWycieczka(wycieczka);
        return "addSuccess";
    }

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(wycService.getById(id).getImage());
        response.getOutputStream().close();
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
    String main(Model model){
        model.addAttribute("promowane", wycService.getByPromoted(true));
        model.addAttribute("zblizajace", wycService.getAll().stream().filter(x ->
                x.getDepartureDate().minusDays(8).isBefore(LocalDate.now()) &&
                        x.getDepartureDate().isAfter(LocalDate.now())
        ).collect(Collectors.toList()));
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
    public String editDelegationPost(@ModelAttribute Wycieczka wycieczka,final @RequestParam("photo") MultipartFile file, HttpServletRequest request){
        try {
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            try {
                File dir = new File(uploadDirectory);
                String fileName = file.getOriginalFilename();
                String filePath = Paths.get(uploadDirectory, fileName).toString();
                System.out.println(dir+" "+fileName+" "+filePath);
                if (fileName == null || fileName.contains("..")) {
                    wycService.addWycieczka(wycieczka);
                    return "redirect:/showTrips";
                }
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            System.out.println(imageData);
            wycieczka.setImage(imageData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wycService.addWycieczka(wycieczka);
        return "redirect:/showTrips";
    }

    @GetMapping("/show")
    String show(Model model){
        model.addAttribute("promowane", wycService.getByPromoted(true));
        model.addAttribute("zblizajace", wycService.getAll().stream().filter(x ->
                x.getDepartureDate().minusDays(8).isBefore(LocalDate.now()) &&
                        x.getDepartureDate().isAfter(LocalDate.now())
        ).collect(Collectors.toList()));
        return "x";
    }

    @GetMapping("/searchHotel/hotelId={hotelId}")
    public String searchHotel(@PathVariable Long hotelId, Model model){
        model.addAttribute("wycieczki", wycService.getByHotelName(hotelService.getById(hotelId).getName()));
        return "wyszukajWynik";
    }

    @GetMapping("/searchAirport/airportId={airportId}")
    public String searchAirport(@PathVariable Long airportId, Model model){
        model.addAttribute("wycieczki", wycService.getByFrom(lotniskoService.getById(airportId)));
        return "wyszukajWynik";
    }

    @GetMapping("/searchAirportTo/airportId={airportId}")
    public String searchAirportTo(@PathVariable Long airportId, Model model){
        model.addAttribute("wycieczki", wycService.getByTo(lotniskoService.getById(airportId)));
        return "wyszukajWynik";
    }

    @GetMapping("/searchStars/{stars}")
    public String searchAirport(@PathVariable Gwiazdki stars, Model model){
        model.addAttribute("wycieczki", wycService.getByHotelStars(stars));
        return "wyszukajWynik";
    }

    @GetMapping("/searchType/{type}")
    public String searchAirport(@PathVariable Typ type, Model model){
        model.addAttribute("wycieczki", wycService.getByType(type));
        return "wyszukajWynik";
    }

    @GetMapping("/searchDeparture/{date}")
    public String searchDeparture(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Model model){
        model.addAttribute("wycieczki", wycService.getByDepartureDate(date));
        return "wyszukajWynik";
    }

    @GetMapping("/searchReturn/{date}")
    public String searchReturn(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Model model){
        model.addAttribute("wycieczki", wycService.getByReturnDate(date));
        return "wyszukajWynik";
    }

    @GetMapping("/buyTrip/tripId={tripId}")
    public String buyTrip(@PathVariable Long airportId, Model model){
        model.addAttribute("wycieczki", wycService.getByFrom(lotniskoService.getById(airportId)));
        return "wyszukajWynik";
    }

    @GetMapping("/lastCall")
    String lastCall(Model model){
        model.addAttribute("wycieczki", wycService.getAll().stream().filter(x ->
                x.getDepartureDate().minusDays(3).isBefore(LocalDate.now()) &&
                        x.getDepartureDate().isAfter(LocalDate.now())
        ).collect(Collectors.toList()));
        return "wyszukajWynik";
    }

    @GetMapping("/lastMinute")
    String lastMinute(Model model){
        model.addAttribute("wycieczki", wycService.getAll().stream().filter(x ->
                x.getDepartureDate().minusDays(6).isBefore(LocalDate.now()) &&
                        x.getDepartureDate().isAfter(LocalDate.now())
        ).collect(Collectors.toList()));
        return "wyszukajWynik";
    }

    @GetMapping("/showTrip/tripId={tripId}")
    public String showTrip(@PathVariable Long tripId, Model model){
        model.addAttribute("wycieczka", wycService.getById(tripId));
        return "wyswietlWycieczke";
    }

    @GetMapping("/searchContinent/continentId={continentId}")
    public String searchContinent(@PathVariable Long continentId, Model model){
        model.addAttribute("wycieczki", wycService.getAll().stream().filter(x ->
                x.getHotel().getCity().getCountry().getContinent().getContinentId() == continentId
        ).collect(Collectors.toList()));
        return "wyszukajWynik";
    }

    @GetMapping("/searchCountry/countryId={countryId}")
    public String searchCountry(@PathVariable Long countryId, Model model){
        model.addAttribute("wycieczki", wycService.getAll().stream().filter(x ->
                x.getHotel().getCity().getCountry().getCountryId() == countryId
        ).collect(Collectors.toList()));
        return "wyszukajWynik";
    }

    @GetMapping("/searchCity/cityId={cityId}")
    public String searchCity(@PathVariable Long cityId, Model model){
        model.addAttribute("wycieczki", wycService.getAll().stream().filter(x ->
                x.getHotel().getCity().getCityId() == cityId
        ).collect(Collectors.toList()));
        return "wyszukajWynik";
    }
}
