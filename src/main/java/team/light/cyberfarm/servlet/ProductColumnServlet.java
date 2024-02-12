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
@WebServlet("/productColumn/*")
public class ProductColumnServlet extends BaseServlet{

    DisplayGoods displayGoods = new DisPlayGoodsImpl();
    /**
     * input parameter: begin, limit, category
     * return json format:
     * {
     *     total: count,
     *     columns: [{id, name, price, coverUrl},...]
     * }
     * @param request request
     * @param response response
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) {

        try {
            int page = Integer.parseInt(request.getParameter("begin"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            int category = Integer.parseInt(request.getParameter("category"));


            int beg = (page - 1) * limit;
            if (beg < 0) return;
            String jsonString = JSON.toJSONString(displayGoods.selectToMap(category, beg, limit));
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(jsonString);
        } catch (NumberFormatException | IOException e) {
            log.warning(e.toString());
        } catch (Exception e) {
            log.severe(e.toString());
        }


    }



}
