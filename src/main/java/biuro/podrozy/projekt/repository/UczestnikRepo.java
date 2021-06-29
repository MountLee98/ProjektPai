package biuro.podrozy.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biuro.podrozy.projekt.model.Uczestnik;

public interface UczestnikRepo extends JpaRepository<Uczestnik, Long>{

}
