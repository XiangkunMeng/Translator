package cn.xkenmon.translator;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * translator
 * Created by mxk94 on 2017/7/14.
 */
class Translator {
    private String key;
    private String url;
    Translator(String key, String url){
        this.key = key;
        this.url = url;
    }
    TransResult translate(String keyword) throws ParserConfigurationException, SAXException, IOException {
        String uri = url+"?w="+keyword+"&key="+key;
        return XMLPars.pars(uri);
    }
}
