package io.hexaforce.webmusic.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MP3SummaryRepository extends JpaRepository<MP3SummaryEntity, Integer> {
	List<MP3SummaryEntity> findByYearOrderByTrackAsc(String year);
}
