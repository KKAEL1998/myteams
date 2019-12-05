package br.com.myteams.controller.conta;

import br.com.myteams.dao.PedidoDAO;
import br.com.myteams.dao.ProdutoPedidoDAO;
import br.com.myteams.model.pedido.Pedido;
import br.com.myteams.model.usuario.Usuario;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/conta")
public class ContaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!resp.isCommitted()) {
            try {
                Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
                CustomConnection connection = DBUtil.getConnection();
                List<Pedido> pedidos = new PedidoDAO(connection).buscaPedidosPorCliente(usuario.getCpf());
                for (Pedido pedido : pedidos) {
                    pedido.setProdutos(new ProdutoPedidoDAO(connection).buscaPorPedido(pedido.getId()));
                }
                req.setAttribute("pedidos", pedidos);
                req.getRequestDispatcher("/WEB-INF/conta.jsp").forward(req, resp);
            } catch (SQLException ex) {
                Logger.getLogger(ContaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
