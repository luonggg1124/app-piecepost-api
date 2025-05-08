package com.piecepost.cache.grpc.redis;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.piecepost.grpc.cache.RedisServiceGrpc;
import com.piecepost.cache.services.redis.RedisService;
import com.piecepost.grpc.cache.CacheExpireRequest;
import com.piecepost.grpc.cache.CacheRequest;
import com.piecepost.grpc.cache.CacheResponse;
import com.piecepost.grpc.cache.CacheSetEXRequest;
import com.piecepost.grpc.cache.CacheSetRequest;
import com.piecepost.utils.TimeUnitUtils;

import io.grpc.stub.StreamObserver;

@Service
public class RedisGrpcService extends RedisServiceGrpc.RedisServiceImplBase {
    @Autowired
    private RedisService cacheService;

    @Override
    public void get(CacheRequest request, StreamObserver<CacheResponse> rStreamObserver) {
        try {
            String key = request.getKey();

            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Key must not be null or empty");
            }
            Object data = cacheService.get(key);
            String value = (data != null) ? data.toString() : "";

            CacheResponse response = CacheResponse.newBuilder()
                    .setValue(value)
                    .build();
            rStreamObserver.onNext(response);
            rStreamObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            rStreamObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            rStreamObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to get value from cache")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void set(CacheSetRequest request, StreamObserver<CacheResponse> rStreamObserver) {
        try {
            String key = request.getKey();
            String value = request.getValue();
            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Key must not be null or empty");
            }
            if (value == null) {
                throw new IllegalArgumentException("Value must not be null");
            }
            cacheService.set(key, value);
            CacheResponse response = CacheResponse.newBuilder()
                    .setValue(Map.of("status", true).toString())
                    .build();
            rStreamObserver.onNext(response);
            rStreamObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            rStreamObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            rStreamObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to set value in cache")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void setEX(CacheSetEXRequest request, StreamObserver<CacheResponse> rStreamObserver) {
        try {
            String key = request.getKey();
            String value = request.getValue();
            long duration = request.getDuration();
            String timeUnitStr = request.getTimeUnit();

            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Key must not be null or empty");
            }
            if (value == null) {
                throw new IllegalArgumentException("Value must not be null");
            }
            if (duration <= 0) {
                throw new IllegalArgumentException("Duration must be greater than 0");
            }

            TimeUnit timeUnit =  TimeUnitUtils.stringToTimeUnit(timeUnitStr);

            cacheService.setEX(key, value, duration, timeUnit);

            CacheResponse response = CacheResponse.newBuilder()
                    .setValue(Map.of("status", true).toString())
                    .build();
            rStreamObserver.onNext(response);
            rStreamObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            rStreamObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            rStreamObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to set key with expiration in cache")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void incr(CacheRequest request, StreamObserver<CacheResponse> rStreamObserver) {
        try {
            String key = request.getKey();
            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Key must not be null or empty");
            }
            cacheService.incr(key);
            Map<String, Object> data = Map.of("status", true);
            CacheResponse response = CacheResponse.newBuilder()
                    .setValue(data.toString())
                    .build();
            rStreamObserver.onNext(response);
            rStreamObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            rStreamObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            rStreamObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to increment value in cache")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void expire(CacheExpireRequest request, StreamObserver<CacheResponse> rStreamObserver) {
        try {
            String key = request.getKey();
            long duration = request.getDuration();
            TimeUnit timeUnit = TimeUnitUtils.stringToTimeUnit(request.getTimeUnit());
            cacheService.expire(key, duration, timeUnit);
            Map<String, Object> data = Map.of("status", true);
            CacheResponse response = CacheResponse.newBuilder()
                    .setValue(data.toString())
                    .build();
            rStreamObserver.onNext(response);
            rStreamObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            rStreamObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription("Invalid time unit: " + request.getTimeUnit())
                    .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            rStreamObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Internal error while expiring cache")
                    .withCause(e)
                    .asRuntimeException());
        }
    }
    @Override
    public void setIfAbsent(CacheSetEXRequest request, StreamObserver<CacheResponse> rStreamObserver) {
        try {
            String key = request.getKey();
            String value = request.getValue();
            long duration = request.getDuration();
            String timeUnitStr = request.getTimeUnit();

            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Key must not be null or empty");
            }
            if (value == null) {
                throw new IllegalArgumentException("Value must not be null");
            }
            if (duration <= 0) {
                throw new IllegalArgumentException("Duration must be greater than 0");
            }

            TimeUnit timeUnit = TimeUnitUtils.stringToTimeUnit(timeUnitStr);

            cacheService.setIfAbsent(key, value, duration, timeUnit);

            CacheResponse response = CacheResponse.newBuilder()
                    .setValue(Map.of("status", true).toString())
                    .build();
            rStreamObserver.onNext(response);
            rStreamObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            rStreamObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            rStreamObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to set key with expiration in cache")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void setIfPresent(CacheSetEXRequest request, StreamObserver<CacheResponse> rStreamObserver) {
        try {
            String key = request.getKey();
            String value = request.getValue();
            long duration = request.getDuration();
            String timeUnitStr = request.getTimeUnit();

            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Key must not be null or empty");
            }
            if (value == null) {
                throw new IllegalArgumentException("Value must not be null");
            }
            if (duration <= 0) {
                throw new IllegalArgumentException("Duration must be greater than 0");
            }

            TimeUnit timeUnit = TimeUnitUtils.stringToTimeUnit(timeUnitStr);

            cacheService.setIfPresent(key, value, duration, timeUnit);

            CacheResponse response = CacheResponse.newBuilder()
                    .setValue(Map.of("status", true).toString())
                    .build();
            rStreamObserver.onNext(response);
            rStreamObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            rStreamObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            rStreamObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to set key with expiration in cache")
                    .withCause(e)
                    .asRuntimeException());
        }
    }
    @Override
    public void duration(CacheRequest request, StreamObserver<CacheResponse> rStreamObserver) {
        try {
            String key = request.getKey();

            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Key must not be null or empty");
            }
            Object data = cacheService.duration(key);
            String value = (data != null) ? data.toString() : "";

            CacheResponse response = CacheResponse.newBuilder()
                    .setValue(value)
                    .build();
            rStreamObserver.onNext(response);
            rStreamObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            rStreamObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            rStreamObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to get duration value from cache")
                    .withCause(e)
                    .asRuntimeException());
        }
    }
    @Override
    public void delete(CacheRequest request, StreamObserver<CacheResponse> rStreamObserver) {
        try {
            String key = request.getKey();

            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Key must not be null or empty");
            }
            Object data = cacheService.delete(key);
            String value = (data != null) ? data.toString() : "";

            CacheResponse response = CacheResponse.newBuilder()
                    .setValue(value)
                    .build();
            rStreamObserver.onNext(response);
            rStreamObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            rStreamObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            rStreamObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to delete value from cache")
                    .withCause(e)
                    .asRuntimeException());
        }
    }
}
