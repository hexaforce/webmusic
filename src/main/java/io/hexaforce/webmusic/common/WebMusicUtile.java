package io.hexaforce.webmusic.common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import io.hexaforce.webmusic.config.WebMusicProperties;
import io.hexaforce.webmusic.model.MP3PathEntity;
import io.hexaforce.webmusic.model.MP3PathRepository;
import io.hexaforce.webmusic.model.MP3SummaryEntity;
import io.hexaforce.webmusic.model.MP3SummaryRepository;

@Component
public class WebMusicUtile {

	@Autowired
	private WebMusicProperties musicProperties;

	public String getTopMusicDirectory() {
		return musicProperties.getTopMusicDirectory();
	}

	@Autowired
	private MP3PathRepository mp3PathRepository;
	@Autowired
	private MP3SummaryRepository mp3SummaryRepository;

	public void setupMP3List() {

		List<MP3PathEntity> mp3List = new ArrayList<>();

		for (File musicDirectory : new File(getTopMusicDirectory()).listFiles()) {

			if (musicDirectory.listFiles() == null)
				continue;
				
			for (File musicFile : musicDirectory.listFiles()) {
				if (musicFile.getPath().endsWith(".mp3")) {
					MP3PathEntity mp3Entity = getMP3Entity(musicFile.getAbsolutePath());
					if (mp3Entity != null) {
						mp3List.add(mp3Entity);
					}
				}
			}

			mp3List = mp3PathRepository.saveAll(mp3List);

			List<MP3SummaryEntity> summaryList = getMP3Summary(mp3List);
			mp3SummaryRepository.saveAll(summaryList);

			mp3List.clear();

		}

	}

	private MP3PathEntity getMP3Entity(String filePath) {
		return MP3PathEntity.builder().filePath(filePath).build();
	}

	private List<MP3SummaryEntity> getMP3Summary(List<MP3PathEntity> mp3List) {

		List<MP3SummaryEntity> summaryList = new ArrayList<>();
		for (MP3PathEntity path : mp3List) {

			Mp3File mp3file;
			try {
				mp3file = new Mp3File(path.getFilePath());
				ID3v1 tag = mp3file.getId3v1Tag();

				MP3SummaryEntity e = MP3SummaryEntity.builder()

						.lengthInSeconds(mp3file.getLengthInSeconds())
						.bitrate(mp3file.getBitrate())
						.vbr(mp3file.isVbr())
						.sampleRate(mp3file.getSampleRate())

						.genreDescription(tag.getGenreDescription())
						.artist_sjis(convert(tag.getArtist()))
						.title_sjis(convert(tag.getTitle()))
						.album_sjis(convert(tag.getAlbum()))

						.track(tag.getTrack())
						.artist(tag.getArtist())
						.title(tag.getTitle())
						.album(tag.getAlbum())
						.year(tag.getYear())
						.genre(tag.getGenre())
						.comment(tag.getComment())

						.build();

				e.setId(path.getId());
				summaryList.add(e);

			} catch (UnsupportedTagException | InvalidDataException | IOException e1) {
				e1.printStackTrace();
				continue;
			}

		}

		return summaryList;
	}

	private String convert(String x) {
		try {
			return new String(x.getBytes("ISO-8859-1"), "Shift_JIS");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return x;
	}

}
