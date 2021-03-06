package mbs2.pr19.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mbs2.pr19.dao.ProductDao;
import mbs2.pr19.entity.Product;

public class PickProductServlet extends HttpServlet {
  
  @EJB
  private ProductDao productDao;
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=utf-8");
    request.setCharacterEncoding(response.getCharacterEncoding());
    ServletContext ctx = getServletConfig().getServletContext();
    HttpSession session = request.getSession(true);
    
    try {
      String sProductId = request.getParameter("id");
      int productId = Integer.parseInt(sProductId);
      Product prod = productDao.findById(productId);
      session.setAttribute("product", prod);
      
      ctx.getRequestDispatcher("/product.jsp").forward(request, response);
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }

}
