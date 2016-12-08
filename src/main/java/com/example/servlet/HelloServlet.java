package com.example.servlet;

import com.example.dto.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    // Thymeleafの出力を書き出すクラス
    private TemplateEngine templateEngine;

    /**
     * サーブレットの初期化処理。
     * TemplateResolverおよびTemplateEngineを生成する。
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // TemplateResolverの生成
        ServletContext servletContext = config.getServletContext();
        ServletContextTemplateResolver templateResolver =
                new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix("/WEB-INF/views/"); // ビューの保存フォルダ
        templateResolver.setSuffix(".html"); // ビューの拡張子
        templateResolver.setTemplateMode(TemplateMode.HTML);

        // TemplateEngineの生成
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 入力されたカウントの数だけUserを生成
        int count = Integer.parseInt(request.getParameter("count"));
        List<User> userList = IntStream.rangeClosed(1, count)
                .mapToObj(i -> new User(i, "User" + i))
                .collect(Collectors.toList());

        // ビューに渡す値を保存するマップ
        HashMap<String, Object> map = new HashMap<>();
        map.put("userList", userList);

        // レスポンスするHTMLを書き出す
        WebContext webContext = new WebContext(request, response,
                getServletContext(), request.getLocale());
        webContext.setVariables(map); // 値をビューに渡す
        Writer writer = new OutputStreamWriter(
                response.getOutputStream(), StandardCharsets.UTF_8);
        templateEngine.process("hello", webContext, writer); // /WEB-INF/views/hello.htmlを書き出す
    }
}
