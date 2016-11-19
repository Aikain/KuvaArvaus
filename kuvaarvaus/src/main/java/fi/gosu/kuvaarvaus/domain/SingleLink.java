package fi.gosu.kuvaarvaus.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aikain on 19.11.2016.
 */
@Entity
public class SingleLink extends AbstractUUIDPersistable {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Image image;

    @ElementCollection
    private Map<String, Date> changeoverTimes;

    public SingleLink() {
        this.changeoverTimes = new HashMap<>();
    }

    public Image getImage() {
        return image;
    }
    public Map<String, Date> getChangeoverTimes() {
        return changeoverTimes;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public void setChangeoverTimes(Map<String, Date> changeoverTimes) {
        this.changeoverTimes = changeoverTimes;
    }
}
