package kosta.mvc.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kosta.mvc.domain.FreeBoard;
import kosta.mvc.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FreeBoardServiceImpl implements FreeBoardService {

	private final BoardRepository boardRep;
	
	@Override
	public List<FreeBoard> selectAll() {
		return boardRep.findAll();
	}

	@Override
	public Page<FreeBoard> selectAll(Pageable pageable) {
		return boardRep.findAll(pageable);
	}

	@Override
	public void insert(FreeBoard board) {
		boardRep.save(board);
	}

	@Override
	public FreeBoard selectBy(Long bno, boolean state) {
		if(state) {
			//조회수 증가..update
			boardRep.readnumUpdate(bno);
		}
		return  boardRep.findById(bno).orElse(null);
	}

	@Override
	public void update(FreeBoard board) {
		FreeBoard dbBoard = boardRep.findById(board.getBno()).orElse(null);
		if(dbBoard==null|| !dbBoard.getPassword().equals(board.getPassword())) {
			throw new RuntimeException("비번이나 글번호 오류로 수정실패..");
		}
		dbBoard.setSubject(board.getSubject());
		dbBoard.setContent(board.getContent());
	}

	@Override
	public void delete(Long bno, String password) {
		FreeBoard dbBoard = boardRep.findById(bno).orElse(null);
		if(dbBoard==null|| !dbBoard.getPassword().equals(password)) {
			throw new RuntimeException("비번이나 글번호 오류로 삭제실패.");
		}
		boardRep.deleteById(bno);
	}

}
