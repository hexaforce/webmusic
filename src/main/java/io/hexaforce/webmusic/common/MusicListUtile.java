package io.hexaforce.webmusic.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hexaforce.webmusic.WebMusicProperties;

@Component
public class MusicListUtile {

	@Autowired
	private WebMusicProperties webMusicProperties;
	
	public String getTopMusicDirectory() {
		return webMusicProperties.getTopMusicDirectory();
	}
	
}
