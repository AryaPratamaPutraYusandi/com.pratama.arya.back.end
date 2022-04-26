package org.arya.service;
import org.arya.model.Author;
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

    List<Author> geAuthorAll() throws SQLException;


    Integer simpanPublisher(Publisher publiser);

    Integer simpanAuthor(Publisher publiser);

    Publisher getPublisher(Integer id);

    Author getAuthorAll(Integer id);

    List<Author>getAuthorAll() throws SQLException;

    Integer simpanAuthor(Author author);

    Author getAuthor(Integer id);



}
