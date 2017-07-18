package cn.xkenmon.translator;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * this is the result of translation
 * Created by mxk94 on 2017/7/15.
 */
public class TransResult {
    private String keyword;
    private Map<String, URL> pron = new HashMap<>();
    private Map<String, String> rst = new HashMap<>();
    private Map<String, String> example = new HashMap<>();

    public Map<String, URL> getPron() {
        return pron;
    }

    void setPron(Map<String, URL> pron) {
        this.pron = pron;
    }

    public Map<String, String> getExample() {
        return example;
    }

    void setExample(Map<String, String> example) {
        this.example = example;
    }

    public Map<String, String> getRst() {

        return rst;
    }

    void setRst(Map<String, String> rst) {
        this.rst = rst;
    }

    public String getKeyword() {
        return keyword;
    }

    void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void show() {
        System.out.println(getKeyword());

        System.out.println("发音:\n");
        for (Map.Entry<String, URL> item : getPron().entrySet()) {
            System.out.println(item.getKey() + "    " + item.getValue());
            System.out.println();
        }

        System.out.println("翻译:");
        for (Map.Entry item : getRst().entrySet()) {
            System.out.print(item.getValue() + "  " + item.getKey());
            System.out.println();
        }

        System.out.println("例句:");
        for (Map.Entry item : getExample().entrySet()) {
            System.out.print(item.getKey());
            System.out.print(item.getValue());
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("翻译:\n");
        for (Map.Entry item : getRst().entrySet()) {
            builder.append(item.getValue() + "  " + item.getKey());
            builder.append("\n");
        }
        builder.append("例句：\n");
        for (Map.Entry item : getExample().entrySet()) {
            builder.append(item.getKey());
            builder.append(item.getValue());
        }
        return builder.toString();
    }
}
