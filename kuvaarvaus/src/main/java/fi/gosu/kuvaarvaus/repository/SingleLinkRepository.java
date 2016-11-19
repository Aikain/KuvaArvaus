package fi.gosu.kuvaarvaus.repository;

import fi.gosu.kuvaarvaus.domain.SingleLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleLinkRepository extends JpaRepository<SingleLink, String> {

}
