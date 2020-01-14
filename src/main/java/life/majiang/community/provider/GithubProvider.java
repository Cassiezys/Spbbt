package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*第三方提供者；承载者
* */
@Component
public class GithubProvider {


    /*获取登陆号码
    * AccessTokenDTO 参数超过两个封装成对象去做（数据传输模型）
    * */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        // accesstoken是一个类  而要转换为 json POST请求  需要的是json对象
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str=response.body().string();
           // System.out.println(str);
            //发现打印出来的并不是json对象，只能拆分
            String[] split = str.split("&");
            String tokenStr = split[0];
            String token = tokenStr.split("=")[1];
            return token;
        }catch ( Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*获取账户信息*/
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            //GET方法
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //string的一个json对象自动解析成java类对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
