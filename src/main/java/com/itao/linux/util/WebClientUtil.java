package com.itao.linux.util;

import com.itao.linux.bean.CommandData;
import com.itao.linux.bean.Param;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.itao.linux.util.Constants.*;

public class WebClientUtil {

    private final static WebClient webClient =
            WebClient.create(Vertx.vertx(), new WebClientOptions().setUserAgent(USER_AGENT));

    public WebClient webClient() {
        return webClient;
    }

    public static Future<HttpResponse<Buffer>> requestCommand(String c) {
        String url = LINUX_COOL_URL + c;
        return webClient.getAbs(url).send().onSuccess(response -> {
            String html = response.bodyAsString("utf-8");
            Document document = Jsoup.parse(html);
            Element page = document.selectFirst("title");
            if (page.text().contains("未找到页面")) {
                CommandData.COMMAND.clear();
            } else {
                CommandData.COMMAND.clear();
                Element title = document.selectFirst(".page-title");
                Element table = document.selectFirst(".wp-block-table");
                Element element = table.previousElementSibling().previousElementSibling();
                String grammarFormat = element.text();
                Elements trs = table.select("tbody > tr");
                List<Param> params = new ArrayList<>();
                for (Element tr : trs) {
                    Elements tds = tr.select("td");
                    String name = tds.get(0).text();
                    String desc = tds.get(1).text();
                    params.add(new Param(name, desc));
                }
                CommandData.COMMAND.setTitle(title.text());
                CommandData.COMMAND.setGrammarFormat(grammarFormat);
                CommandData.COMMAND.setParams(params);
                CommandData.COMMAND.setExist(true);
            }
        }).onFailure(System.out::println);
    }
}
