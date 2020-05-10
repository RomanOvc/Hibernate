package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "photoframe")
public class PhotoFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "color")
    private String color;

    @Column(name = "type", updatable = false)
    private String type;

    @OneToOne(mappedBy = "photoFrame")
    private DigitalFrame digitalFrame;


    @OneToOne(mappedBy = "photoFrame")
    private PlainFrame plainFrame;


    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "frame_id")
    private Set<Photo> photos;

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DigitalFrame getDigitalFrame() {
        return digitalFrame;
    }

    public void setDigitalFrame(DigitalFrame digitalFrame) {
        this.digitalFrame = digitalFrame;
    }

    public PlainFrame getPlainFrame() {
        return plainFrame;
    }

    public void setPlainFrame(PlainFrame plainFrame) {
        this.plainFrame = plainFrame;
    }


    @Override
    public String toString() {
        return "PhotoFrame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +

//                ", digitalFrame=" + digitalFrame +
//                ", plainFrame=" + plainFrame +
                '}';
    }
}
