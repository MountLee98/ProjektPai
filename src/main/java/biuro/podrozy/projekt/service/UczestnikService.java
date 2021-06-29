package biuro.podrozy.projekt.service;

import java.util.List;

import biuro.podrozy.projekt.model.Uczestnik;

public interface UczestnikService {

	List<Uczestnik> getAll();
	void addUczestnik(Uczestnik ucz);
	Uczestnik getById(Long id);
	List<Uczestnik> getByIsChild(boolean isChild);
	void setName(Long id, String name);
	void setSurname(Long id, String surname);
	void setIsChild(Long id, boolean isChild);
	boolean deleteById(Long id);
}
