package models;

import javax.persistence.*;

@Entity
@Table(name = "digitalframe")
public class DigitalFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "memory")
    private String memory;


    @Column(name = "viewing_angle")
    private String viewing_angle;

    @Column(name = "size")
    private String size;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photoframe_id", updatable = false)
    private PhotoFrame photoFrame;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getViewing_angle() {
        return viewing_angle;
    }

    public void setViewing_angle(String viewing_angle) {
        this.viewing_angle = viewing_angle;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public PhotoFrame getPhotoFrame() {
        return photoFrame;
    }

    public void setPhotoFrame(PhotoFrame photoFrame) {
        this.photoFrame = photoFrame;
    }

    @Override
    public String toString() {
        return "DigitalFrame{" +
                "id=" + id +
                ", memory='" + memory + '\'' +
                ", viewing_angle='" + viewing_angle + '\'' +
                ", size='" + size + '\'' +
                ", photoFrame=" + photoFrame +
                '}';

    }
}
