package com.company.project.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sage.util.HttpUtil;


/**
* Created by CodeGenerator on 2018/08/07.
*/
public class TestGengetApkVer {

    public ApkVerResp getApkVer(ApkVerReq req) {
        String respStr = HttpUtil.get("http://tsmtest.snowballtech.com:8084/gateway/v1.0/order/get_order_no");
        JSONObject jsonObj = JSON.parseObject(respStr);
        String msgCode = (String) jsonObj.get("resp_code");
        JSONObject data = (JSONObject) jsonObj.get("data");

        if (msgCode != null && "0000".equals(msgCode) && data != null) {
            return JSON.parseObject(JSON.toJSONString(data), ApkVerResp.class);
        }
        return null;

   }
}
