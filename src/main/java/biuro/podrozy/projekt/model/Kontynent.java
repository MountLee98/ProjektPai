package biuro.podrozy.projekt.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true/*, value = {"countries"}*/)
public class Kontynent {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long continentId;

	@NotNull(message = "Podaj nazwę")
	private String name;
	
	//@OneToMany(cascade = CascadeType.REMOVE)
	//@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//	@OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "countryId")
//	private List<Kraj> countries;
	
//	public Kontynent() {
////		countries = new ArrayList<>();
//	}
	
	public Kontynent(String name) {
		this.name = name;
//		countries = new ArrayList<>();
	}

	public Long getContinentId() {
		return continentId;
	}

	public void setContinentId(Long continentId) {
		this.continentId = continentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Kraj> getCountries() {
//		return countries;
//	}
//
//	public void setCountries(List<Kraj> countries) {
//		this.countries = countries;
//	}
	
	
		
}
