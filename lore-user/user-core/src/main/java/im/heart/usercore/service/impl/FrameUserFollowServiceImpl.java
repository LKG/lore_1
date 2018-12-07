package im.heart.usercore.service.impl;

import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.usercore.entity.FrameUserFollow;
import im.heart.usercore.entity.FrameUserRelate;
import im.heart.usercore.repository.FrameUserFollowRepository;
import im.heart.usercore.repository.FrameUserRelateRepository;
import im.heart.usercore.service.FrameUserFollowService;
import im.heart.usercore.service.FrameUserRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Service(value = FrameUserFollowService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS)
public class FrameUserFollowServiceImpl extends CommonServiceImpl<FrameUserFollow, BigInteger> implements FrameUserFollowService{
	@Autowired
	private FrameUserFollowRepository frameUserFollowRepository;


	@Override
	public List<FrameUserFollow> saveAll(Iterable<FrameUserFollow> entities) {
		return this.frameUserFollowRepository.saveAll(entities);
	}

	@Override
	public List<FrameUserFollow> findByUserId(BigInteger userId) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
		Specification<FrameUserFollow> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserFollow.class);
		return this.frameUserFollowRepository.findAll(spec);
	}

	@Override
	public List<FrameUserFollow> findByUserIdAndType(BigInteger userId, String relateType) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
		filters.add(new SearchFilter("type", SearchFilter.Operator.EQ, relateType));
		Specification<FrameUserFollow> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserFollow.class);
		return this.frameUserFollowRepository.findAll(spec);
	}

	@Override
	public FrameUserFollow findByUserIdAndRelateId(BigInteger userId, BigInteger relateId) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
		filters.add(new SearchFilter("relateId", SearchFilter.Operator.EQ, relateId));
		Specification<FrameUserFollow> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserFollow.class);
		return this.frameUserFollowRepository.findOne(spec).get();
	}
	@Override
	public FrameUserFollow findByUserIdAndRelateIdAndType(BigInteger userId, BigInteger relateId, String relateType) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
		filters.add(new SearchFilter("relateId", SearchFilter.Operator.EQ, relateId));
		filters.add(new SearchFilter("type", SearchFilter.Operator.EQ, relateType));
		Specification<FrameUserFollow> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserFollow.class);
		return this.frameUserFollowRepository.findOne(spec).get();
	}


}
