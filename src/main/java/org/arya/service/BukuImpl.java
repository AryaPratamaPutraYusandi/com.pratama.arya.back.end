package org.arya.service;

import org.arya.model.Buku;
import org.arya.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.arya.model.Author;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BukuImpl implements BukuService {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BukuImpl() throws SQLException {
    }


    @Override
    public Buku getDataBuku(int id) throws SQLException {
        return null;
    }

    @Override
    public Buku getDataBukuAll(int id) throws SQLException {
        String sql = "SELECT book_id, title, isbn13, language_id, num_pages, publication_date, publisher_id\n" +
                "    FROM book where book_id = ?";


        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        Buku buku = new Buku();
        while (result.next()) {
            buku.setJudulbuku(result.getString("title"));
        }
        System.out.println("buku" + buku.getJudulbuku());

        return buku;
    }

    @Override
    public List<Buku> getDataBukuAll() throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT \n" +
                "\ta.book_id, \n" +
                "\ttitle, \n" +
                "\tisbn13, \n" +
                "\tlanguage_id, \n" +
                "\tnum_pages, \n" +
                "\tpublication_date, \n" +
                "\tpublisher_id, c.author_name as pengarang\n" +
                "from\n" +
                "\t\tbook a\n" +
                "\tleft join book_author b\n" +
                "\tleft join author c on\n" +
                "\tc.author_id = b.author_id\n" +
                "\ton\n" +
                "\tb.book_id = a.book_id";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        List<Buku> listBuku = new ArrayList<>();
        while (result.next()) {
            Buku buku = new Buku();
            buku.setJudulbuku(result.getString("title"));
            buku.setPengarangbuku(result.getString("pengarang"));
            listBuku.add(buku);
        }
        System.out.println(" ==> " + listBuku.size());
        return listBuku;
    }

    /*=====================================================================================*/

    @Override
    public List<Publisher> getPublisherAll() throws SQLException {
        String sql = "SELECT publisher_id, publisher_name FROM publisher";
        return namedParameterJdbcTemplate.query(sql, (rs, rn) -> {

            Publisher publisher = new Publisher();
            publisher.setPublisherId(rs.getInt("publisher_id"));
            publisher.setPublisherName(rs.getString("publisher_name"));
            return publisher;
        });
    }

    @Override
    public org.arya.model.Publisher getPublisherAll(Integer id) {
        return null;
    }



    @Override
    public Integer simpanPublisher(Publisher publiser) {
        String sql = "INSERT INTO publisher (publisher_id, publisher_name)" + "VALUES(:publisher_id, :publisher_name)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("publisher_id", publiser.getPublisherId());
        map.addValue("publisher_name", publiser.getPublisherName());
        namedParameterJdbcTemplate.update(sql, map);
        return publiser.getPublisherId();

    }

    @Override
    public Integer simpanAuthor(Publisher publiser) {
        return null;
    }

    @Override
    public Publisher getPublisher(Integer id) {
        String sql = "SELECT publisher_id, publisher_name FROM publisher    where publisher_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, map, (rs, rn) -> {
            Publisher publisher = new Publisher();
            publisher.setPublisherId(rs.getInt("publisher_id"));
            publisher.setPublisherName(rs.getString("publisher_name"));
            return publisher;
        });

    }




//==============================================================================================================//


    @Override
    public org.arya.model.Author getAuthorAll(Integer id) {
        return null;
    }
    @Override
    public List<Author> geAuthorAll() throws SQLException {
        return null;
    }

    @Override
    public List<Author> getAuthorAll() throws SQLException {
        String sql = "SELECT author_id, author_name FROM author";
        return namedParameterJdbcTemplate.query(sql, (rs, rn) -> {

            Author author = new Author();
            author.setAuthorId(rs.getInt("author_id"));
            author.setAuthorName(rs.getString("author_name"));
            return author;
    });
    }
    @Override
    public Integer simpanAuthor(Author author) {
        String sql = "INSERT INTO author (author_id, author_name)" + "VALUES(:author_id, :author_name)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("author_id", author.getAuthorId());
        map.addValue("author_name", author.getAuthorName());
        namedParameterJdbcTemplate.update(sql, map);
        return author.getAuthorId();

    }

    @Override
    public Author getAuthor(Integer id) {
        String sql = "SELECT author_id, author_name FROM author    where author_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, map, (rs, rn) -> {
            Author author = new Author();
            author.setAuthorId(rs.getInt("author_id"));
            author.setAuthorName(rs.getString("author_name"));
            return author;
        });

    }
}






