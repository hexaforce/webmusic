package io.hexaforce.webmusic.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MP3PathRepository extends JpaRepository<MP3PathEntity, Integer> {

}
