package io.hexaforce.webmusic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hexaforce.webmusic.common.WebMusicUtile;
import io.hexaforce.webmusic.model.MusicItem;
import io.hexaforce.webmusic.model.MusicItemRepository;

@Service
public class WebMusicServiceImpl implements WebMusicService {

	@Autowired
	private MusicItemRepository musicItemRepository;

	@Autowired
	private WebMusicUtile songListUtile;

	@Override
	public List<MusicItem> getYearReleasedMusicItemList(String released) {
		List<MusicItem> musicItemList = musicItemRepository.findByYearReleased(released);
		return musicItemList;
	}

	@Override
	public List<MusicItem> getArtistContainingMusicItemList(String searchWord) {
		List<MusicItem> musicItemList = musicItemRepository.findByArtistContaining(searchWord);
		return musicItemList;
	}

	@Override
	public Optional<MusicItem> getMusicItem(Integer id) {
		return musicItemRepository.findById(id);
	}
	
}
