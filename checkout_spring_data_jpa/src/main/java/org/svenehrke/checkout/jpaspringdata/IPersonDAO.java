package org.svenehrke.checkout.jpaspringdata;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonDAO extends JpaRepository<PersonEntity, Long> {

	public PersonEntity findByFirstName(String firstName);
}
