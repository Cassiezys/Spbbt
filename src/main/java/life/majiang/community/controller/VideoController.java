package life.majiang.community.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class VideoController {

    /*初始化客户端*/
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        //点播服务所在的Region，国内请填cn-shanghai，不要填写别的区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("2c2386658a4a4eaf9d01dc0a59c211e7");
        return client.getAcsResponse(request);
    }

    @RequestMapping("/testVideo")
    public void  testVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DefaultAcsClient client = initVodClient("LTAI4FjDSJ5Y5gMS6JBnbsZ7", "h3GyUVqkBtdjwzTuaIKfptxIaY6KCu");
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        String PlayURL = null;
        try {
            response = getPlayInfo(client);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                PlayURL = playInfo.getPlayURL();
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        req.getSession().setAttribute("PlayURL",PlayURL);
        req.getRequestDispatcher("index.html").forward(req, resp);

    }
}
