package fi.gosu.kuvaarvaus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;
import javax.persistence.*;

@Entity
public class Image extends AbstractUUIDPersistable {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<HalfImage> halfImages;

    public Image() {
        this.halfImages = new ArrayList<>();
    }

    @JsonIgnore
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<HalfImage> getHalfImages() {
        Collections.sort(halfImages, new Comparator<HalfImage>() {
            @Override
            public int compare(HalfImage h1, HalfImage h2) {
                return h1.getVisibility() - h2.getVisibility(); // Ascending
            }

        });
        return halfImages;
    }

    public void setHalfImages(List<HalfImage> halfImages) {
        this.halfImages = halfImages;
    }

}
