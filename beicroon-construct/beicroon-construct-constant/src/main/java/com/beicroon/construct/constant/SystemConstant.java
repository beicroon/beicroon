package com.beicroon.construct.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface SystemConstant {

    String NAME = "beicroon";

    String SUFFIX = "com";

    String BASE_PACKAGE = SUFFIX + "." + NAME;

    String MAPPER_PACKAGE = BASE_PACKAGE + ".service.**.dao.mapper";

    Charset CHARSET = StandardCharsets.UTF_8;

    ZoneOffset TIMEZONE = ZoneOffset.of("+8");

    String TRACE_ID = "traceId";

    String PRIMARY_KEY_NAME = "`id`";

    String DISABLE_KEY_NAME = "`disabled_at`";

    Set<String> IGNORES_COLUMNS = new HashSet<>(Arrays.asList(
            "id",
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
            "deleted_at",
            "deleter_id",
            "deleter_name",
            "deleter_code",
            "version",
            "tenant_id"
    ));

}
