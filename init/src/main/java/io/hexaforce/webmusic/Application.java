package io.hexaforce.webmusic;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.mpatric.mp3agic.Mp3File;

import io.hexaforce.webmusic.config.WebMusicProperties;
import io.hexaforce.webmusic.model.BitRate;
import io.hexaforce.webmusic.model.MP3PathEntity;
import io.hexaforce.webmusic.model.MP3PathRepository;
import io.hexaforce.webmusic.model.MP3SummaryEntity;
import io.hexaforce.webmusic.model.MP3SummaryRepository;
import lombok.extern.slf4j.Slf4j;

@EnableConfigurationProperties(WebMusicProperties.class)
@SpringBootApplication
@Slf4j
public class Application {

	private final AtomicInteger increment = new AtomicInteger(10001);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner main(MP3PathRepository pr, MP3SummaryRepository sr, WebMusicProperties P) {
		return (args) -> {
			for (File d : new File(P.getMp3Directory()).listFiles()) {

				String year = d.getName().substring(0, 4);
				if (d.listFiles() == null)
					continue;

				for (File f : d.listFiles()) {
					if (f.getPath().endsWith(".mp3")) {
						MP3PathEntity entity = getMP3Entity(f.getAbsolutePath());
						entity.setId(increment.incrementAndGet());
						MP3SummaryEntity summary = getMP3Summary(pr.save(entity), year);
						if (summary != null)
							sr.save(summary);
					}
				}

			}
		};
	}

	private MP3PathEntity getMP3Entity(String filePath) {
		try {
			return MP3PathEntity.builder().filePath(new String(filePath.getBytes("utf-8"))).build();
		} catch (Exception e) {
			log.error("FilePath1 : {}", filePath);
			e.printStackTrace();
			return null;
		}
	}

	private MP3SummaryEntity getMP3Summary(MP3PathEntity path, String year) {

		Mp3File mp3file;
		try {
			mp3file = new Mp3File(path.getFilePath());

			String aaa = new File(path.getFilePath()).getName();
			String track = null;
			String title = null;
			String artist = null;
			if (Integer.parseInt(year) < 2010) {
				String bbb = aaa.substring(aaa.indexOf("_") + 1);
				track = bbb.substring(0, bbb.indexOf("(")).replace(".", "");
				String ddd = bbb.substring(bbb.indexOf("(") + 1);
				artist = ddd.substring(0, ddd.indexOf(")"));
				title = ddd.substring(ddd.indexOf(")") + 1).replace(".mp3", "");
			} else {
				String[] bbb = aaa.split(" - ");
				track = bbb[0].substring(0, bbb[0].indexOf(' ')).trim();
				if (track.indexOf(("_")) != -1)
					track = track.substring(track.indexOf("_") + 1);
				track = track.replace("(", "-").replace(")", "").replace("#", "");
				title = bbb[0].substring(bbb[0].indexOf(' ')).trim();
				artist = bbb[1].replace("(", "").replace(")", "").replace(".mp3", "").trim();
			}

			String temp = track;
			if (track.indexOf("-") != -1) {
				temp = track.split("-")[0];
			}
			switch (temp.length()) {
			case 1:
				track = "0" + track;
			case 2:
				track = "0" + track;
			}

			String album = year + "-BEST100";
			int genre = 146;
			String genreDescription = "JPop";
			String comment = "Hexaforce on demand mp3 stream service.";

			MP3SummaryEntity entity = MP3SummaryEntity.builder()

					.lengthInSeconds(mp3file.getLengthInSeconds()).bitRate(mp3file.getBitrate())
					.sampleRate(mp3file.getSampleRate()).vbr(mp3file.isVbr() ? BitRate.VBR : BitRate.CBR)

					.track(track).artist(artist).title(title).album(album).year(year).genre(genre)
					.genreDescription(genreDescription).comment(comment)

					.build();

			entity.setId(path.getId());

			log.info("success : {}", path.getFilePath());
			return entity;

		} catch (Exception e) {
			log.error("FilePath2 : {}", path.getFilePath());
			e.printStackTrace();
		}
		return null;

	}

}
