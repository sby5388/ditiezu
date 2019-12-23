package com.by5388.ditiezu.user;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator  on 2019/12/23.
 */
public class JsoupLoginIteye {

    public void login(String userName, String pwd) throws Exception {
        //第一次请求
        //获取连接
        final Connection con = Jsoup.connect("http://www.iteye.com/login");
        //配置模拟浏览器
        con.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        //获取响应
        final Response rs = con.execute();
        //转换为Dom树
        final Document d1 = Jsoup.parse(rs.body());
        //获取form表单，可以通过查看页面源码代码得知
        final List<Element> et = d1.select("#login_form");

        //获取，cooking和表单属性，下面map存放post时的数据
        Map<String, String> datas = new HashMap<>();
        for (Element e : et.get(0).getAllElements()) {
            if (e.attr("name").equals("name")) {
                //设置用户名
                e.attr("value", userName);
            }

            if (e.attr("name").equals("password")) {
                //设置用户密码
                e.attr("value", pwd);
            }

            if (e.attr("name").length() > 0) {
                //排除空值表单属性
                datas.put(e.attr("name"), e.attr("value"));
            }
        }


        //第二次请求，post表单数据，以及cookie信息
        Connection con2 = Jsoup.connect("http://www.iteye.com/login");
        con2.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        //设置cookie和post上面的map数据
        Response login = con2.ignoreContentType(true).method(Method.POST).data(datas).cookies(rs.cookies()).execute();
        //打印，登陆成功后的信息
        System.out.println(login.body());

        //登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
        Map<String, String> map = login.cookies();
        for (String s : map.keySet()) {
            System.out.println(s + " " + map.get(s));
        }

    }
}