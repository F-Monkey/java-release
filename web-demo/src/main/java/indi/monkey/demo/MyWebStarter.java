package indi.monkey.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyWebStarter {
	
	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("release~~~");
			}
		});
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(MyWebStarter.class, args);
	}
	
	
	@RequestMapping("/exit")
	public void exit() {
		System.exit(0);
	}
}
