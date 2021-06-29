package biuro.podrozy.projekt.model;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true/*, value = {"tripsPurchases"}*/)
public class Wycieczka {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tripId;

	private String name;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@JoinColumn(name = "airportId")
	@JoinColumn(name = "airport_from_Id")
	//@OnDelete(action = OnDeleteAction.CASCADE)
    private Lotnisko from;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@JoinColumn(name = "airportId", insertable=false, updatable=false)
	@JoinColumn(name = "airport_to_Id")
	//@OnDelete(action = OnDeleteAction.CASCADE)
    private Lotnisko to;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "hotelId")
	//@OnDelete(action = OnDeleteAction.CASCADE)
    private Hotel hotel;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate departureDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate returnDate;
	
	private int daysDuration;
	
	private Typ type;
	
	private float adultPrice;
	
	private float kidsPrice;
	
	private boolean isPromoted;
	
	private int adultNumberOfSeats;
	
	private int kidsNumberOfSeats;
	
	private File photo;
	
//	@OneToMany(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "tripPurchaseId")
//    private List<ZakupWycieczki> tripsPurchases;

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lotnisko getFrom() {
		return from;
	}

	public void setFrom(Lotnisko from) {
		this.from = from;
	}

	public Lotnisko getTo() {
		return to;
	}

	public void setTo(Lotnisko to) {
		this.to = to;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysDuration() {
		return daysDuration;
	}

	public void setDaysDuration(int daysDuration) {
		this.daysDuration = daysDuration;
	}

	public Typ getType() {
		return type;
	}

	public void setType(Typ type) {
		this.type = type;
	}

	public float getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(float adultPrice) {
		this.adultPrice = adultPrice;
	}

	public float getKidsPrice() {
		return kidsPrice;
	}

	public void setKidsPrice(float kidsPrice) {
		this.kidsPrice = kidsPrice;
	}

	public boolean getIsPromoted() {
		return isPromoted;
	}

	public void setPromoted(boolean isPromoted) {
		this.isPromoted = isPromoted;
	}

	public int getAdultNumberOfSeats() {
		return adultNumberOfSeats;
	}

	public void setAdultNumberOfSeats(int adultNumberOfSeats) {
		this.adultNumberOfSeats = adultNumberOfSeats;
	}

	public int getKidsNumberOfSeats() {
		return kidsNumberOfSeats;
	}

	public void setKidsNumberOfSeats(int kidsNumberOfSeats) {
		this.kidsNumberOfSeats = kidsNumberOfSeats;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

//	public List<ZakupWycieczki> getTripsPurchases() {
//		return tripsPurchases;
//	}
//
//	public void setTripsPurchases(List<ZakupWycieczki> tripsPurchases) {
//		this.tripsPurchases = tripsPurchases;
//	}
	
	
}
