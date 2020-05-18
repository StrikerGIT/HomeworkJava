package ru.geekbrains.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.geekbrains.server.auth.AuthService;
import ru.geekbrains.server.auth.AuthServiceJdbcImpl;
import ru.geekbrains.server.persistance.UserRepository;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SpringContext {

    @Bean
    public UserRepository userRepository(DataSource dataSource) throws SQLException {
        return new UserRepository(dataSource);
    }

    @Bean
    public AuthServiceJdbcImpl authService(UserRepository userRepository) throws SQLException {
        return new AuthServiceJdbcImpl(userRepository);
    }

    @Bean
    public ChatServer chatServer(AuthService authService) throws SQLException {
        return new ChatServer(authService);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/network_chat");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("root");

        return ds;
    }
}
