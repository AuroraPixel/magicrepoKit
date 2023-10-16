package com.magicrepokit.auth.constant;

/**
 * Auth常量
 */
public interface MRKAuthConstant {

    /**
     * 客户端表名
     */
    String CLIENT_TABLE_NAME = "mrk_client";

    /**
     * 客户端SQL字段
     */
    String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, " +
            "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, " +
            "refresh_token_validity, additional_information, autoapprove ";

    /**
     * 客户端查询SQL
     */
    String BASE_SELECT = "SELECT " + CLIENT_FIELDS + "FROM " + CLIENT_TABLE_NAME;

    /**
     * 查询client_id
     */
    String SELECT_BY_CLIENT_ID = BASE_SELECT + " where client_id = ?";

    /**
     * redis中授权码存储前缀key
     */
    String REDIS_KEY_AUTHORIZATION_CODE_PREFIX = "mrk:auth:authorization_code:";

    /**
     * 用户登录的type
     */
    String USER_TYPE = "userType";

    String PASSWORD = "password";

    int ACCESS_TOKEN_VALIDITY_SECONDS = 7200;

    int REFRESH_TOKEN_VALIDITY_SECONDS = 3600*24*7;

    /**
     * 用户锁定的key
     */
    String FAIL_REDIS_KEY = "mrk:auth:login:fail:";

    String CLIENT_ID = "client_id";

    String USER_NAME = "user_name";
    String NICK_NAME = "nick_name";
    String REAL_NAME = "real_name";
    String USER_ID = "user_id";
    String DEPT_ID = "dept_id";
    String POST_ID = "post_id";
    String ROLE_ID = "role_id";
    String ROLE_NAME = "role_name";
    String TENANT_ID = "tenant_id";
    String ACCOUNT = "account";

    /**
     * accessToke
     */
    String REDIS_ACCESS_TOKEN_KEY = "mrk:access-token:";

    /**
     * refreshToken
     */
    String REDIS_REFRESH_TOKEN_KEY = "mkr:refresh-token:";

    /**
     * 记录用户状态
     */
    String REDIS_USER_STATUS_KEY = "mkr:status:";

    /**
     * 被锁定的最多次数
     */
    int FAIL_COUNT = 5;

    static String getFailRedisKey(String account){
        return FAIL_REDIS_KEY+account;
    }
}
