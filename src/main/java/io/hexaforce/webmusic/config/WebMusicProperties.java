package io.hexaforce.webmusic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.song", ignoreUnknownFields = true)
public class WebMusicProperties {
	private String topMusicDirectory;
}
