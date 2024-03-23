package kr.puppyplace.user.mypet.repository;

import kr.puppyplace.user.mypet.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPetRepository extends JpaRepository<Pet, Long> {

}
