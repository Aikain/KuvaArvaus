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
    @JoinColumn(name = "Image_User", unique = false)
    private User user;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "HalfImage_Image", unique = false)
    private List<HalfImage> halfImages;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private String name;

    public Image() {
        this.halfImages = new ArrayList<>();
        this.createTime = new Date(System.currentTimeMillis());
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
