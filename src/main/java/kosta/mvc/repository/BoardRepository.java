package kosta.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.mvc.domain.FreeBoard;

public interface BoardRepository extends JpaRepository<FreeBoard, Long> {
	/**
	 * 조회수 증가 시키는 기능
	 * */
	@Query("update FreeBoard b set b.readnum = b.readnum+1 where b.bno = ?1")
	@Modifying//dml할 때 필수...
	void readnumUpdate(Long bno);
	
}
