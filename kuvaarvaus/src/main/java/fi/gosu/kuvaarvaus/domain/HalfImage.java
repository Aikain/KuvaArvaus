package fi.gosu.kuvaarvaus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Getter
@Setter
public class HalfImage extends AbstractUUIDPersistable {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private byte[] content;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "HalfImage_Image", unique = false)
    @JsonIgnore
    private Image image;

    @Min(0)
    @Max(100)
    private int visibility;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public HalfImage() {
        createTime = new Date(System.currentTimeMillis());
    }

    public Date getSingleLinkTime() {
        return this.image.getSingleLink().getChangeoverTimes().get(this.getId());
    }
}
