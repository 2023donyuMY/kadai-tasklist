package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasklist;
import utils.DBUtil;

/**
 * Servlet implementation class EditSevlet
 */
@WebServlet("/edit")
public class EditSevlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        //該当IDのタスク1件をデータベースから取得
        Tasklist t = em.find(Tasklist.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        //タスク情報とセッションIDをリクエストスコープに登録
        request.setAttribute("tasklist", t);
        request.setAttribute("_token", request.getSession().getId());

        //タスクIDをセッションスコープに登録
        request.getSession().setAttribute("tasklist_id", t.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasklists/edit.jsp");
        rd.forward(request, response);
    }

}
