package fi.gosu.kuvaarvaus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
public class HalfImage extends AbstractUUIDPersistable {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "HalfImage_Image", unique = false)
    private Image image;
    @Min(0)
    @Max(100)
    private int visibility;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public HalfImage() {
        createTime = new Date(System.currentTimeMillis());
    }

    @JsonIgnore
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @JsonIgnore
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSingleLinkTime() {
        return this.image.getSingleLink().getChangeoverTimes().get(this.getId());
    }
}
