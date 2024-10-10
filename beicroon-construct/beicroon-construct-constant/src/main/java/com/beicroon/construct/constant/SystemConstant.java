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

    String PACKAGE_BASE = SUFFIX + "." + NAME;

    String PACKAGE_MAPPER = PACKAGE_BASE + ".**.mapper";

    Charset CHARSET = StandardCharsets.UTF_8;

    ZoneOffset TIMEZONE = ZoneOffset.of("+8");

    String TRACE_ID = "traceId";

    String PRIMARY_KEY_NAME = "`id`";

    String DISABLE_KEY_NAME = "`disabled_at`";

    String REMOVE_KEY_NAME = "`removed_at`";

    Set<String> IGNORES_COLUMNS = new HashSet<>(Arrays.asList(
            "id",
            "tenant_id",
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
    ));

}
