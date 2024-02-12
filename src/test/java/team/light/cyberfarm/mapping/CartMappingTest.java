package team.light.cyberfarm.mapping;


import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import team.light.cyberfarm.entity.Cart;
import team.light.cyberfarm.tool.SqlSessionGetter;


public class CartMappingTest {

    private static SqlSession session;
    private static CartMapping cartMapping;

    @BeforeAll
    public static void init() {
        session = SqlSessionGetter.getSession();
        cartMapping = session.getMapper(CartMapping.class);
    }

    @Test
    public void selectUserCartByIdTestTest() {
        System.out.println(cartMapping.selectUserCartById(1));
    }

    @Test
    public void selectUserCartCountByIdTest() {
        System.out.println(cartMapping.selectUserCartCountById(1));
    }

    @Test
    public void insertUserCartByIdTest() {
        Cart cart = new Cart();
        cart.setCount(1);

        Integer carts = cartMapping.insertUserCartById(4, 1, 1);
        System.out.println(carts);
    }

    @AfterAll
    public static void close() {
        session.close();
    }
}
