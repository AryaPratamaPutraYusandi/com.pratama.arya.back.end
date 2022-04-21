package org.arya.service;

import org.arya.model.Autor;
import org.arya.model.Buku;
import org.arya.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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
    public Autor getAuthorAll(int id) throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT author_id, author_name\n" + " FROM author =? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        Autor autor = new Autor();
        while (result.next()) {
            autor.setAuthorId(result.getInt("id"));
            autor.setAuthorName(result.getString("pengarang"));
        }
        System.out.println("Id" + autor.getAuthorId());
        System.out.println("pengarang" + autor.getAuthorName());
        return autor;
    }

    @Override
   /* public List<Autor> getAuthorAll() throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT author_id\n" +
                "author_name\n" +
                "authorId, c.author_id as id\n" +
                "authorName,c.author_name as pengarang" +
                "form \n" +
                "author a\n" +
                "left join c.author_id\n" +
                "left join c.author_name\n";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        List<Autor> listAutor = new ArrayList<>();
        while (result.next()) {
            Autor autor = new Autor();
            autor.setAuthorId(result.getInt("id"));
            autor.setAuthorName(result.getString("pengarang"));
            listAutor.add(autor);
        }
        System.out.println(" ==> " + listAutor.size());
        return listAutor;

    }*/

    public List<Autor> getAuthorAll() throws SQLException {
        String sql = "SELECT publisher_id, publisher_name FROM publisher";
        return namedParameterJdbcTemplate.query(sql, (rs, rn) -> {

            Autor autor = new Autor();
            autor.setAuthorId(rs.getInt("author_id"));
            autor.setAuthorName(rs.getString("author_name"));
            return autor;

        });
    }

    public org.arya.model.Autor getAuthorAll() {
        return null;
    }

@Override
        public Integer simpanAuthor(Autor autor){
            String sql = "INSERT INTO author (author_id, author_name)" + "VALUES(:author_id, :author_name)";
            MapSqlParameterSource map = new MapSqlParameterSource();
            map.addValue("author_id", autor.getAuthorId());
            map.addValue("author_name", autor.getAuthorName());
            namedParameterJdbcTemplate.update(sql, map);
            return simpanAuthor();

    }
@Override
    public Autor getAuthor(Integer id) {
        String sql = "SELECT author_id, author_name FROM author    where author_id = :author";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("author", id);
        return namedParameterJdbcTemplate.queryForObject(sql, map, (rs, rn) -> {
            Publisher publisher = new Publisher();
            publisher.setPublisherId(rs.getInt("author_id"));
            publisher.setPublisherName(rs.getString("author_name"));
            return autor.getAuthor();

            });
    }
}








