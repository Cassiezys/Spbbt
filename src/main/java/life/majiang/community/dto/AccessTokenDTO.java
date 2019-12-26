package life.majiang.community.dto;

import lombok.Data;

/*OAuth Apps
* step1
*       request a user's GitHub identity
*       GET https://github.com/login/oauth/authorize
* step2
*       users are redireted back to ur site by GitHub
* Parameters:
*           client_id
*           code */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
