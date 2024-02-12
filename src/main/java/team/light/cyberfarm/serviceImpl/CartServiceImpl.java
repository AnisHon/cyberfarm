package team.light.cyberfarm.serviceImpl;

import org.apache.ibatis.session.SqlSession;
import team.light.cyberfarm.entity.Cart;
import team.light.cyberfarm.mapping.CartMapping;
import team.light.cyberfarm.service.CartService;
import team.light.cyberfarm.tool.SqlSessionGetter;

import java.util.List;

public class CartServiceImpl implements CartService {


    @Override
    public Integer getUserCartCount(Integer userId) {
        SqlSession session = SqlSessionGetter.getSession();
        CartMapping mapper = session.getMapper(CartMapping.class);
        Integer count = mapper.selectUserCartCountById(userId);
        session.close();
        return count;
    }

    @Override
    public List<Cart> getUserCart(Integer userId) {
        SqlSession session = SqlSessionGetter.getSession();
        CartMapping mapper = session.getMapper(CartMapping.class);
        List<Cart> carts = mapper.selectUserCartById(userId);
        session.close();
        return carts;
    }

    @Override
    public boolean addCart(Integer goodsId, Integer userId, Integer count) {

        SqlSession session = SqlSessionGetter.getSession();
        CartMapping mapper = session.getMapper(CartMapping.class);
        Integer i = mapper.insertUserCartById(goodsId, userId, count);
        session.commit();
        session.close();
        return i != 0;
    }

    @Override
    public boolean deleteCart(Integer cartId) {
        SqlSession session = SqlSessionGetter.getSession();
        CartMapping mapper = session.getMapper(CartMapping.class);
        int i = mapper.deleteUserCartById(cartId);
        session.commit();
        return i != 0;
    }

    public void clearCart(Integer cartId) {
        SqlSession session = SqlSessionGetter.getSession();
        CartMapping mapper = session.getMapper(CartMapping.class);
        mapper.emptyCart(cartId);
        session.commit();
        session.close();
    }


}
