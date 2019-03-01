package io.hexaforce.webmusic.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
public class MP3SummaryEntity {
    @Id
    private Integer id;
	private long lengthInSeconds;
	private int bitRate;
	private int sampleRate;
	@Enumerated(EnumType.STRING)
	private BitRate vbr;
	private String track;
	private String artist;
	private String title;
	private String album;
	private String year;
	private int genre;
	private String genreDescription;
	private String comment;

}
