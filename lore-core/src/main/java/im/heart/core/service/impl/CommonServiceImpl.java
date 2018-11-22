package im.heart.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import im.heart.core.service.CommonService;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author gg
 * @desc  共用服务实现类
 * @param <T>
 * @param <ID>
 */
public class CommonServiceImpl<T, ID extends Serializable> implements 	CommonService<T, ID> {
	@Autowired
	private JpaSpecificationExecutor<T> jpaSpecificationExecutor;
	@Autowired
	private CrudRepository<T, ID> crudRepository;

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return this.jpaSpecificationExecutor.findAll(spec,pageable);
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		return this.jpaSpecificationExecutor.findAll(spec);
	}

	@Override
	public Long count(Specification<T> spec) {
		return this.jpaSpecificationExecutor.count(spec);
	}

	@Override
	public Optional<T> findById(ID id) {
		return this.crudRepository.findById(id);
	}
	@Override
	public boolean existsById(ID id) {
		return this.crudRepository.existsById(id);
	}

	@Override
	public T  save(T entity) {
		return this.crudRepository.save(entity);
	}

	@Override
	public void deleteById(ID id) {
		this.crudRepository.deleteById(id);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		this.crudRepository.deleteAll(entities);
	}

}
