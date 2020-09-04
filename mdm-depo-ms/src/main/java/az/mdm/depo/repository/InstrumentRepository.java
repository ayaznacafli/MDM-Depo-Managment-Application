package az.mdm.depo.repository;

import az.mdm.depo.model.Category;
import az.mdm.depo.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument,Long> {

    List<Instrument> findInstrumentsByCategory(Category category);
}
