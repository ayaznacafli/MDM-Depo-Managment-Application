package az.mdm.depo.repository;


import az.mdm.depo.model.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity,Long> {
}
