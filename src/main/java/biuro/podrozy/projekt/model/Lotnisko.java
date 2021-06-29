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
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true/*, value = {"tripsTo", "tripsFrom"}*/)
public class Lotnisko {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long airportId;
	
	private String name;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cityId")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Miasto city;
	
//	@OneToMany(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "tripId")
//	private List<Wycieczka> tripsTo;
//	
//	@OneToMany(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "tripId")
//	private List<Wycieczka> tripsFrom;

//	public Lotnisko() {
////		tripsTo = new ArrayList<>();
////		tripsFrom = new ArrayList<>();
//	}
	
	public Lotnisko(String name) {
		this.name = name;
//		tripsTo = new ArrayList<>();
//		tripsFrom = new ArrayList<>();
	}

	public Lotnisko(String name, Miasto city) {
		this.name = name;
		this.city = city;
//		tripsTo = new ArrayList<>();
//		tripsFrom = new ArrayList<>();
	}
	
	public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Miasto getCity() {
		return city;
	}

	public void setCity(Miasto city) {
		this.city = city;
	}

//	public List<Wycieczka> getTripsTo() {
//		return tripsTo;
//	}
//
//	public void setTripsTo(List<Wycieczka> tripsTo) {
//		this.tripsTo = tripsTo;
//	}
//
//	public List<Wycieczka> getTripsFrom() {
//		return tripsFrom;
//	}
//
//	public void setTripsFrom(List<Wycieczka> tripsFrom) {
//		this.tripsFrom = tripsFrom;
//	}
	
	
}
