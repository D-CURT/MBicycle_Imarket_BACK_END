package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.services.interfaces.ProductService;
import com.mbicycle.imarket.services.interfaces.ProfileService;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class ReversedOrderConverter extends AbstractConverter<OrderDTO, Order> {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SecurityService securityService;

    @Override
    public void convert(OrderDTO source, Order target) {
        target.setId(source.getId());
        target.setDelivery(source.getDelivery());
        target.setPayment(source.getPayment());

        SimpleDateFormat sdfGot = new SimpleDateFormat("M/d/yy, h:mm a", Locale.US);
        try {
            if(source.getDateOpened() != null)
                target.setDateOpened(sdfGot.parse(source.getDateOpened()));
            if(source.getDateClosed() != null)
                target.setDateClosed(sdfGot.parse(source.getDateClosed()));
            if(source.getDateGot() != null)
                target.setDateGot(sdfGot.parse(source.getDateGot()));
            if(source.getDatePaid() != null)
                target.setDatePaid(sdfGot.parse(source.getDatePaid()));
            if(source.getDateReady() != null)
                target.setDateReady(sdfGot.parse(source.getDateReady()));
            if(source.getDateSent() != null)
                target.setDateSent(sdfGot.parse(source.getDateSent()));
        }
        catch (ParseException pe) {
            pe.printStackTrace();
            throw new RuntimeException("Parse date error.");
        }

        //TODO: Move to service layer
        User user = userService.get(securityService.findLoggedInUsername());
        target.setProfile(user.getProfile());
        //

        List<Integer> productsIds = source.getProductsIds();
        if (productsIds != null) {
            target.setOrderProducts(productsIds.stream()
                                               .map(integer ->  new OrderProduct(productService.get(integer)))
                                               .collect(Collectors.toList()));
        }
    }
}
