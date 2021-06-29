package biuro.podrozy.projekt.model;

import java.util.ArrayList;
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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true/*, value = {"hotels", "airports"}*/)
public class Miasto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityId;
	
	private String name;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "countryId")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Kraj country;

	//@OneToMany(cascade = CascadeType.REMOVE)
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "hotelId")
//	private List<Hotel> hotels;
	
	//@OneToMany(cascade = CascadeType.REMOVE)
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "airportId")
//	private List<Lotnisko> airports;
	
//	public Miasto() {
////		hotels = new ArrayList<>();
////		airports = new ArrayList<>();
//	}
	
	public Miasto(String name) {
		this.name = name;
//		hotels = new ArrayList<>();
//		airports = new ArrayList<>();
	}
	
	public Miasto(String name, Kraj country) {
		this.name = name;
		this.country = country;
//		hotels = new ArrayList<>();
//		airports = new ArrayList<>();
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Kraj getCountry() {
		return country;
	}

	public void setCountry(Kraj country) {
		this.country = country;
	}

//	public List<Hotel> getHotels() {
//		return hotels;
//	}
//
//	public void setHotels(List<Hotel> hotels) {
//		this.hotels = hotels;
//	}
//
//	public List<Lotnisko> getAirports() {
//		return airports;
//	}
//
//	public void setAirports(List<Lotnisko> airports) {
//		this.airports = airports;
//	}
	
	
}
