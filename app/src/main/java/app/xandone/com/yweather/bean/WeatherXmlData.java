package app.xandone.com.yweather.bean;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by xandone on 2017/1/6.
 */
@Root(name = "Weather", strict = false)
public class WeatherXmlData {
    @Element(required = false)
    private String city;
    @Element(required = false)
    private String status1;
    @Element(required = false)
    private String status2;
    @Element(required = false)
    private String figure1;
    @Element(required = false)
    private String figure2;
    @Element(required = false)
    private String direction1;
    @Element(required = false)
    private String direction2;
    @Element(required = false)
    private String power1;
    @Element(required = false)
    private String power2;
    @Element(required = false)
    private String temperature1;
    @Element(required = false)
    private String temperature2;
    @Element(required = false)
    private String ssd;
    @Element(required = false)
    private String tgd1;
    @Element(required = false)
    private String tgd2;
    @Element(required = false)
    private String zwx;
    @Element(required = false)
    private String ktk;
    @Element(required = false)
    private String pollution;
    @Element(required = false)
    private String xcz;
    @Element(required = false)
    private String zho;
    @Element(required = false)
    private String diy;
    @Element(required = false)
    private String fas;
    @Element(required = false)
    private String chy;
    @Element(required = false)
    private String zho_shuoming;
    @Element(required = false)
    private String diy_shuoming;
    @Element(required = false)
    private String fas_shuoming;
    @Element(required = false)
    private String chy_shuoming;
    @Element(required = false)
    private String pollution_l;
    @Element(required = false)
    private String zwx_l;
    @Element(required = false)
    private String ssd_l;
    @Element(required = false)
    private String fas_l;
    @Element(required = false)
    private String zho_l;
    @Element(required = false)
    private String chy_l;
    @Element(required = false)
    private String ktk_l;
    @Element(required = false)
    private String xcz_l;
    @Element(required = false)
    private String diy_l;
    @Element(required = false)
    private String pollution_s;
    @Element(required = false)
    private String zwx_s;
    @Element(required = false)
    private String ssd_s;
    @Element(required = false)
    private String ktk_s;
    @Element(required = false)
    private String xcz_s;
    @Element(required = false)
    private String gm;
    @Element(required = false)
    private String gm_l;
    @Element(required = false)
    private String gm_s;
    @Element(required = false)
    private String yd;
    @Element(required = false)
    private String yd_l;
    @Element(required = false)
    private String yd_s;
    @Element(required = false)
    private String savedate_weather;
    @Element(required = false)
    private String savedate_life;
    @Element(required = false)
    private String savedate_zhishu;

    public String getChy_shuoming() {
        return chy_shuoming;
    }

    public void setChy_shuoming(String chy_shuoming) {
        this.chy_shuoming = chy_shuoming;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getFigure1() {
        return figure1;
    }

    public void setFigure1(String figure1) {
        this.figure1 = figure1;
    }

    public String getFigure2() {
        return figure2;
    }

    public void setFigure2(String figure2) {
        this.figure2 = figure2;
    }

    public String getDirection1() {
        return direction1;
    }

    public void setDirection1(String direction1) {
        this.direction1 = direction1;
    }

    public String getDirection2() {
        return direction2;
    }

    public void setDirection2(String direction2) {
        this.direction2 = direction2;
    }

    public String getPower1() {
        return power1;
    }

    public void setPower1(String power1) {
        this.power1 = power1;
    }

    public String getPower2() {
        return power2;
    }

    public void setPower2(String power2) {
        this.power2 = power2;
    }

    public String getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(String temperature1) {
        this.temperature1 = temperature1;
    }

    public String getTemperature2() {
        return temperature2;
    }

    public void setTemperature2(String temperature2) {
        this.temperature2 = temperature2;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getTgd1() {
        return tgd1;
    }

    public void setTgd1(String tgd1) {
        this.tgd1 = tgd1;
    }

    public String getTgd2() {
        return tgd2;
    }

    public void setTgd2(String tgd2) {
        this.tgd2 = tgd2;
    }

    public String getZwx() {
        return zwx;
    }

    public void setZwx(String zwx) {
        this.zwx = zwx;
    }

    public String getKtk() {
        return ktk;
    }

    public void setKtk(String ktk) {
        this.ktk = ktk;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getXcz() {
        return xcz;
    }

    public void setXcz(String xcz) {
        this.xcz = xcz;
    }

    public String getZho() {
        return zho;
    }

    public void setZho(String zho) {
        this.zho = zho;
    }

    public String getDiy() {
        return diy;
    }

    public void setDiy(String diy) {
        this.diy = diy;
    }

    public String getFas() {
        return fas;
    }

    public void setFas(String fas) {
        this.fas = fas;
    }

    public String getChy() {
        return chy;
    }

    public void setChy(String chy) {
        this.chy = chy;
    }

    public String getZho_shuoming() {
        return zho_shuoming;
    }

    public void setZho_shuoming(String zho_shuoming) {
        this.zho_shuoming = zho_shuoming;
    }

    public String getDiy_shuoming() {
        return diy_shuoming;
    }

    public void setDiy_shuoming(String diy_shuoming) {
        this.diy_shuoming = diy_shuoming;
    }

    public String getFas_shuoming() {
        return fas_shuoming;
    }

    public void setFas_shuoming(String fas_shuoming) {
        this.fas_shuoming = fas_shuoming;
    }

    public String getPollution_l() {
        return pollution_l;
    }

    public void setPollution_l(String pollution_l) {
        this.pollution_l = pollution_l;
    }

    public String getZwx_l() {
        return zwx_l;
    }

    public void setZwx_l(String zwx_l) {
        this.zwx_l = zwx_l;
    }

    public String getSsd_l() {
        return ssd_l;
    }

    public void setSsd_l(String ssd_l) {
        this.ssd_l = ssd_l;
    }

    public String getFas_l() {
        return fas_l;
    }

    public void setFas_l(String fas_l) {
        this.fas_l = fas_l;
    }

    public String getZho_l() {
        return zho_l;
    }

    public void setZho_l(String zho_l) {
        this.zho_l = zho_l;
    }

    public String getChy_l() {
        return chy_l;
    }

    public void setChy_l(String chy_l) {
        this.chy_l = chy_l;
    }

    public String getKtk_l() {
        return ktk_l;
    }

    public void setKtk_l(String ktk_l) {
        this.ktk_l = ktk_l;
    }

    public String getXcz_l() {
        return xcz_l;
    }

    public void setXcz_l(String xcz_l) {
        this.xcz_l = xcz_l;
    }

    public String getDiy_l() {
        return diy_l;
    }

    public void setDiy_l(String diy_l) {
        this.diy_l = diy_l;
    }

    public String getPollution_s() {
        return pollution_s;
    }

    public void setPollution_s(String pollution_s) {
        this.pollution_s = pollution_s;
    }

    public String getZwx_s() {
        return zwx_s;
    }

    public void setZwx_s(String zwx_s) {
        this.zwx_s = zwx_s;
    }

    public String getSsd_s() {
        return ssd_s;
    }

    public void setSsd_s(String ssd_s) {
        this.ssd_s = ssd_s;
    }

    public String getKtk_s() {
        return ktk_s;
    }

    public void setKtk_s(String ktk_s) {
        this.ktk_s = ktk_s;
    }

    public String getXcz_s() {
        return xcz_s;
    }

    public void setXcz_s(String xcz_s) {
        this.xcz_s = xcz_s;
    }

    public String getGm() {
        return gm;
    }

    public void setGm(String gm) {
        this.gm = gm;
    }

    public String getGm_l() {
        return gm_l;
    }

    public void setGm_l(String gm_l) {
        this.gm_l = gm_l;
    }

    public String getGm_s() {
        return gm_s;
    }

    public void setGm_s(String gm_s) {
        this.gm_s = gm_s;
    }

    public String getYd() {
        return yd;
    }

    public void setYd(String yd) {
        this.yd = yd;
    }

    public String getYd_l() {
        return yd_l;
    }

    public void setYd_l(String yd_l) {
        this.yd_l = yd_l;
    }

    public String getYd_s() {
        return yd_s;
    }

    public void setYd_s(String yd_s) {
        this.yd_s = yd_s;
    }

    public String getSavedate_weather() {
        return savedate_weather;
    }

    public void setSavedate_weather(String savedate_weather) {
        this.savedate_weather = savedate_weather;
    }

    public String getSavedate_life() {
        return savedate_life;
    }

    public void setSavedate_life(String savedate_life) {
        this.savedate_life = savedate_life;
    }

    public String getSavedate_zhishu() {
        return savedate_zhishu;
    }

    public void setSavedate_zhishu(String savedate_zhishu) {
        this.savedate_zhishu = savedate_zhishu;
    }

    public String getUdatetime() {
        return udatetime;
    }

    public void setUdatetime(String udatetime) {
        this.udatetime = udatetime;
    }

    @Element(required = false)
    private String udatetime;


}
