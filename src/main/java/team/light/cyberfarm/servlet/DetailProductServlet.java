package team.light.cyberfarm.servlet;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import team.light.cyberfarm.service.DisplayGoods;
import team.light.cyberfarm.serviceImpl.DisPlayGoodsImpl;

import java.io.IOException;

@Log
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
            try {
                response.getWriter().print("404");
            } catch (IOException ex) {
                log.warning(ex.toString());
            }
        }




    }

    public void selectProductCount(HttpServletRequest request, HttpServletResponse response) {
        try {
            int category = Integer.parseInt(request.getParameter("category"));
            response.getWriter().print(displayGoods.selectCategoryCount(category));
        } catch (NumberFormatException e) {
            try {
                response.getWriter().print("404");
            } catch (IOException ex) {
                log.warning(ex.toString());
            }
        } catch (IOException e) {
            log.warning(e.toString());
        }
    }


}
