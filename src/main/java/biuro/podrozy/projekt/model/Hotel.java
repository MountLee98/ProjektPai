package biuro.podrozy.projekt.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true, value = {"trips"})
public class Hotel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelId;

	@NotNull(message = "Podaj nazwę")
	private String name;

	@NotNull(message = "Wybierz standard hotelu")
	private Gwiazdki stars;
	
	private String description;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cityId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@NotNull(message = "Wybierz miasto")
    private Miasto city;
	
//	@OneToMany(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "tripId")
//    private List<Wycieczka> trips;

//	public Hotel() {
//
//	}
	
	public Hotel(String name) {
		this.name = name;
	}

	public Hotel(String name, Miasto city) {
		this.name = name;
		this.city = city;
	}
	
	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gwiazdki getStars() {
		return stars;
	}

	public void setStars(Gwiazdki stars) {
		this.stars = stars;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Miasto getCity() {
		return city;
	}

	public void setCity(Miasto city) {
		this.city = city;
	}

//	public List<Wycieczka> getTrips() {
//		return trips;
//	}
//
//	public void setTrips(List<Wycieczka> trips) {
//		this.trips = trips;
//	}
	
	
}
