package com.algaworks.algashop.productcatalog.infrastructure.storage.s3;

import com.algaworks.algashop.productcatalog.application.storage.FileReference;
import com.algaworks.algashop.productcatalog.application.storage.StorageProvider;
import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URL;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StorageProviderAwsS3Impl implements StorageProvider {

    private final StorageProviderAwsS3Properties properties;
    private final S3Template s3Template;

    @Override
    public boolean healthCheck() {
        try {
            return s3Template.bucketExists(properties.getBucketName());
        } catch (Exception _) {
            return false;
        }
    }

    @Override
    @SneakyThrows
    public URL requestUploadUrl(FileReference fileReference) {
        String key = fileReference.fileName();
        if (fileExists(key)) {
            throw new StorageProviderException(String.format("Remote file %s already exists", key));
        }

        ObjectMetadata.Builder metadataBuilder = ObjectMetadata.builder();

        if (fileReference.allowPublicRead()) {
            metadataBuilder.acl("public-read");
        }

        try {
            return s3Template.createSignedPutURL(
                properties.getBucketName(),
                key,
                fileReference.expiresIn(),
                metadataBuilder.build(),
                fileReference.contentType().toString()
            );
        } catch (S3Exception e) {
            throw new StorageProviderException(String.format("Unknown error when tried to create presigned URL for file %s", key), e);
        }
    }

    @Override
    public void deleteFile(String remoteFileName) {
    }

    @Override
    public boolean fileExists(String remoteFileName) {
        return s3Template.objectExists(properties.getBucketName(), remoteFileName);
    }
}
