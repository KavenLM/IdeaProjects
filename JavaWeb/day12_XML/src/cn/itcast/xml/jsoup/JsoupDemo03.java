package cn.itcast.xml.jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsoupDemo03 {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        //获取xml文件的路径
        String path = JsoupDemo03.class.getClassLoader().getResource("student.xml").getPath();

        Document document = Jsoup.parse(new File(path), "utf-8");

        JXDocument jxDocument = new JXDocument(document);
        List<JXNode> jxNodes = jxDocument.selN("//name");
        for (JXNode jxnode : jxNodes){
            System.out.println(jxnode);
        }
    }
}
