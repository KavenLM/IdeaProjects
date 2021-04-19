package cn.itcast.xml.jsoup;

import cn.wanghaomiao.xpath.model.JXDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupDemo02 {
    public static void main(String[] args) throws IOException {
        //获取xml文件的路径
        String path = JsoupDemo02.class.getClassLoader().getResource("student.xml").getPath();
//        System.out.println(path);
        //获取document对象
        Document document = Jsoup.parse(new File(path), "utf-8");
//        Elements e = document.select("#1");
//        Elements sex = document.select("#1>sex");
        Elements sex = document.select("[number=s1]>sex");
        System.out.println(sex);

        System.out.println("--------------------");
        JXDocument jxDocument = new JXDocument(document);

    }
}
