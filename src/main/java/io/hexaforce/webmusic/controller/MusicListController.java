package io.hexaforce.webmusic.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.hexaforce.webmusic.common.MusicListRequest;
import io.hexaforce.webmusic.model.MusicItem;
import io.hexaforce.webmusic.service.MusicListService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MusicListController {

	@Autowired
	private MusicListService musicListService;

	@GetMapping("/healthcheck")
	public ResponseEntity<String> healthcheck() {
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	@GetMapping("/")
	public String index(Map<String, Object> model) {
		log.debug("index");
		return "index";
	}

	@GetMapping("/music-list")
	public String showSongList(Map<String, Object> model) {
		log.debug("music-list");
		return "music-list";
	}
	
	@GetMapping("/search-music-list/{year}")
	public ResponseEntity<List<MusicItem>> songListSearch2(@PathVariable("year") String year) {
		List<MusicItem> musicItemList = musicListService.getYearReleasedMusicItemList(year);
		log.info("Year:{}", year);
		return new ResponseEntity<List<MusicItem>>(musicItemList, HttpStatus.OK);
	}

	@PostMapping("/search-music-list")
	public ResponseEntity<List<MusicItem>> songListSearch3(@RequestBody MusicListRequest request) {
		List<MusicItem> musicItemList = musicListService.getArtistContainingMusicItemList(request.getSearchWord());
		log.info("searchWord:{}", request.getSearchWord());
		return new ResponseEntity<List<MusicItem>>(musicItemList, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/download-music/{id}")
	public ResponseEntity<InputStreamResource> downloadMusic(@PathVariable("id") Integer id) {

		log.info("id:{}", id);
		MusicItem musicItem = musicListService.getMusicItem(id).get();

		InputStreamResource downloadResource = null;
		try {
			downloadResource = new InputStreamResource(new FileInputStream(musicItem.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
		try {
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''"
					+ URLEncoder.encode(musicItem.getFileName(), StandardCharsets.UTF_8.name()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<InputStreamResource>(downloadResource, headers, HttpStatus.OK);

	}

}
