package im.heart.shop.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.material.entity.MaterialPeriodical;
import im.heart.material.service.MaterialPeriodicalService;
import im.heart.shop.dto.OrderDto;
import im.heart.shop.dto.OrderItemDto;
import im.heart.shop.entity.Order;
import im.heart.shop.entity.OrderItem;
import im.heart.shop.repository.OrderItemRepository;
import im.heart.shop.repository.OrderRepository;
import im.heart.shop.service.OrderService;
import im.heart.shop.utils.OrderHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service(value = OrderService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor=Exception.class)
public class OrderServiceImpl extends CommonServiceImpl<Order, BigInteger> implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private MaterialPeriodicalService materialPeriodicalService;
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public Order  create(OrderDto orderDto) {
        List<OrderItemDto> items=orderDto.getItems();
        Map<BigInteger,OrderItemDto> itemDtos= Maps.newHashMap();

        for(OrderItemDto item:items){
            itemDtos.put(item.getProdId(),item);
        }
        Order order=new Order();
        BeanUtils.copyProperties(orderDto,order);
        order.setOrderId(new BigInteger(OrderHelper.generateOrderNum()));
        order.setItems(null);
        this.orderRepository.save(order);
        BigInteger orderId=order.getOrderId();
        List<MaterialPeriodical> list= this.materialPeriodicalService.findAllById(itemDtos.keySet());
        List<OrderItem> orderItems= Lists.newArrayList();
        for(MaterialPeriodical prod:list){
            OrderItem orderItem=new OrderItem();
            orderItem.setProdId(prod.getId());
            orderItem.setOrderId(orderId);
            orderItem.setProdQuantity(itemDtos.get(prod.getId()).getProdQuantity());
            orderItem.setProdUrl(prod.getCoverImgUrl());
            orderItem.setProdName(prod.getPeriodicalName());
            orderItem.setProdFinalPrice(prod.getFinalPrice());
            orderItem.setProdOriginPrice(prod.getOriginPrice());
            orderItem.setSellerId(prod.getUserId());
            orderItems.add(orderItem);
        }
        this.orderItemRepository.saveAll(orderItems);
		return order;
	}

}
