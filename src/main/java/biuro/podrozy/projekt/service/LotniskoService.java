package biuro.podrozy.projekt.service;

import java.util.List;

import biuro.podrozy.projekt.model.Lotnisko;

public interface LotniskoService {

	List<Lotnisko> getAll();
	void addLotnisko(Long id, Lotnisko lotnisko);
	void addLotnisko(Lotnisko lotnisko);
	Lotnisko getById(Long id);
	Lotnisko getByName(String name);
	void setName(Long id, String name);
	String deleteById(Long id);
}
