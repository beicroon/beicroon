package com.beicroon.construct.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.util.Set;

public interface SystemConstant {

    String PACKAGE_BASE = "com.beicroon";

    String PACKAGE_MAPPER = PACKAGE_BASE + ".**.mapper";

    Charset CHARSET = StandardCharsets.UTF_8;

    ZoneOffset TIMEZONE = ZoneOffset.of("+8");

    String TRACE_ID = "traceId";

    String PRIMARY_KEY_NAME = "`id`";

    String DISABLE_KEY_NAME = "`disabled_at`";

    String REMOVE_KEY_NAME = "`removed_at`";

    Set<String> IGNORE_COLUMNS = Set.of(
            "id",
            "tenant_id",
            "source_name",
            "source_version",
            "created_at",
            "creator_id",
            "creator_code",
            "creator_name",
            "modified_at",
            "modifier_id",
            "modifier_code",
            "modifier_name",
            "disabled_at",
            "disabled_id",
            "disabled_name",
            "disabled_code",
            "removed_at",
            "remover_id",
            "remover_name",
            "remover_code"
    );

    String[] ALLOW_METHODS = {"GET", "POST", "PUT", "DELETE", "OPTIONS"};

    String[] ALLOW_HEADERS = {
            "Accept",
            "Referer",
            "User-Agent",
            "Content-Type",
            HeaderConstant.SOURCE,
            HeaderConstant.VERSION,
            HeaderConstant.TENANT_ID,
            HeaderConstant.AUTHORIZATION_TOKEN,
    };

}
