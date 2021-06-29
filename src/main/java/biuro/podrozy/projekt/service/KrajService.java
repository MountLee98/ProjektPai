package biuro.podrozy.projekt.service;

import java.util.List;

import biuro.podrozy.projekt.model.Kraj;

public interface KrajService {

	List<Kraj> getAll();
	void addKraj(Long id, Kraj kraj);
	void addKraj(Kraj kraj);
	Kraj getById(Long id);
	Kraj getByName(String name);
	void setName(Long id, String name);
	String deleteById(Long id);
}
