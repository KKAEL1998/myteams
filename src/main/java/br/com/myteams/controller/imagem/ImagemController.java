package br.com.myteams.controller.imagem;

import br.com.myteams.config.Config;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;

@WebServlet({"/imagem", "/imagem/*"})
@MultipartConfig(location = Config.PATH_IMAGEM)
public class ImagemController extends HttpServlet
{

    private static final Path PATH = Paths.get(Config.PATH_IMAGEM);

    /**
     * Realiza o upload da imagem e responde com um json contendo a uri da imagem
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Part imagem = req.getPart("imagem");

        if (!imagem.getContentType().startsWith("image"))
        {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
        else
        {
            if (!PATH.toFile().exists())
            {
                Files.createDirectory(PATH);
            }
            String fileName = nomeArquivo(imagem);
            anexaNomeImagemResposta(resp, req, fileName);
            imagem.write(fileName);
        }
    }

    /**
     * Recupera a imagem para ser exebida no sistema
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String pathInfo = req.getPathInfo();
        Path path = Paths.get(PATH.toString() + pathInfo);
        if (!path.toFile().exists())
        {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        else
        {
            String fileName = pathInfo.substring(1, pathInfo.lastIndexOf('.'));
            String s = new String(Base64.getDecoder().decode(fileName));
            String contentType = s.substring(0, s.indexOf('_'));
            resp.setContentType(contentType);
            try (FileInputStream in = new FileInputStream(path.toFile()))
            {
                ServletOutputStream out = resp.getOutputStream();
                byte[] buf = new byte[1024];
                int count;
                while ((count = in.read(buf)) >= 0)
                {
                    out.write(buf, 0, count);
                }
            }
        }
    }

    /**
     * Adiciona o json contendo a uri da imagem a resposa da requisicao
     *
     * @param response
     * @param request
     * @param fileName
     * @throws IOException
     */
    private void anexaNomeImagemResposta(HttpServletResponse response, HttpServletRequest request, String fileName) throws IOException
    {
        response.setContentType(MediaType.APPLICATION_JSON);
        response.getWriter().append("{\"url\":\"").append(request.getContextPath()).append("/imagem/").append(fileName).append("\"}");
    }

    /**
     * Gera um nome para imagem, para diminuir a chances de ter nomes duplicados na pasta onde as imagens são salvas
     *
     * @param imagem
     * @return O formato da imagem e a hora da gravacao em base64 mais a extensão do arquivo
     */
    private String nomeArquivo(Part imagem)
    {
        String nome = imagem.getSubmittedFileName();
        nome = new String(Base64.getEncoder().encode((imagem.getContentType() + "_" + Calendar.getInstance().getTimeInMillis()).getBytes())) + nome.substring(nome.lastIndexOf('.'));
        return nome;
    }

}
