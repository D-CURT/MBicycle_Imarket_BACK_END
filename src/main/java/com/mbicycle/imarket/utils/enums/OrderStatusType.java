package com.mbicycle.imarket.utils.enums;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.entities.Order;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Predicate;


public enum OrderStatusType {
    OPENED (order->order.getDateOpened() != null),
    REQUIRED_INTERVENTION (order->order.getDatePaid() != null && order.getDateReady() == null),
    //IMPORTANT (order->order.getDateReady() != null || order.getDateSent() != null),
    CLOSED (order->order.getDateClosed() != null);

    private final Predicate<Order> status;

    OrderStatusType(Predicate<Order> status) {
        this.status = status;
    }

    public static OrderStatusType getStatus(Order order) {
        return Arrays.stream(values())
                     .filter(orderStatusType -> orderStatusType.status.test(order))
                     .findAny()
                     .orElse(null);
    }

}

//public enum  OrderStatusType {
//    OPENED (dto -> dto.getDateOpened() != null) {
//        @Override
//        public Order apply(OrderDTO dto, Order order) {
//            order.setPayment(dto.getPayment());
//            order.setDelivery(dto.getDelivery());
//            order.setDateOpened(currentDate());
//            return order;
//        }
//    },
//    PAID (dto -> dto.getDatePaid() != null && dto.getDateReady() == null && dto.getDateSent() == null) {
//        @Override
//        public Order apply(OrderDTO dto, Order order) {
//            order.setDatePaid(currentDate());
//            return order;
//        }
//    },
//    SENT (dto -> dto.getDateSent() != null && dto.getDateGot() == null) {
//        @Override
//        public Order apply(OrderDTO dto, Order order) {
//            order.setDateSent(currentDate());
//            return order;
//        }
//    },
//    GOT (dto -> dto.getDateGot() != null && dto.getDateClosed() == null) {
//
//        @Override
//        public Order apply(OrderDTO dto, Order order) {
//            order.setDateGot(currentDate());
//            return order;
//        }
//    },
//    READY (dto -> dto.getDateReady() != null && dto.getDateClosed() == null) {
//        @Override
//        public Order apply(OrderDTO dto, Order order) {
//            order.setDateReady(currentDate());
//            return order;
//        }
//    },
//    CLOSED (dto -> dto.getDateClosed() != null) {
//        @Override
//        public Order apply(OrderDTO dto, Order order) {
//            order.setDateClosed(currentDate());
//            return order;
//        }
//    };
//
//    private final Predicate<OrderDTO> status;
//
//    OrderStatusType(Predicate<OrderDTO> status) {
//        this.status = status;
//    }
//
//    public abstract Order apply(OrderDTO dto, Order order);
//
//    public static OrderStatusType status(OrderDTO dto) {
//        return Arrays.stream(values())
//                     .filter(orderStatusType -> orderStatusType.status.test(dto))
//                     .findAny()
//                     .orElse(null);
//    }
//
//    private static Date currentDate() {
//        return new Date(System.currentTimeMillis());
//    }
//}
