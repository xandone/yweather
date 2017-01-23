package app.xandone.com.yweather.bean;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xandone on 2017/1/6.
 */
@Root(name = "Profiles", strict = false)//需要解析的头部
public class WeatherXml {
    @ElementList(required = true, inline = true, entry = "Weather")
    private List<WeatherXmlData> list = new ArrayList<>();

    public List<WeatherXmlData> getList() {
        return list;
    }

    public void setList(List<WeatherXmlData> list) {
        this.list = list;
    }
}
