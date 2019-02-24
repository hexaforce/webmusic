package io.hexaforce.webmusic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mp3summary")
public class MP3SummaryEntity extends BaseEntity {

	private long lengthInSeconds;
	private int bitrate;
	private boolean vbr;
	private int sampleRate;

	private String genreDescription;
	private String artist_sjis;
	private String title_sjis;
	private String album_sjis;

	private String track;
	private String artist;
	private String title;
	private String album;
	private String year;
	private int genre;
	private String comment;

}
