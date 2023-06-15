package kr.hs.dgsw.towerofhanoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TowerOfHanoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TowerOfHanoiApplication.class, args);
	}

}
