package org.svenehrke.checkout.jpaspringdataquerydsl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonDAO extends JpaRepository<PersonEntity, Long> {

	public PersonEntity findByFirstName(String firstName);
}
