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
//@JsonIgnoreProperties(ignoreUnknown = true, value = {"cities"})
public class Kraj {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countryId;
	
	private String name;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "continentId")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Kontynent continent;
	
	//@OneToMany(cascade = CascadeType.REMOVE)
	//@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//	@OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cityId")
//	private List<Miasto> cities;
	
//	public Kraj() {
////		cities = new ArrayList<>();
//	}
	
	public Kraj(String name) {
		this.name = name;
//		cities = new ArrayList<>();
	}

	public Kraj(String name, Kontynent continent) {
		this.name = name;
		this.continent = continent;
//		cities = new ArrayList<>();
	}
	
	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Kontynent getContinent() {
		return continent;
	}

	public void setContinent(Kontynent continent) {
		this.continent = continent;
	}

//	public List<Miasto> getCities() {
//		return cities;
//	}
//
//	public void setCities(List<Miasto> cities) {
//		this.cities = cities;
//	}
	
	
}
