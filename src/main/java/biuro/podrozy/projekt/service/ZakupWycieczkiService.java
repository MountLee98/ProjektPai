package biuro.podrozy.projekt.service;

import java.util.List;

import biuro.podrozy.projekt.model.Wycieczka;
import biuro.podrozy.projekt.model.ZakupWycieczki;

public interface ZakupWycieczkiService {

	List<ZakupWycieczki> getAll();
	void addZakupWycieczki(Long id, ZakupWycieczki zakup);
	void addZakupWycieczki(ZakupWycieczki zakup);
	List<ZakupWycieczki> getByTrip(Wycieczka wyc);
	ZakupWycieczki getById(Long id);
	boolean deleteById(Long id);
}
