package kr.puppyplace.user.mypet.repository;

import java.util.List;
import kr.puppyplace.user.mypet.domain.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Long> {

    List<Breed> findByNameContainingOrEngNameContaining(String breedName,
            String breedNameEng);
}
