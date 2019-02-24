package io.hexaforce.webmusic.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.hexaforce.webmusic.common.WebMusicUtile;

@Configuration
@EnableConfigurationProperties(WebMusicProperties.class)
public class WebMusicConfiguration implements WebMvcConfigurer {

	@Autowired
	private WebMusicUtile webMusicUtile;

	@PostConstruct
	private void init() {
		webMusicUtile.setupMP3List();
	}
}
