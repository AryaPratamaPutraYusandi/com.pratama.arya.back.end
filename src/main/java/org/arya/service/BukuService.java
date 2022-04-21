package org.arya.service;
import org.arya.model.Autor;
import org.arya.model.Buku;
import org.arya.model.Publisher;
import java.sql.SQLException;
import java.util.List;

public interface BukuService {


    Buku getDataBuku(int id) throws SQLException;

    Buku getDataBukuAll(int id) throws SQLException;

    List<Buku> getDataBukuAll() throws SQLException;


    List<Publisher> getPublisherAll() throws SQLException;

    Publisher getPublisherAll(Integer id);

    Integer simpanPublisher(Publisher publiser);

    Publisher getPublisher(Integer id);

    Autor getAuthorAll(int id)throws SQLException;

    List<Autor> getAuthorAll() throws SQLException;
    Integer simpanAuthor(Autor autor);

    Autor getAuthor(Integer id);

}
