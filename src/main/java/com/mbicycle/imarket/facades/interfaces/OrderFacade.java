package com.mbicycle.imarket.facades.interfaces;

<<<<<<< HEAD
import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.dto.ProfileDTO;
=======
import com.mbicycle.imarket.beans.dto.OrderDTO;
>>>>>>> 6703bc28637e5ae04c375c3bd4f20c186aec383b
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderFacade {
    boolean add(OrderDTO orderDTO);

    boolean update(OrderDTO orderDTO);

    boolean delete(OrderDTO orderDTO);

    List<OrderDTO> getAll();

    OrderDTO get(ProfileDTO profileDTO);
}
