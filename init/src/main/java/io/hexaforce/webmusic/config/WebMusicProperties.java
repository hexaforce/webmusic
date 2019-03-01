package io.hexaforce.webmusic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "io.hexaforce.webmusic", ignoreUnknownFields = true)
public class WebMusicProperties {
	private String mp3Directory;
}
