package team.light.cyberfarm.servlet;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import team.light.cyberfarm.entity.Cart;
import team.light.cyberfarm.service.CartService;
import team.light.cyberfarm.serviceImpl.CartServiceImpl;

import java.io.IOException;
import java.util.List;

@Log
@WebServlet("/cart/*")
public class CartServlet extends BaseServlet{

    private static final CartService cartService = new CartServiceImpl();

    public void selectCount(HttpServletRequest request, HttpServletResponse response) {
        Integer userId = (Integer)request.getSession().getAttribute("userId");
        Integer count = cartService.getUserCartCount(userId);
        try {
            response.getWriter().print(count);
        } catch (IOException e) {
            log.severe(e.toString());
        }
    }
    public void selectCarts(HttpServletRequest request, HttpServletResponse response) {
        Integer userId = (Integer)request.getSession().getAttribute("userId");
        List<Cart> userCart = cartService.getUserCart(userId);
        try {
            response.setContentType("text/json; charset=UTF-8");
            response.getWriter().print(JSON.toJSONString(userCart));
        } catch (IOException e) {
            log.severe(e.toString());
        }


    }
    public void addCart(HttpServletRequest request, HttpServletResponse response) {
        Integer userId = (Integer)request.getSession().getAttribute("userId");

        try {

            Integer goodsId = Integer.parseInt(request.getParameter("id"));
            String parameter = request.getParameter("count");
            Integer count = Integer.parseInt(parameter);

            boolean result = cartService.addCart(goodsId, userId, count);

            response.sendRedirect("../my_farm.html");
        } catch (NumberFormatException ignore) {

        } catch (IOException e) {
            log.severe(e.toString());
        }

    }

    public void deleteCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            String parameter = request.getParameter("id");
            int id = Integer.parseInt(parameter);
            cartService.deleteCart(id);
        } catch (NumberFormatException ignored) {
        }


    }

    public void clearCart(HttpServletRequest request, HttpServletResponse response) {
        Integer id = (Integer) request.getSession().getAttribute("userId");
        cartService.clearCart(id);
    }



}
