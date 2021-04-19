package cn.itcast.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupDemo01 {
    public static void main(String[] args) throws IOException {
        //获取xml文件的路径
        String path = JsoupDemo01.class.getClassLoader().getResource("student.xml").getPath();
//        System.out.println(path);
        //获取document对象
        Document document = Jsoup.parse(new File(path), "utf-8");
        //根据document对象得到elements对象
        Elements elements = document.getElementsByTag("name");
        System.out.println(elements.size());
        //得到element对象，获取数据
        Element element = elements.get(0);
        String name = element.text();
        System.out.println(name);

        System.out.println("------------------");

       Element e1 = document.getElementById("1");
        System.out.println(e1);

        System.out.println("------------------");
        Elements e2 = document.getElementsByAttribute("id");

        System.out.println("----------------------");
        Elements e3 = document.getElementsByAttributeValue("id","1");
    }
}
