package services;

import dao.PhotoDAO;
import models.DigitalFrame;
import models.Photo;
import models.PhotoFrame;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class PhotoService extends SessionUtil implements PhotoDAO {
    @Override
    public void add(Photo photo) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(photo);
        closeTransactionSession();
    }

    @Override
    public void getAll() throws SQLException {
        openTransactionSession();
        String sql = "SELECT * FROM photo ";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Photo.class);
        List<Photo> photoList = query.list();
        //close session with a transaction
        closeTransactionSession();
        for (var l : photoList) {
            StringBuilder sb = new StringBuilder();
            sb.append("Photo:[ id: ").append(l.getId()).append(";")
                    .append(" name_image: ").append(l.getPhoto_name()).append(";")
                    .append(" name: ").append(l.getPhotoFrame().getName()).append(";");
            System.out.println(sb);
        }
    }


    @Override
    public Photo getByIdPhoto(int id) throws SQLException {
        return null;
    }

    @Override
    public void getByName(String name) throws SQLException {
        openTransactionSession();
//        SELECT photoframe.name, photo.photo_name FROM photoframe_hiber.photoframe inner join photoframe_hiber.photo on photoframe_hiber.photoframe.id = photoframe_hiber.photo.frame_id where name=:name;
        String sql = "SELECT * FROM photo inner join photoframe on photo.frame_id = photoframe.id where photoframe.name='" + name + "'";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Photo.class);
        List<Photo> photoList = query.list();
        //close session with a transaction
        closeTransactionSession();
        for (var l : photoList) {
            StringBuilder sb = new StringBuilder();
            sb.append("Photo:[ ")
                    .append(" name_image: ").append(l.getPhoto_name()).append(";")
                    .append(" name: ").append(l.getPhotoFrame().getName()).append(";]");
            System.out.println(sb);
        }
    }

    @Override
    public void remove(Photo photo) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.remove(photo);

        //close session with a transaction
        closeTransactionSession();
    }

}
