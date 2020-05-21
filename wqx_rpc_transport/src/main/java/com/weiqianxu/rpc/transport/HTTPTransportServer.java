package com.weiqianxu.rpc.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @descript：实现服务端网络传输
 * @Author: WeiQianXu
 * @Date: 2020/5/18 11:31
 */

@Slf4j
public class HTTPTransportServer implements TransportServer {

    private RequestHandler handler;
    private Server server;

    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);

        //sevlet 接受请求
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        //holder,jetty在处理网络请求时候的一个抽象
        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder,"/*");
    }

    public void start() {

        try {
            server.start();
            //等待
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    public void stop() {

        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    class RequestServlet extends HttpServlet{

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            log.info("client connent");

            InputStream inputStream = req.getInputStream();
            OutputStream outputStream = resp.getOutputStream();

            if (handler != null){
                handler.onRequest(inputStream,outputStream);
            }

            outputStream.flush();
        }
    }
}
