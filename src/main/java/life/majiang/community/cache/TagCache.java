package life.majiang.community.cache;

import life.majiang.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*缓存所有标签*/
public class TagCache {
    /*初始化所有的tag*/
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOSList = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("php","java","node.js","python","c++","c","golang","spring","django","flask","springboot","后端","c#","swoole","ruby","asp.net","ruby-on-rails","scala","rust","lavarel","爬虫"));
        tagDTOSList.add(program);
        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("javascript","前端","vue.js","css","html","html5","node.js","react.js","jquery","css3","es6","typescript","chrome","npm","bootstrap","sass","less","chrome-devtools","firefox","angular","coffeescript","safari","postcss","postman","fiddler","webkit","yarn","firebug","edge"));
        tagDTOSList.add(framework);
        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","docker","apache","centos","ubuntu","服务器","负载均衡","运维","ssh","vagrant","容器","jenkins","devops","debian","fabric"));
        tagDTOSList.add(server);
        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","mongodb","sql","数据库","json","elasticsearch","nosql","memcached","postgresql","sqlite","mariadb"));
        tagDTOSList.add(db);
        TagDTO tools = new TagDTO();
        tools.setCategoryName("工具");
        tools.setTags(Arrays.asList("git","github","macos","visual-studio-code","windows","vim","sublime-text","intellij-idea","eclipse","phpstorm","webstorm","编辑器","svn","visual-studio","pycharm","emacs"));
        tagDTOSList.add(tools);
        return tagDTOSList;
    }

    /*校验tag*/
    public static String fileInvalid(String tags){
        String[] strs= StringUtils.split(tags,",");
        List<TagDTO> tagDTOS = get();
        //将两层map输出
        List<String> tagList = tagDTOS.stream().flatMap(tagDTO -> tagDTO.getTags().stream()).collect(Collectors.toList());
        String invalidStr = Arrays.stream(strs).filter(st -> !tagList.contains(st)).collect(Collectors.joining(","));
        return invalidStr;
    }
}
