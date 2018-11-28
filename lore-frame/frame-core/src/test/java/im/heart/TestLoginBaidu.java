package im.heart;

import com.google.common.collect.Maps;
import im.heart.core.utils.OkHttpClientUtils;
import okhttp3.Response;

import java.util.Map;

public class TestLoginBaidu {
    public static void main(String[] args) throws  Exception {

        String loginUrl="https://passport.baidu.com/v2/api/?login";
        Map<String, Object> params= Maps.newHashMap();
        String token="";
        String  gid="";
        String username="lkg6123";
        String password="";
        String rsakey="s8WDAEZ65EpsQhr4iE17uGvCftoNjPOA";
        String crypttype="12";
        String countrycode="";
        String ppui_logintime="";
        String fp_uid="";
        String fp_info="";
        String ds="";
        String tk="";
        String dv="";
        String traceid="";
        String callback="";
        params.put("charset","GBK");
        params.put("token",token);
        params.put("tpl","do");
        params.put("subpro","");
        params.put("apiver","v3");
        params.put("tt","");
        params.put("codestring","");
        params.put("safeflg","");
        params.put("u","https://wenku.baidu.com/");
        params.put("isPhone",false);
        params.put("detect",1);
        params.put("gid",gid);
        params.put("quick_user",0);
        params.put("logintype","dialogLogin");
        params.put("logLoginType","pc_loginDialog");
        params.put("idc","");
        params.put("loginmerge",true);
        params.put("splogin","rate");
        params.put("username",username);
        params.put("password",password);
        params.put("rsakey",rsakey);
        params.put("crypttype",crypttype);
        params.put("countrycode",countrycode);
        params.put("ppui_logintime",ppui_logintime);
        params.put("fp_uid",fp_uid);
        params.put("fp_info",fp_info);
        params.put("loginversion","v4");
        params.put("ds",ds);
        params.put("tk",tk);
        params.put("dv",dv);
        params.put("traceid",traceid);
        params.put("callback",callback);
        final Response response = OkHttpClientUtils.fetchResponse(loginUrl, params);
        System.out.println(response.body().string());
    }
}
