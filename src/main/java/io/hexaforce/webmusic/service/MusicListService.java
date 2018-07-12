package io.hexaforce.webmusic.service;

import java.util.List;
import java.util.Optional;

import io.hexaforce.webmusic.model.MusicItem;

public interface MusicListService {

	public List<MusicItem> getYearReleasedMusicItemList(String released);

	public List<MusicItem> getArtistContainingMusicItemList(String searchWord);
	
	public Optional<MusicItem> getMusicItem(Integer id);
	
}
