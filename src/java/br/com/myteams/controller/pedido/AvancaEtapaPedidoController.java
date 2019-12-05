package br.com.myteams.controller.pedido;

import br.com.myteams.dao.PedidoDAO;
import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.pedido.Pedido;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/pedido/avanca/*")
public class AvancaEtapaPedidoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (CustomConnection connection = DBUtil.getConnection()) {
            PedidoDAO pedidoDAO = new PedidoDAO(connection);
            Pedido pedido = pedidoDAO.procuraPorId(Long.parseLong(req.getPathInfo().substring(1)));
            if (pedido != null) {
                pedido.avancaEtapa();
                pedidoDAO.atualizaStatus(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvancaEtapaPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NegocioException ex) {
            Logger.getLogger(AvancaEtapaPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        resp.sendRedirect(req.getContextPath() + "/admin");
    }

}
