package br.com.myteams.controller.administrativo;

import br.com.myteams.dao.PedidoDAO;
import br.com.myteams.dao.ProdutoPedidoDAO;
import br.com.myteams.model.pedido.Pedido;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/admin")
public class PainelAdministrativoController extends HttpServlet
{

    /**
     * Verifica se já possui uma rota já defina, caso contrario redireciona para o painel administrativo
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (!resp.isCommitted())
        {
            try
            {
                CustomConnection connection = DBUtil.getConnection();
                List<Pedido> pedidos = new PedidoDAO(connection).buscaPedidosPendentes();
                for (Pedido pedido : pedidos)
                {
                    pedido.setProdutos(new ProdutoPedidoDAO(connection).buscaPorPedido(pedido.getId()));
                }
                req.setAttribute("pedidos", pedidos);
                req.getRequestDispatcher("/WEB-INF/painel-administrativo.jsp").forward(req, resp);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(PainelAdministrativoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
