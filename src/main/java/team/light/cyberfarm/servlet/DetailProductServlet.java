package team.light.cyberfarm.servlet;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import team.light.cyberfarm.service.DisplayGoods;
import team.light.cyberfarm.serviceImpl.DisPlayGoodsImpl;

import java.io.IOException;

@WebServlet("/detailProduct/*")
public class DetailProductServlet extends BaseServlet {

    public DisplayGoods displayGoods = new DisPlayGoodsImpl();

    public void selectProduct(HttpServletRequest request, HttpServletResponse response) {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String jsonString = JSON.toJSONString(displayGoods.selectGood(id));
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }


}
