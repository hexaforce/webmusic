package io.hexaforce.webmusic.api;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import io.hexaforce.webmusic.model.MP3PathRepository;
import io.hexaforce.webmusic.model.MP3SummaryEntity;
import io.hexaforce.webmusic.model.MP3SummaryRepository;
import lombok.extern.slf4j.Slf4j;

import static java.util.Comparator.*;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class WebmusicAipService {

	@Autowired
	private EntityManager m;
	@Autowired
	private MP3PathRepository pr;
	@Autowired
	private MP3SummaryRepository sr;

	public List<String> listYear() {
		try {
			return m.createNativeQuery("SELECT DISTINCT year FROM mp3summary ORDER BY year ASC").getResultList();
		} catch (Exception e) {
			log.warn(e.getMessage());
			return Collections.emptyList();
		}
	}

	public List<MP3SummaryEntity> findByYear(String year) {
		try {
			return sr.findByYearOrderByTrackAsc(year);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return Collections.emptyList();
		}
	}

	public List<MP3SummaryEntity> findByWord(String word) {
		try {
			log.info("######## {} ###########", word);
			List<BigInteger> mp3List = m.createNativeQuery("SELECT id FROM mp3path WHERE file_path LIKE '%" + word + "%' ORDER BY id;").getResultList();
			if (mp3List.isEmpty())
				return Collections.emptyList();

			List<Integer> ids = mp3List.stream().map(BigInteger::intValue).collect(Collectors.toList());
			List<MP3SummaryEntity> results = sr.findAllById(ids);

			results.sort(comparing(MP3SummaryEntity::getTrack));
			results.sort(comparing(MP3SummaryEntity::getYear));
			return results;

		} catch (Exception e) {
			log.warn(e.getMessage());
			return Collections.emptyList();
		}
	}

	private String newFilename;

	public String loadMP3(Integer id) throws Exception {
		pr.findById(id).ifPresent(p -> {
			sr.findById(id).ifPresent(s -> {
				try {
					modTag(new Mp3File(p.getFilePath()), s);
				} catch (Exception e) {
					log.error("load error: {}", p.toString());
				}
			});
		});
		return newFilename;
	}

	private void modTag(Mp3File mp3file, MP3SummaryEntity s) throws Exception {
		if (mp3file.hasId3v1Tag()) {
			ID3v1 t = mp3file.getId3v1Tag();
			t.setTrack(s.getTrack());
			t.setArtist(s.getArtist());
			t.setTitle(s.getTitle());
			t.setAlbum(s.getAlbum());
			t.setYear(s.getYear());
			t.setGenre(s.getGenre());
			t.setComment(s.getComment());
		}
		if (mp3file.hasId3v2Tag()) {
			ID3v2 t = mp3file.getId3v2Tag();
			t.setTrack(s.getTrack());
			t.setArtist(s.getArtist());
			t.setTitle(s.getTitle());
			t.setAlbum(s.getAlbum());
			t.setYear(s.getYear());
			t.setGenre(s.getGenre());
			t.setComment(s.getComment());
		}
		newFilename = s.getAlbum() + " " + s.getTrack() + " (" + s.getArtist() + ")" + " " + s.getTitle() + ".mp3";
		mp3file.save(newFilename);
	}

}
