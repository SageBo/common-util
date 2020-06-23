package com.company.project.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sage.util.HttpUtil;


/**
 * Created by sage on 2018/8/7.
 */
public class TestApi {

    public ApkVerResp getApkVer(ApkVerReq apkVerReq) {
        String respStr = HttpUtil.get("url");
        JSONObject jsonObj = JSON.parseObject(respStr);
        String msgCode = (String) jsonObj.get("resp_code");
        JSONObject data = (JSONObject) jsonObj.get("data");

        if (msgCode != null && "0000".equals(msgCode) && data != null) {
            return JSON.parseObject(JSON.toJSONString(data), ApkVerResp.class);
        }
        return null;

    }
}
