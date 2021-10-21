package com.yoona.community.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.yoona.community.dto.AccessTokenDTO;
import com.yoona.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class GitHubProvider {
    private static final Logger logger = LoggerFactory.getLogger("com.yoona.community");
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token " + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            return JSON.parseObject(string, GithubUser.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    return null;
    }
}
