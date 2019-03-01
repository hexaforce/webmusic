package io.hexaforce.webmusic.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.hexaforce.webmusic.model.MP3SummaryEntity;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
class WebmusicAipController {

	@Autowired
	private WebmusicAipService service;

	@CrossOrigin
	@GetMapping("/healthcheck")
	public ResponseEntity<String> healthcheck() {
		return new ResponseEntity<String>(HttpStatus.OK.name(), HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/list/year")
	public ResponseEntity<List<String>> listYear() {
		List<String> result = service.listYear();
		HttpStatus status = result.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		return new ResponseEntity<List<String>>(result, status);
	}

	@CrossOrigin
	@GetMapping("/search/year/{year}")
	public ResponseEntity<List<MP3SummaryEntity>> findByYear(@PathVariable("year") String year) {
		List<MP3SummaryEntity> result = service.findByYear(year);
		HttpStatus status = result.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		return new ResponseEntity<List<MP3SummaryEntity>>(result, status);
	}

	@CrossOrigin
	@PostMapping("/search/word")
	public ResponseEntity<List<MP3SummaryEntity>> findByWord(@RequestBody SearchWord request) {
		List<MP3SummaryEntity> result = service.findByWord(request.getWord());
		HttpStatus status = result.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		return new ResponseEntity<List<MP3SummaryEntity>>(result, status);
	}

	@CrossOrigin
	@Synchronized
	@SuppressWarnings("unchecked")
	@GetMapping("/mp3/{id}")
	public ResponseEntity<InputStreamResource> downloadMP3(@PathVariable("id") Integer id) throws Exception {

		String newFilename = service.loadMP3(id);
		log.info("ID:{} Filename:{}", id, newFilename);

		if (newFilename == null)
			return (ResponseEntity<InputStreamResource>) ResponseEntity.notFound();
		InputStreamResource mp3Resource = new InputStreamResource(new DeleteOnCloseFileInputStream(newFilename));

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
		httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, CONTENT_ATTACHMENT_DISPOSITION(newFilename));
		return new ResponseEntity<InputStreamResource>(mp3Resource, httpHeaders, HttpStatus.OK);
	}

	static String CONTENT_ATTACHMENT_DISPOSITION(String fileName) {
		StringBuffer buffer = new StringBuffer("attachment; ");
		buffer.append("filename=\"" + fileName + "\"; ");
		try {
			String encodeFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
			buffer.append("filename*=UTF-8''" + encodeFileName.replace("+", "%20"));
		} catch (Exception e) {
			log.error("invalid fileName: {}", fileName);
		}
		return buffer.toString();
	}

}
