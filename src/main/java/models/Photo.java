package models;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "photo_name")
    public String photo_name;

    @ManyToOne
    @JoinColumn(name = "frame_id")
    public PhotoFrame photoFrame;

    public PhotoFrame getPhotoFrame() {
        return photoFrame;
    }

    public void setPhotoFrame(PhotoFrame photoFrame) {
        this.photoFrame = photoFrame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", name_photo='" + photo_name + '\'' +
                ", photoFrame=" + photoFrame +
                '}';

    }

}



