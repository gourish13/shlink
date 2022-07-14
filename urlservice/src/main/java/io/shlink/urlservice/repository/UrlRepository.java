package io.shlink.urlservice.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.shlink.urlservice.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
	
	Optional<Url> findByIdAndUrlCodeAndCreatedAtAfter(
			Long Id, String urlCode, Timestamp expectedCreatedAt);
	List<Url> findAllByUserId(Integer userId);
	@Transactional
	void deleteByIdAndUrlCode(Long id, String urlCode);
	@Transactional
	void deleteAllByUserId(Integer userId);
	@Transactional
	void deleteByCreatedAtAfter(Timestamp expiryDate);

}