package com.beicroon.provider.aliyunoss.template;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.beicroon.construct.enums.DateTimeEnums;
import com.beicroon.construct.oss.template.IOssTemplate;
import com.beicroon.construct.utils.FileUtils;
import com.beicroon.construct.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

@Slf4j
@Component
public class AliyunOssTemplate implements IOssTemplate {

    @Value("${beicroon.oss.aliyun.endpoint:}")
    private String endpoint;

    @Value("${beicroon.oss.aliyun.access-key-id:}")
    private String accessKeyId;

    @Value("${beicroon.oss.aliyun.access-key-secret:}")
    private String accessKeySecret;

    @Value("${beicroon.oss.aliyun.bucket:}")
    private String bucket;

    @Override
    public String getName() {
        return "aliyunoss";
    }

    @Override
    public String upload(byte[] bytes, String filename, String ext) {
        String datePath = DateTimeEnums.Y_M_D.getFormatter().format(LocalDate.now());

        String storagePath = FileUtils.getPath(ext, datePath);

        String fileUrl = String.format(
                "%s-%s.%s", FileUtils.getPath(storagePath, filename), TimeUtils.currentTimestamp(), ext
        );

        OSS ossClient = new OSSClientBuilder().build(this.endpoint, this.accessKeyId, this.accessKeySecret);

        PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucket, fileUrl, new ByteArrayInputStream(bytes));

        ossClient.putObject(putObjectRequest);

        ossClient.shutdown();

        return String.format("https://%s.%s/%s", this.bucket, this.endpoint, fileUrl);
    }

}
