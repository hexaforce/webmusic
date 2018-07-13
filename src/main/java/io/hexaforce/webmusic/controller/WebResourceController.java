package io.hexaforce.webmusic.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
class WebResourceController {

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

}
