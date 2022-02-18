package com.test.demotest.client;

import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Iterator;
import java.util.Map;

/**
 * @author daxue0929
 * @date 2022/2/16
 */

@Service
public class RestClientImpl implements RestClient{

    public static URI genRequestUri(@NonNull final String baseUrl) {
        if (baseUrl == null) {
            throw new NullPointerException("baseUrl");
        } else {
            return genRequestUri(baseUrl, CollectionsUtils.mapOf());
        }
    }
    public static URI genRequestUri(@NonNull final String baseUrl, @NonNull final Map<String, Object> queryParam) {
        if (baseUrl == null) {
            throw new NullPointerException("baseUrl");
        } else if (queryParam == null) {
            throw new NullPointerException("queryParam");
        } else {
            return genRequestUriBuilder(baseUrl, queryParam).build().toUri();
        }
    }
    public static URI genRequestUri(@NonNull final String baseUrl, @NonNull final Object... uriVariables) {
        if (baseUrl == null) {
            throw new NullPointerException("baseUrl");
        } else if (uriVariables == null) {
            throw new NullPointerException("uriVariables");
        } else {
            return genRequestUri(baseUrl, CollectionsUtils.mapOf(), uriVariables);
        }
    }
    public static URI genRequestUri(@NonNull final String baseUrl, @NonNull final Map<String, Object> queryParam, @NonNull final Object... uriVariables) {
        if (baseUrl == null) {
            throw new NullPointerException("baseUrl");
        } else if (queryParam == null) {
            throw new NullPointerException("queryParam");
        } else if (uriVariables == null) {
            throw new NullPointerException("uriVariables");
        } else {
            return genRequestUriBuilder(baseUrl, queryParam).build(uriVariables);
        }
    }
    public static UriComponentsBuilder genRequestUriBuilder(@NonNull final String baseUrl, @NonNull final Map<String, Object> queryParam) {
        if (baseUrl == null) {
            throw new NullPointerException("baseUrl");
        } else if (queryParam == null) {
            throw new NullPointerException("queryParam");
        } else {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
            Iterator var3 = queryParam.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var3.next();
                builder.queryParam((String)entry.getKey(), new Object[]{entry.getValue()});
            }
            return builder;
        }
    }

    @Override
    public <T, R> ResponseEntity<R> exchange(
        final URI uri,
        final HttpMethod method,
        final HttpHeaders headers,
        final T body,
        final ParameterizedTypeReference<R> typeReference
    ) throws Exception {
        return ((RestClient)this).doExchange(uri, method, headers, body, typeReference);
    }



    @Override
    public ResponseEntity get(final URI uri, final HttpHeaders headers, final ParameterizedTypeReference typeReference) throws
        Exception {
        return this.exchange(uri, HttpMethod.GET, headers, (Object)null, typeReference);
    }

    @Override
    public ResponseEntity get(final URI uri, final ParameterizedTypeReference typeReference) throws Exception {
        return this.exchange(uri, HttpMethod.GET, RestClient.jsonHeader(), (Object)null, typeReference);
    }

    @Override
    public ResponseEntity<Map<String, Object>> get(final URI uri, final HttpHeaders headers) throws Exception {
        return this.exchange(uri, HttpMethod.GET, headers, (Object)null, MAP_RESPONSE_TYPE);
    }

    @Override
    public ResponseEntity<Map<String, Object>> get(final URI uri) throws Exception {
        return this.exchange(uri, HttpMethod.GET, RestClient.jsonHeader(), (Object)null, MAP_RESPONSE_TYPE);
    }



    @Override
    public <T, R> ResponseEntity<R> post(
        final URI uri, final HttpHeaders headers, final T body, final ParameterizedTypeReference<R> typeReference
    ) throws Exception {
        return this.exchange(uri, HttpMethod.POST, headers, body, typeReference);
    }

    @Override
    public <T, R> ResponseEntity<R> post(final URI uri, final T body, final ParameterizedTypeReference<R> typeReference) throws
        Exception {
        return this.exchange(uri, HttpMethod.POST, RestClient.jsonHeader(), body, typeReference);
    }

    @Override
    public <T> ResponseEntity<Map<String, Object>> post(final URI uri, final HttpHeaders headers, final T jsonBody) throws Exception {
        return this.exchange(uri, HttpMethod.POST, headers, jsonBody, MAP_RESPONSE_TYPE);
    }

    @Override
    public ResponseEntity<Map<String, Object>> post(final URI uri, final HttpHeaders headers) throws Exception {
        return this.exchange(uri, HttpMethod.POST, headers, (Object)null, MAP_RESPONSE_TYPE);
    }

    @Override
    public <T> ResponseEntity<Map<String, Object>> post(final URI uri, final T jsonBody) throws Exception {
        return this.exchange(uri, HttpMethod.POST, RestClient.jsonHeader(), jsonBody, MAP_RESPONSE_TYPE);
    }

    @Override
    public <T, R> ResponseEntity<R> put(
        final URI uri, final HttpHeaders headers, final T jsonBody, final ParameterizedTypeReference<R> typeReference
    ) throws Exception {
        return this.exchange(uri, HttpMethod.PUT, headers, jsonBody, typeReference);
    }

    @Override
    public <T, R> ResponseEntity<R> put(final URI uri, final T jsonBody, final ParameterizedTypeReference<R> typeReference) throws
        Exception {
        return this.exchange(uri, HttpMethod.PUT, RestClient.jsonHeader(), jsonBody, typeReference);
    }

    @Override
    public <T> ResponseEntity<Map<String, Object>> put(final URI uri, final HttpHeaders headers, final T jsonBody) throws Exception {
        return this.exchange(uri, HttpMethod.PUT, headers, jsonBody, MAP_RESPONSE_TYPE);
    }

    @Override
    public ResponseEntity<Map<String, Object>> put(final URI uri, final HttpHeaders headers) throws Exception {
        return this.exchange(uri, HttpMethod.PUT, headers, (Object)null, MAP_RESPONSE_TYPE);
    }

    @Override
    public <T> ResponseEntity<Map<String, Object>> put(final URI uri, final T jsonBody) throws Exception {
        return this.exchange(uri, HttpMethod.PUT, RestClient.jsonHeader(), jsonBody, MAP_RESPONSE_TYPE);
    }

    @Override
    public <T> ResponseEntity<T> delete(
        final URI uri,
        final HttpHeaders headers,
        final ParameterizedTypeReference<T> typeReference
    ) throws Exception {
        return this.exchange(uri, HttpMethod.DELETE, headers, (Object)null, typeReference);
    }

    @Override
    public <T> ResponseEntity<T> delete(final URI uri, final ParameterizedTypeReference<T> typeReference) throws Exception {
        return this.exchange(uri, HttpMethod.DELETE, RestClient.jsonHeader(), (Object)null, typeReference);
    }

    @Override
    public ResponseEntity<Map<String, Object>> delete(final URI uri, final HttpHeaders headers) throws Exception {
        return this.exchange(uri, HttpMethod.DELETE, headers, (Object)null, MAP_RESPONSE_TYPE);
    }

    @Override
    public ResponseEntity<Map<String, Object>> delete(final URI uri) throws Exception {
        return this.exchange(uri, HttpMethod.DELETE, RestClient.jsonHeader(), (Object)null, MAP_RESPONSE_TYPE);
    }




}
