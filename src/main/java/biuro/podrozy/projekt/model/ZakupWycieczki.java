package biuro.podrozy.projekt.model;

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
@JsonIgnoreProperties(ignoreUnknown = true/*, value = {"clientList"}*/)
public class ZakupWycieczki {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tripPurchaseId;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "tripId")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Wycieczka trip;
	
	//@OneToMany
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//	@JoinColumn(name = "tripId")
//    @Column(name = "trip")
//    private List<Uczestnik> clientList;
	
	private float price;

	public Long getTripPurchaseId() {
		return tripPurchaseId;
	}

	public void setTripPurchaseId(Long tripPurchaseId) {
		this.tripPurchaseId = tripPurchaseId;
	}

	public Wycieczka getTrip() {
		return trip;
	}

	public void setTrip(Wycieczka trip) {
		this.trip = trip;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

//	public List<Uczestnik> getClientList() {
//		return clientList;
//	}
//
//	public void setClientList(List<Uczestnik> clientList) {
//		this.clientList = clientList;
//	}
		
	
}
