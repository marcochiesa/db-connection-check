package org.getmarco.dbconncheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class DbconncheckApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DbconncheckApplication.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DbconncheckApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Checking connection");
		jdbcTemplate.execute("SELECT 1");
		log.info("ssl status ...");
		List<String> list = jdbcTemplate.query("show status like '%ssl%'",
		  (rs, rowNum) -> {return rs.getString(1) + ": " + rs.getString(2);});
		list.forEach(log::info);
	}
}
