package com.useage.jdk8.filter;

import com.useage.jdk8.stream.Customer;
import com.useage.jdk8.stream.Order;
import com.useage.jdk8.stream.OrderItem;
import sun.jvm.hotspot.utilities.Assert;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class FileterDemo {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        //最近半年的金额大于40的订单
        orders.stream()
                .filter(Objects::nonNull) //过滤null值
                .filter(order -> order.getPlacedAt().isAfter(LocalDateTime.now().minusMonths(6))) //最近半年的订单
                .filter(order -> order.getTotalPrice() > 40) //金额大于40的订单
                .forEach(System.out::println);

        //计算所有订单商品数量
        //通过两次遍历实现
        LongAdder longAdder = new LongAdder();
        orders.stream().forEach(order ->
                order.getOrderItemList().forEach(orderItem -> longAdder.add(orderItem.getProductQuantity())));

        //使用两次mapToLong+sum方法实现
//        assertThat(longAdder.longValue(), is(orders.stream().mapToLong(order ->
//                order.getOrderItemList().stream()
//                        .mapToLong(OrderItem::getProductQuantity).sum()).sum()));


        //直接展开订单商品进行价格统计
        System.out.println(orders.stream()
                .flatMap(order -> order.getOrderItemList().stream())
                .mapToDouble(item -> item.getProductQuantity() * item.getProductPrice()).sum());

        //另一种方式flatMap+mapToDouble=flatMapToDouble
        System.out.println(orders.stream()
                .flatMapToDouble(order ->
                        order.getOrderItemList()
                                .stream().mapToDouble(item -> item.getProductQuantity() * item.getProductPrice()))
                .sum());


        //大于50的订单,按照订单价格倒序前5
        orders.stream().filter(order -> order.getTotalPrice() > 50)
                .sorted(comparing(Order::getTotalPrice).reversed())
                .limit(5)
                .forEach(System.out::println);


        //去重的下单用户
        System.out.println(orders.stream().map(order -> order.getCustomerName()).distinct().collect(joining(",")));

        //所有购买过的商品
        System.out.println(orders.stream()
                .flatMap(order -> order.getOrderItemList().stream())
                .map(OrderItem::getProductName)
                .distinct().collect(joining(",")));


        //按照下单时间排序，查询前2个订单的顾客姓名和下单时间
        orders.stream()
                .sorted(comparing(Order::getPlacedAt))
                .map(order -> order.getCustomerName() + "@" + order.getPlacedAt())
                .limit(2).forEach(System.out::println);
        //按照下单时间排序，查询第3和第4个订单的顾客姓名和下单时间
        orders.stream()
                .sorted(comparing(Order::getPlacedAt))
                .map(order -> order.getCustomerName() + "@" + order.getPlacedAt())
                .skip(2).limit(2).forEach(System.out::println);


//生成一定位数的随机字符串
//        System.out.println(random.ints(48, 122)
//                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
//                .mapToObj(i -> (char) i)
//                .limit(20)
//                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
//                .toString());

//所有下单的用户，使用toSet去重后实现字符串拼接
        System.out.println(orders.stream()
                .map(order -> order.getCustomerName()).collect(toSet())
                .stream().collect(joining(",", "[", "]")));

//用toCollection收集器指定集合类型
        System.out.println(orders.stream().limit(2).collect(toCollection(LinkedList::new)).getClass());

//使用toMap获取订单ID+下单用户名的Map
        orders.stream()
                .collect(toMap(Order::getId, Order::getCustomerName))
                .entrySet().forEach(System.out::println);

//使用toMap获取下单用户名+最近一次下单时间的Map
        orders.stream()
                .collect(toMap(Order::getCustomerName, Order::getPlacedAt, (x, y) -> x.isAfter(y) ? x : y))
                .entrySet().forEach(System.out::println);

//订单平均购买的商品数量
        System.out.println(orders.stream().collect(averagingInt(order ->
                order.getOrderItemList().stream()
                        .collect(summingInt(OrderItem::getProductQuantity)))));


//根据是否有下单记录进行分区
//        System.out.println(Customer.getData().stream().collect(
//                partitioningBy(customer -> orders.stream().mapToLong(Order::getCustomerId)
//                        .anyMatch(id -> id == customer.getId()))));
    }

    public static void fillOrder(List<Order> orders) {
        Order order = new Order();
    }
}
