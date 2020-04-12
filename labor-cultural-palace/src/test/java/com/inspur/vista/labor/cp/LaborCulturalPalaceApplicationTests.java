package com.inspur.vista.labor.cp;

import com.alibaba.fastjson.JSONObject;
import com.inspur.vista.labor.cp.util.CPUtils;
import com.inspur.vista.labor.cp.util.ECGeoCoordinateTransformUtil;
import com.inspur.vista.labor.cp.util.QRCodeConstants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class LaborCulturalPalaceApplicationTests {

    @Test
    void test() {
        double[] cur = ECGeoCoordinateTransformUtil.gcj02tobd09(117.126955, 36.6627);
        System.out.println(cur[0]);
        System.out.println(cur[1]);
    }

    @Test
    void test1() {
        List<Integer> NoNum = new ArrayList<>();
        NoNum.add(1);
        NoNum.add(2);
        NoNum.add(3);
        NoNum.add(4);
        NoNum.add(5);
        NoNum.add(6);
        int state = 0;
        String result = "";
        for (int i = 0; i < NoNum.size(); i++) {
            if (i == NoNum.size() - 1) {
                state = 2;
            }
            if (state == 0) {
                if (NoNum.get(i + 1) == NoNum.get(i) + 1) {
                    result += Integer.toString(NoNum.get(i));
                    result += "-";
                    state = 1;
                } else {
                    result += Integer.toString(NoNum.get(i));
                    result += ",";
                }
            } else if (state == 1) {
                if (NoNum.get(i + 1) != NoNum.get(i) + 1) {
                    result += Integer.toString(NoNum.get(i));
                    result += ",";
                    state = 0;
                } else {
                    result += NoNum.get(i) + "-";
                }
            } else {
                result += Integer.toString(NoNum.get(i));
            }
        }

        String[] str = result.split(",");
        String week = "";
        for (int stritem = 0; stritem < str.length; stritem++) {
            String[] sp = str[stritem].split("-");
            week = week + getWeek(Integer.parseInt(sp[0])) + " —— " + getWeek(Integer.parseInt(sp[sp.length - 1])) + ",";

            List<String> tt = Arrays.asList(sp);
            System.out.println(JSONObject.toJSONString(tt));
        }
        System.out.println(week.substring(0, week.length() - 1));
    }

    private String getWeek(int week) {
        switch (week) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期日";
            default:
                return "";
        }
    }

    @Test
    void test2() {
        Map<String, Object> param = new HashMap<>();
        param.put("reserveId", "defcf67c10b7479a831561cbfde24755");
        param.put("userCode", "m596adf0668865756cbf6a9df4521dc63");
        String qrCodeParam = "type=" + QRCodeConstants.QR_CODE_TYPE_RESERVE + "&param=" + CPUtils.encryptQRCodeParam(param);
        System.out.println(qrCodeParam);
        System.out.println(JSONObject.toJSONString(CPUtils.decryptQRCodeParam(CPUtils.encryptQRCodeParam(param))));
    }
}
