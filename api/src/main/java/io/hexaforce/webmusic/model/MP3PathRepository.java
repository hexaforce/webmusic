package io.hexaforce.webmusic.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MP3PathRepository extends JpaRepository<MP3PathEntity, Integer> {
	@Query("SELECT c FROM MP3PathEntity c WHERE c.filePath LIKE '%:word%'")
	List<MP3PathEntity> findByLikeWordAndStream(String word);
}
