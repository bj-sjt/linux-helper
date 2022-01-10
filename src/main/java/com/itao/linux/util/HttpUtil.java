package com.itao.linux.util;

import com.itao.linux.bean.Command;
import com.itao.linux.bean.CommandData;
import com.itao.linux.bean.Param;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.itao.linux.util.Constants.SEARCH_ERROR;

public class HttpUtil {

    public static Command getCommand(String html){
        Command command = new Command();
        if (html == null) {

            command.setTitle(SEARCH_ERROR);
            return command;
        }
        Document document = Jsoup.parse(html);
        Element page = document.selectFirst("title");
        if (page.text().contains("未找到页面")) {
            command.clear();
        } else {
            command.clear();
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
            command.setTitle(title.text());
            command.setGrammarFormat(grammarFormat);
            command.setParams(params);
            command.setExist(true);
        }
        return command;
    }
}
