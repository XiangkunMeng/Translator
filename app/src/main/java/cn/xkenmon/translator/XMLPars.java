package cn.xkenmon.translator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * XmlParsing class
 * Created by mxk94 on 2017/7/15.
 */
class XMLPars {

    static TransResult pars(String url_str) throws IOException, SAXException, ParserConfigurationException {

        return parsXML(url_str);
    }


    private static TransResult parsXML(String xml) throws ParserConfigurationException, SAXException, IOException {
        TransResult result = new TransResult();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        URLConnection connection;
        connection = new URL(xml).openConnection();
        InputStream in;
        in = connection.getInputStream();

        Document document = builder.parse(in);
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        if (nodeList != null) {
            Node key = root.getElementsByTagName("key").item(0);
            NodeList ps_list = root.getElementsByTagName("ps");
            NodeList acceptation_list = root.getElementsByTagName("acceptation");
            NodeList sent_list = root.getElementsByTagName("sent");

            String keyword;
            Map<String, URL> ps = new HashMap<>();
            Map<String, String> acceptation = new HashMap<>();
            Map<String, String> example = new HashMap<>();

            keyword = key.getTextContent();
            for (int i = 0; i < ps_list.getLength(); i++) {
                Node item = ps_list.item(i);
                if (item.getNextSibling().getNodeType() == Node.TEXT_NODE)
                    try {
                        ps.put(item.getTextContent(), new URL(item.getNextSibling().getNextSibling().getTextContent()));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
            }
            for (int i = 0; i < acceptation_list.getLength(); i++) {
                Node item = acceptation_list.item(i);
                if (item.getNextSibling().getNodeType() == Node.TEXT_NODE)
                    acceptation.put(item.getTextContent(), item.getPreviousSibling().getPreviousSibling().getTextContent());
            }
            for (int i = 0; i < sent_list.getLength(); i++) {
                NodeList example_list = sent_list.item(i).getChildNodes();
                for (int j = 0; j < example_list.getLength(); j++) {
                    Node orig_node = example_list.item(j);
                    Node trans_node = example_list.item(j += 2);
                    example.put(orig_node.getFirstChild().getTextContent(), trans_node.getFirstChild().getTextContent());
                }
            }

            result.setKeyword(keyword);
            result.setExample(example);
            result.setPron(ps);
            result.setRst(acceptation);
        }
        return result;
    }

}
