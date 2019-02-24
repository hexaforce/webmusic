package io.hexaforce.webmusic;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
@EnableAutoConfiguration
public class WebMusic {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
		.sources(WebMusic.class)
		.listeners(new ApplicationPidFileWriter())
		.bannerMode(Banner.Mode.CONSOLE).run(args);
	}

}
