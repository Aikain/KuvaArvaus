package fi.gosu.kuvaarvaus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Image extends AbstractUUIDPersistable {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private byte[] content;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "Image_User")
    @JsonIgnore
    private User user;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "HalfImage_Image")
    private List<HalfImage> halfImages;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SingleLink singleLink;

    public Image() {
        this.halfImages = new ArrayList<>();
        this.createTime = new Date(System.currentTimeMillis());
    }

    public List<HalfImage> getHalfImages() {
        halfImages.sort(Comparator.comparingInt(HalfImage::getVisibility));
        return halfImages;
    }

    public SingleLink getSingleLink() {
        if (singleLink == null) return new SingleLink();
        return singleLink;
    }
}
