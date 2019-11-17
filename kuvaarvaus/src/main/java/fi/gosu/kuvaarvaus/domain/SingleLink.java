package fi.gosu.kuvaarvaus.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aikain on 19.11.2016.
 */
@Entity
@Getter
@Setter
public class SingleLink extends AbstractUUIDPersistable {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Image image;

    @ElementCollection
    private Map<String, Date> changeoverTimes;

    public SingleLink() {
        this.changeoverTimes = new HashMap<>();
    }
}
