package fi.gosu.kuvaarvaus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Image extends AbstractUUIDPersistable {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    @ManyToOne
    private User user;
    @OneToMany
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
