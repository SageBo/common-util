package ${apiPackage};

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sage.util.HttpUtil;


/**
* Created by ${author} on ${date}.
*/
public class TestGen${apiName} {

    public ${respBean} ${apiName}(${reqBean} req) {
        String respStr = HttpUtil.${method}("${url}");
        JSONObject jsonObj = JSON.parseObject(respStr);
        String msgCode = (String) jsonObj.get("resp_code");
        JSONObject data = (JSONObject) jsonObj.get("data");

        if (msgCode != null && "0000".equals(msgCode) && data != null) {
            return JSON.parseObject(JSON.toJSONString(data), ${respBean}.class);
        }
        return null;

   }
}
