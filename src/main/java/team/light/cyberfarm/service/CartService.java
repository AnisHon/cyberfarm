package team.light.cyberfarm.service;

import team.light.cyberfarm.entity.Cart;

import java.util.List;

public interface CartService {

    Integer getUserCartCount(Integer userId);

    List<Cart> getUserCart(Integer userId);

    boolean addCart(Integer goodId, Integer userId, Integer count);

    boolean deleteCart(Integer cartId);

    void clearCart(Integer cartId);

}
