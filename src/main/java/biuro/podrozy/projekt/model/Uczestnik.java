package biuro.podrozy.projekt.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Uczestnik {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;
	
	private String name;
	
	private String surname;
	
	private boolean isChild;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "tripPurchaseId")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private ZakupWycieczki tripPurchase;
	
//	public Uczestnik() {
//
//	}
	
	public Uczestnik(String name) {
		this.name = name;
	}
	
	public Uczestnik(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	
	public Uczestnik(String name, String surname, boolean isChild) {
		this.name = name;
		this.surname = surname;
		this.isChild = isChild;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean getIsChild() {
		return isChild;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	public ZakupWycieczki getTripPurchase() {
		return tripPurchase;
	}

	public void setTripPurchase(ZakupWycieczki tripPurchase) {
		this.tripPurchase = tripPurchase;
	}
		
}
