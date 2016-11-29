package fi.gosu.kuvaarvaus.repository;

import fi.gosu.kuvaarvaus.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {

    Image findBySingleLink_id(String id);
}
