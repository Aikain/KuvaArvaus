package fi.gosu.kuvaarvaus.repository;

import fi.gosu.kuvaarvaus.domain.HalfImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HalfImageRepository extends JpaRepository<HalfImage, String> {

}
