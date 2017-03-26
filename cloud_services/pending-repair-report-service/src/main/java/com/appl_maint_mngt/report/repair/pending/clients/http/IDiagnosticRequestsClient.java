package com.appl_maint_mngt.report.repair.pending.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.appl_maint_mngt.diagnostic.request.controllers.rest.IDiagnosticRequestRestApi;

@FeignClient(url="http://localhost:8414", name="diagnostic-request-service", fallback=DiagnosticRequestClientFallback.class)
public interface IDiagnosticRequestsClient extends IDiagnosticRequestRestApi {

}
