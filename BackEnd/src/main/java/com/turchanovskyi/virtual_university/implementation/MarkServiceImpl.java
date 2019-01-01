package com.turchanovskyi.virtual_university.implementation;

import com.turchanovskyi.virtual_university.interfaces.MarkService;
import com.turchanovskyi.virtual_university.model.Mark;
import com.turchanovskyi.virtual_university.repository.MarkRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Service("markService")
public class MarkServiceImpl implements MarkService {

	private final MarkRepository markRepository;

	public MarkServiceImpl(MarkRepository markRepository) {
		this.markRepository = markRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<Mark> findAll() {
		return markRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Mark findById(Long id) {
		return markRepository.findById(id).get();
	}

	@Transactional
	@Override
	public Mark save(Mark mark) {
		return markRepository.save(mark);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		markRepository.deleteById(id);
	}
}
