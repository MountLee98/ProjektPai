package biuro.podrozy.projekt.service;

import java.util.List;

import biuro.podrozy.projekt.model.Kontynent;

public interface KontynentService {

	List<Kontynent> getAll();
	void addKontynent(Kontynent kont);
	Kontynent getById(Long id);
	Kontynent getByName(String name);
	void setName(Long id, String name);
	String deleteById(Long id);
}
