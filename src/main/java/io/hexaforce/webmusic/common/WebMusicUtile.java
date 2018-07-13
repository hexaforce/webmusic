package io.hexaforce.webmusic.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hexaforce.webmusic.WebMusicProperties;

@Component
public class WebMusicUtile {

	@Autowired
	private WebMusicProperties memorableSongsProperties;
	
	public void aaa() {
		memorableSongsProperties.getTopMusicDirectory();
	}
	
}
