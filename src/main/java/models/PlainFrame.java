package models;

import javax.persistence.*;

@Entity
@Table(name = "plainframe")
public class PlainFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "material")
    private String material;

    @Column(name = "width")
    private String width;

    @Column(name = "material_insert")
    private String material_insert;

    @OneToOne
    @JoinColumn(name = "photoframe_id", updatable = false)
    private PhotoFrame photoFrame;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getMaterial_insert() {
        return material_insert;
    }

    public void setMaterial_insert(String material_insert) {
        this.material_insert = material_insert;
    }

    public PhotoFrame getPhotoFrame() {
        return photoFrame;
    }

    public void setPhotoFrame(PhotoFrame photoFrame) {
        this.photoFrame = photoFrame;
    }

    @Override
    public String toString() {
        return "PlainFrame{" +
                "id=" + id +
                ", material='" + material + '\'' +
                ", width='" + width + '\'' +
                ", material_insert='" + material_insert + '\'' +
                ", photoFrame=" + photoFrame +
                '}';

    }

}
