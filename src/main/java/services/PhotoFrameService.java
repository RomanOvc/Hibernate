package services;


import dao.PhotoFrameDAO;

import models.DigitalFrame;

import models.PhotoFrame;
import models.PlainFrame;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class PhotoFrameService extends SessionUtil implements PhotoFrameDAO {
    @Override
    public void add(PhotoFrame photoFrame) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.save(photoFrame);

        closeTransactionSession();
    }

    @Override
    public void getAll() throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM photoframe ";


        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(PhotoFrame.class);


        List<PhotoFrame> photoFrameList = query.list();


        //close session with a transaction
        closeTransactionSession();
        for (var l : photoFrameList) {
//            System.out.println(l.getDigitalFrame());g
//            System.out.println(l.getPlainFrame());
            if (l.getDigitalFrame() == null) {

                StringBuilder sb = new StringBuilder();
                sb.append("PlainFrame:[ id: ").append(l.getId()).append(";")
                        .append(" name: ").append(l.getName()).append(";")
                        .append(" price: ").append(l.getPrice()).append(";")
                        .append(" color ").append(l.getColor()).append(";")
                        .append(" type: ").append(l.getType()).append(";")
                        .append(" material: ").append(l.getPlainFrame().getMaterial()).append(";")
                        .append(" width: ").append(l.getPlainFrame().getWidth()).append(";")
                        .append(" material insert: ").append(l.getPlainFrame().getMaterial_insert()).append("]");
//                        .append(System.lineSeparator());
                System.out.println(sb);
//                System.out.println(l + "" + l.getPlainFrame());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("DigitalFrame:[ id: ").append(l.getId()).append(";")
                        .append(" name: ").append(l.getName()).append(";")
                        .append(" price: ").append(l.getPrice()).append(";")
                        .append(" color ").append(l.getColor()).append(";")
                        .append(" type: ").append(l.getType()).append(";")
                        .append(" memory: ").append(l.getDigitalFrame().getMemory()).append(";")
                        .append(" size: ").append(l.getDigitalFrame().getSize()).append(";")
                        .append(" viewing angle: ").append(l.getDigitalFrame().getViewing_angle()).append("]");
                System.out.println(sb);
//                System.out.println(l + "" + l.getDigitalFrame());
            }

        }


    }


    @Override
    public PhotoFrame getById(int id) throws SQLException {
        openTransactionSession();
        String sql = "SELECT * FROM photoframe WHERE ID = :id";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(PhotoFrame.class);
        query.setParameter("id", id);
        PhotoFrame address = (PhotoFrame) query.getSingleResult();
        //close session with a transaction
        closeTransactionSession();
        return address;

    }


    @Override
    public PhotoFrame getByName(String name) throws SQLException {
        openTransactionSession();
        String sql = "SELECT * FROM photoframe WHERE name = :name";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(PhotoFrame.class);
        query.setParameter("name", name);
        PhotoFrame address = (PhotoFrame) query.getSingleResult();
        //close session with a transaction
        closeTransactionSession();
        return address;

    }


    @Override
    public void update(PhotoFrame photoFrame) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(photoFrame);
        //close session with a transaction
        closeTransactionSession();

    }

    @Override
    public void remove(PhotoFrame photoFrame) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.remove(photoFrame);

        //close session with a transaction
        closeTransactionSession();
    }
}
