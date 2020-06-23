package geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Inject
    private ServletContext sc;

    private Connection conn;

    @PostConstruct
    public void init() {
        String jdbcConnectionString = sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");

        try {
            conn = DriverManager.getConnection(jdbcConnectionString, username, password);

            if (this.findAll().isEmpty()) {
                this.insert(new Product(-1L, "Product1", 100));
                this.insert(new Product(-1L, "Product2", 200));
                this.insert(new Product(-1L, "Product3", 300));
                this.insert(new Product(-1L, "Product4", 300));
                this.insert(new Product(-1L, "Product5", 300));
                this.insert(new Product(-1L, "Product6", 300));
                this.insert(new Product(-1L, "Product7", 300));
                this.insert(new Product(-1L, "Product8", 300));
                this.insert(new Product(-1L, "Product9", 300));
            }
            createTableIfNotExists(conn);
        } catch (SQLException ex) {
            logger.error("", ex);
            throw new RuntimeException(ex);
        }
    }

    public ProductRepository() {
    }

    public void insert(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into products(title, coast) values (?, ?);")) {
            stmt.setString(1, product.getTitle());
            stmt.setInt(2, product.getCoast());
            stmt.execute();
        }
    }

    public void update(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update products set title = ?, coast = ? where id = ?;")) {
            stmt.setString(1, product.getTitle());
            stmt.setInt(2, product.getCoast());
            stmt.setLong(3, product.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from products where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Product findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, title, coast from products where id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getLong(1), rs.getString(2), rs.getInt(3));
            }
        }
        return new Product(-1L, "", 0);
    }

    public List<Product> findAll() throws SQLException {
        List<Product> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, title, coast from products");

            while (rs.next()) {
                res.add(new Product(rs.getLong(1), rs.getString(2), rs.getInt(3)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists products (\n" +
                    "\tid int auto_increment primary key,\n" +
                    "    title varchar(50),\n" +
                    "    coast int \n" +
                    ");");
        }
    }
}
