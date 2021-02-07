package com.zzr.springboot.demo.auto.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_SERVICE_UNAVAILABLE;

@WebServlet(
        asyncSupported = true,
        urlPatterns = "/asyncMyServlet"

)
public class MyAsyncServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();

        asyncContext.setTimeout(1000L);
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                asyncEvent.getSuppliedResponse().getWriter().println("asyncServlet");
                asyncEvent.getSuppliedResponse().flushBuffer();
                println("complete");
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                HttpServletResponse servletResponse = (HttpServletResponse)asyncEvent.getSuppliedResponse();
                servletResponse.setStatus(SC_SERVICE_UNAVAILABLE);
                servletResponse.getWriter().println("timeout");
                println("timeOut");
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                println("error");
            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                println("start");
            }
        });



    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("AsyncServlet[" + threadName + "]: " + object);
    }
}
