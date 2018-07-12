package io.hexaforce.webmusic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.hexaforce.webmusic.model.MusicItem;
import io.hexaforce.webmusic.model.MusicItemRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableConfigurationProperties(WebMusicProperties.class)
public class WebMusicConfiguration implements WebMvcConfigurer {

	@Autowired
	private WebMusicProperties webMusicProperties;

	@Autowired
	private MusicItemRepository musicItemRepository;

	private MusicItem convert(MP3File mp3File) {
		MusicItem musicItem = null;
		if (mp3File.hasID3v1Tag())
			musicItem = new MusicItem(mp3File.getID3v1Tag());
		if (musicItem == null && mp3File.hasID3v2Tag())
			musicItem = new MusicItem(mp3File.getID3v2Tag());
		return musicItem;
	}

	@PostConstruct
	private void setupSongListDatabase() {
		List<MusicItem> musicItemList = new ArrayList<MusicItem>();
		for (File musicDirectory : new File(webMusicProperties.getTopMusicDirectory()).listFiles()) {
			String year = musicDirectory.getName().substring(0, 4);
			for (File musicFile : musicDirectory.listFiles()) {
				if (musicFile.getPath().endsWith(".mp3")) {
					MusicItem musicItem = null;
					try {
						musicItem = convert(new MP3File(musicFile));
						musicItem.setYear(year);
						musicItem.setAbsolutePath(musicFile.getAbsolutePath());
						musicItem.setFileName(musicFile.getName());
						musicItemList.add(musicItem);
					} catch (IOException | TagException | UnsupportedOperationException e) {
						log.error("### IOException | TagException | UnsupportedOperationException ### {}", musicFile);
					}
				} else {

				}
			}
		}
		if (!musicItemList.isEmpty()) {
			for (MusicItem x:musicItemList) {
				log.info(x.getAbsolutePath());
			}
			musicItemRepository.saveAll(musicItemList);
		}
	}
}
