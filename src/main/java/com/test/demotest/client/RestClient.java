package com.test.demotest.client;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author daxue0929
 * @date 2022/2/16
 */

public interface RestClient {

    ParameterizedTypeReference<Map<String, Object>> MAP_RESPONSE_TYPE = new ParameterizedTypeReference<Map<String, Object>>() {};
    ParameterizedTypeReference<List<Map<String, Object>>> LIST_MAP_RESPONSE_TYPE = new ParameterizedTypeReference<List<Map<String, Object>>>() {};
    ParameterizedTypeReference<JsonNode> JSON_RESPONSE_TYPE = new ParameterizedTypeReference<JsonNode>() {};

    default RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    static <T> HttpEntity<T> customEntity(T body, @NonNull HttpHeaders headers) {
        if (headers == null) {
            throw new NullPointerException("headers");
        } else {
            return new HttpEntity(body, headers);
        }
    }
    static HttpHeaders jsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    <T, R> ResponseEntity<R> exchange(URI uri, HttpMethod method, HttpHeaders headers, T body, ParameterizedTypeReference<R> typeReference) throws Exception;

    @Retryable({Exception.class})
    default <T, R> ResponseEntity<R> doExchange(URI uri,HttpMethod method,HttpHeaders headers,T body, ParameterizedTypeReference<R> typeReference) throws Exception {
        try {
            return getRestTemplate().exchange(uri, method, customEntity(body, headers), typeReference);
        } catch (ResourceAccessException var7) {
            throw new Exception(var7);
        } catch (HttpClientErrorException var8) {
            if (StringUtils.hasText(var8.getResponseBodyAsString())) {
                throw new Exception(var8.getResponseBodyAsString());
            } else {
                throw new Exception(var8.getLocalizedMessage() + ": unknown reason");
            }
        } catch (RestClientException var9) {
            throw new Exception(var9);
        }
    }

    <T> ResponseEntity<T> get(URI uri, HttpHeaders headers, ParameterizedTypeReference<T> typeReference) throws Exception;

    <T> ResponseEntity<T> get(URI uri, ParameterizedTypeReference<T> typeReference) throws Exception;

    ResponseEntity<Map<String, Object>> get(URI uri, HttpHeaders headers) throws Exception;

    ResponseEntity<Map<String, Object>> get(URI uri) throws Exception;

    <T, R> ResponseEntity<R> post(URI uri, HttpHeaders headers, T body, ParameterizedTypeReference<R> typeReference) throws Exception;

    <T, R> ResponseEntity<R> post(URI uri, T body, ParameterizedTypeReference<R> typeReference) throws Exception;

    <T> ResponseEntity<Map<String, Object>> post(URI uri, HttpHeaders headers, T jsonBody) throws Exception;

    ResponseEntity<Map<String, Object>> post(URI uri, HttpHeaders headers) throws Exception;

    <T> ResponseEntity<Map<String, Object>> post(URI uri, T jsonBody) throws Exception;

    <T, R> ResponseEntity<R> put(URI uri, HttpHeaders headers, T jsonBody, ParameterizedTypeReference<R> typeReference) throws Exception;

    <T, R> ResponseEntity<R> put(URI uri, T jsonBody, ParameterizedTypeReference<R> typeReference) throws Exception;

    <T> ResponseEntity<Map<String, Object>> put(URI uri, HttpHeaders headers, T jsonBody) throws Exception;

    ResponseEntity<Map<String, Object>> put(URI uri, HttpHeaders headers) throws Exception;

    <T> ResponseEntity<Map<String, Object>> put(URI uri, T jsonBody) throws Exception;

    <T> ResponseEntity<T> delete(URI uri, HttpHeaders headers, ParameterizedTypeReference<T> typeReference) throws Exception;

    <T> ResponseEntity<T> delete(URI uri, ParameterizedTypeReference<T> typeReference) throws Exception;

    ResponseEntity<Map<String, Object>> delete(URI uri, HttpHeaders headers) throws Exception;

    ResponseEntity<Map<String, Object>> delete(final URI uri) throws Exception;


}
